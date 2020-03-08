package com.knoldus;

import twitter4j.Status;

import java.time.LocalDate;
import java.util.List;

/**
 * TweetFilter is an interface which contains all the features specified.
 */
public interface TweetFilter {

    /**
     * getLatestTweets gets all the latest Post (Newer to Older) with limit.
     *
     * @param hashTag on which the post will be retrieved.
     * @param limit   is used to specify the upper bound on the length of the list that will be returned.
     * @return list of status retrieved.
     */
    List<Status> getLatestTweets(String hashTag, long limit);

    /**
     * getOlderTweets gets all the posts Older to Newer with limit.
     *
     * @param hashTag on which the post will be retrieved.
     * @param limit   is used to specify the upper bound on the length of the list that will be returned.
     * @return list of status retrieved.
     */
    List<Status> getOlderTweets(String hashTag, long limit);

    /**
     * getTweetsWithHighReTweets gets the posts sorted with highest number of ReTweets.
     *
     * @param hashTag on which the post will be retrieved.
     * @return list of status retrieved.
     */
    List<Status> getTweetsWithHighReTweets(String hashTag);

    /**
     * getTweetsWithHighLikes gets the posts sorted with highest number of likes.
     *
     * @param hashTag on which the post will be retrieved.
     * @return list of status retrieved.
     */
    List<Status> getTweetsWithHighLikes(String hashTag);

    /**
     * getTweetsOnDate gets the posts made on certain date.
     *
     * @param hashTag   on which the post will be retrieved.
     * @param localDate on which date the posts should be retrieved.
     * @return list of status retrieved.
     */
    List<Status> getTweetsOnDate(String hashTag, LocalDate localDate);

    /**
     * getLikesOnHashTagByInterval Get the number of likes on a particular keyword in a time interval.
     *
     * @param hashTag on which the post will be retrieved.
     * @param minutes is the time interval.
     * @return list of status retrieved.
     */
    long getLikesOnHashTagByInterval(String hashTag, long minutes);

}
