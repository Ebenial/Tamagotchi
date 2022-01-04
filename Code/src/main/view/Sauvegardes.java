package main.view;

import javax.swing.*;

import main.util.CustomJButton;
import main.util.TransparentJPanel;


import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER;

public class Sauvegardes extends JPanel {

    public static CustomJButton retour;

    private ArrayList<CustomJButton> arrayButton = new ArrayList<CustomJButton>();
    private ArrayList<String> saveName = new ArrayList<>();
    private ArrayList<CustomJButton> arrayDelete = new ArrayList<>();
    FenetrePrincipale principale;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel centre;


    /**
     * Contient le panneau des règles
     *
     * @param principale - la JFrame dans laquelle est affiché le panneau des règles
     */
    public Sauvegardes(FenetrePrincipale principale) {
        this.principale = principale;
        this.setLayout(new BorderLayout());

        //BORDERLAYOUT.CENTER
        this.centre = new JPanel();
        centre.setOpaque(false);

        JPanel panelInScroll = new JPanel();
        panelInScroll.setOpaque(false);
        panelInScroll.setLayout(new GridLayout(1, 4));

        this.panel1 = new JPanel();
        panel1.setOpaque(false);
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
        this.panel2 = new JPanel();
        panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
        panel2.setOpaque(false);

        updateSaves();
        for (CustomJButton j : arrayButton) {
            j.setBackground(new Color(0,0,0,0));
            j.setBorder(BorderFactory.createEmptyBorder(5,0,5,0));
            panel1.add(j);
        }
        for (CustomJButton j : arrayDelete) {
            j.setBackground(new Color(0,0,0,0));
            panel2.add(j);
        }

        JScrollPane jScrollPane = new JScrollPane(panelInScroll);
        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);
        jScrollPane.setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane.setBorder(createEmptyBorder());


        retour = new CustomJButton("Retour", principale, null, null, null, null, null);

        TransparentJPanel trP = new TransparentJPanel();
        trP.setBorder(BorderFactory.createEmptyBorder(30,0,5,0));
        trP.add(retour);

        centre.add(panel1);
        centre.add(panel2);

        panelInScroll.add(centre);

        add(jScrollPane, BorderLayout.CENTER);
        add(trP, BorderLayout.SOUTH);

    }

    public void writeBestScore() {
        Path filename = Path.of("./Code/resources/score/score.txt");
        if(Long.parseLong(readBestScore()) < this.principale.getCurrentEnvironnement().getTimerPanel().getCount()) {
            try {
                Files.writeString(filename, "" + this.principale.getCurrentEnvironnement().getTimerPanel().getCount());
            } catch (IOException ignored) {

            }
        }
    }

    public String readBestScore() {
        Path filename = Path.of("./Code/resources/score/score.txt");
        String score = "0";
        try {
            score = Files.readString(filename);
            System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii : " + score);
        } catch (IOException ignored) {

        }
        return score;
    }

    public void updateSaves() {
        Set<String> hset = listFilesUsingJavaIO("./Code/resources/saves/");
        for (String s : hset) {
            if (s.toLowerCase().endsWith(".save")) {
                String[] tokens = s.split("\\.");
                String buttonName = tokens[0];
                int length = 13;
                if (buttonName.length() > length) {
                    buttonName = buttonName.substring(0, length);
                    buttonName = buttonName + "...";
                }
                arrayButton.add(new CustomJButton(buttonName, principale, null, "Code/resources/others/button_background_large.png", null, null, null));
                saveName.add(s);
                arrayDelete.add(new CustomJButton(principale));
                System.out.println("NOMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMMM : " + s);
            }
        }
    }

    public Sauvegardes() {
    }

    public Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(Objects.requireNonNull(new File(dir).listFiles()))
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
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

    public JPanel getPanel1() {
        return this.panel1;
    }

    public void setPanel1(JPanel panel) {
        this.panel1 = panel;
    }

    public void setPanel2(JPanel panel) {
        this.panel2 = panel;
    }

    public void setCentre(JPanel panel) {
        this.centre = panel;
    }

    public JPanel getPanel2() {
        return this.panel2;
    }

    public JPanel getCentre() {
        return this.centre;
    }

    public ArrayList<CustomJButton> getArrayButton() {
        return arrayButton;
    }

    public ArrayList<CustomJButton> getArrayDelete() {
        return arrayDelete;
    }

    public ArrayList<String> getSaveName() {
        return saveName;
    }

    public void setArrayButton(ArrayList<CustomJButton> arrayButton) {
        this.arrayButton = arrayButton;
    }

    public void setArrayDelete(ArrayList<CustomJButton> arrayDelete) {
        this.arrayDelete = arrayDelete;
    }

    public void setSaveName(ArrayList<String> saveName) {
        this.saveName = saveName;
    }
}
