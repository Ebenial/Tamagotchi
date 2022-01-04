package main.view;

import main.util.CustomFont;
import main.util.CustomJButton;
import main.util.TransparentJPanel;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {
    public static CustomJButton retour;
    public static long score = 0;

    public GameOver(FenetrePrincipale principale){
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel button = new JPanel();
        button.setLayout(new GridBagLayout());
        button.setOpaque(false);


        JLabel tomb = new JLabel();
        tomb.setHorizontalAlignment(JLabel.CENTER);
        tomb.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/tomb.png").getImage().getScaledInstance(256, 256, java.awt.Image.SCALE_SMOOTH)));
        JLabel titre = new JLabel("GAME OVER");
        titre.setFont(CustomFont.customFont50);
        titre.setForeground(Color.red);
        titre.setHorizontalAlignment(JLabel.CENTER);
        this.setBackground(Color.black);
        JLabel resultat = new JLabel("Votre score est "+score);
        resultat.setFont(CustomFont.customFont40);
        resultat.setForeground(Color.WHITE);


        retour = new CustomJButton("Retour au menu", principale,null, "Code/resources/others/button_background_large.png", null, null, null);
        retour.setOpaque(false);

        button.add(retour);

        c.gridx = 0;
        c.gridy = 0;
        c.weighty = 0.2;
        this.add(titre, c);

        c.gridx = 0;
        c.gridy = 1;
        c.weighty = 0.2;
        this.add(tomb,c);

        c.gridx = 0;
        c.gridy = 2;
        c.weighty = 0.2;
        this.add(resultat,c);

        c.gridx = 0;
        c.gridy = 3;
        c.weighty = 0.2;
        this.add(button,c);


    }
}
