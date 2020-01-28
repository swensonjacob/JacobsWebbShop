package se.jacobswenson.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SwingSetup {

    private static String coolvetica = "src/se/jacobswenson/Resources/coolveticarg.ttf";

    public static JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(new Color(200, 57, 65));
        button.setBackground(Color.white);
        button.setOpaque(true);
        Border line = new LineBorder(new Color(200, 57, 65));
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        button.setPreferredSize(new Dimension(70, 30));
        button.setFont(applyFont(coolvetica));
        return button;
    }

    public static JList createJList() {
        JList list = new JList();
        list.setBackground(new Color(246, 235, 219));
        return list;
    }

    public static JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(246, 235, 219));
        return panel;
    }


    public static JLabel createLabel(String text, int alignment) {
        JLabel jLabel = new JLabel(text, alignment);
        jLabel.setForeground(new Color(200, 57, 65));
        jLabel.setFont(applyFont(coolvetica));
        jLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        return jLabel;
    }

    public static JLabel createBigLabel(String text, int alignment) {
        JLabel jLabel = new JLabel(text, alignment);
        jLabel.setForeground(new Color(200, 57, 65));
        Font font = applyFont(coolvetica);
        Font sizedFont = font.deriveFont(26f);
        jLabel.setFont(sizedFont);
        return jLabel;
    }

    private static Font applyFont(String path) {
        File font_file = new File(path);
        Font font = null;
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, font_file);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
        Font sizedFont = font.deriveFont(14f);
        return sizedFont;
    }

    public static JLabel createLogo() {
        String logoPath = "src/se/jacobswenson/Resources/logo_transparent.png";
        JLabel logoShoe = new JLabel(new ImageIcon(logoPath));
        logoShoe.setBorder(new EmptyBorder(10, 10, 10, 10));
        return logoShoe;
    }

    public static Border getEmptyBorder() {
        return new EmptyBorder(10, 10, 10, 10);
    }

    public static JButton createHomeButton() {
        return createIconButton("homeIcon.png");
    }

    public static JButton createCartButton() {
        return createIconButton("cartIcon.png");
    }

    public static JButton createLogOutButton() {
        return createIconButton("logout.png");
    }

    public static JButton createModelButton(String filename) {
        return createIconButton(filename);
    }
    private static JButton createIconButton(String fileName) {
        Icon icon = new ImageIcon("src/se/jacobswenson/Resources/" + fileName);
        JButton button = new JButton(icon);
        button.setForeground(new Color(200, 57, 65));
        button.setBackground(Color.white);
        button.setOpaque(true);
        Border line = new LineBorder(new Color(200, 57, 65));
        Border margin = new EmptyBorder(5, 15, 5, 15);
        Border compound = new CompoundBorder(line, margin);
        button.setBorder(compound);
        button.setPreferredSize(new Dimension(70, 30));
        button.setFont(applyFont(coolvetica));
        return button;
    }

    public static JLabel createshoeIcon(String fileName) {
        String logoPath = "src/se/jacobswenson/Resources/" + fileName;
        JLabel logoShoe = new JLabel(new ImageIcon(logoPath));
        logoShoe.setBorder(new EmptyBorder(10, 10, 10, 10));
        return logoShoe;
    }
}
