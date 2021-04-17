package edu.duke.erss.ups.dao;

import edu.duke.erss.ups.entity.Truck;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TruckDao {

    List<Truck> getAll();

    void updateTruckStatus(@Param("truckID") int truckID, @Param("status") String status);

    void updateTruckPos(@Param("posX") int posX, @Param("posY") int posY, @Param("truckID") int truckID);

    void insertTruck(@Param("truck") Truck truck);

    List<Truck> getTruckByTrackingID(@Param("trackingID") Long trackingID);
    void deleteAll();
}
