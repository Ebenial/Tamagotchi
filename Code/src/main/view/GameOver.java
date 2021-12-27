package main.view;

import main.util.CustomFont;
import main.util.CustomJButton;
import main.util.TransparentJPanel;

import javax.swing.*;
import java.awt.*;

public class GameOver extends JPanel {
    public static CustomJButton retour;
    public GameOver(FenetrePrincipale principale){
        this.setLayout(new GridLayout(3,3));

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
        retour = new CustomJButton("Retour au menu", principale,null, "Code/resources/others/button_background_large.png", null, null, null);
        retour.setOpaque(false);

        button.add(retour);

        this.add(new TransparentJPanel());
        this.add(titre);
        this.add(new TransparentJPanel());
        this.add(new TransparentJPanel());
        this.add(tomb);
        this.add(new TransparentJPanel());
        this.add(new TransparentJPanel());
        this.add(button);
        this.add(new TransparentJPanel());

    }
}
