package com.knoldus;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.List;

/**
 * TwitterStatusImpl provides the definitions for methods in TwitterStatus.
 */
public class TwitterStatusImpl implements TwitterStatus {

    TwitterInstance twitterInstance;

    public TwitterStatusImpl(TwitterInstance twitterInstance) {
        this.twitterInstance = twitterInstance;
    }

    /**
     * getTwitterStatus can be used to retrieve a list of twitter status based on a hasTag.
     *
     * @param hashTag on which the post will be retrieved.
     * @param limit   the number of  posts that will be retrieved.
     * @return list of status retrieved.
     */
    @Override
    public List<Status> getTwitterStatus(String hashTag, int limit) {
        Query query = new Query(hashTag);
        query.setCount(limit);
        List<Status> list = new ArrayList<>();
        try {
            list = twitterInstance.getTwitterInstance().search(query).getTweets();
        } catch (TwitterException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
