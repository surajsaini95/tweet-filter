package com.knoldus;

import twitter4j.Status;

import java.util.List;

public class TweetExtractor {
    public static void main(String[] args) {
        TweetFilter tweetFilter = new TweetFilterImpl(new ReadTwitterStatusImpl(new TwitterInstanceImpl()));
        List<Status> list ;
       /* System.out.println("\nLatest Tweets");
        list= tweetFilter.getLatestTweets("#suraj",15);
        list.forEach((status)->System.out.println(status.getId()+"\t\t"+status.getCreatedAt()));

        System.out.println("\n\nOlder Tweets");
        list = tweetFilter.getOlderTweets("#suraj",15);
        list.forEach((status)->System.out.println(status.getId()+"\t\t"+status.getCreatedAt()));


        System.out.println("\n\nHigh ReTweets");
        list = tweetFilter.getTweetsWithHighReTweets("#suraj");
        list.forEach((status)->System.out.println(status.getId()+"\t\t"+status.getRetweetCount()));
*/
        System.out.println("\n\nHigh Likes");
        list = tweetFilter.getTweetsWithHighLikes("#suraj");
        list.forEach((status)->System.out.println(status.getId()+"\t\t"+status.getFavoriteCount()));

    }
}
