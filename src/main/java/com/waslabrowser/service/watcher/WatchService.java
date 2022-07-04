package com.waslabrowser.service.watcher;

import com.waslabrowser.service.watcher.twitter.Datum;
import com.waslabrowser.service.watcher.twitter.FollowerReponse;
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

    public List<Follower> watch() {
        Response<FollowerReponse> followers = twitterApi.getFollowers(null);
        FollowerReponse body = followers.body();
        List<Datum> data = body.getData();
        String token = body.getMeta().getNextToken();
        while (token != null) {
            Response<FollowerReponse> next = twitterApi.getFollowers(token);
            FollowerReponse reponse = next.body();
            data.addAll(reponse.getData());
            token = reponse.getMeta().getNextToken();
        }
        log.info("All Followers {}", data.stream().map(Follower::new).toList());
        Iterable<Follower> allById = userDao.findAllById(data.stream().map(Datum::getId).toList());
        List<String> dbIds = StreamSupport.stream(allById.spliterator(), false).map(Follower::getId).toList();
        data.removeIf(d -> dbIds.contains(d.getId()));
        List<Follower> newFollowers = data.stream().map(Follower::new).toList();
        log.info("New Followers {}", newFollowers);
        userDao.saveAll(newFollowers);
        return newFollowers;
    }

    public Iterable<Follower> getAll() {
        return userDao.findAll();
    }

}
