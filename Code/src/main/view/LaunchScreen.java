package main.view;

import javax.swing.*;
import java.awt.*;

/**
 *Écran de lancement du jeu, affiche le logo PEGI 7 à l'écran
 */
public class LaunchScreen extends JPanel {

    /**
     * Construit le panneau/écran de lancement
     */
    public  LaunchScreen(){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.black);

        JLabel pegi = new JLabel();
        pegi.setHorizontalAlignment(JLabel.CENTER);
        pegi.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/pegi7.png").getImage().getScaledInstance(410,500, Image.SCALE_SMOOTH)));
        add(pegi);
    }
}
