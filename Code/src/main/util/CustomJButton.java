package main.util;

import main.controler.ListenerBouton;
import main.view.FenetrePrincipale;

import javax.swing.*;

public class CustomJButton extends JButton {
    public CustomJButton(String text, FenetrePrincipale principale){
        setText(text);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.addActionListener(new ListenerBouton(principale));
        this.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/button_background.png").getImage().getScaledInstance(384, 96, java.awt.Image.SCALE_SMOOTH))); //old : 400 par 100
        this.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        this.setVerticalAlignment(JButton.CENTER);
        this.setFont(CustomFont.customFont50_PLAIN); //old font : setFont(new Font("Century Gothic", Font.PLAIN, 50));
        //jouer.setPreferredSize(new Dimension(600, 150));
    }
}
