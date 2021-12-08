package main.view;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import main.controler.ListenerBouton;

import javax.swing.ImageIcon;

import java.awt.*;

public class Options extends JPanel{

    public JLabel titre;
    public static JButton retour;
    public JLabel langue;
    public ButtonGroup choixLangue;

    public Options(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        //BORDERLAYOUT.NORTH
        //Ajout du titre de la page
        titre = new JLabel("Options du jeu", SwingConstants.CENTER);
        titre.setFont(new Font("Comic sans MS", Font.BOLD, 50));
        titre.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        //BORDERLAYOUT.WEST
        //Ajout d'un panel vide à gauche 
        JPanel gauche = new JPanel();
        gauche.setPreferredSize(new Dimension(500, 0));
        gauche.setOpaque(false);

        //BORDERLAYOUT.EAST
        //Ajout d'un panel vide à droite
        JPanel droite = new JPanel();
        droite.setPreferredSize(new Dimension(500,0));
        droite.setOpaque(false);

        //BORDERLAYOUT.CENTER
        //Ajout d'un panel qui va stocker les différents éléments du panneau
        JPanel options = new JPanel();
        options.setLayout(new GridBagLayout());
        options.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        //Indique au joueur de choisir son nom
        langue = new JLabel("Choix de la langue", SwingConstants.CENTER);
        langue.setFont(new Font("Comic sans ms", Font.PLAIN, 40));
        langue.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        //TextArea pour choisir un nom de joueur
        choixLangue = new ButtonGroup();

        JRadioButton fr = new JRadioButton("Français");
        JRadioButton en = new JRadioButton("English");

        choixLangue.add(fr);
        choixLangue.add(en);

        //Ajout des différents boutons au panneau des options
        gbc.insets = new Insets(30, 0, 30, 0);
        gbc.gridx = 1;
        options.add(langue, gbc);
        gbc.gridy = 1;
        options.add(fr, gbc);
        gbc.gridx++;
        options.add(en, gbc);

        //BORDERLAYOUT.SOUTH
        //Ajout du bouton retour pour revenir à la page d'accueil
        retour = new JButton("Retour");
        retour.addActionListener(new ListenerBouton(principale));
        retour.setPreferredSize(new Dimension(300, 80));
        retour.setHorizontalTextPosition(JButton.CENTER);    //Permet d'afficher le texte sur l'image et pas à droite (par défaut)
        retour.setFont(new Font("Serif", Font.BOLD, 50));
        retour.setForeground(Color.WHITE);

        //Ajout des composants au panneau
        this.add(titre, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(droite, BorderLayout.EAST);
        this.add(options, BorderLayout.CENTER);
        this.add(retour, BorderLayout.SOUTH);

    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/options2.jpg").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
