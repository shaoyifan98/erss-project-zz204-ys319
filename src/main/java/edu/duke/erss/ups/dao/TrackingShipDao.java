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

    void updateDistance(@Param("shipID") Long shipID, @Param("distance") Double distance);

    List<ShipInfo> getShipInfoByShipID(@Param("shipID") Long shipID);

    List<ShipInfo> getShipInfoByTrackingID(@Param("trackingID") Long trackingID);

    List<ShipInfo> getShipInfoByUserID(@Param("userID") Integer userID);

    List<ShipInfo> getShipInforByTruckID(@Param("truckID") Integer truckID);

    void updateComment(@Param("trackingID") Long trackingID, @Param("comment") String comment);

    void updateDestination(@Param("trackingID") Long trackingID, @Param("x") int x, @Param("y") int y);

    void updateStatus(@Param("shipID") long shipID, @Param("status") String status);
}
