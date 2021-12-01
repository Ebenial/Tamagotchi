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

    private Jeu jeu;

    private String lieu;
    public static JButton gauche;
    public static JButton droite;
    public static JButton options;
    public JLabel avatarChoisi;
    private JLabel sante;
    private JLabel bonheur;
    private JLabel nourriture;
    private JLabel energie;
    private JLabel hygiene;
    private JLabel divertissement;
    private JLabel imageSante;
    private JLabel imageBonheur;
    private JLabel imageNourriture;
    private JLabel imageEnergie;
    private JLabel imageHygiene;
    private JLabel imageDivertissement;
    
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
        imageSante = new JLabel(new ImageIcon(new ImageIcon("Code/resources/others/logoSante.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        sante = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getSante()));
        imageBonheur = new JLabel(new ImageIcon(new ImageIcon("Code/resources/others/logoBonheur.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        bonheur = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getBonheur()));
        imageNourriture = new JLabel(new ImageIcon(new ImageIcon("Code/resources/others/logonourriture.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        nourriture = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getNourriture()));
        imageEnergie = new JLabel(new ImageIcon(new ImageIcon("Code/resources/others/logoenergie.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        energie = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getEnergie()));
        imageHygiene = new JLabel(new ImageIcon(new ImageIcon("Code/resources/others/logoHygiene.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        hygiene = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getHygiene()));
        imageDivertissement = new JLabel(new ImageIcon(new ImageIcon("Code/resources/others/logodivertissement.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
        divertissement = new JLabel(this.jeu.choixBarreStats(this.jeu.getAvatar().getDivertissement()));

        //Ajout des images aux panneaux
        imagesStats.add(imageSante);
        infosStats.add(sante);
        imagesStats.add(imageBonheur);
        infosStats.add(bonheur);
        imagesStats.add(imageNourriture);
        infosStats.add(nourriture);
        imagesStats.add(imageEnergie);
        infosStats.add(energie);
        imagesStats.add(imageHygiene);
        infosStats.add(hygiene);
        imagesStats.add(imageDivertissement);
        infosStats.add(divertissement);

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
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/chienPerso.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            avatarChoisi = new JLabel(imageAvatar);
        }else if(jeu.getAvatar().getType() == "Chat"){
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/chatPerso.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            avatarChoisi = new JLabel(imageAvatar);
        }else if(jeu.getAvatar().getType() == "Oiseau"){
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/oiseauPerso.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            avatarChoisi = new JLabel(imageAvatar);
        }else if(jeu.getAvatar().getType() == "Poulpe"){
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/poulpe.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
            avatarChoisi = new JLabel(imageAvatar);
        }else{
            ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/robot.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
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
        options.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/logoOptions.png").getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH)));

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
            g.drawImage(new ImageIcon("Code/resources/background/jardin.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }else if(this.lieu == "Cuisine"){
            g.drawImage(new ImageIcon("Code/resources/background/cuisineG.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }else if(this.lieu == "Douche"){
            g.drawImage(new ImageIcon("Code/resources/background/salleDeBain.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }else if(this.lieu == "Chambre"){
            g.drawImage(new ImageIcon("Code/resources/background/chambre.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }else{
            g.drawImage(new ImageIcon("Code/resources/background/sarah-boeving-kitchen.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }
}