package main.util;

import main.controler.ListenerBouton;
import main.view.FenetrePrincipale;

import javax.swing.*;
import java.awt.*;

public class CustomJButton extends JButton {
    public CustomJButton(String text, FenetrePrincipale principale){
        mainParameters(text, principale);
        this.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/button_background.png").getImage().getScaledInstance(384, 96, java.awt.Image.SCALE_SMOOTH))); //old : 400 par 100 / 384 par 96
        this.setFont(CustomFont.customFont50_PLAIN); //old font : setFont(new Font("Century Gothic", Font.PLAIN, 50));
        //this.setPreferredSize(new Dimension(600, 150));
    }

    public CustomJButton(String text, FenetrePrincipale principale, Font font){
        mainParameters(text, principale);
        this.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/button_background.png").getImage().getScaledInstance(384, 96, java.awt.Image.SCALE_SMOOTH))); //old : 400 par 100
        this.setFont(font); //old font : setFont(new Font("Century Gothic", Font.PLAIN, 50));
    }

    public CustomJButton(String text, FenetrePrincipale principale, String imagePath){
        mainParameters(text, principale);
        this.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(552, 96, java.awt.Image.SCALE_SMOOTH))); //old : 400 par 100
        this.setFont(CustomFont.customFont50_PLAIN); //old font : setFont(new Font("Century Gothic", Font.PLAIN, 50));
    }

    public void mainParameters(String text, FenetrePrincipale principale){
        setText(text);
        this.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        this.addActionListener(new ListenerBouton(principale));
        this.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        this.setVerticalAlignment(JButton.CENTER);
    }
}
