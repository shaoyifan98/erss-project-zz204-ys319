package edu.duke.erss.ups.dao;

import edu.duke.erss.ups.entity.ShipInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrackingShipDao {
    List<ShipInfo> getAll();

    void insertNewTracking(@Param("shipInfo") ShipInfo info);

    void updateTracking(@Param("shipInfo") ShipInfo shipInfo);

    List<ShipInfo> getShipInfoByShipID(@Param("shipID") Long shipID);

    List<ShipInfo> getShipInfoByTrackingID(@Param("trackingID") Long trackingID);

    List<ShipInfo> getShipInfoByUserID(@Param("userID") Integer userID);

    
}
