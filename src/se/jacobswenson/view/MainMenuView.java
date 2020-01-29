package se.jacobswenson.view;
import se.jacobswenson.model.Modell;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

import static se.jacobswenson.view.SwingSetup.*;

public class MainMenuView extends JFrame implements ActionListener {

    private PanelHandler panelHandler;
    private List<JButton> modelButtons;
    private List<Modell> modelsInStock ;
    private JButton backButton = createLogOutButton();
    private JButton cartButton = createCartButton();
    private JPanel buttonPanel = createPanel();
    private HashMap<String, Integer> modelInfo = new HashMap<>();


    public MainMenuView(PanelHandler panelHandler) {

        this.panelHandler = panelHandler;

        modelButtons = new ArrayList<>();
        panelHandler.setMainMenuPanel(createPanel());

        JPanel menuBar = createPanel();
        menuBar.setLayout(new GridLayout(2, 1, 10, 10));
        menuBar.setBorder(new EmptyBorder(120, 20, 120, 0));
        backButton.addActionListener(this);
        cartButton.addActionListener(this);
        menuBar.add(backButton);
        menuBar.add(cartButton);

        buttonPanel.setLayout(new GridLayout(modelButtons.size(), 2, 5, 5));
        buttonPanel.setBorder(new EmptyBorder(10, 10, 50, 100));
        updateModels();

        panelHandler.getMainMenuPanel().setLayout(new BorderLayout());
        panelHandler.getMainMenuPanel().add(createLogo(), BorderLayout.NORTH);
        panelHandler.getMainMenuPanel().add(buttonPanel, BorderLayout.CENTER);
        panelHandler.getMainMenuPanel().add(menuBar, BorderLayout.WEST);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object ae = e.getSource();

        if (e.getSource().equals(backButton)) {
            panelHandler.changeToMainMenuPanel();
        } else if (e.getSource().equals(cartButton)) {
            panelHandler.changeToCartPanel();
        } else if (ae instanceof JButton) {
            JButton b = (JButton) ae;
            modelsInStock.forEach(m -> {
                if (m.getId() == modelInfo.get(b.getText())) {
                    panelHandler.changeToModelPanel(m);
                }
            });
        }
    }
    public void updateModels() {
        this.modelsInStock = this.panelHandler.getController().getAllModels();

        this.modelsInStock.forEach(s -> {
            String btnText = "   " +s.getMarke().getNamn() + ", " + s.getNamn();
            if (!modelInfo.containsValue(s.getId())) {
                modelInfo.put(btnText, s.getId());
                JButton btn = createModelButton(s.getBildFilnamn());
                btn.setText(btnText);
                modelButtons.add(btn);
            }

        });
        modelButtons.forEach(m -> {
            buttonPanel.add(m);
            m.addActionListener(this);
        });
    }
}

