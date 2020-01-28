package se.jacobswenson.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class Bestallningsorder {
    private int id;
    private LocalDateTime orderDatum;
    private LocalDateTime skickadDatum;
    private int statusId;
    private int kundId;
    private LocalDateTime senastAndrad;

    private Kund kund;
    private OrderStatus orderStatus;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getOrderDatum() {
        return orderDatum;
    }

    public void setOrderDatum(LocalDateTime orderDatum) {
        this.orderDatum = orderDatum;
    }

    public LocalDateTime getSkickadDatum() {
        return skickadDatum;
    }

    public void setSkickadDatum(LocalDateTime skickadDatum) {
        this.skickadDatum = skickadDatum;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getKundId() {
        return kundId;
    }

    public void setKundId(int kundId) {
        this.kundId = kundId;
    }

    public LocalDateTime getSenastAndrad() {
        return senastAndrad;
    }

    public void setSenastAndrad(LocalDateTime senastAndrad) {
        this.senastAndrad = senastAndrad;
    }
}
