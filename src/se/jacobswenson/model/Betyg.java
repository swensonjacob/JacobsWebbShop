package se.jacobswenson.model;

public class Betyg {
    private int id;
    private String kommentar;
    private int kund_id;
    private int produkt_id;
    private int betygsvardeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public int getKund_id() {
        return kund_id;
    }

    public void setKund_id(int kund_id) {
        this.kund_id = kund_id;
    }

    public int getProdukt_id() {
        return produkt_id;
    }

    public void setProdukt_id(int produkt_id) {
        this.produkt_id = produkt_id;
    }

    public int getBetygsvardeId() {
        return betygsvardeId;
    }

    public void setBetygsvardeId(int betygsvardeId) {
        this.betygsvardeId = betygsvardeId;
    }
}
