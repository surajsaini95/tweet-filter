package com.knoldus;

import twitter4j.Status;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TweetFilterImpl implements TweetFilter {

    ReadTwitterStatusImpl readTwitterStatus;

    public TweetFilterImpl(ReadTwitterStatusImpl readTwitterStatus) {
        this.readTwitterStatus = readTwitterStatus;
    }

    @Override
    public List<Status> getLatestTweets(String hashTag, long limit) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Status> getOlderTweets(String hashTag, long limit) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .sorted(Comparator.comparing(Status::getCreatedAt))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Status> getTweetsWithHighReTweets(String hashTag) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .sorted(Comparator.comparing(Status::getRetweetCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Status> getTweetsWithHighLikes(String hashTag) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .sorted(Comparator.comparing(Status::getFavoriteCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Status> getTweetsOnDate(String hashTag, Date date) {
        return null;
    }

    @Override
    public int getLikesOnHashTagByInterval(String hashTag, int minutes) {
        return 0;
    }
}
