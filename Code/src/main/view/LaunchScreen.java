package main.view;

import javax.swing.*;
import java.awt.*;

public class LaunchScreen extends JPanel {
    public  LaunchScreen(){
        this.setLayout(new GridBagLayout());

        this.setBackground(Color.black);

        JLabel pegi = new JLabel();
        pegi.setHorizontalAlignment(JLabel.CENTER);
        pegi.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/pegi7.png").getImage().getScaledInstance(410,500, Image.SCALE_SMOOTH)));

        add(pegi);
    }
}
