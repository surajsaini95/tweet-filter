package com.knoldus;

import twitter4j.Status;

import java.util.List;

public interface ReadTwitterStatus {

    List<Status> getTwitterStatus(String hashTag);
}
