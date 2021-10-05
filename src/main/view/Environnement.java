package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.BoutonFleche;

import main.model.Jeu;

import java.awt.*;

/**
 * Créé un panneau chambre qui est un des environnements du jeu ainsi que le lieu de départ lors d'une nouvelle partie
 */
public class Environnement extends JPanel{

    public Jeu jeu;

    private String lieu;
    public static JButton gauche;
    public static JButton droite;
    public static JButton options;
    public JLabel avatarChoisi;
    private JLabel sante;
    private JLabel bonheur;
    private JLabel faim;
    private JLabel fatigue;
    private JLabel hygiene;
    private JLabel besoins;
    private JLabel imageSante;
    private JLabel imageBonheur;
    private JLabel imageFaim;
    private JLabel imageFatigue;
    private JLabel imageHygiene;
    private JLabel imageBesoins;
    
    /**
     * Panneau qui contient les éléments nécessaires à la création de l'avatar
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public Environnement(String lieu, FenetrePrincipale principale){

        this.lieu = lieu;

        this.setLayout(new BorderLayout());

        this.jeu = principale.getJeu();

        //BORDERLAYOUT.NORTH
        //Affichage des statistiques et de l'heure
        JPanel nord = new JPanel();
        nord.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height/4));
        nord.setLayout(new GridLayout(1, 5));
        nord.setOpaque(false);

        //Configuration du panneau des statistiques de l'avatar
        JPanel statistiques = new JPanel();
        statistiques.setOpaque(false);
        statistiques.setLayout(new BorderLayout());

        //Ajout d'un panneau qui va afficher les icônes des statistiques
        JPanel imagesStats = new JPanel();
        imagesStats.setOpaque(false);
        imagesStats.setLayout(new GridLayout(6, 1));
    
        //Ajout d'un panneau qui va afficher les barres de statistiques
        JPanel infosStats = new JPanel();
        infosStats.setOpaque(false);
        infosStats.setLayout(new GridLayout(6, 1));

        //Construction des différents éléments des statistiques
        imageSante = new JLabel(new ImageIcon(new ImageIcon("resources/others/logoSante.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        sante = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getSante()));
        imageBonheur = new JLabel(new ImageIcon(new ImageIcon("resources/others/logoBonheur.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        bonheur = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getBonheur()));
        imageFaim = new JLabel(new ImageIcon(new ImageIcon("resources/others/logoFaim.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        faim = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getFaim()));
        imageFatigue = new JLabel(new ImageIcon(new ImageIcon("resources/others/logoFatigue.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        fatigue = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getFatigue()));
        imageHygiene = new JLabel(new ImageIcon(new ImageIcon("resources/others/logoHygiene.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        hygiene = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getHygiene()));
        imageBesoins = new JLabel(new ImageIcon(new ImageIcon("resources/others/logoBesoins.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        besoins = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getBesoins()));

        //Ajout des images aux panneaux
        imagesStats.add(imageSante);
        infosStats.add(sante);
        imagesStats.add(imageBonheur);
        infosStats.add(bonheur);
        imagesStats.add(imageFaim);
        infosStats.add(faim);
        imagesStats.add(imageFatigue);
        infosStats.add(fatigue);
        imagesStats.add(imageHygiene);
        infosStats.add(hygiene);
        imagesStats.add(imageBesoins);
        infosStats.add(besoins);

        //Ajout des panneaux contenant les images et les barres de statistiques au panneau global qui contient toutes les statistiques
        statistiques.add(imagesStats, BorderLayout.WEST);
        statistiques.add(infosStats, BorderLayout.EAST);

        //Ajout du panneau des stats dans le coin gauche de l'écran
        nord.add(statistiques);
        nord.add(new JLabel());
        nord.add(new JLabel());
        nord.add(new JLabel());
        nord.add(new JLabel());
        

        //BORDERLAYOUT.WEST
        //Affichage du bouton fléché gauche pour changer d'environnement
        if(this.lieu != "Jardin" ){
            gauche = new BoutonFleche("Gauche", 150, 120);
            gauche.addActionListener(new ListenerBouton(this.lieu, "Gauche", principale));
            gauche.setSize(new Dimension(200, 150));
            this.add(gauche, BorderLayout.WEST);
        }else{
            JPanel gaucheVide = new JPanel();
            gaucheVide.setPreferredSize(new Dimension(200, 150));
            gaucheVide.setOpaque(false);
            this.add(gaucheVide, BorderLayout.WEST);
        }

        //BORDERLAYOUT.EAST
        //Pas de possibilité d'aller à droite depuis la chambre, on ajoute un panel vide de la même taille que le bouton de gauche pour centrer le tout
        if(this.lieu != "Chambre"){
            droite = new BoutonFleche("Droite", 150, 120);
            droite.addActionListener(new ListenerBouton(this.lieu, "Droite", principale));
            droite.setSize(new Dimension(600, 150));
            this.add(droite, BorderLayout.EAST);
        }else{
            JPanel droiteVide = new JPanel();
            droiteVide.setPreferredSize(new Dimension(200, 150));
            droiteVide.setOpaque(false);
            this.add(droiteVide, BorderLayout.EAST);
        }

        //BORDERLAYOUT.CENTER
        //Affichage de l'avatar
        if(jeu.getAvatar().getType() == "Chien"){
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("resources/tamagotchi/chienPerso.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            avatarChoisi = new JLabel(imageAvatar);
        }else if(jeu.getAvatar().getType() == "Chat"){
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("resources/tamagotchi/chatPerso.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            avatarChoisi = new JLabel(imageAvatar);
        }else if(jeu.getAvatar().getType() == "Oiseau"){
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("resources/tamagotchi/oiseauPerso.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            avatarChoisi = new JLabel(imageAvatar);
        }else if(jeu.getAvatar().getType() == "Poulpe"){
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("resources/tamagotchi/poulpe.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            avatarChoisi = new JLabel(imageAvatar);
        }else{
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("resources/tamagotchi/robot.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            avatarChoisi = new JLabel(imageAvatar);
        }

        //BORDERLAYOUT.SOUTH
        //Affichage des actions possibles pour le joueur ainsi que la roue des options
        JPanel sud = new JPanel();
        sud.setOpaque(false);
        sud.setPreferredSize(new Dimension(1000, Toolkit.getDefaultToolkit().getScreenSize().height/6));
        sud.setLayout(new GridLayout(1, 3));

        JPanel actions = new JPanel();
        actions.setBackground(Color.GREEN);

        JPanel vide3 = new JPanel();
        vide3.setOpaque(false);

        options = new JButton();
        options.setBackground(null);
        options.setContentAreaFilled(false);
        options.setFocusPainted(false);
        options.setBorderPainted(false);
        options.setIcon(new ImageIcon(new ImageIcon("resources/others/logoOptions.png").getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));

        sud.add(actions);
        sud.add(vide3);
        sud.add(options);

        //Ajout des éléments au panneau
        this.add(nord, BorderLayout.NORTH);
        this.add(avatarChoisi, BorderLayout.CENTER);
        this.add(sud, BorderLayout.SOUTH);
    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        if(this.lieu == "Jardin"){
            g.drawImage(new ImageIcon("resources/background/jardin.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }else if(this.lieu == "Cuisine"){
            g.drawImage(new ImageIcon("resources/background/cuisineG.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }else if(this.lieu == "Douche"){
            g.drawImage(new ImageIcon("resources/background/salleDeBain.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }else if(this.lieu == "Chambre"){
            g.drawImage(new ImageIcon("resources/background/chambre.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }else{
            g.drawImage(new ImageIcon("resources/background/sarah-boeving-kitchen.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}
