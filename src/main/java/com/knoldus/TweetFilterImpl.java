package com.knoldus;

import twitter4j.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TweetFilterImpl provides the definition to the methods of TweetFilter.
 */
public class TweetFilterImpl implements TweetFilter {

    TwitterStatusImpl readTwitterStatus;

    public TweetFilterImpl(TwitterStatusImpl readTwitterStatus) {
        this.readTwitterStatus = readTwitterStatus;
    }

    /**
     * getLatestTweets gets all the latest Post (Newer to Older) with limit.
     *
     * @param hashTag on which the post will be retrieved.
     * @param limit   is used to specify the upper bound on the length of the list that will be returned.
     * @return list of status retrieved.
     */
    @Override
    public List<Status> getLatestTweets(String hashTag, long limit) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream().limit(limit).collect(Collectors.toList());

    }

    /**
     * getOlderTweets gets all the posts Older to Newer with limit.
     *
     * @param hashTag on which the post will be retrieved.
     * @param limit   is used to specify the upper bound on the length of the list that will be returned.
     * @return list of status retrieved.
     */
    @Override
    public List<Status> getOlderTweets(String hashTag, long limit) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .sorted(Comparator.comparing(Status::getCreatedAt))
                .limit(limit)
                .collect(Collectors.toList());
    }

    /**
     * getTweetsWithHighReTweets gets the posts sorted with highest number of ReTweets.
     *
     * @param hashTag on which the post will be retrieved.
     * @return list of status retrieved.
     */
    @Override
    public List<Status> getTweetsWithHighReTweets(String hashTag) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .sorted(Comparator.comparing(Status::getRetweetCount).reversed())
                .collect(Collectors.toList());
    }

    /**
     * getTweetsWithHighLikes gets the posts sorted with highest number of likes.
     *
     * @param hashTag on which the post will be retrieved.
     * @return list of status retrieved.
     */
    @Override
    public List<Status> getTweetsWithHighLikes(String hashTag) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .sorted(Comparator.comparing(Status::getFavoriteCount).reversed())
                .collect(Collectors.toList());
    }

    /**
     * getTweetsOnDate gets the posts made on certain date.
     *
     * @param hashTag   on which the post will be retrieved.
     * @param localDate on which date the posts should be retrieved.
     * @return list of status retrieved.
     */
    @Override
    public List<Status> getTweetsOnDate(String hashTag, LocalDate localDate) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .filter(status -> status.getCreatedAt()
                        .toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate().isEqual(localDate))
                .collect(Collectors.toList());
    }

    /**
     * getLikesOnHashTagByInterval Get the number of likes on a particular keyword in a time interval.
     *
     * @param hashTag on which the post will be retrieved.
     * @param minutes is the time interval.
     * @return list of status retrieved.
     */
    @Override
    public long getLikesOnHashTagByInterval(String hashTag, long minutes) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .filter(status -> validatePostTiming(status.getCreatedAt(), minutes))
                .count();
    }

    /**
     * validatePostTiming is used to validate the post created time.
     *
     * @param postDate is the date on which the post is made.
     * @param minutes  interval used to validate.
     * @return validation result.
     */
    private boolean validatePostTiming(Date postDate, long minutes) {
        Date date = new Date();
        LocalDateTime present = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime past = present.minusMinutes(minutes);
        LocalDateTime dateToCheck = postDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return dateToCheck.isAfter(past) && dateToCheck.isBefore(present);
    }
}
