package se.jacobswenson.model;

import java.time.LocalDateTime;

public class SlutILager {

    private int id;
    private int produktId;
    private LocalDateTime datum;

    Produkt produkt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProduktId() {
        return produktId;
    }

    public void setProduktId(int produktId) {
        this.produktId = produktId;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }
}
