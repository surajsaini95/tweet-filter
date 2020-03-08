package com.knoldus;

import twitter4j.Status;

import java.util.Date;
import java.util.List;

public interface TweetFilter {

    List<Status> getLatestTweets(String hashTag,long limit);

    List<Status> getOlderTweets(String hashTag,long limit);

    List<Status> getTweetsWithHighReTweets(String hashTag);

    List<Status> getTweetsWithHighLikes(String hashTag);

    List<Status> getTweetsOnDate(String hashTag,Date date);

    int getLikesOnHashTagByInterval(String hashTag,int minutes);

}
