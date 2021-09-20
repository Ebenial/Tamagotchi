package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;

import java.awt.*;

/**
 * Ecran d'accueil lors du lancement du programme
 */
public class Accueil extends JPanel{

    public static JLabel titre;
    public static JButton jouer;
    public static JButton regles;
    public static JButton options;
    public static JButton quitter;
    public static JLabel signature;
    
    /**
     * Créé le panneau d'accueil
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public Accueil(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        //BORDERLAYOUT.NORTH
        //Titre de la page et paramétrage
        titre = new JLabel("Tamagotchi", SwingConstants.CENTER);
        titre.setFont(new Font("Century Gothic", Font.PLAIN, 100));
        titre.setForeground(Color.RED);
        titre.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        //BORDERLAYOUT.WEST
        //Ajout d'un panel vide à gauche 
        JPanel gauche = new JPanel();
        gauche.setPreferredSize(new Dimension(500, 0));
        gauche.setOpaque(false);

        //BORDERLAYOUT.EAST
        //Ajout d'un panel à droite qui contient les dernières nouveautés du programme
        JPanel nouveautes = new JPanel();
        nouveautes.setLayout(new GridLayout(10, 1, 30, 20));
        nouveautes.setPreferredSize(new Dimension(500,0));
        nouveautes.setOpaque(false);

        //Ajout du label Nouveautés pour le panel des nouveautés
        JLabel titreNouveautes = new JLabel("Nouveaut\u00e9s", SwingConstants.CENTER);
        titreNouveautes.setFont(new Font("Century Gothic", Font.BOLD, 30));
        titreNouveautes.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));
        titreNouveautes.setForeground(Color.WHITE);

        //Première nouveauté de la liste
        JLabel news1 = new JLabel("Création du personnage en cours !");
        news1.setFont(new Font("Century Gothic", Font.PLAIN, 20));
        news1.setForeground(Color.WHITE);

        //BORDERLAYOUT.CENTER
        //Ajout d'un panel qui va stocker les différents boutons du menu
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        menu.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        //Bouton qui ouvre le dossier des sauvegardes
        jouer = new JButton("Jouer");
        jouer.addActionListener(new ListenerBouton(principale));
        jouer.setIcon(new ImageIcon(new ImageIcon("../data/Boutoncontinuer.jpg").getImage().getScaledInstance(600, 150, java.awt.Image.SCALE_SMOOTH)));
        jouer.setPreferredSize(new Dimension(600, 150));
        jouer.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        jouer.setFont(new Font("Century Gothic", Font.BOLD, 50));

        //Bouton qui mène à la page de création d'un nouveau Tamagotchi
        regles = new JButton("Règles");
        regles.addActionListener(new ListenerBouton(principale));
        regles.setIcon(new ImageIcon(new ImageIcon("../data/BoutonnouvellePartie.png").getImage().getScaledInstance(600, 150, java.awt.Image.SCALE_SMOOTH)));
        regles.setPreferredSize(new Dimension(600, 150));
        regles.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        regles.setFont(new Font("Century Gothic", Font.BOLD, 50));
        regles.setForeground(Color.BLACK);

        //Bouton qui mène à la page des options
        options = new JButton("Options");
        options.addActionListener(new ListenerBouton(principale));
        options.setIcon(new ImageIcon(new ImageIcon("../data/BoutonUnivers.jpg").getImage().getScaledInstance(600, 150, java.awt.Image.SCALE_SMOOTH)));
        options.setPreferredSize(new Dimension(600, 150));
        options.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        options.setFont(new Font("Century Gothic", Font.BOLD, 50));
        options.setForeground(Color.BLACK);

        //Bouton pour quitter le programme
        quitter = new JButton("Quitter");
        quitter.addActionListener(new ListenerBouton(principale));
        quitter.setIcon(new ImageIcon(new ImageIcon("../data/BoutonQuitter.jpg").getImage().getScaledInstance(600, 150, java.awt.Image.SCALE_SMOOTH)));
        quitter.setPreferredSize(new Dimension(600, 150));
        quitter.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        quitter.setFont(new Font("Century Gothic", Font.BOLD, 50));

        //BORDERLAYOUT.SOUTH
        //Ajout de la signature en bas de fenêtre
        signature = new JLabel("D\u00E9velopp\u00e9 par ASPYG", SwingConstants.RIGHT);
        signature.setForeground(Color.WHITE);
        signature.setBorder(BorderFactory.createEmptyBorder(0,0,10,10));


        //Ajout des différents boutons au panneau du menu
        gbc.insets = new Insets(30, 0, 30, 0);
        gbc.gridx = 1;
        menu.add(jouer, gbc);
        gbc.gridy = 1;
        menu.add(regles, gbc);
        gbc.gridy = 2;
        menu.add(options, gbc);
        gbc.gridy = 3;
        menu.add(quitter, gbc);

        //Ajout des nouveautés au panneau des nouveautés
        nouveautes.add(titreNouveautes);
        nouveautes.add(news1);

        //Ajout des composants au panneau d'accueil
        this.add(titre, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(menu, BorderLayout.CENTER);
        this.add(nouveautes, BorderLayout.EAST);
        this.add(signature, BorderLayout.SOUTH);

    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}