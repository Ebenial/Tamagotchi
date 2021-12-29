package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.CustomFont;
import main.util.CustomJButton;

import java.awt.*;

public class Options extends JPanel{

    public JLabel titre;
    public static CustomJButton retour;
    public JLabel langue;
    public static JButton sauvegarde;
    public ButtonGroup choixLangue;

    public Options(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());


        //BORDERLAYOUT.NORTH
        //Ajout du titre de la page
        titre = new JLabel("Options du jeu", SwingConstants.CENTER);
        titre.setFont(CustomFont.customFont50_PLAIN);
        titre.setBorder(BorderFactory.createEmptyBorder(45,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)
        titre.setForeground(Color.white);

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
        options.setBackground(new Color(255, 255, 255, 161));

        GridBagConstraints gbc = new GridBagConstraints();

        //Indique au joueur de choisir son nom
        langue = new JLabel("Choix de la langue", SwingConstants.CENTER);
        langue.setFont(CustomFont.customFont40);
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

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setOpaque(false);

        retour = new CustomJButton("Retour", principale, null, null, null, null, null);
        retour.setPreferredSize(new Dimension(384, 96));

        JPanel leftBox = new JPanel();
        leftBox.setPreferredSize(new Dimension(500,0));
        JPanel rightBox = new JPanel();
        rightBox.setPreferredSize(new Dimension(500,0));

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(0, 0, 50, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        buttonPanel.add(leftBox, constraints);
        constraints.gridx = 1;
        buttonPanel.add(retour, constraints);
        constraints.gridx = 2;
        buttonPanel.add(rightBox, constraints);

        //Ajout d'un action listener sur le bouton de sauvegarde
        sauvegarde = new JButton("Sauvegarder la partie");
        sauvegarde.addActionListener(new ListenerBouton(principale));
        gbc.gridy = 2;
        options.add(sauvegarde, gbc);

        //Ajout des composants au panneau
        this.add(titre, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(droite, BorderLayout.EAST);
        this.add(options, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);

    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
