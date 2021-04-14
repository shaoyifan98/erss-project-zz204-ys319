package edu.duke.erss.ups.entity;

public enum ShipStatus {
    CREATED("created"),
    INROUTE("truck in route to warehouse"),
    WAITING("truck waiting for package"),
    DELIVERING("out for delivery"),
    DELIVERED("delivered");

    private String text;

    ShipStatus(String str) {
        this.text = str;
    }

    public String getText() {
        return text;
    }

    public static ShipStatus fromString(String text) {
        for (ShipStatus s : ShipStatus.values()) {
            if (s.text.equalsIgnoreCase(text)) {
                return s;
            }
        }
        throw new IllegalArgumentException("No such enum " + text);
    }
}
