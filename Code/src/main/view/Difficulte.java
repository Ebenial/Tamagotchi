package main.view;

import main.util.CustomFont;
import main.util.CustomJButton;

import javax.swing.*;
import java.awt.*;


public class Difficulte extends JPanel{

    public static CustomJButton facile;
    public static CustomJButton normal;
    public static CustomJButton difficile;
    public static CustomJButton legendaire;
    public static CustomJButton retour;
    JLabel signature;

    /**
     * Construit le panneau pour choisir le type de partie (nouvelle, continuer)
     * @param fenetre - la fenêtre dans laquelle sera affiché le panneau
     */
    public Difficulte(FenetrePrincipale fenetre){

        this.setLayout(new BorderLayout());
        JLabel titre = new JLabel("Choix de la difficulté");
        titre.setFont(CustomFont.customFont50_PLAIN);
        titre.setBorder(BorderFactory.createEmptyBorder(45,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)
        titre.setForeground(Color.white);
        titre.setHorizontalAlignment(SwingConstants.CENTER);

        //BORDERLAYOUT.WEST
        //Ajout d'un panel vide à gauche 
        JPanel gauche = new JPanel();
        gauche.setPreferredSize(new Dimension(500, 0));
        gauche.setOpaque(false);

        //BORDERLAYOUT.EAST
        //Ajout d'un panel vide à droite 
        JPanel nouveautes = new JPanel();
        nouveautes.setPreferredSize(new Dimension(500,0));
        nouveautes.setOpaque(false);

        //BORDERLAYOUT.CENTER
        //Ajout d'un panel qui va stocker les différents boutons du menu
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        menu.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        facile = new CustomJButton("Facile", fenetre,null, "Code/resources/others/button_background_large.png", null, null, null);
        this.add(facile);

        normal = new CustomJButton("Normal", fenetre,null, "Code/resources/others/button_background_large.png", null, null, null);
        this.add(normal);

        //Bouton qui mène à la page des sauvegardes
        difficile = new CustomJButton("Difficile", fenetre,null, "Code/resources/others/button_background_large.png", null, null, null);
        this.add(difficile);

        //Bouton qui mène à la page des sauvegardes
        legendaire = new CustomJButton("Légendaire", fenetre,null, "Code/resources/others/button_background_large.png", null, null, null);
        this.add(legendaire);

        //Bouton qui ramène à la page d'accueil
        retour = new CustomJButton("Retour", fenetre,null, "Code/resources/others/button_background_large.png", null, null, null);
        this.add(retour);

        //BORDERLAYOUT.SOUTH
        //Ajout de la signature en bas de fenêtre
        signature = new JLabel("D\u00E9velopp\u00e9 par ASPYG", SwingConstants.RIGHT);
        signature.setForeground(Color.WHITE);
        signature.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));


        //Ajout des différents boutons au panneau du menu
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 1;
        gbc.gridy = 1;
        menu.add(titre, gbc);
        gbc.gridy = 2;
        menu.add(facile, gbc);
        gbc.gridy = 3;
        menu.add(normal, gbc);
        gbc.gridy = 4;
        menu.add(difficile, gbc);
        gbc.gridy = 5;
        menu.add(legendaire, gbc);
        gbc.gridy = 6;
        menu.add(retour, gbc);

        //Ajout des composants au panneau d'accueil
        this.add(titre, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(menu, BorderLayout.CENTER);
        this.add(nouveautes, BorderLayout.EAST);
        this.add(signature, BorderLayout.SOUTH);

    }

    /**
     * Modifie le background
     * @param g - 
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}