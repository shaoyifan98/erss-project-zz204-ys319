package edu.duke.erss.ups.entity;

public class ShipInfo {
    private Long trackingID;
    private Long shipID;
    private Integer truckID;
    private String status;
    private Integer whID;
    private Integer destX;
    private Integer destY;

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
