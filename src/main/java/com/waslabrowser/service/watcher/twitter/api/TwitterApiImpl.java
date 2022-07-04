package com.waslabrowser.service.watcher.twitter.api;

import com.waslabrowser.service.watcher.twitter.FollowerReponse;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import retrofit2.Response;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
public class TwitterApiImpl {
    @Value("${twitter.token}")
    private String token;
    @Value("${twitter.id}")
    private String id;

    private TwitterApi twitterApi;

    public TwitterApiImpl(TwitterApi twitterApi) {
        this.twitterApi = twitterApi;
    }

    public Response<FollowerReponse> getFollowers(@Nullable String paginationToken) {
        try {
            return twitterApi.getFollowers("Bearer " + token, id, paginationToken).execute();
        } catch (IOException e) {
            return Response.error(500, ResponseBody.create(MediaType.get("application/json"), "".getBytes(StandardCharsets.UTF_8)));
        }
    }
}
