package se.jacobswenson.controller;

import se.jacobswenson.model.Kund;
import se.jacobswenson.model.Modell;
import se.jacobswenson.model.Sko;
import se.jacobswenson.repository.Repository;

import java.util.List;

public class Controller {
    Repository repository = new Repository();


    public boolean userNameExist(String userName) {
        return repository.verifyUsername(userName);
    }

    public boolean passwordCorrect(String userName, String password) {
        return repository.verifyPassword(userName, password);
    }

    public boolean addToCart(List<Sko> shoes) {
       return repository.orderItems(shoes);
    }

    public List<Modell> getAllModels() {
       return repository.getAllModelsInStock();
    }

    public  List<Sko> getAllShoesFromModel(Modell model) {
        return repository.getAllShoesforModel(model);
    }

}


