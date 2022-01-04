package main.util;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * Créé un bouton personnalisé
 */
public class BoutonFleche extends JButton {

    /**
     * Construit un bouton fléché personnalisé
     * @param sensFleche - indique le sens de la flèche (forme du bouton)
     * @param longueur - taille horizontale du bouton
     * @param hauteur - taille verticale du bouton
     */
    public BoutonFleche(String sensFleche, int longueur, int hauteur){

        this.setBackground(null);
        this.setContentAreaFilled(false);
        this.setFocusPainted(false);
        this.setBorderPainted(false);
        if(sensFleche.equals("Gauche")){
            this.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/flecheGauche.png").getImage().getScaledInstance(longueur, hauteur, java.awt.Image.SCALE_SMOOTH)));
        }else if(sensFleche.equals("Droite")){
            this.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/flecheDroite.png").getImage().getScaledInstance(longueur, hauteur, java.awt.Image.SCALE_SMOOTH)));
        }
    }

    /**
     * Active la fleche
     */
    public void activerFleche() {
        this.setEnabled(true);
        this.setVisible(true);
    }

    /**
     * Désactive la fleche
     */
    public void desactiverFleche() {
        this.setEnabled(false);
        this.setVisible(false);
    }
}
