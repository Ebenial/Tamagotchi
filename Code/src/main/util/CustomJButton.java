package main.util;

import main.controler.ListenerBouton;
import main.view.FenetrePrincipale;
import main.view.NouvellePartie;

import javax.swing.*;
import java.awt.*;

public class CustomJButton extends JButton {

    public CustomJButton(String text, FenetrePrincipale principale, NouvellePartie panel, String imagePath, Font font, String lieu, String direction) {
        setText(text);
        this.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        if (principale == null) {
            this.addActionListener(new ListenerBouton(panel));
        } else {
            if (panel == null) {
                if (lieu == null && direction == null) {
                    this.addActionListener(new ListenerBouton(principale));
                } else if (lieu != null && direction == null) {
                    this.addActionListener(new ListenerBouton(lieu, principale));
                } else {
                    this.addActionListener(new ListenerBouton(lieu, direction, principale));
                }
            } else {
                this.addActionListener(new ListenerBouton(principale, panel));
            }
        }

        this.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        this.setVerticalAlignment(JButton.CENTER);
        if (imagePath != null) {
            this.setIcon(new ImageIcon(new ImageIcon(imagePath).getImage().getScaledInstance(552, 96, java.awt.Image.SCALE_SMOOTH)));
        } else {
            this.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/button_background.png").getImage().getScaledInstance(384, 96, java.awt.Image.SCALE_SMOOTH))); //old : 400 par 100
        }
        if (font == null) {
            this.setFont(CustomFont.customFont50_PLAIN); //old font : setFont(new Font("Century Gothic", Font.PLAIN, 50));
        } else {
            this.setFont(font); //old font : setFont(new Font("Century Gothic", Font.PLAIN, 50));
        }
    }
}
