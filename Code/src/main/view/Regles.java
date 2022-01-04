package main.view;

import javax.swing.*;

import main.util.CustomFont;
import main.util.CustomJButton;

import java.awt.*;

/**
 * Affiche les règles du jeu
 */
public class Regles extends JPanel {

    public static JButton retour;

    /**
     * Contient le panneau des règles
     * @param principale - la JFrame dans laquelle est affiché le panneau des règles
     */
    public Regles(FenetrePrincipale principale){

        this.setLayout(new BorderLayout());

        //BORDERLAYOUT.NORTH
        //Ajout du titre de la page
        JLabel titre = new JLabel("Règles du jeu", SwingConstants.CENTER);
        titre.setFont(CustomFont.customFont50_PLAIN);
        titre.setBorder(BorderFactory.createEmptyBorder(45,0,10,0));
        titre.setForeground(Color.white);

        //BORDERLAYOUT.WEST
        //Ajout d'un panel vide à gauche
        JPanel gauche = new JPanel();
        gauche.setPreferredSize(new Dimension(200, 0));
        gauche.setOpaque(false);

        //BORDERLAYOUT.EAST
        //Ajout d'un panel vide à droite
        JPanel droite = new JPanel();
        droite.setPreferredSize(new Dimension(200,0));
        droite.setOpaque(false);

        //BORDERLAYOUT.CENTER
        //Ajout d'un panel qui va stocker les différents éléments du panneau

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JPanel regles = new JPanel();
        regles.setLayout(new FlowLayout());
        regles.setBackground(new Color(255, 255, 255, 161));
       // regles.setPreferredSize(new Dimension(3*(screenSize.width/2),3*(screenSize.height/2)));

        JTextArea textArea = new JTextArea();
        textArea.setText("Règles du jeu :\n" +
                "\n" +
                "Objectif : \n" +
                "\n" +
                "Réussir à maintenir en vie son Keneil le plus longtemps possible.\n" +
                "\n" +
                "Fonctionnement du jeu ? \n" +
                "\n" +
                "Lorsque vous lancerez une partie, votre Keneil aura 6 barres de statistiques remplies. \n" +
                "\n" +
                "- Une barre de santé (symbolisée par un coeur)\n" +
                "- une barre de bonheur (symbolisée par un émoji sourire)\n" +
                "- une barre de nourriture (symbolisée par une pomme)\n" +
                "- une barre d'énergie (symbolisée par un éclair)\n" +
                "- une barre de douche (symbolisée par un main avec du savon)\n" +
                "- une barre de divertissement (symbolisée par une manette de jeu vidéo)\n" +
                "\n" +
                "Ces 6 barres représentent l'état du Tamagotchi à un instant T.\n" +
                "\n" +
                "Les 4 barres (nourriture, énergie, douche, divertissement) vont descendre au fur et à mesure du temps de jeu en fonction de la difficultée choisie.\n" +
                "Plus la difficultée est élevée, plus les statistiques de ces barres descendront vite, et inversement. \n" +
                "\n" +
                "Attention !! Le temps de jeu se base sur l'heure réelle. Ce qui veut dire que même lorsque vous n'êtes pas connecté sur le jeu, les statistiques de votre Keneil\n" +
                "continuerons à descendre.\n" +
                "\n" +
                "Les 2 barres (santé et bonheur) sont les barres principales. Si une de ces deux barres tombe à zéro, alors votre Keneil s'en ira au Paradis des Keneil, mais vous \n" +
                "ne pourrez plus jamais le revoir. \n" +
                "Ces barres ne descendent pas uniquement en fonction du temps qui passe, mais elles vont être influencées par les 4 barres secondaires.\n" +
                "\n" +
                "\n" +
                "Temps T : Temps de mise à jour pour les 4 barres secondaires.\n" +
                "Temps S : Temps de mise à jour pour la barre de santé.\n" +
                "Temps B : Temps de mise à jour pour la barre de bonheur.\n" +
                "\n" +
                "Pour HYGIENE et NOURRITURE à chaque instant T : \n" +
                "\n" +
                "1) Tant que l'hygiène ET la nourriture sont inférieures ou égales à 3 :\n" +
                "\t- le temps S (de Santé) est multiplié par 0.9 (la santé baisse plus vite).\n" +
                "\n" +
                "2) Tant que l'hygiène OU la nourriture sont inférieures ou égales à 3 : \n" +
                "\t- la SANTE descendra de 1.\n" +
                "\n" +
                "3) Tant que l'hygiène OU la nourriture sont compris entre 4 et 9 :\n" +
                "\t- La SANTE augmentera de 1.\n" +
                "\t- le temps S (de Santé) est multiplié par 1.1 (la Santé augmentera plus vite).\n" +
                "\n" +
                "Pour ENERGIE et DIVERTISSEMENT à chaque instant T : \n" +
                "\n" +
                "1) Tant que l'énergie ET le divertissement sont inférieures ou égales à 3 :\n" +
                "\t- le temps B (de Bonheur) est multuplié par 0.9 (le bonheur baisse plus vite).\n" +
                "\n" +
                "2) Tant que l'énergie OU le divertissement sont inférieures ou égales à 3 : \n" +
                "\t- Le BONHEUR descendra de 1.\n" +
                "\n" +
                "3) Tant que l'énergie OU le divertissement sont compris entre 4 et 9 :\n" +
                "\t- Le BONHEUR augmente de 1.\n" +
                "\t- le temps B (de Bonheur) est multiplié par 1.1 (le Bonheur augmentera plus vite).\n" +
                "\n" +
                "La mises à jour des statistiques de santé et de bonheur sont donc effectués à chaque temps (respectivement S et B). \n" +
                "La vitesse de mise à jour de ces statistiques évolue donc en fonction du temps, mais il y aura toujours :\n" +
                "\n" +
                "Si = temps S initial;\n" +
                "Bi = temps B initial;\n" +
                "\n" +
                "(Si / 2) < S < (Si * 1,5)\n" +
                "(Bi / 2) < B < (Bi * 1,5)\n" +
                "\n" +
                "Ainsi, à partir d'une certaine valeur prédéfinie, la vitesse de mise à jour de Santé et Bonheur ne pourra plus ralentir ou accélérer.\n" +
                "Les 4 barres de statistiques évoluent de la même façon lors de la reconnexion du joueur, en fonction du temps passé depuis la dernière connexion. \n" +
                "\n" +
                "\n" +
                "Les événements :\n" +
                "\n" +
                "Les événements PEUVENT se déclencher à chaque instant E.\n" +
                "\n" +
                "Le nombre d'événements en fonction du temps, dépend une fois encore de la difficultée choisie au début de la partie.\n" +
                "A chaque instant E, il y a 1 chance sur 10 qu'un événement ait lieu. \n" +
                "\n" +
                "Si un événement à lieu, alors un événements aléatoire sera tiré. \n" +
                "\n" +
                "Liste d'événements :\n" +
                "\n" +
                "- Malade : La SANTE baisse de 2.\n" +
                "\n" +
                "- Anniversaire : Le DIVERTISSEMENT et le BONHEUR augmentent de 1.\n" +
                "\n" +
                "- Soirée : Le DIVERTISSEMENT et le BONHEUR augmentent de 1.\n" +
                "\n" +
                "- Amoureux : Le BONHEUR augmente de 1 et l'HYGIENE augmente de 2.\n" +
                "\n" +
                "- Sport : l'ENERGIE baisse de 1 et la SANTE augmente de 1.\n" +
                "\n" +
                "- Jouer : Le DIVERTISSEMENT augmente de 2.\n" +
                "\n" +
                "- Restaurant : La NOURRITURE augmente de 2.\n" +
                "\n" +
                "- Vacances : L'ENERGIE augmente de 3.\n" +
                "\n" +
                "- Depression : Le BONHEUR baisse de 2.\n" +
                "\n" +
                "Tous les événements qui auraient dû se passer pendant le jeu, lorsque vous êtes déconnecté se dérouleront à la reconnexion.\n" +
                "\n" +
                "Comment jouer ? \n" +
                "\n" +
                "Lorsque vous arrivez sur le jeu, vous allez pouvoir vous déplacer dans 4 environnements :\n" +
                "Dans chacun de ces environnements, vous pourrez effectuer une action à chaque instant T.\n" +
                "\n" +
                "- La chambre : \n" +
                "Permet de dormir (Energie + 2)\n" +
                "\n" +
                "- La salle de bain :\n" +
                "Permet de se laver (Hygiène + 2)\n" +
                "\n" +
                "- La Salle à manger :\n" +
                "Permet de manger (Nourriture + 2)\n" +
                "\n" +
                "- Le jardin :\n" +
                "Permet de jouer (Divertissement + 2)\n" +
                "\n" +
                "Les flèches permettent de se déplacer d'un environnement à l'autre.\n" +
                "Les boutons d'actions permettent d'effectuer l'action de son environnement.\n" +
                "\n" +
                "Comment sauvegarder ? \n" +
                "\n" +
                "La partie est sauvegardée automatiquement toutes les 5 secondes.\n" +
                "Vous pouvez également sauvegarder votre partie directement via le menu des options (accessible en cliquant sur le rouage).");

        textArea.setEditable(false);
        textArea.setFont(CustomFont.customFont18);
        textArea.setOpaque(false);
        textArea.setForeground(Color.black);

        JScrollPane scroll = new JScrollPane (textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(((screenSize.width/2)),screenSize.height/2));
        scroll.setOpaque(false);
        scroll.setForeground(Color.black);


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
        this.add(scroll, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }
    

    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g - objet Graphics permettant l'ajout d'une image sur une fenêtre.
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}
