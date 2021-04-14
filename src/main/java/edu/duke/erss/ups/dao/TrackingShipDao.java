package edu.duke.erss.ups.dao;

import edu.duke.erss.ups.entity.ShipInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TrackingShipDao {
    List<ShipInfo> getAll();

    void insertNewTracking(@Param("shipID") long shipID, @Param("status") String status);

    void updateTracking();
}
