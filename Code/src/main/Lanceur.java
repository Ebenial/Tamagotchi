package main;

import main.model.BoucleJeu;
import main.view.FenetrePrincipale;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Lanceur du programme Tamagotchi - Keneil
 * @author ASPYG
 * @version 0.1
 */
public class Lanceur{

    /**
     * Lance le programme
     * @param args - lance les paramètres du String[], pas nécessaire ici
      */
    public static void main(String[] args){
        // Création dossier save si n'existe pas
        try {
            Path savesPath = Paths.get("./Code/resources/saves/");
            if (!Files.exists(savesPath) || !Files.isDirectory(savesPath)) {
                Files.createDirectories(savesPath);
            }
        } catch (IOException e) {
            System.err.println("Impossible de créer le dossier qui va contenir les sauvegardes.\nFermeture du jeu.");
            System.exit(1);
        }

        FenetrePrincipale principale = new FenetrePrincipale();
        new BoucleJeu(principale);
    }
}