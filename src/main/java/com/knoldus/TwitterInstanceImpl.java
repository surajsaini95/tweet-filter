package com.knoldus;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

        ConfigurationBuilder cb = new ConfigurationBuilder();
        try (InputStream input = TwitterInstanceImpl.class.getClassLoader().getResourceAsStream("twitterConfig.properties")) {
            Properties prop = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find twitterConfig.properties");
            } else {
                prop.load(input);
                cb.setDebugEnabled(true)
                        .setOAuthConsumerKey(prop.getProperty("consumer.key"))
                        .setOAuthConsumerSecret(prop.getProperty("consumer.secret"))
                        .setOAuthAccessToken(prop.getProperty("token.key"))
                        .setOAuthAccessTokenSecret(prop.getProperty("token.secret"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }
}
