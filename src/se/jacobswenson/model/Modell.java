package se.jacobswenson.model;

public class Modell {

    private int id;
    private String namn;
    private int markesId;
    private String bildFilnamn;

    private Marke marke;

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

    public int getMarkesId() {
        return markesId;
    }

    public void setMarkesId(int markesId) {
        this.markesId = markesId;
    }

    public String getBildFilnamn() {
        return bildFilnamn;
    }

    public void setBildFilnamn(String bildFilnamn) {
        this.bildFilnamn = bildFilnamn;
    }

    public Marke getMarke() {
        return marke;
    }

    public void setMarke(Marke marke) {
        this.marke = marke;
    }
}
