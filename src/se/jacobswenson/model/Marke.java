package se.jacobswenson.model;

public class Marke {

    private int id;
    private String namn;
    private String kontaktuppg;

    public Marke(int id, String namn, String kontaktuppg) {
        this.id = id;
        this.namn = namn;
        this.kontaktuppg = kontaktuppg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamn() {
        return namn;
    }

    public void setNamn(String namn) {
        this.namn = namn;
    }

    public String getKontaktuppg() {
        return kontaktuppg;
    }

    public void setKontaktuppg(String kontaktuppg) {
        this.kontaktuppg = kontaktuppg;
    }
}
