package main.util;

import main.controler.ListenerBouton;
import main.model.Lieu;
import main.view.FenetrePrincipale;
import main.view.NouvellePartie;

import javax.swing.*;
import java.awt.*;

/**
 * JButton personnalisé
 */
public class CustomJButton extends JButton {

    /**
     * Créé un JButton personnalisé en fonction des besoins
     * Tous les paramètres ne sont pas nécessaires et peuvent donc être passés null
     * @param text - le texte du bouton
     * @param principale - la fenêtre dans laquelle le bouton sera affiché
     * @param panel - le panneau dans lequel le bouton sera affiché
     * @param imagePath - l'image de fond du bouton
     * @param font - la police du bouton
     * @param lieu - le lieu du bouton
     * @param direction - la direction du bouton
     */
    public CustomJButton(String text, FenetrePrincipale principale, NouvellePartie panel, String imagePath, Font font, Lieu lieu, String direction) {
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
            this.setFont(CustomFont.customFont50_PLAIN);
        } else {
            this.setFont(font); //old font : setFont(new Font("Century Gothic", Font.PLAIN, 50));
        }
    }

    /**
     * Autre constructeur pour des besoins différents
     * @param principale - la fenêtre dans laquelle le bouton sera affiché
     */
    public CustomJButton(FenetrePrincipale principale){
        this.setBorder(BorderFactory.createEmptyBorder(21, 0, 21, 0));
        this.setOpaque(false);
        this.addActionListener(new ListenerBouton(principale));
        this.setBackground(new Color(0,0,0,0));
        this.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/trash_can.png").getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH)));
    }
}
