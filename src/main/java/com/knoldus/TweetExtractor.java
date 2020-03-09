package com.knoldus;

import twitter4j.Status;

import java.time.LocalDate;
import java.util.List;

/**
 * This is a driver class to run the application.
 * It displays all the outputs.
 */
public class TweetExtractor {
    public static void main(String[] args) {

        TweetFilter tweetFilter = new TweetFilterImpl(new TwitterStatusImpl(new TwitterInstanceImpl()));
        List<Status> list;
        String hashTag = "#suraj";
        int limit = 5;

        System.out.println("\nLatest Tweets");
        list = tweetFilter.getLatestTweets(hashTag, limit);
        list.forEach((status) -> System.out.println(status.getId() + "\t\t" + status.getCreatedAt()));

        System.out.println("\n\nOlder Tweets");
        long offset = 1;
        list = tweetFilter.getOlderTweets(hashTag, limit, offset);
        list.forEach((status) -> System.out.println(status.getId() + "\t\t" + status.getCreatedAt()));

        System.out.println("\n\nHigh ReTweets");
        list = tweetFilter.getTweetsWithHighReTweets(hashTag);
        list.forEach((status) -> System.out.println(status.getId() + "\t\t" + status.getRetweetCount()));

        System.out.println("\n\nHigh Likes");
        list = tweetFilter.getTweetsWithHighLikes(hashTag);
        list.forEach((status) -> System.out.println(status.getId() + "\t\t" + status.getFavoriteCount()));

        System.out.println("\n\nTweets on date ");
        int year = 2020;
        int month = 3;
        int day = 6;
        LocalDate localDate = LocalDate.of(year, month, day);
        list = tweetFilter.getTweetsOnDate(hashTag, localDate);
        list.forEach((status) -> System.out.println(status.getId() + "\t\t" + status.getCreatedAt()));

        System.out.print("\n\nlikes on a particular keyword : ");
        long intervalInMinutes = 20;
        hashTag = "#srk";
        long likesCount = tweetFilter.getLikesOnHashTagByInterval(hashTag, intervalInMinutes);
        System.out.println(likesCount);

    }
}
