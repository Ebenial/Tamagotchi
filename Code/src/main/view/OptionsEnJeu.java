package main.view;

import main.controler.ListenerRadioButton;
import main.util.CustomFont;
import main.util.CustomJButton;
import main.util.CustomJPanel;

import javax.swing.*;
import java.awt.*;

public class OptionsEnJeu extends JPanel{

    public JLabel titre;
    public JLabel soundOn;
    public static CustomJButton retour;
    public static CustomJButton retourAuMenu;
    public static CustomJButton sauvegarde;
    public static JRadioButton r1;
    public static JRadioButton r2;

    public OptionsEnJeu(FenetrePrincipale principale){
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

        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        menu.setOpaque(false);
        GridBagConstraints constraints = new GridBagConstraints();

        //******************************************************************
        //OPTIONS DE SON

        CustomJPanel options = new CustomJPanel(new ImageIcon("Code/resources/others/button_background_large.png").getImage(), new Dimension(new Dimension(552,96)));
        options.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        soundOn = new JLabel("Musique : ", SwingConstants.CENTER);
        soundOn.setFont(CustomFont.customFont28);
        soundOn.setBorder(BorderFactory.createEmptyBorder(10,0,10,0));    //Bordure autour du texte (haut, gauche, bas, droite)

        // créer une case à cocher avec une icône
        r1 = new JRadioButton("On",true);
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


        //******************************************************************

        sauvegarde = new CustomJButton("Sauvegarder", principale, null, "Code/resources/others/button_background_large.png", null, null, null);
        retourAuMenu = new CustomJButton("Retour au menu", principale, null, "Code/resources/others/button_background_large.png", null, null, null);
        retour = new CustomJButton("Retour", principale, null, "Code/resources/others/button_background_large.png", null,null, null);

        constraints.insets = new Insets(15,0,15,0);
        constraints.gridx = 1;
        constraints.gridy = 1;
        menu.add(options, constraints);
        constraints.gridy = 2;
        menu.add(retourAuMenu, constraints);
        constraints.gridy = 3;
        menu.add(sauvegarde, constraints);
        constraints.gridy = 4;
        menu.add(retour, constraints);

        //Ajout des composants au panneau
        this.add(titre, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(droite, BorderLayout.EAST);
        this.add(menu, BorderLayout.CENTER);
    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
