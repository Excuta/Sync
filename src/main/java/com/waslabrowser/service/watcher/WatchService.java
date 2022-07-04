package com.waslabrowser.service.watcher;

import com.waslabrowser.service.common.response.PageResponseBody;
import com.waslabrowser.service.watcher.twitter.Datum;
import com.waslabrowser.service.watcher.twitter.FollowerReponse;
import com.waslabrowser.service.watcher.twitter.Meta;
import com.waslabrowser.service.watcher.twitter.api.TwitterApiImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import retrofit2.Response;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class WatchService {
    private TwitterApiImpl twitterApi;
    private UserDao userDao;

    public WatchService(TwitterApiImpl twitterApi, UserDao userDao) {
        this.twitterApi = twitterApi;
        this.userDao = userDao;
    }

    public PageResponseBody<Follower> watch() {
        FollowerReponse body = getFollowerReponse(null);
        List<Datum> data = body.getData();
        Meta meta = body.getMeta();
        String token = meta.getNextToken();
        Long resultCount = meta.getResultCount();
        long count = userDao.count();
        if (count == resultCount) return new PageResponseBody<>();
        while (token != null) {
            FollowerReponse response = getFollowerReponse(token);
            data.addAll(response.getData());
            token = response.getMeta().getNextToken();
        }
        log.info("All Followers {}", data.stream().map(Follower::new).toList());
        List<String> dbIds = getIdsInDb(data);
        data.removeIf(d -> dbIds.contains(d.getId()));
        List<Follower> newFollowers = data.stream().map(Follower::new).toList();

        log.info("New Followers {}", newFollowers);
        userDao.saveAll(newFollowers);
        return new PageResponseBody<>(newFollowers, false, newFollowers.size());
    }

    private List<String> getIdsInDb(List<Datum> data) {
        Iterable<Follower> allById = userDao.findAllById(data.stream().map(Datum::getId).toList());
        List<String> dbIds = StreamSupport.stream(allById.spliterator(), false).map(Follower::getId).toList();
        return dbIds;
    }

    private FollowerReponse getFollowerReponse(String token) {
        Response<FollowerReponse> next = twitterApi.getFollowers(token);
        FollowerReponse reponse = next.body();
        return reponse;
    }

    public PageResponseBody<Follower> getAll() {
        List<Follower> followers = StreamSupport.stream(userDao.findAll().spliterator(), false).toList();
        return new PageResponseBody<>(followers, false, followers.size());
    }

}
