package edu.duke.erss.ups.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserTrackingDao {

    List<Long> getUserPackages(@Param("userID") int userID);

    void insertTracking(@Param("userID") int userID, @Param("trackingID") long trackingID);

    List<Long> getUserIDByTrackingID(@Param("trackingID") long trackingID);

    
}
