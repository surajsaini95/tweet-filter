package com.knoldus;

import twitter4j.Status;

import java.util.List;

/**
 * TwitterStatus provides the feature related to twitter status.
 */
public interface TwitterStatus {
    /**
     * getTwitterStatus can be used to retrieve a list of twitter status based on a hasTag.
     *
     * @param hashTag on which the post will be retrieved.
     * @param limit   the number of  posts that will be retrieved.
     * @return list of status retrieved.
     */
    List<Status> getTwitterStatus(String hashTag, int limit);
}
