package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.CustomJButton;

import java.awt.*;
import java.io.File;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sauvegardes extends JPanel {

    public static JButton retour;
    public static CustomJButton sauvegarde1;
    public static CustomJButton sauvegarde2;
    public static CustomJButton sauvegarde3;



    /**
     * Contient le panneau des règles
     * @param principale - la JFrame dans laquelle est affiché le panneau des règles
     */
    public Sauvegardes(FenetrePrincipale principale){
        sauvegarde1 = new CustomJButton("Vide", principale,null, "Code/resources/others/button_background_large.png", null, null, null);
        sauvegarde2 = new CustomJButton("Vide", principale,null, "Code/resources/others/button_background_large.png", null, null, null);
        sauvegarde3 = new CustomJButton("Vide", principale,null, "Code/resources/others/button_background_large.png", null, null, null);
        System.out.println("SAUVEGARDESeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee");
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Set<String> hset = listFilesUsingJavaIO(".");
        boolean un = false;
        boolean deux = false;
        boolean trois = false;
        for(String s : hset) {
            if (s.toLowerCase().endsWith(".json")) {
                System.out.println(s);
                if(!un) {
                    sauvegarde1 = new CustomJButton(s, principale,null, "Code/resources/others/button_background_large.png", null, null, null);

                    un = true;
                }
                else if(!deux) {
                    sauvegarde2 = new CustomJButton(s, principale,null, "Code/resources/others/button_background_large.png", null, null, null);

                    deux = true;
                }
                else if(!trois) {
                    sauvegarde3 = new CustomJButton(s, principale,null, "Code/resources/others/button_background_large.png", null, null, null);
                    trois = true;
                }

            }
        }

        add(sauvegarde1);
        add(sauvegarde2);
        add(sauvegarde3);







        retour = new JButton("Retour");
        retour.addActionListener(new ListenerBouton(principale));

        this.add(retour, BorderLayout.SOUTH);
    }

    private Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }


    /**
     * Permet de surcharger le paintComponent pour ajouter l'Image background en fond d'écran du panel.
     * @param g -
     */
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(new ImageIcon("Code/resources/background/accueil.gif").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
    }
}