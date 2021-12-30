package main.view;

import javax.swing.*;

import main.controler.ListenerBouton;
import main.util.CustomJButton;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sauvegardes extends JPanel {

    public static JButton retour;

    public static ArrayList<CustomJButton> arrayButton = new ArrayList<CustomJButton>();
    public static ArrayList<String> saveName = new ArrayList<>();



    /**
     * Contient le panneau des règles
     * @param principale - la JFrame dans laquelle est affiché le panneau des règles
     */
    public Sauvegardes(FenetrePrincipale principale){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        Set<String> hset = listFilesUsingJavaIO(".");
        for(String s : hset) {
            if (s.toLowerCase().endsWith(".json")) {
                String[] tokens = s.split("\\.");
                String buttonName = tokens[0];
                arrayButton.add(new CustomJButton(buttonName, principale,null, "Code/resources/others/button_background_large.png", null, null, null));
                saveName.add(s);
            }
        }
        for (CustomJButton j: Sauvegardes.arrayButton) {
            add(j);
        }

        retour = new JButton("Retour");
        retour.addActionListener(new ListenerBouton(principale));

        add(retour);

    }

    public Sauvegardes() {}

    public Set<String> listFilesUsingJavaIO(String dir) {
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