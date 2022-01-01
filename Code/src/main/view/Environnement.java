package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.model.BoucleJeu;
import main.model.Lieu;
import main.util.BoutonFleche;

import main.model.Jeu;
import main.util.CustomFont;
import main.util.TimerPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Créé un panneau chambre qui est un des environnements du jeu ainsi que le lieu de départ lors d'une nouvelle partie
 */
public class Environnement extends JPanel implements KeyListener{
    private Jeu jeu;
    private BoucleJeu boucleJeu;

    private final Lieu lieu;
    public static JButton gauche;
    public static JButton droite;
    public static JButton options;
    public static JButton action1;
    public JLabel avatarChoisi;
    private final JLabel sante;
    private final JLabel bonheur;
    private final JLabel nourriture;
    private final JLabel energie;
    private final JLabel hygiene;
    private final JLabel divertissement;

    /**
     * Panneau qui contient les éléments nécessaires à la création de l'avatar
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public Environnement(Lieu lieu, FenetrePrincipale principale){
        this.lieu = lieu;
        this.boucleJeu = principale.getBoucle();

        this.setLayout(new BorderLayout());

        this.jeu = principale.getJeu();

        //BORDERLAYOUT.NORTH
        //Affichage des statistiques et de l'heure
        JPanel nord = new JPanel();
        nord.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height/4));
        nord.setLayout(new GridBagLayout());
        nord.setOpaque(false);

        GridBagConstraints cNord = new GridBagConstraints();

        //Configuration du panneau des statistiques de l'avatar
        JPanel statistiques = new JPanel();
        statistiques.setOpaque(false);
        statistiques.setLayout(new GridLayout(1,1));

        //Ajout d'un panneau qui va afficher les icônes des statistiques
        JPanel imagesStats = new JPanel();
        imagesStats.setOpaque(false);
        imagesStats.setLayout(new GridLayout(6, 1));
    
        //Ajout d'un panneau qui va afficher les barres de statistiques
        JPanel infosStats = new JPanel();
        infosStats.setOpaque(false);
        infosStats.setLayout(new GridLayout(6, 1));



        //Construction des différents éléments des statistiques
        JLabel imageSante = new JLabel(new ImageIcon(new ImageIcon("Code/resources/environnement/health_icon.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
        sante = new JLabel(jeu.choixBarreStats(jeu.getAvatar().getSante()));
        JLabel imageBonheur = new JLabel(new ImageIcon(new ImageIcon("Code/resources/environnement/happiness_icon.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
        bonheur = new JLabel(jeu.choixBarreStats(jeu.getAvatar().getBonheur()));
        JLabel imageNourriture = new JLabel(new ImageIcon(new ImageIcon("Code/resources/environnement/food_icon.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
        nourriture = new JLabel(jeu.choixBarreStats(jeu.getAvatar().getNourriture()));
        JLabel imageEnergie = new JLabel(new ImageIcon(new ImageIcon("Code/resources/environnement/energy_icon.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
        energie = new JLabel(jeu.choixBarreStats(jeu.getAvatar().getEnergie()));
        JLabel imageHygiene = new JLabel(new ImageIcon(new ImageIcon("Code/resources/environnement/hygien_icon.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
        hygiene = new JLabel(jeu.choixBarreStats(jeu.getAvatar().getHygiene()));
        JLabel imageDivertissement = new JLabel(new ImageIcon(new ImageIcon("Code/resources/environnement/entertainment_icon.png").getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT)));
        divertissement = new JLabel(jeu.choixBarreStats(jeu.getAvatar().getDivertissement()));

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
        statistiques.add(imagesStats);
        statistiques.add(infosStats);

        JPanel vide = new JPanel();
        vide.setPreferredSize(new Dimension((Toolkit.getDefaultToolkit().getScreenSize().width)*3/4, 100));
        vide.setOpaque(false);

        TimerPanel timerPanel = new TimerPanel();

        //Ajout du panneau des stats dans le coin gauche de l'écran
        nord.add(statistiques);

        cNord.gridx = 0;
        cNord.gridy = 0;
        cNord.weightx = 0.25;

        nord.add(imagesStats);

        cNord.gridx = 1;
        nord.add(infosStats);

        cNord.gridx = 2;
        cNord.weightx = 0.5;
        nord.add(timerPanel);


        //BORDERLAYOUT.WEST
        //Affichage du bouton fléché gauche pour changer d'environnement
        if(this.lieu != Lieu.JOUER){
            gauche = new BoutonFleche("Gauche", 98, 98);
            gauche.addActionListener(new ListenerBouton(this.lieu, "Gauche", principale));
            gauche.setSize(new Dimension(98, 98));
            this.add(gauche, BorderLayout.WEST);
        }else{
            JPanel gaucheVide = new JPanel();
            gaucheVide.setPreferredSize(new Dimension(98, 98));
            gaucheVide.setOpaque(false);
            this.add(gaucheVide, BorderLayout.WEST);
        }

        //BORDERLAYOUT.EAST
        //Pas de possibilité d'aller à droite depuis la chambre, on ajoute un panel vide de la même taille que le bouton de gauche pour centrer le tout
        if(this.lieu != Lieu.CHAMBRE){
            droite = new BoutonFleche("Droite", 98, 98);
            droite.addActionListener(new ListenerBouton(this.lieu, "Droite", principale));
            droite.setSize(new Dimension(98, 98));
            this.add(droite, BorderLayout.EAST);
        }else{
            JPanel droiteVide = new JPanel();
            droiteVide.setPreferredSize(new Dimension(98, 98));
            droiteVide.setOpaque(false);
            this.add(droiteVide, BorderLayout.EAST);
        }

        //BORDERLAYOUT.CENTER
        //Affichage de l'avatar
        switch (jeu.getAvatar().getType()) {
            case "Chien" : {
                ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/dog.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
                avatarChoisi = new JLabel(imageAvatar);
                break;
            }
            case "Chat" : {
                ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/cat.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
                avatarChoisi = new JLabel(imageAvatar);
                break;
            }
            case "Oiseau" : {
                ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/bird.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
                avatarChoisi = new JLabel(imageAvatar);
                break;
            }
            case "Poulpe" : {
                ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/octopus.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
                avatarChoisi = new JLabel(imageAvatar);
                break;
            }
            default : {
                ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/robot.gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
                avatarChoisi = new JLabel(imageAvatar);
                break;
            }
        }

        //BORDERLAYOUT.SOUTH
        //Affichage des actions possibles pour le joueur ainsi que la roue des options
        JPanel sud = new JPanel();
        sud.setOpaque(false);
        sud.setPreferredSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height/6));
        sud.setLayout(new GridBagLayout());
        GridBagConstraints cSud = new GridBagConstraints();



        JPanel actions = new JPanel();
        actions.setOpaque(false);


        action1 = new JButton("Action1");

        String actionText = "";

        switch (this.lieu){
            case CHAMBRE: {
                actionText = "Dormir";
                break;
            }

            case LAVER: {
                actionText = "Laver";
                break;

            }

            case MANGER: {
                actionText = "Manger";
                break;
            }

            case JOUER:{
                actionText = "Jouer";
            }
        }
        action1.setText(actionText);
        action1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        action1.addActionListener(new ListenerBouton(this.lieu, "Action1", principale));
        action1.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        action1.setVerticalAlignment(JButton.CENTER);
        action1.setFont(CustomFont.customFont50_PLAIN);
        action1.setContentAreaFilled(false);
        action1.setIcon(new ImageIcon(new ImageIcon("Code/resources/environnement/action_button.png").getImage().getScaledInstance(270, 115, java.awt.Image.SCALE_SMOOTH)));

        actions.add(action1);

        JPanel vide3 = new JPanel();
        vide3.setPreferredSize(new Dimension((Toolkit.getDefaultToolkit().getScreenSize().width)*3/4, 100));
        vide3.setOpaque(false);
        options = new JButton();
        options.addActionListener(new ListenerBouton(this.lieu, "Options", principale));
        options.setContentAreaFilled(false);
        options.setFocusPainted(false);
        options.setBorderPainted(false);
        options.setIcon(new ImageIcon(new ImageIcon("Code/resources/others/settings_icon.png").getImage().getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH)));
        options.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));

        cSud.gridx = 0;
        cSud.gridy = 0;
        cSud.weightx = 0.25;

        sud.add(actions);

        cSud.gridx = 1;
        cSud.weightx = 0.5;
        sud.add(vide3);

        cSud.gridx = 2;
        cSud.weightx = 0.25;
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

        switch (this.lieu) {
            case JOUER:
                g.drawImage(new ImageIcon("Code/resources/background/lieu_jouer.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
                break;
            case MANGER:
                g.drawImage(new ImageIcon("Code/resources/background/lieu_manger.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
                break;
            case LAVER:
                g.drawImage(new ImageIcon("Code/resources/background/lieu_laver.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
                break;
            case CHAMBRE: {
                String image = this.jeu.isDay() ? "jour" : "nuit";
                g.drawImage(new ImageIcon("Code/resources/background/lieu_" + image + ".png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
                break;
            }
        }
    }

    public JLabel getSante() {
        return sante;
    }

    public JLabel getBonheur() {
        return bonheur;
    }

    public JLabel getEnergie() {
        return energie;
    }

    public JLabel getHygiene() {
        return hygiene;
    }

    public JLabel getDivertissement() {
        return divertissement;
    }

    public JLabel getNourriture() {
        return nourriture;
    }

    public Lieu getLieu(){return this.lieu;}

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

}
