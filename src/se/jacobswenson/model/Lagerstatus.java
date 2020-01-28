package se.jacobswenson.model;

public class Lagerstatus {
    private int id;
    private int antaILager;
    private int produkt_id;

    private Produkt produkt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAntaILager() {
        return antaILager;
    }

    public void setAntaILager(int antaILager) {
        this.antaILager = antaILager;
    }

    public int getProdukt_id() {
        return produkt_id;
    }

    public void setProdukt_id(int produkt_id) {
        this.produkt_id = produkt_id;
    }
}
