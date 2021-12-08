package test;
import main.model.Avatar;
import main.model.Jeu;
import main.model.Joueur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.*;

public class Test_Jeu {

    @Test 
    public void testJeu_Cst() {
        Jeu test_jeu = new Jeu("Pierre", "Felix", "Chat");
        assertNotNull(test_jeu);

        assertNotNull(test_jeu.getAvatar());
        assertNotNull(test_jeu.getJoueur());
    }
    
    @Test 
    public void test_init() {
        Jeu test_jeu = new Jeu("Pierre", "Felix", "Chat");
        Date expected_compteur = new Date();
        boolean expected_enCours = true;
        assertNotNull(test_jeu.getCompteur());
        assertNotNull(test_jeu.getEnCours());

        assertEquals(expected_enCours, test_jeu.getEnCours());
        assertEquals(expected_compteur, test_jeu.getCompteur());
    }


    @Test 
    public void test_tempsEcoule() {
        Avatar test_Avatar = new Avatar("chien", "Clifford");
        Jeu test_jeu = new Jeu("Pierre", test_Avatar.getNom(), test_Avatar.getType());

        Date expected_date = new Date();
        int expected_nourriture = 7;
        int expected_energie = 6;
        int expected_hygiene = 5;
        int expected_divertissement = 4;

        test_jeu.tempsEcoule(); // modifie les attributs de l'avatar et la valeur du compteur

        assertEquals(expected_nourriture, test_jeu.getAvatar().getNourriture());
        assertEquals(expected_energie, test_jeu.getAvatar().getEnergie());
        assertEquals(expected_hygiene, test_jeu.getAvatar().getHygiene());
        assertEquals(expected_divertissement, test_jeu.getAvatar().getDivertissement());
        
        assertEquals(expected_date, test_jeu.getCompteur()); 
    }    
}
