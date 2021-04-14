package edu.duke.erss.ups.entity;

public class Result {
    private String trackingID;
    private String destination;
    private String status;

    public Result(){};

    public Result(String trackingID, String destination, String status){
        this.trackingID = trackingID;
        this.destination = destination;
        this.status = status;
    }

    public String getTrackingID() {
        return trackingID;
    }

    public void setTrackingID(String trackingID) {
        this.trackingID = trackingID;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
    
}
