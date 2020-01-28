package se.jacobswenson.model;

public class Adress {

    private int id;
    private String adressrad1;
    private String adressrad2;
    private String postNummer;
    private String stad;
    private int kund_id;

    private Kund kund;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdressrad1() {
        return adressrad1;
    }

    public void setAdressrad1(String adressrad1) {
        this.adressrad1 = adressrad1;
    }

    public String getAdressrad2() {
        return adressrad2;
    }

    public void setAdressrad2(String adressrad2) {
        this.adressrad2 = adressrad2;
    }

    public String getPostNummer() {
        return postNummer;
    }

    public void setPostNummer(String postNummer) {
        this.postNummer = postNummer;
    }

    public String getStad() {
        return stad;
    }

    public void setStad(String stad) {
        this.stad = stad;
    }

    public int getKund_id() {
        return kund_id;
    }

    public void setKund_id(int kund_id) {
        this.kund_id = kund_id;
    }
}
