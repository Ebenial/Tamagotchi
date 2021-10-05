package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.BoutonFleche;

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
    public static JButton valider;
    public static JButton retour;
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
        creation.setFont(new Font("Comic sans ms", Font.BOLD, 100));
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
        joueur.setFont(new Font("Comic sans ms", Font.PLAIN, 40));
        joueur.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        //TextArea pour choisir un nom de joueur
        nomJoueur = new JTextArea("Mon nom de joueur");
        nomJoueur.setPreferredSize(new Dimension(600, 150));
        nomJoueur.setFont(new Font("Comic sans ms", Font.BOLD, 50));

        //Indique au joueur de choisir un nom pour son avatar
        avatar = new JLabel("Mon avatar va s'appeler", SwingConstants.CENTER);
        avatar.setFont(new Font("Comic sans ms", Font.PLAIN, 28));
        avatar.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        //TextArea pour choisir un nom d'avatar
        nomAvatar = new JTextArea("Nom de mon avatar");
        nomAvatar.setPreferredSize(new Dimension(600, 150));
        nomAvatar.setFont(new Font("Comic sans ms", Font.BOLD, 50));

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
        sud.setLayout(new GridLayout(1, 5));
        sud.setOpaque(false);

        //Bouton pour valider la création du personnage
        valider = new JButton("Valider");
        valider.addActionListener(new ListenerBouton(principale, this));
        valider.setPreferredSize(new Dimension(600, 150));
        valider.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        valider.setFont(new Font("Comic sans ms", Font.BOLD, 50));

        //Bouton pour retourner à la sélection nouvelle partie / charger une partie
        retour = new JButton("Retour");
        retour.addActionListener(new ListenerBouton("Chambre", principale));
        retour.setPreferredSize(new Dimension(600, 150));
        retour.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        retour.setFont(new Font("Comic sans ms", Font.BOLD, 50));

        //Bouton pour changer de type d'avatar vers la gauche String sensFleche, int x, int y, int longueur, int hauteur
        choixGauche = new BoutonFleche("Gauche", 150, 120);
        choixGauche.addActionListener(new ListenerBouton(this));

        //Bouton pour changer de type d'avatar vers la droite
        choixDroite = new BoutonFleche("Droite", 150, 120);
        choixDroite.addActionListener(new ListenerBouton(this));

        //Tableau contenant toutes les images possibles pour un avatar
        imagesAvatar = new ImageIcon[5];
        imagesAvatar[0] = new ImageIcon("resources/tamagotchi/chat.gif");
        imagesAvatar[0].setImage(imagesAvatar[0].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        imagesAvatar[1] = new ImageIcon("resources/tamagotchi/chien.gif");
        imagesAvatar[1].setImage(imagesAvatar[1].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        imagesAvatar[2] = new ImageIcon("resources/tamagotchi/oiseau.gif");
        imagesAvatar[2].setImage(imagesAvatar[2].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        imagesAvatar[3] = new ImageIcon("resources/tamagotchi/poulpe.gif");
        imagesAvatar[3].setImage(imagesAvatar[3].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        imagesAvatar[4] = new ImageIcon("resources/tamagotchi/robot.gif");
        imagesAvatar[4].setImage(imagesAvatar[4].getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));

        position = 0;
        avatarChoisi = new JLabel(imagesAvatar[position]);

        //Ajout des différents éléments au panneau qui occupe le bas de l'écran
        sud.add(retour);
        sud.add(choixGauche);
        sud.add(avatarChoisi);
        sud.add(choixDroite);
        sud.add(valider);

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
        if(s == "Gauche"){
            position--;
            if(this.position < 0){
                position = this.imagesAvatar.length - 1;
            }
            avatarChoisi.setIcon(imagesAvatar[position]);
        }else{
            position++;
            if(this.position > (this.imagesAvatar.length - 1)){
                position = 0;
            }
            avatarChoisi.setIcon(imagesAvatar[position]);
        }
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
        g.drawImage(new ImageIcon("resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}