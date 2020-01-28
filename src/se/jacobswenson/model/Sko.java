package se.jacobswenson.model;

import java.util.List;

public class Sko extends Produkt{

    private int id;
    private int produktId;
    private int storlekId;
    private int modellId;

    private Storlek storlek;
    private Modell modell;
    private List<Skotyp> skoTyper;
    private Farg farg;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getProduktId() {
        return produktId;
    }

    public void setProduktId(int produktId) {
        this.produktId = produktId;
    }

    public int getStorlekId() {
        return storlekId;
    }

    public void setStorlekId(int storlekId) {
        this.storlekId = storlekId;
    }

    public int getModellId() {
        return modellId;
    }

    public void setModellId(int modellId) {
        this.modellId = modellId;
    }


    public Storlek getStorlek() {
        return storlek;
    }

    public void setStorlek(Storlek storlek) {
        this.storlek = storlek;
    }

    public Modell getModell() {
        return modell;
    }

    public void setModell(Modell modell) {
        this.modell = modell;
    }

    public List<Skotyp> getSkoTyper() {
        return skoTyper;
    }

    public void setSkoTyper(List<Skotyp> skoTyper) {
        this.skoTyper = skoTyper;
    }

    public Farg getFarg() {
        return farg;
    }

    public void setFarg(Farg farg) {
        this.farg = farg;
    }
}
