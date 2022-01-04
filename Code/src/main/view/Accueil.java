package main.view;

import javax.swing.*;

import main.util.CustomFont;
import main.util.CustomJButton;
import main.util.TransparentJPanel;

import java.awt.*;

/**
 * Ecran d'accueil lors du lancement du programme
 */
public class Accueil extends JPanel {

    public static CustomJButton jouer;
    public static CustomJButton regles;
    public static CustomJButton options;
    public static CustomJButton quitter;
    public static JLabel signature;

    /**
     * Créé le panneau d'accueil
     *
     * @param principale - la JFrame a laquelle on applique le panneau
     */
    public Accueil(FenetrePrincipale principale) {

        this.setLayout(new BorderLayout());

        // BORDERLAYOUT.NORTH
        // Logo titre
        JLabel logoTitre = new JLabel("", SwingConstants.CENTER);
        logoTitre.setIcon(new ImageIcon("Code/resources/others/keneil_logo_accueil.png"));
        logoTitre.setBorder(BorderFactory.createEmptyBorder(10, 0, 25, 0));

        //BORDERLAYOUT.WEST
        //Ajout d'un panel vide à gauche 
        JPanel gauche = new JPanel();
        gauche.setPreferredSize(new Dimension(500, 0));
        gauche.setOpaque(false);

        //BORDERLAYOUT.EAST
        JPanel droite = new JPanel();
        droite.setLayout(new GridBagLayout());
        droite.setPreferredSize(new Dimension(500, 0));
        droite.setOpaque(false);

        TransparentJPanel droitePart2 = new TransparentJPanel();
        droitePart2.setLayout(new GridLayout(2,1));

        String score = principale.getSauvegardes().readBestScore();
        long sc = Long.parseLong(score);
        int sec = (int) sc % 60;
        int min = (int) (sc / 60) % 60;
        int hours = (int) ((sc / 60) / 60)%24;
        int days = (int) (((sc / 60) / 60) / 24);

        String strSec = (sec < 10) ? "0" + sec : Integer.toString(sec);
        String strmin = (min < 10) ? "0" + min : Integer.toString(min);
        String strHours = (hours < 10) ? "0" + hours : Integer.toString(hours);
        String strDays = (days < 10) ? "0" + days : Integer.toString(days);

        //Ajout du label Nouveautés pour le panel des nouveautés
        JLabel titreMeilleurScore = new JLabel("Meilleur Score : ", SwingConstants.CENTER);

        titreMeilleurScore.setFont(CustomFont.customFont28);
        titreMeilleurScore.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        titreMeilleurScore.setForeground(Color.WHITE);

        //Première nouveauté de la liste
        JLabel meilleurScore = new JLabel(strDays + "j | " + strHours + "h | " + strmin + "m | " + strSec+"s");
        meilleurScore.setFont(CustomFont.customFont18);
        meilleurScore.setForeground(Color.WHITE);

        droitePart2.add(titreMeilleurScore);
        droitePart2.add(meilleurScore);
        droite.add(droitePart2);

        //BORDERLAYOUT.CENTER
        //Ajout d'un panel qui va stocker les différents boutons du menu
        JPanel menu = new JPanel();
        menu.setLayout(new GridBagLayout());
        menu.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();

        //Bouton qui ouvre le dossier des sauvegardes
        jouer = new CustomJButton("Jouer", principale, null, null, null, null, null);

        //Bouton qui mène à la page des règles du jeu
        regles = new CustomJButton("Règles", principale, null, null, null, null, null);

        //Bouton qui mène à la page des options
        options = new CustomJButton("Options", principale, null, null, null, null, null);

        //Bouton pour quitter le programme
        quitter = new CustomJButton("Quitter", principale, null, null, null, null, null);

        //BORDERLAYOUT.SOUTH
        //Ajout de la signature en bas de fenêtre
        signature = new JLabel("D\u00E9velopp\u00e9 par ASPYG", SwingConstants.RIGHT);
        signature.setForeground(Color.WHITE);
        signature.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 10));

        //Ajout des différents boutons au panneau du menu
        gbc.insets = new Insets(20, 30, 20, 30);

        gbc.gridy = 1;
        menu.add(jouer, gbc);
        gbc.gridy = 2;
        menu.add(options, gbc);

        gbc.gridy = 3;
        menu.add(regles, gbc);
        gbc.gridy = 4;
        menu.add(quitter, gbc);

        //Ajout des composants au panneau d'accueil
        this.add(logoTitre, BorderLayout.NORTH);
        this.add(gauche, BorderLayout.WEST);
        this.add(menu, BorderLayout.CENTER);
        this.add(droite, BorderLayout.EAST);
        this.add(signature, BorderLayout.SOUTH);
    }

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     *
     * @param g -
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}