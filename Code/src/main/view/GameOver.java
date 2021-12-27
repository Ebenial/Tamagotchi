package main.view;

import main.util.CustomFont;
import main.util.CustomJButton;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {
    public static CustomJButton retour;
    public GameOver(FenetrePrincipale principale){
        this.setLayout(new BorderLayout());

        JLabel tomb = new JLabel();
        tomb.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/tomb.png").getImage().getScaledInstance(256, 256, java.awt.Image.SCALE_SMOOTH)));
        JLabel titre = new JLabel("GAME OVER");
        titre.setFont(CustomFont.customFont50);
        titre.setForeground(Color.red);
        this.setBackground(Color.black);
        retour = new CustomJButton("Retour au menu", principale,null, "Code/resources/others/button_background_large.png", null, null, null);
        this.add(retour);

        this.add(titre, BorderLayout.NORTH);
        this.add(tomb, BorderLayout.CENTER);
        this.add(retour, BorderLayout.SOUTH);

    }
}
