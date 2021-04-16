package edu.duke.erss.ups.entity;

public class ShipInfo {
    private Long trackingID;
    private Long shipID;
    private Integer truckID;
    private String status;
    private Integer destX;
    private Integer destY;
    private String comment;
    private double distance;

    private Integer whID;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Integer getDestX() {
        return destX;
    }

    public void setDestX(Integer destX) {
        this.destX = destX;
    }

    public Integer getDestY() {
        return destY;
    }

    public void setDestY(Integer destY) {
        this.destY = destY;
    }

    public Integer getWhID() {
        return whID;
    }

    public void setWhID(Integer whID) {
        this.whID = whID;
    }

    public Long getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(Long trackingID) {
        this.trackingID = trackingID;
    }

    public Long getShipID() {
        return shipID;
    }

    public void setShipID(Long shipID) {
        this.shipID = shipID;
    }

    public Integer getTruckID() {
        return truckID;
    }

    public void setTruckID(Integer truckID) {
        this.truckID = truckID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
