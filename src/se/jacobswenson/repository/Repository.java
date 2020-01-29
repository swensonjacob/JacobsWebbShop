package se.jacobswenson.repository;

import se.jacobswenson.model.*;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Repository {

    private Properties info = new Properties();
    private Kund currentUser;


    public Repository() {

        try {
            info.load(new FileInputStream("src/se/jacobswenson/settings/settings.properties"));
        } catch (IOException e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public boolean verifyUsername(String userName) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatement = conn.prepareStatement("SELECT Count(*) FROM kund where anvandarnamn = ?")) {

            pstatement.setString(1,userName);
            ResultSet result = pstatement.executeQuery();
            result.next();

            if (result.getInt(1) == 0) {
                return false;
            } else {
                return true;
            }


        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    public boolean verifyPassword(String userName, String password) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatement = conn.prepareStatement("SELECT Count(*) FROM kund where anvandarnamn = ? AND losenord = ?");
                PreparedStatement pstatement2 = conn.prepareStatement("SELECT * FROM kund where anvandarnamn = ? AND losenord = ?"))
        {

            pstatement.setString(1,userName);
            pstatement.setString(2,password);
            ResultSet result = pstatement.executeQuery();
            result.next();

            if (result.getInt(1) == 0) {
                return false;
            } else {
                pstatement2.setString(1,userName);
                pstatement2.setString(2,password);
                result = pstatement2.executeQuery();
                while (result.next()) {
                    Kund user = new Kund();
                    user.setId(result.getInt(1));
                    user.setFornamn(result.getString(2));
                    user.setEfternamn(result.getString(3));
                    user.setTelefon(result.getString(4));
                    user.setAnvandarnamn(result.getString(5));
                    user.setLosenord(result.getString(6));
                    user.setEmail(result.getString(7));
                    user.setAdressId(result.getInt(8));
                    currentUser = user;
                }
                return true;
            }

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Modell> getAllModelsInStock() {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatementModel = conn.prepareStatement("SELECT * FROM modell " +
                        "INNER JOIN sko ON modell.id = sko.modell_id" +
                        " INNER JOIN produkt ON sko.produkt_id = produkt.id" +
                        " INNER JOIN lagerstatus ON produkt.id = lagerstatus.produkt_id" +
                        " WHERE lagerstatus.antal_i_lager > 0" );
                PreparedStatement pstatementMarke = conn.prepareStatement("SELECT * FROM marke " +
                        " WHERE marke.id = ?")) {

            ResultSet result = pstatementModel.executeQuery();
            List<Modell> modelsInStock =new ArrayList<>();
            while (result.next()) {
                Modell modell = new Modell();
                modell.setId(result.getInt(1));
                modell.setNamn(result.getString(2));
                modell.setMarkesId(result.getInt(3));
                modell.setBildFilnamn(result.getString(4));
                modelsInStock.add(modell);
            }

           for (Modell model : modelsInStock) {
               pstatementMarke.setInt(1,model.getMarkesId());
               ResultSet result2 = pstatementMarke.executeQuery();
               while (result2.next()) {
                   model.setMarke(new Marke(result2.getInt(1),result2.getString(2),result2.getString(3)));
               }
               }
            return modelsInStock;

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean orderItems (List<Sko> shoes) {

        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                CallableStatement stm = conn.prepareCall("CALL addToCart(?,?,?,?)")) {

            stm.setInt(1,currentUser.getId());
            stm.setInt(2,shoes.get(0).getProduktId());
            stm.setInt(3,1);
            stm.registerOutParameter(4,Types.INTEGER);
            stm.setInt(4, Types.INTEGER);

            stm.executeQuery();

            int orderNumber = stm.getInt(4);

            if (shoes.size()>1) {
                for (int i = 1; i < shoes.size(); i++) {
                    stm.setInt(1,currentUser.getId());
                    stm.setInt(2,shoes.get(i).getProduktId());
                    stm.setInt(3,1);
                    stm.setInt(4, orderNumber);

                    stm.executeQuery();
                }
            }
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Något gick fel, beställningen kunde inte läggas.");
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<Sko> getAllShoesforModel(Modell model) {
        try (
                Connection conn = DriverManager.getConnection(info.getProperty("connectionString"),
                        info.getProperty("user"),
                        info.getProperty("password"));
                PreparedStatement pstatement = conn.prepareStatement("SELECT * FROM sko" +
                        " INNER JOIN modell on sko.modell_id = modell.id WHERE modell.namn = ?");
                PreparedStatement pstatementStorlek = conn.prepareStatement("SELECT * FROM storlek");
                PreparedStatement pstatementColor = conn.prepareStatement("SELECT * FROM farg " +
                        "INNER JOIN sko on sko.farg_id = farg.id " +
                        "WHERE sko.id = ?");
                PreparedStatement pstatementType = conn.prepareStatement("SELECT * FROM skotyp " +
                        "INNER JOIN skotypsmappning on skotyp.id = skotypsmappning.skotyp_id " +
                        "INNER JOIN sko on sko.id = skotypsmappning.sko_id " +
                        "WHERE sko.id = ?")) {

            pstatement.setString(1,model.getNamn());
            ResultSet result = pstatement.executeQuery();
            List< Sko> shoes =new ArrayList<>();
            while (result.next()) {
                Sko shoe = new Sko();
                shoe.setId(result.getInt(1));
                shoe.setProduktId(result.getInt(2));
                shoe.setStorlekId(result.getInt(3));
                shoe.setModellId(result.getInt(4));
                shoe.setModell(model);
                shoes.add(shoe);
            }

            List<Storlek> sizes = new ArrayList<>();
            result = pstatementStorlek.executeQuery();
            while (result.next()) {
                Storlek size = new Storlek();
                size.setId(result.getInt(1));
                size.setUkStorlek(result.getDouble(2));
                size.setUsStorlek(result.getDouble(3));
                size.setEuStorlek(result.getDouble(4));
                sizes.add(size);
            }

          for (Sko shoe: shoes) {
              for (Storlek size : sizes) {
                  if (shoe.getStorlekId() == size.getId()) {
                      shoe.setStorlek(size);
                  }
              }
              pstatementColor.setInt(1,shoe.getId());
              result = pstatementColor.executeQuery();
              while (result.next()) {
                  Farg farg = new Farg();
                  farg.setId(result.getInt(1));
                  farg.setNamn(result.getString(2));
                  shoe.setFarg(farg);
              }

              List<Skotyp> shoeCategorys = new ArrayList<>();
              pstatementType.setInt(1,shoe.getId());
              result = pstatementType.executeQuery();
              while (result.next()) {
                  Skotyp skotyp = new Skotyp();
                  skotyp.setId(result.getInt(1));
                  skotyp.setNamn(result.getString(2));
                  shoeCategorys.add(skotyp);
              }
              shoe.setSkoTyper(shoeCategorys);
          }

            return shoes;

        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    }
