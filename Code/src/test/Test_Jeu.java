package test;
import main.model.Avatar;
import main.model.Jeu;
import main.model.Joueur;
import main.view.FenetrePrincipale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.*;

public class Test_Jeu {

    @Test
    public void testJeu_Cst() {
        FenetrePrincipale fen = new FenetrePrincipale();
        Jeu test_jeu = new Jeu("Pierre", "Felix", "chat", fen);
        assertNotNull(test_jeu);

        assertNotNull(test_jeu.getAvatar());
        assertNotNull(test_jeu.getJoueur());

        assertEquals("Pierre", test_jeu.getJoueur().getNom());
        assertEquals("Felix", test_jeu.getAvatar().getNom());
        assertEquals("chat", test_jeu.getAvatar().getType());
    }

    @Test
    public void test_getAvatar() {
        FenetrePrincipale fen = new FenetrePrincipale();
        Jeu test_jeu = new Jeu("Pierre", "Felix", "chat", fen);

        Avatar des_avatar = new Avatar("chat", "Felix");
        assertEquals(des_avatar.getType(), test_jeu.getAvatar().getType());
        assertEquals(des_avatar.getSante(), test_jeu.getAvatar().getSante());
        assertEquals(des_avatar.getNourriture(), test_jeu.getAvatar().getNourriture());
        assertEquals(des_avatar.getNom(), test_jeu.getAvatar().getNom());
        assertEquals(des_avatar.getHygiene(), test_jeu.getAvatar().getHygiene());
        assertEquals(des_avatar.getEnergie(), test_jeu.getAvatar().getEnergie());
        assertEquals(des_avatar.getDivertissement(), test_jeu.getAvatar().getDivertissement());
        assertEquals(des_avatar.getCanSleep(), test_jeu.getAvatar().getCanSleep());
        assertEquals(des_avatar.getCanShower(), test_jeu.getAvatar().getCanShower());
        assertEquals(des_avatar.getCanPlay(), test_jeu.getAvatar().getCanPlay());
        assertEquals(des_avatar.getCanEat(), test_jeu.getAvatar().getCanEat());
    }

    @Test
    public void test_getJoueur() {
        FenetrePrincipale fen = new FenetrePrincipale();
        Jeu test_jeu = new Jeu("Pierre", "Felix", "chat", fen);

        Joueur des_joueur = new Joueur("Pierre");
        assertEquals(des_joueur.getNom(), test_jeu.getJoueur().getNom());
    }
}
