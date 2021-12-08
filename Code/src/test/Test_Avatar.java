package test;
import main.model.Avatar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.*;

// IMPORTANT : CHANGER expected PAR attendu

public class Test_Avatar {


    //+-------------------------------+
    //|    Test des CONSTRUCTEURS     |
    //+-------------------------------+

    @Test 
    public void testAvatar_Cst1() {
        String expected_type = "chat";
        String expected_nom = "felix";

        Avatar test_Avatar = new Avatar(expected_type, expected_nom);
        assertNotNull(test_Avatar);
        assertEquals(test_Avatar.getType(), expected_type);
        assertEquals(test_Avatar.getNom(), expected_nom);
    }

    @Test
    public void testAvatar_Cst2() {
        String expected_type = "chat";
        String expected_nom = "felix";
        int expected_sante = 10;
        int expected_bonheur = 9;

        int expected_nourriture = 8;
        int expected_energie = 7;
        int expected_hygiene = 6;
        int expected_divertissement = 5;

        Avatar test_Avatar = new Avatar(expected_type, expected_nom, expected_sante, expected_bonheur, expected_nourriture, expected_energie, expected_hygiene, expected_divertissement);
        assertNotNull(test_Avatar);
        assertEquals(test_Avatar.getType(), expected_type);
        assertEquals(test_Avatar.getNom(), expected_nom);
        assertEquals(test_Avatar.getSante(), expected_sante);
        assertEquals(test_Avatar.getBonheur(), expected_bonheur);
        assertEquals(test_Avatar.getNourriture(), expected_nourriture);
        assertEquals(test_Avatar.getEnergie(), expected_energie);
        assertEquals(test_Avatar.getHygiene(), expected_hygiene);
        assertEquals(test_Avatar.getDivertissement(), expected_divertissement);
    }

    //+-------------------------------+
    //|    Test des MODIFIEURS        |
    //+-------------------------------+

    @Test
    public void test_modifierSante() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 5, 10, 8, 7, 6, 7);
        int expected_sante = 10;
        test_Avatar.modifierSante(5);
        assertEquals(test_Avatar.getSante(), expected_sante);

        //cas erreur
        test_Avatar.modifierSante(-1);
        assertNotEquals(test_Avatar.getSante(), expected_sante);

        //cas limite
        int expected_sante2 = 0;
        test_Avatar.setSante(expected_sante2);
        test_Avatar.modifierSante(1);
        assertEquals(test_Avatar.getSante(), expected_sante2);
    }

    @Test
    public void test_modifierBonheur() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 5, 6, 8, 7, 6, 7);
        int expected_bonheur = 10;
        test_Avatar.modifierBonheur(4);
        assertEquals(test_Avatar.getBonheur(), expected_bonheur);

        //cas erreur
        test_Avatar.modifierBonheur(-1);
        assertNotEquals(test_Avatar.getBonheur(), expected_bonheur);

        //cas limite
        int expected_bonheur2 = 0;
        test_Avatar.setBonheur(expected_bonheur2);
        test_Avatar.modifierBonheur(1);
        assertEquals(test_Avatar.getBonheur(), expected_bonheur2);
    }

    @Test
    public void test_modifierNourriture() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 5, 5, 5, 7, 6, 7);
        int expected_nourriture = 7;
        test_Avatar.modifierNourriture(2);
        assertEquals(test_Avatar.getNourriture(), expected_nourriture);

        //cas erreur
        test_Avatar.modifierNourriture(-1);
        assertNotEquals(test_Avatar.getNourriture(), expected_nourriture);

        //cas limite
        int expected_nourriture2 = 0;
        test_Avatar.setNourriture(expected_nourriture2);
        test_Avatar.modifierNourriture(1);
        assertEquals(test_Avatar.getNourriture(), expected_nourriture2);
    }

    @Test
    public void test_modifierEnergie() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 10, 6, 5, 7, 6, 7);
        int expected_energie = 9;
        test_Avatar.modifierEnergie(2);
        assertEquals(test_Avatar.getEnergie(), expected_energie);

        //cas erreur
        test_Avatar.modifierEnergie(-1);
        assertNotEquals(test_Avatar.getEnergie(), expected_energie);

        //cas limite
        int expected_energie2 = 0;
        test_Avatar.setEnergie(expected_energie2);
        test_Avatar.modifierEnergie(1);
        assertEquals(test_Avatar.getEnergie(), expected_energie2);
    }

    @Test
    public void test_modifierHygiene() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 10, 5, 8, 7, 3, 7);
        int expected_hygiene = 4;
        test_Avatar.modifierHygiene(1);
        assertEquals(test_Avatar.getHygiene(), expected_hygiene);

        //cas erreur
        test_Avatar.modifierHygiene(-1);
        assertNotEquals(test_Avatar.getHygiene(), expected_hygiene);

        //cas limite
        int expected_hygiene2 = 0;
        test_Avatar.setHygiene(expected_hygiene2);
        test_Avatar.modifierHygiene(1);
        assertEquals(test_Avatar.getHygiene(), expected_hygiene2);
    }

    @Test
    public void test_modifierDivertissement() {
        //cas normal
        Avatar test_Avatar = new Avatar("robot", "antoine", 5, 5, 5, 7, 6, 8);
        int expected_divertissement = 10;
        test_Avatar.modifierDivertissement(2);
        assertEquals(test_Avatar.getDivertissement(), expected_divertissement);

        //cas erreur
        test_Avatar.modifierDivertissement(-1);
        assertNotEquals(test_Avatar.getDivertissement(), expected_divertissement);

        //cas limite
        int expected_divertissement2 = 0;
        test_Avatar.setDivertissement(expected_divertissement2);
        test_Avatar.modifierDivertissement(1);
        assertEquals(test_Avatar.getDivertissement(), expected_divertissement2);
    }

    //+-------------------------------+
    //|    Test des GETTERS           |
    //+-------------------------------+

    @Test
    public void test_getType() {
        //cas normal
        Avatar robot = new Avatar("robot", "antoine");
        String expected_type = "robot";
        assertEquals(robot.getType(), expected_type);

        //cas erreur
        assertNotEquals(robot.getType(), "chat");
    }

    @Test
    public void test_getNom() {
        //cas normal
        Avatar chat = new Avatar("chat", "felix");
        String expected_nom = "felix";
        assertEquals(chat.getNom(), expected_nom);

        //cas erreur
        assertNotEquals(chat.getNom(), "andre");
    }

    @Test
    public void test_getSante() {
        //cas normal
        Avatar chien = new Avatar("chien", "rex", 5, 5, 5, 7, 6, 8);
        int expected_sante = 5;
        assertEquals(chien.getSante(), expected_sante);

        //cas erreur
        assertNotEquals(chien.getSante(), 2);
    }

    @Test
    public void test_getBonheur() {
        //cas normal
        Avatar chien = new Avatar("chien", "pluto", 9, 2, 5, 7, 6, 8);
        int expected_bonheur = 2;
        assertEquals(chien.getBonheur(), expected_bonheur);

        //cas erreur
        assertNotEquals(chien.getBonheur(), 10);
    }

    @Test
    public void test_getNourriture() {
        //cas normal
        Avatar poulpe = new Avatar("poulpe", "squidward", 2, 9, 8, 7, 6, 8);
        int expected_bonheur = 8;
        assertEquals(poulpe.getNourriture(), expected_bonheur);

        //cas erreur
        assertNotEquals(poulpe.getNourriture(), 10);
    }
    
    @Test
    public void test_getEnergie() {
        //cas normal
        Avatar chat = new Avatar("chat", "garfield", 10, 7, 8, 6, 6, 8);
        int expected_energie = 6;
        assertEquals(chat.getEnergie(), expected_energie);

        //cas erreur
        assertNotEquals(chat.getEnergie(), 2);
    }

    @Test
    public void test_getHygiene() {
        //cas normal
        Avatar robot = new Avatar("robot", "cop", 10, 7, 8, 6, 1, 8);
        int expected_hygiene = 1;
        assertEquals(robot.getHygiene(), expected_hygiene);

        //cas erreur
        assertNotEquals(robot.getHygiene(), 9);
    }

    @Test
    public void test_getDivertissement() {
        //cas normal
        Avatar oiseau = new Avatar("oiseau", "flappy", 10, 7, 8, 6, 1, 8);
        int expected_divertissement = 8;
        assertEquals(oiseau.getDivertissement(), expected_divertissement);

        //cas erreur
        assertNotEquals(oiseau.getDivertissement(), 7);
    }
}
