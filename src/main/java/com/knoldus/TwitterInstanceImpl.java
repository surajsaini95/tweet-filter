package com.knoldus;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * TwitterInstanceImpl provides the definitions for methods in TwitterInstance.
 */
public class TwitterInstanceImpl implements TwitterInstance {

    /**
     * method getTwitterInstance can be used to get the instance of Twitter.
     *
     * @return the instance of type Twitter
     */
    @Override
    public Twitter getTwitterInstance() {

        Config configFactory = ConfigFactory.load();
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(configFactory.getString("consumer.key"))
                .setOAuthConsumerSecret(configFactory.getString("consumer.secret"))
                .setOAuthAccessToken(configFactory.getString("token.key"))
                .setOAuthAccessTokenSecret(configFactory.getString("token.secret"));

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
