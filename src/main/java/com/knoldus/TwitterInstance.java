package com.knoldus;

import twitter4j.Twitter;

/**
 * this interface can be used to get the instance of the Twitter through factory method.
 */
public interface TwitterInstance {

    /**
     * method getTwitterInstance can be used to get the instance of Twitter.
     *
     * @return the instance of type Twitter.
     */
    Twitter getTwitterInstance();
}
