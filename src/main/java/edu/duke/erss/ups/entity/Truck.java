package edu.duke.erss.ups.entity;

public class Truck {
    private Integer id;
    private String status;
    private Integer posX;
    private Integer posY;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getPosX() {
        return posX;
    }

    public void setPosX(Integer posX) {
        this.posX = posX;
    }

    public Integer getPosY() {
        return posY;
    }

    public void setPosY(Integer posY) {
        this.posY = posY;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public enum Status {
        IDLE("idle"),
        TRAVELING("traveling"),
        ARR_WH("arrive warehouse"),
        LOADING("loading"),
        DELIVERING("delivering");

        private String text;

        Status(String str) {
            this.text = str;
        }

        public String getText() {
            return text;
        }

        public static Status fromString(String text) {
            for (Status s : Status.values()) {
                if (s.text.equalsIgnoreCase(text)) {
                    return s;
                }
            }
            throw new IllegalArgumentException("No such enum " + text);
        }
    }
}
