package main.view;

import javax.swing.*;

import main.util.CustomJButton;
import main.util.TransparentJPanel;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sauvegardes extends JPanel {

    public static CustomJButton retour;

    public static ArrayList<CustomJButton> arrayButton = new ArrayList<>();
    public static ArrayList<String> saveName = new ArrayList<>();
    public static ArrayList<CustomJButton> arrayDelete = new ArrayList<>();


    /**
     * Contient le panneau des règles
     * @param principale - la JFrame dans laquelle est affiché le panneau des règles
     */
    public Sauvegardes(FenetrePrincipale principale){
        this.setLayout(new BorderLayout());



        JPanel panelInScroll = new JPanel();
        panelInScroll.setOpaque(false);
        panelInScroll.setLayout(new BorderLayout());

        JPanel panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setOpaque(false);
        Set<String> hset = listFilesUsingJavaIO(".");
        for(String s : hset) {
            if (s.toLowerCase().endsWith(".json")) {
                String[] tokens = s.split("\\.");
                String buttonName = tokens[0];
                arrayButton.add(new CustomJButton(buttonName, principale,null, "Code/resources/others/button_background_large.png", null, null, null));
                saveName.add(s);
                arrayDelete.add(new CustomJButton(principale));
            }
        }
        for (CustomJButton j: Sauvegardes.arrayButton) {
            panel1.add(j);
        }

        for (CustomJButton j: Sauvegardes.arrayDelete) {
            panel2.add(j);
        }

        JScrollPane jScrollPane = new JScrollPane(panelInScroll);
        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);

        retour = new CustomJButton("Retour", principale, null, null, null, null, null);

        TransparentJPanel trP = new TransparentJPanel();
        trP.add(retour);

        panelInScroll.add(panel1, BorderLayout.CENTER);
        panelInScroll.add(panel2, BorderLayout.EAST);

        add(jScrollPane,BorderLayout.CENTER);
        add(trP, BorderLayout.SOUTH);

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