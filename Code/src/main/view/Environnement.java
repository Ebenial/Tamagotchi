package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.model.Lieu;
import main.util.BoutonFleche;

import main.model.Jeu;
import main.util.CustomFont;
import main.util.TimerPanel;
import main.util.TransparentJPanel;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Vue de la partie en cours, affiche le lieu dans lequel
 * se trouve l'avatar
 */
public class Environnement extends JPanel implements KeyListener{
    private final Jeu jeu;

    private Lieu lieu;
    public static BoutonFleche gauche;
    public static BoutonFleche droite;
    public static JButton options;
    public static JButton action1;

    private static final int AVATAR_X_OFFSET = 200;
    private static final int AVATAR_Y_OFFSET = 200;

    private final JLabel avatarChoisi;
    private final JLabel sante;
    private final JLabel bonheur;
    private final JLabel nourriture;
    private final JLabel energie;
    private final JLabel hygiene;
    private final JLabel divertissement;
    private final TimerPanel timerPanel;

    /**
     * Initialise la vue
     *
     * @param lieu - lieu actuel
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public Environnement(Lieu lieu, FenetrePrincipale principale){
        this.lieu = lieu;

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
        JPanel statistiquesPart1 = new JPanel();
        statistiquesPart1.setOpaque(false);
        statistiquesPart1.setLayout(new BoxLayout(statistiquesPart1, BoxLayout.Y_AXIS));

        JPanel statistiquesPart2 = new JPanel();
        statistiquesPart2.setOpaque(false);
        statistiquesPart2.setLayout(new BoxLayout(statistiquesPart2, BoxLayout.Y_AXIS));


        //Ajout d'un panneau qui va afficher les icônes des statistiques
        JPanel imagesStatsPart1 = new JPanel();
        imagesStatsPart1.setOpaque(false);
        imagesStatsPart1.setLayout(new GridLayout(2, 1));
        imagesStatsPart1.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));

        //Ajout d'un panneau qui va afficher les barres de statistiques
        JPanel infosStatsPart1 = new JPanel();
        infosStatsPart1.setOpaque(false);
        infosStatsPart1.setLayout(new GridLayout(2, 1));
        infosStatsPart1.setBorder(BorderFactory.createEmptyBorder(0,0,30,0));

        //Ajout d'un panneau qui va afficher les icônes des statistiques
        JPanel imagesStatsPart2 = new JPanel();
        imagesStatsPart2.setOpaque(false);
        imagesStatsPart2.setLayout(new GridLayout(4, 1));

        //Ajout d'un panneau qui va afficher les barres de statistiques
        JPanel infosStatsPart2 = new JPanel();
        infosStatsPart2.setOpaque(false);
        infosStatsPart2.setLayout(new GridLayout(4, 1));



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
        imagesStatsPart1.add(imageSante);
        infosStatsPart1.add(sante);
        imagesStatsPart1.add(imageBonheur);
        infosStatsPart1.add(bonheur);
        imagesStatsPart2.add(imageNourriture);
        infosStatsPart2.add(nourriture);
        imagesStatsPart2.add(imageEnergie);
        infosStatsPart2.add(energie);
        imagesStatsPart2.add(imageHygiene);
        infosStatsPart2.add(hygiene);
        imagesStatsPart2.add(imageDivertissement);
        infosStatsPart2.add(divertissement);

        //Ajout des panneaux contenant les images et les barres de statistiques au panneau global qui contient toutes les statistiques
        statistiquesPart1.add(imagesStatsPart1);
        statistiquesPart2.add(infosStatsPart1);

        statistiquesPart1.add(imagesStatsPart2);
        statistiquesPart2.add(infosStatsPart2);

        this.timerPanel = new TimerPanel(principale);

        //Ajout du panneau des stats dans le coin gauche de l'écran
        cNord.insets = new Insets(1,10,1,10);

        cNord.gridx = 0;
        cNord.gridy = 0;
        nord.add(statistiquesPart1, cNord);

        cNord.gridx = 1;
        nord.add(statistiquesPart2, cNord);

        cNord.gridx = 2;
        cNord.weightx = 1.0;
        nord.add(new TransparentJPanel(), cNord);

        cNord.gridx =3;
        cNord.weightx = 0.25;
        nord.add(timerPanel, cNord);

        // BORDERLAYOUT.WEST et EAST
        // Création des flèches
        gauche = new BoutonFleche("Gauche", 98, 98);
        gauche.setSize(new Dimension(98, 98));

        droite = new BoutonFleche("Droite", 98, 98);
        droite.setSize(new Dimension(98, 98));

        //BORDERLAYOUT.CENTER
        //Affichage de l'avatar
        String typeAvatar;
        switch (jeu.getAvatar().getType()) {
            case "Chien" : {
                typeAvatar = "dog";
                break;
            }
            case "Chat" : {
                typeAvatar = "cat";
                break;
            }
            case "Oiseau" : {
                typeAvatar = "bird";
                break;
            }
            case "Poulpe" : {
                typeAvatar = "octopus";
                break;
            }
            default : {
                typeAvatar = "robot";
                break;
            }
        }

        ImageIcon imageAvatar = new ImageIcon(new ImageIcon("Code/resources/tamagotchi/" + typeAvatar + ".gif").getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT));
        this.avatarChoisi = new JLabel(imageAvatar);
        this.avatarChoisi.setBorder(BorderFactory.createEmptyBorder(AVATAR_Y_OFFSET, 0, 0, 0));

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
        action1.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        action1.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        action1.setVerticalAlignment(JButton.CENTER);
        action1.setFont(CustomFont.customFont50_PLAIN);
        action1.setContentAreaFilled(false);
        action1.setIcon(new ImageIcon(new ImageIcon("Code/resources/environnement/action_button_available.png").getImage().getScaledInstance(270, 115, java.awt.Image.SCALE_SMOOTH)));

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

        this.add(gauche, BorderLayout.WEST);
        this.add(droite, BorderLayout.EAST);

        this.changerLieu(principale, lieu);
    }

    private void replaceButtonListener(JButton button, ListenerBouton newListener) {
        for (ActionListener al : button.getActionListeners()) {
            button.removeActionListener(al);
        }
        button.addActionListener(newListener);
    }

    /**
     * Initialise les boutons d'un lieu. Utilisé lors du changement de lieu
     * @param lieu - nouveau lieu
     */
    public void changerLieu(FenetrePrincipale principale, Lieu lieu) {
        this.lieu = lieu;

        //BORDERLAYOUT.WEST
        //Affichage du bouton fléché gauche pour changer d'environnement
        if(this.lieu != Lieu.JOUER){
            gauche.activerFleche();
            this.replaceButtonListener(gauche, new ListenerBouton(this.lieu, "Gauche", principale));
        }else{
            gauche.desactiverFleche();
        }

        //BORDERLAYOUT.EAST
        //Pas de possibilité d'aller à droite depuis la chambre, on ajoute un panel vide de la même taille que le bouton de gauche pour centrer le tout
        if(this.lieu != Lieu.CHAMBRE){
            droite.activerFleche();
            this.replaceButtonListener(droite, new ListenerBouton(this.lieu, "Droite", principale));
        }else{
            droite.desactiverFleche();
        }

        // Change l'action
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
        this.replaceButtonListener(action1, new ListenerBouton(this.lieu, "Action1", principale));

        // Position de l'avatar en fonction du lieu
        int avatarXOff = AVATAR_X_OFFSET;
        switch (lieu) {
            case CHAMBRE:
                this.avatarChoisi.setHorizontalAlignment(JLabel.RIGHT);
                break;

            case MANGER:
                this.avatarChoisi.setHorizontalAlignment(JLabel.LEFT);
                avatarXOff -= gauche.getWidth(); // compense la place que prend le bouton
                avatarXOff -= 30; // décale à gauche pour le positionner sur le tapis
                break;

            case LAVER:
                this.avatarChoisi.setHorizontalAlignment(JLabel.LEFT);
                avatarXOff -= gauche.getWidth(); // compense la place que prend le bouton
                break;

            case JOUER:
                this.avatarChoisi.setHorizontalAlignment(JLabel.LEFT);
                avatarXOff += 150; // décale à droite pour le positionner à côté du ballon
                break;

            default:
        }
        this.avatarChoisi.setBorder(BorderFactory.createEmptyBorder(AVATAR_Y_OFFSET, avatarXOff, 0, avatarXOff));
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

        this.repaint();
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

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}

    public TimerPanel getTimerPanel() {
        return this.timerPanel;
    }

    public void actionState(boolean state){
        if (state){
            action1.setIcon(new ImageIcon(new ImageIcon("Code/resources/environnement/action_button_available.png").getImage().getScaledInstance(270, 115, java.awt.Image.SCALE_SMOOTH)));
        }else{
            action1.setIcon(new ImageIcon(new ImageIcon("Code/resources/environnement/action_button.png").getImage().getScaledInstance(270, 115, java.awt.Image.SCALE_SMOOTH)));
        }
    }

}
