package main.view;

import javax.swing.*;

import main.controler.ListenerRadioButton;
import main.util.CustomFont;
import main.util.CustomJButton;

import java.awt.*;

public class Options extends JPanel{

    public JLabel titre;
    public static CustomJButton retour;
    public JLabel soundOn;
    public static JRadioButton r1;
    public static JRadioButton r2;

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


        soundOn = new JLabel("Musique : ", SwingConstants.CENTER);
        soundOn.setFont(CustomFont.customFont28);
        soundOn.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        // créer une case à cocher avec une icône
        r1 = new JRadioButton("On" ,/*, new ImageIcon("C:/male.png"), */true);
        r2 = new JRadioButton("Off", false);
        r1.setOpaque(false);
        r2.setOpaque(false);
        r1.addItemListener(new ListenerRadioButton(principale));
        r2.addItemListener(new ListenerRadioButton(principale));
        r1.setFont(CustomFont.customFont18);
        r2.setFont(CustomFont.customFont18);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(r1);
        buttonGroup.add(r2);


        //Ajout des différents boutons au panneau des options
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.gridx = 1;
        options.add(soundOn, gbc);
        gbc.gridx = 2;
        options.add(r1, gbc);
        gbc.gridx = 3;
        options.add(r2, gbc);


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
        constraints.insets = new Insets(50, 0, 50, 0);
        constraints.gridx = 0;
        constraints.gridy = 0;
        buttonPanel.add(leftBox, constraints);
        constraints.gridx = 1;
        buttonPanel.add(retour, constraints);
        constraints.gridx = 2;
        buttonPanel.add(rightBox, constraints);


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
