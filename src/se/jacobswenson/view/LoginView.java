package se.jacobswenson.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static se.jacobswenson.view.SwingSetup.*;

public class LoginView extends JFrame  implements ActionListener {

    private JTextField username = new JTextField();
    private JPasswordField password= new JPasswordField();
    private PanelHandler panelHandler;
    private JLabel errorText;

    public LoginView(PanelHandler panelHandler) {

        this.panelHandler = panelHandler;

        panelHandler.setLoginPanel(createPanel());
        JPanel loginPanel = SwingSetup.createPanel();
        JButton loginButton = createButton("Logga in");
        loginButton.addActionListener(this);

        loginPanel.setLayout(new GridLayout(3, 2));
        loginPanel.add(createLabel("Användarnamn",2));
        loginPanel.add(createLabel("Lösenord",2));
        loginPanel.add(username);
        loginPanel.add(password);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);
        loginPanel.setBorder(new EmptyBorder(10, 180, 210, 180));


        //Login
        errorText= createLabel("",0);
        errorText.setVerticalAlignment(JLabel.CENTER);
        panelHandler.getLoginPanel().setLayout(new BorderLayout());
        panelHandler.getLoginPanel().add(createLogo(), BorderLayout.NORTH);
        panelHandler.getLoginPanel().add(errorText, BorderLayout.CENTER);
        panelHandler.getLoginPanel().add(loginPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String passwordString = new String (password.getPassword());
        String usernameInput = username.getText();
        if (panelHandler.getController().userNameExist(usernameInput)) {
            if (panelHandler.getController().passwordCorrect(usernameInput, passwordString)) {
                panelHandler.changeToMainMenuPanel();
            } else {
                errorText.setText("Felaktigt lösenord");
                password.setText("");
            }
        } else {
            errorText.setText("Felaktigt användarnamn");
            username.setText("");
            password.setText("");
        }
    }
}
