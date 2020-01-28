package se.jacobswenson.view;

import se.jacobswenson.model.Sko;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static se.jacobswenson.view.SwingSetup.*;

public class CartView implements ActionListener {

    private List<Sko> shoesInCart = new ArrayList<>();
    private JButton homeButton = createHomeButton();
    private JButton removeButton = createButton("Rensa varukorg");
    private JButton orderButton = createButton("Beställ");
    private PanelHandler panelHandler;
    private JList itemPanel;
    private JLabel cartText = createBigLabel("Varukorgen är tom",0);


    public CartView(PanelHandler pHandler) {
        this.panelHandler = pHandler;
        panelHandler.setCartPanel(createPanel());

        itemPanel = createJList();
        itemPanel.setLayout(new GridLayout(9,1));
        itemPanel.setBorder(new EmptyBorder(0,10,10,100));
        itemPanel.add(cartText);

        JPanel buttonPanel = createPanel();
        buttonPanel.setLayout(new GridLayout(1,2, 20,20));
        buttonPanel.setBorder(new EmptyBorder(20,20,20,20));
        buttonPanel.add(removeButton);
        buttonPanel.add(orderButton);

        JPanel menuBar = createPanel();
        menuBar.setLayout(new GridLayout(2, 1, 10, 10));
        menuBar.setBorder(new EmptyBorder(80, 20, 80, 0));
        menuBar.add(homeButton);


        homeButton.addActionListener(this);
        removeButton.addActionListener(this);
        orderButton.addActionListener(this);

        panelHandler.getCartPanel().setLayout(new BorderLayout());
        panelHandler.getCartPanel().add(createLogo(), BorderLayout.NORTH);
        panelHandler.getCartPanel().add(itemPanel, BorderLayout.CENTER);
        panelHandler.getCartPanel().add(menuBar, BorderLayout.WEST);
        panelHandler.getCartPanel().add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(homeButton)) {
            panelHandler.changeToMainMenuPanel();
        }else if (e.getSource().equals(removeButton)) {
            JOptionPane.showMessageDialog(null, "Rensar varukorg");
            clearCart();
        } else if(e.getSource().equals(orderButton)) {
            if (shoesInCart.size()>0) {
                if (panelHandler.getController().addToCart(shoesInCart)) {
                    orderConfirmation();
                    clearCart();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Ingenting i varukorgen");
            }
        }

    }

    public void addShoeToCart(Sko shoe) {

        if (shoesInCart.size()==0) {
            cartText.setText("Varukorg:");
        }
        shoesInCart.add(shoe);
        itemPanel.add(createLabel(getShoeText(shoe),0));
    }

    public void orderConfirmation() {

        StringBuilder sb = new StringBuilder("Din beställning är genomförd: \n");
        shoesInCart.forEach(s -> sb.append(getShoeText(s) + "\n"));
        JOptionPane.showMessageDialog(null,sb);
    }

    public String getShoeText(Sko shoe) {
        return shoe.getModell().getMarke().getNamn() + " " +
                shoe.getModell().getNamn() + " " +
                shoe.getFarg().getNamn() + " " +
                shoe.getStorlek().getEuStorlek();
    }

    public void clearCart() {
        shoesInCart.clear();
        itemPanel.removeAll();
        cartText.setText("Varukorgen är tom");
        itemPanel.add(cartText);
    }

}
