package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.BoutonFleche;

import main.model.Jeu;

import java.awt.*;

/**
 * Créé un panneau chambre qui est un des environnements du jeu ainsi que le lieu de départ lors d'une nouvelle partie
 */
public class Chambre extends JPanel {

    public Jeu jeu;

    public static JButton dormir;
    public static JButton gauche;
    public static JButton droite;
    public static JButton options;
    public JLabel avatarChoisi;
    
    /**
     * Panneau qui contient les éléments nécessaires à la création de l'avatar
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public Chambre(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        this.jeu = principale.getJeu();

        //BORDERLAYOUT.NORTH
        //Affichage des statistiques et de l'heure
        JPanel nord = new JPanel();
        nord.setPreferredSize(new Dimension(1000, Toolkit.getDefaultToolkit().getScreenSize().height/4));
        nord.setLayout(new GridLayout(1, 3));
        nord.setOpaque(false);

        JPanel statistiques = new JPanel();
        statistiques.setBackground(Color.RED);

        JPanel vide1 = new JPanel();
        vide1.setOpaque(false);

        JPanel heure = new JPanel();
        heure.setOpaque(false);

        nord.add(statistiques);
        nord.add(vide1);
        nord.add(heure);
        

        //BORDERLAYOUT.WEST
        //Affichage du bouton fléché gauche pour changer d'environnement
        gauche = new BoutonFleche("Gauche", 150, 120);
        gauche.addActionListener(new ListenerBouton(principale));
        gauche.setSize(new Dimension(200, 150));

        //BORDERLAYOUT.EAST
        //Pas de possibilité d'aller à droite depuis la chambre, on ajoute un panel vide de la même taille que le bouton de gauche pour centrer le tout
        JPanel videDroite = new JPanel();
        videDroite.setPreferredSize(new Dimension(200, 150));
        videDroite.setOpaque(false);

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

        JPanel vide2 = new JPanel();
        vide2.setOpaque(false);

        JPanel options = new JPanel();
        options.setOpaque(false);

        sud.add(actions);
        sud.add(vide2);
        sud.add(options);

        //Ajout des éléments au panneau
        this.add(nord, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(avatarChoisi, BorderLayout.CENTER);
        this.add(videDroite, BorderLayout.EAST);
        this.add(sud, BorderLayout.SOUTH);
    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - 
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("resources/background/chambre.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
