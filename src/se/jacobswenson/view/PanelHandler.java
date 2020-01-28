package se.jacobswenson.view;

import se.jacobswenson.controller.Controller;
import se.jacobswenson.model.Modell;
import se.jacobswenson.model.Sko;

import javax.swing.*;
import java.awt.*;


public class PanelHandler extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private JPanel mainMenuPanel;
    private JPanel modelPanel;
    private JPanel cartPanel;
    private Controller controller = new Controller();
    private CartView cartView;

    public PanelHandler() {

        new LoginView(this);
        new MainMenuView(this);
        cartView = new CartView(this);

        //mainPanel
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(loginPanel, "login");
        mainPanel.add(mainMenuPanel, "mainMenu");
        mainPanel.add(cartPanel, "cart");

        add(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setVisible(true);
        setSize(new Dimension(800, 600));
    }


    public JPanel getLoginPanel() {
        return loginPanel;
    }

    public void setLoginPanel(JPanel loginPanel) {
        this.loginPanel = loginPanel;
    }

    public JPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    public void setMainMenuPanel(JPanel mainMenuPanel) {
        this.mainMenuPanel = mainMenuPanel;
    }

    public JPanel getModelPanel() {
        return modelPanel;
    }

    public void setModelPanel(JPanel modelPanel) {
        this.modelPanel = modelPanel;
    }

    public JPanel getCartPanel() {
        return cartPanel;
    }

    public void setCartPanel(JPanel cartPanel) {
        this.cartPanel = cartPanel;
    }

    public void changeToModelPanel(Modell model) {
        new ModelView(this, model);
        mainPanel.add(modelPanel, "model");
        changePanel("model");
    }

    public void changeToCartPanel() {
        changePanel("cart");
    }

    public void changeToMainMenuPanel() {
        changePanel("mainMenu");
    }

    private void changePanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }


    public void addToCart(Sko shoe) {
        cartView.addShoeToCart(shoe);
    }

    public Controller getController() {
        return controller;
    }
}

