package edu.duke.erss.ups.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserTrackingDao {
    List<Long> getUserPackages(@Param("userID") int userID);
}
