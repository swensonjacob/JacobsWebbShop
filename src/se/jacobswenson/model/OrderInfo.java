package se.jacobswenson.model;

public class OrderInfo {

    private int id;
    private int antal;
    private int produktId;
    private int bestallningsorderId;

    private Bestallningsorder bestallningsorder;
    private Produkt produkt;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public int getProduktId() {
        return produktId;
    }

    public void setProduktId(int produktId) {
        this.produktId = produktId;
    }

    public int getBestallningsorderId() {
        return bestallningsorderId;
    }

    public void setBestallningsorderId(int bestallningsorderId) {
        this.bestallningsorderId = bestallningsorderId;
    }
}
