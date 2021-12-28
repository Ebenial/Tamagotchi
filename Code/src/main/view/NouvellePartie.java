package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.BoutonFleche;
import main.util.CustomFont;
import main.util.CustomJButton;
import main.util.TransparentJPanel;

import java.awt.*;

/**
 * Panneau de création de l'avatar du joueur
 */
public class NouvellePartie extends JPanel{

    public JLabel creation;
    public JLabel joueur;
    public static JTextArea nomJoueur;
    public JLabel avatar;
    public static JTextArea nomAvatar;
    public static JButton choixGauche;
    public static JButton choixDroite;
    public static CustomJButton valider;
    public static CustomJButton retour;
    public ImageIcon[] imagesAvatar;
    public JLabel avatarChoisi;
    public static String monChoix;
    public int position;
    
    /**
     * Panneau qui contient les éléments nécessaires à la création de l'avatar
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public NouvellePartie(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        //BORDERLAYOUT.NORTH
        //Titre de la page et paramétrage
        creation = new JLabel("Nouvel avatar", SwingConstants.CENTER);
        creation.setFont(CustomFont.customFont100);
        creation.setForeground(Color.white);
        creation.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

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
        menu.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        //Indique au joueur de choisir son nom
        joueur = new JLabel("Je m'appelle", SwingConstants.CENTER);
        joueur.setFont(CustomFont.customFont40);
        joueur.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        //TextArea pour choisir un nom de joueur
        nomJoueur = new JTextArea("Mon nom de joueur");
        nomJoueur.setPreferredSize(new Dimension(600, 150));
        nomJoueur.setFont(CustomFont.customFont50);

        //Indique au joueur de choisir un nom pour son avatar
        avatar = new JLabel("Mon avatar va s'appeler", SwingConstants.CENTER);
        avatar.setFont(CustomFont.customFont28);
        avatar.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        //TextArea pour choisir un nom d'avatar
        nomAvatar = new JTextArea("Nom de mon avatar");
        nomAvatar.setPreferredSize(new Dimension(600, 150));
        nomAvatar.setFont(CustomFont.customFont50);

        //Ajout des différents boutons au panneau du menu
        gbc.insets = new Insets(30, 0, 30, 0);
        gbc.gridx = 1;
        menu.add(joueur, gbc);
        gbc.gridy = 1;
        menu.add(nomJoueur, gbc);
        gbc.gridy = 2;
        menu.add(avatar, gbc);
        gbc.gridy = 3;
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
        this.add(creation, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(menu, BorderLayout.CENTER);
        this.add(nouveautes, BorderLayout.EAST);
        this.add(sud, BorderLayout.SOUTH);

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