package com.waslabrowser.service.watcher.twitter.api;

import com.waslabrowser.service.watcher.twitter.FollowerReponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TwitterApi {
    @GET("users/{id}/followers")
    Call<FollowerReponse> getFollowers(@Header("Authorization") String authorization, @Path("id") String id, @Query("pagination_token") String paginationToken);
}
