package main.view;

import javax.swing.*;

import main.util.CustomJButton;

import java.awt.*;

/**
 * Propose de lancer une nouvelle partie ou de continuer une sauvegarde
 */
public class Jouer extends JPanel{

    public static CustomJButton nouvellePartie;
    public static CustomJButton continuer;
    public static CustomJButton retour;
    JLabel titre;
    JLabel signature;

    /**
     * Construit le panneau pour choisir le type de partie (nouvelle, continuer)
     * @param fenetre - la fenêtre dans laquelle sera affiché le panneau
     */
    public Jouer(FenetrePrincipale fenetre){

        this.setLayout(new BorderLayout());

        //BORDERLAYOUT.NORTH
        //Titre de la page et paramétrage
        titre = new JLabel("Tamagotchi", SwingConstants.CENTER);
        titre.setFont(new Font("Century Gothic", Font.PLAIN, 28));
        titre.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

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

        //Bouton qui lance une nouvelle partie
        nouvellePartie = new CustomJButton("Nouvelle Partie", fenetre,null, "Code/resources/others/button_background_large.png", null, null, null);
        this.add(nouvellePartie);


        //Bouton qui mène à la page des sauvegardes
        continuer = new CustomJButton("Continuer", fenetre,null, "Code/resources/others/button_background_large.png", null, null, null);
        this.add(continuer);

        //Bouton qui ramène à la page d'accueil
        retour = new CustomJButton("Retour", fenetre,null, "Code/resources/others/button_background_large.png", null, null, null);
        this.add(retour);

        //BORDERLAYOUT.SOUTH
        //Ajout de la signature en bas de fenêtre
        signature = new JLabel("D\u00E9velopp\u00e9 par ASPYG", SwingConstants.RIGHT);
        signature.setForeground(Color.WHITE);
        signature.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));


        //Ajout des différents boutons au panneau du menu
        gbc.insets = new Insets(30, 0, 30, 0);
        gbc.gridx = 1;
        menu.add(nouvellePartie, gbc);
        gbc.gridy = 1;
        menu.add(continuer, gbc);
        gbc.gridy = 2;
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