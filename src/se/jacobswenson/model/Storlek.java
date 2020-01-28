package se.jacobswenson.model;

public class Storlek {

    private int id;
    private double ukStorlek;
    private double usStorlek;
    private double euStorlek;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUkStorlek() {
        return ukStorlek;
    }

    public void setUkStorlek(double ukStorlek) {
        this.ukStorlek = ukStorlek;
    }

    public double getUsStorlek() {
        return usStorlek;
    }

    public void setUsStorlek(double usStorlek) {
        this.usStorlek = usStorlek;
    }

    public double getEuStorlek() {
        return euStorlek;
    }

    public void setEuStorlek(double euStorlek) {
        this.euStorlek = euStorlek;
    }
}
