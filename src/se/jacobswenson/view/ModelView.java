package se.jacobswenson.view;
import se.jacobswenson.model.Modell;
import se.jacobswenson.model.Sko;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static se.jacobswenson.view.SwingSetup.*;

public class ModelView implements ActionListener {

    private Modell model;
    private HashMap<String, Integer> skoInfo = new HashMap<>();
    private List<Sko> shoes;
    private PanelHandler panelHandler;
    private JButton homeButton;
    private JButton cartButton;
    private JPanel modelPanel;
    private List<JButton> shoeButtons;


    public ModelView(PanelHandler panelHandler, Modell model){

        this.model = model;

        this.panelHandler = panelHandler;
        this.homeButton = createHomeButton();
        this.cartButton = createCartButton();
        this.shoeButtons = new ArrayList<>();
        homeButton.addActionListener(this);
        cartButton.addActionListener(this);
        panelHandler.setModelPanel(createPanel());
        modelPanel = createPanel();


        JPanel menuBar = createPanel();
        menuBar.setLayout(new GridLayout(2,1,10,10));
        menuBar.setBorder(new EmptyBorder(120, 20, 120, 0));
        menuBar.add(homeButton);
        menuBar.add(cartButton);

        this.shoes = panelHandler.getController().getAllShoesFromModel(model);
        shoes.forEach(s -> {
            String btnText = s.getFarg().getNamn() + ", " + s.getStorlek().getEuStorlek();
            skoInfo.put(btnText, s.getId());
            shoeButtons.add(createButton(btnText));
        });

        shoeButtons.forEach(b -> b.addActionListener(this));

        modelPanel.setLayout(new GridLayout(4,1,10,10));
        modelPanel.add(createshoeIcon(model.getBildFilnamn()));
        modelPanel.add(createBigLabel(model.getMarke().getNamn() + " " + model.getNamn(),0));
        modelPanel.setBorder(new EmptyBorder(0,10,40,100));
        shoeButtons.forEach(b -> modelPanel.add(b));

        panelHandler.getModelPanel().setLayout(new BorderLayout());
        panelHandler.getModelPanel().add(createLogo(), BorderLayout.NORTH);
        panelHandler.getModelPanel().add(menuBar,BorderLayout.WEST);
        panelHandler.getModelPanel().add(modelPanel,BorderLayout.CENTER);
}

    public Modell getModel() {
        return model;
    }

    public void setModel(Modell model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object ae = e.getSource();

        if (e.getSource().equals(homeButton)) {
            panelHandler.changeToMainMenuPanel();
        } else if (e.getSource().equals(cartButton)) {
            panelHandler.changeToCartPanel();
        } else if (ae instanceof JButton) {
           JButton b= (JButton) ae;
            shoes.forEach( s -> {
                if (s.getId() == skoInfo.get(b.getText())) {
                    JOptionPane.showMessageDialog(null,"Ett par " + s.getModell().getMarke().getNamn() + " " + s.getModell().getNamn() + " är tillagda i varukorgen");
                   panelHandler.addToCart(s);
                }
            });
        }

    }
}
