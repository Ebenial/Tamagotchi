package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.*;

import java.awt.*;

/**
 * Panneau de création de l'avatar du joueur
 */
public class NouvellePartie extends JPanel{

    public JLabel titre;
    public JLabel joueur;
    public static CustomJTextField nomJoueur;
    public JLabel avatar;
    public static CustomJTextField nomAvatar;
    public static JButton choixGauche;
    public static JButton choixDroite;
    public static CustomJButton valider;
    public static CustomJButton retour;
    public ImageIcon[] imagesAvatar;
    public JLabel avatarChoisi;
    public static String monChoix;
    public int position;
    public static String difficulty;
    
    /**
     * Panneau qui contient les éléments nécessaires à la création de l'avatar
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public NouvellePartie(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        //BORDERLAYOUT.NORTH
        //Titre de la page et paramétrage
        titre = new JLabel("NOUVELLE PARTIE", SwingConstants.CENTER);
        titre.setFont(CustomFont.customFont50_PLAIN);
        titre.setForeground(Color.white);
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
        //Ajout d'un panel qui va stocker les différents éléments du panneau
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        menu.setBackground(new Color(255, 255, 255, 161));
        menu.setBorder(BorderFactory.createEmptyBorder(0,15,0,15));

        GridBagConstraints gbc = new GridBagConstraints();

        //Indique au joueur de choisir son nom
        joueur = new JLabel("Choisi ton pseudo", SwingConstants.CENTER);
        joueur.setFont(CustomFont.customFont28);
        joueur.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)


        //TextArea pour choisir un nom de joueur
        nomJoueur = new CustomJTextField("Ton pseudo");
        nomJoueur.setFont(CustomFont.customFont40);

        //Indique au joueur de choisir un nom pour son avatar
        avatar = new JLabel("Choisi le prénom de ton avatar", SwingConstants.CENTER);
        avatar.setFont(CustomFont.customFont28);
        avatar.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        //TextArea pour choisir un nom d'avatar
        nomAvatar = new CustomJTextField("Surnom de ton avatar");
        nomAvatar.setFont(CustomFont.customFont40);

        //Ajout des différents boutons au panneau du menu
        gbc.insets = new Insets(30, 0, 30, 0);

        gbc.weightx=1.;
        gbc.fill=GridBagConstraints.HORIZONTAL;

        gbc.gridx = 1;
        gbc.gridy = 1;
        menu.add(joueur, gbc);
        gbc.gridy = 2;
        menu.add(nomJoueur, gbc);
        gbc.gridy = 3;
        menu.add(avatar, gbc);
        gbc.gridy = 4;
        menu.add(nomAvatar, gbc);

        //BORDERLAYOUT.SOUTH
        //Contient l'affichage de l'image de l'avater et les flèches pour changer de type d'avatar
        JPanel sud = new JPanel();
        sud.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        sud.setOpaque(false);

        JPanel retourPanel =  new JPanel();
        retourPanel.setLayout(new GridLayout(3,1));
        retourPanel.setOpaque(false);

        JPanel validerPanel = new JPanel();
        validerPanel.setLayout(new GridLayout(3, 1));
        validerPanel.setOpaque(false);

        //Bouton pour valider la création du personnage
        valider = new CustomJButton("Valider", principale, this, null, null, null, null);

        //Bouton pour retourner à la sélection nouvelle partie / charger une partie

        retour = new CustomJButton("Retour", principale, null, null, null, "Chambre", null);
        retour.setPreferredSize(new Dimension(384, 96));

        //Bouton pour changer de type d'avatar vers la gauche String sensFleche, int x, int y, int longueur, int hauteur
        choixGauche = new BoutonFleche("Gauche", 96, 96);
        choixGauche.addActionListener(new ListenerBouton(this));

        //Bouton pour changer de type d'avatar vers la droite
        choixDroite = new BoutonFleche("Droite", 96, 96);
        choixDroite.addActionListener(new ListenerBouton(this));

        //Tableau contenant toutes les images possibles pour un avatar
        imagesAvatar = new ImageIcon[5];
        imagesAvatar[0] = new ImageIcon("Code/resources/tamagotchi/cat.gif");
        imagesAvatar[0].setImage(imagesAvatar[0].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        imagesAvatar[1] = new ImageIcon("Code/resources/tamagotchi/dog.gif");
        imagesAvatar[1].setImage(imagesAvatar[1].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        imagesAvatar[2] = new ImageIcon("Code/resources/tamagotchi/bird.gif");
        imagesAvatar[2].setImage(imagesAvatar[2].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        imagesAvatar[3] = new ImageIcon("Code/resources/tamagotchi/octopus.gif");
        imagesAvatar[3].setImage(imagesAvatar[3].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        imagesAvatar[4] = new ImageIcon("Code/resources/tamagotchi/robot.gif");
        imagesAvatar[4].setImage(imagesAvatar[4].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));

        position = 0;
        avatarChoisi = new JLabel(imagesAvatar[position]);

        //Ajout des différents éléments au panneau qui occupe le bas de l'écran

        retourPanel.add(new TransparentJPanel());
        retourPanel.add(retour);
        retourPanel.add(new TransparentJPanel());

        validerPanel.add(new TransparentJPanel());
        validerPanel.add(valider);
        validerPanel.add(new TransparentJPanel());

        c.insets = new Insets(5, 15, 5, 15);
        c.gridy = 1;
        c.gridx = 1;
        sud.add(retourPanel, c);
        c.gridx = 2;
        sud.add(choixGauche, c);
        c.gridx = 3;
        sud.add(avatarChoisi, c);
        c.gridx = 4;
        sud.add(choixDroite, c);
        c.gridx = 5;
        sud.add(validerPanel, c);

        //Ajout des composants au panneau d'accueil
        this.add(titre, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(menu, BorderLayout.CENTER);
        this.add(nouveautes, BorderLayout.EAST);
        this.add(sud, BorderLayout.SOUTH);

    }

    public void setDifficulty(String difficult) {
        difficulty = difficult;
    }

    /**
     * Permet de changer l'image de l'avatar lors du clic sur le bouton gauche ou droit
     * @param s - le String permettant de savoir si le joueur a cliqué sur le bouton gauche ou droite
     */
    public void actionSwitchAvatar(String s){
        if(s.equals("Gauche")){
            position--;
            if(this.position < 0){
                position = this.imagesAvatar.length - 1;
            }
        }else{
            position++;
            if(this.position > (this.imagesAvatar.length - 1)){
                position = 0;
            }
        }
        avatarChoisi.setIcon(imagesAvatar[position]);
    }

    /**
     * Renseigne sur le choix d'avatar du joueur à la validation
     */
    public void choixAvatar(){
        switch(this.position){
            case 1:
                monChoix = "Chien";
                break;

            case 2:
                monChoix = "Oiseau";
                break;

            case 3:
                monChoix = "Poulpe";
                break;

            case 4:
                monChoix = "Robot";
                break;

            default:
                monChoix = "Chat";
                break;

        }
    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}