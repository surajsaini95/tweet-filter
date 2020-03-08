package com.knoldus;

import twitter4j.Status;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TweetFilterImpl implements TweetFilter {

    ReadTwitterStatusImpl readTwitterStatus;

    public TweetFilterImpl(ReadTwitterStatusImpl readTwitterStatus) {
        this.readTwitterStatus = readTwitterStatus;
    }

    @Override
    public List<Status> getLatestTweets(String hashTag, long limit) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Status> getOlderTweets(String hashTag, long limit) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .sorted(Comparator.comparing(Status::getCreatedAt))
                .limit(limit)
                .collect(Collectors.toList());
    }

    @Override
    public List<Status> getTweetsWithHighReTweets(String hashTag) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .sorted(Comparator.comparing(Status::getRetweetCount).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Status> getTweetsWithHighLikes(String hashTag) {
        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .sorted(Comparator.comparing(Status::getFavoriteCount).reversed())
                .collect(Collectors.toList());
    }

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

    @Override
    public long getLikesOnHashTagByInterval(String hashTag, long minutes) {

        return readTwitterStatus.getTwitterStatus(hashTag)
                .stream()
                .filter(status -> check(status.getCreatedAt(),minutes)).count();
    }
    private boolean check(Date postDate,long minutes){
        Date date = new Date();
        LocalDateTime present = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime past = present.minusMinutes(minutes);
        /*System.out.println("present : "+present);
        System.out.println("past : "+past);
*/
        LocalDateTime dateToCheck = postDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        return  dateToCheck.isAfter(past)&&dateToCheck.isBefore(present);
    }
}
