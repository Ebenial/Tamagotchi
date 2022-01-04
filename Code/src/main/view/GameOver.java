package main.view;

import main.util.CustomFont;
import main.util.CustomJButton;

import javax.swing.*;
import java.awt.*;

/**
 *La classe GameOver correspond au JPanel qui est affiché lorsque le joueur perd la partie
 */
public class GameOver extends JPanel {
    public static CustomJButton retour;
    public static long score = 0;
    private final JLabel resultat;

    /**
     * Constructeur de la classe GameOver
     * @param principale - la fenêtre (JFrame) principale du jeu
     */
    public GameOver(FenetrePrincipale principale){
        this.setLayout(new GridBagLayout());
        this.setBackground(Color.black);
        GridBagConstraints c = new GridBagConstraints();

        //Création des différents éléments à afficher dans la page
        JPanel button = new JPanel();
        button.setLayout(new GridBagLayout());
        button.setOpaque(false);

        retour = new CustomJButton("Retour au menu", principale,null, "Code/resources/others/button_background_large.png", null, null, null);
        retour.setOpaque(false);
        button.add(retour);

        JLabel tomb = new JLabel();
        tomb.setHorizontalAlignment(JLabel.CENTER);
        tomb.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/tomb.png").getImage().getScaledInstance(256, 256, java.awt.Image.SCALE_SMOOTH)));

        JLabel titre = new JLabel("GAME OVER");
        titre.setFont(CustomFont.customFont50);
        titre.setForeground(Color.red);
        titre.setHorizontalAlignment(JLabel.CENTER);

        resultat = new JLabel("Votre score est "+score);
        resultat.setFont(CustomFont.customFont40);
        resultat.setForeground(Color.WHITE);

        //Ajout des différents éléments sur la page

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

    /**
     * Accesseur du résultat de la partie
     * @return - le résultat de la partie sous forme de JLabel
     */
    public JLabel getResultat() {
        return this.resultat;
    }
}
