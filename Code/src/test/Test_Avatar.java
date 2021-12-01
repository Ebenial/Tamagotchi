package test;
import main.model.Avatar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.*;

// IMPORTANT : CHANGER des PAR attendu

public class Test_Avatar {


    //+-------------------------------+
    //|    Test des CONSTRUCTEURS     |
    //+-------------------------------+

    @Test 
    public void testAvatar_Cst1() {
        String des_type = "chat";
        String des_nom = "felix";

        Avatar test_Avatar = new Avatar(des_type, des_nom);
        assertEquals(test_Avatar.getType(), des_type);
        assertEquals(test_Avatar.getNom(), des_nom);
    }

    @Test
    public void testAvatar_Cst2() {
        String des_type = "chat";
        String des_nom = "felix";
        int des_sante = 10;
        int des_bonheur = 9;

        int des_nourriture = 8;
        int des_energie = 7;
        int des_hygiene = 6;
        int des_divertissement = 5;

        Avatar test_Avatar = new Avatar(des_type, des_nom, des_sante, des_bonheur, des_nourriture, des_energie, des_hygiene, des_divertissement);
        assertEquals(test_Avatar.getType(), des_type);
        assertEquals(test_Avatar.getNom(), des_nom);
        assertEquals(test_Avatar.getSante(), des_sante);
        assertEquals(test_Avatar.getBonheur(), des_bonheur);
        assertEquals(test_Avatar.getNourriture(), des_nourriture);
        assertEquals(test_Avatar.getEnergie(), des_energie);
        assertEquals(test_Avatar.getHygiene(), des_hygiene);
        assertEquals(test_Avatar.getDivertissement(), des_divertissement);
    }

    //+-------------------------------+
    //|    Test des MODIFIEURS        |
    //+-------------------------------+

    @Test
    public void test_modifierSante() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 5, 10, 8, 7, 6, 7);
        int des_sante = 10;
        test_Avatar.modifierSante(5);
        assertEquals(test_Avatar.getSante(), des_sante);

        //cas erreur
        test_Avatar.modifierSante(-1);
        assertNotEquals(test_Avatar.getSante(), des_sante);

        //cas limite
        int des_sante2 = 0;
        test_Avatar.setSante(des_sante2);
        test_Avatar.modifierSante(1);
        assertEquals(test_Avatar.getSante(), des_sante2);
    }

    @Test
    public void test_modifierBonheur() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 5, 6, 8, 7, 6, 7);
        int des_bonheur = 10;
        test_Avatar.modifierBonheur(4);
        assertEquals(test_Avatar.getBonheur(), des_bonheur);

        //cas erreur
        test_Avatar.modifierBonheur(-1);
        assertNotEquals(test_Avatar.getBonheur(), des_bonheur);

        //cas limite
        int des_bonheur2 = 0;
        test_Avatar.setBonheur(des_bonheur2);
        test_Avatar.modifierBonheur(1);
        assertEquals(test_Avatar.getBonheur(), des_bonheur2);
    }

    @Test
    public void test_modifierNourriture() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 5, 5, 5, 7, 6, 7);
        int des_nourriture = 7;
        test_Avatar.modifierNourriture(2);
        assertEquals(test_Avatar.getNourriture(), des_nourriture);

        //cas erreur
        test_Avatar.modifierNourriture(-1);
        assertNotEquals(test_Avatar.getNourriture(), des_nourriture);

        //cas limite
        int des_nourriture2 = 0;
        test_Avatar.setNourriture(des_nourriture2);
        test_Avatar.modifierNourriture(1);
        assertEquals(test_Avatar.getNourriture(), des_nourriture2);
    }

    @Test
    public void test_modifierEnergie() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 10, 6, 5, 7, 6, 7);
        int des_energie = 9;
        test_Avatar.modifierEnergie(2);
        assertEquals(test_Avatar.getEnergie(), des_energie);

        //cas erreur
        test_Avatar.modifierEnergie(-1);
        assertNotEquals(test_Avatar.getEnergie(), des_energie);

        //cas limite
        int des_energie2 = 0;
        test_Avatar.setEnergie(des_energie2);
        test_Avatar.modifierEnergie(1);
        assertEquals(test_Avatar.getEnergie(), des_energie2);
    }

    @Test
    public void test_modifierHygiene() {
        //cas normal
        Avatar test_Avatar = new Avatar("chien", "clifford", 10, 5, 8, 7, 3, 7);
        int des_hygiene = 4;
        test_Avatar.modifierHygiene(1);
        assertEquals(test_Avatar.getHygiene(), des_hygiene);

        //cas erreur
        test_Avatar.modifierHygiene(-1);
        assertNotEquals(test_Avatar.getHygiene(), des_hygiene);

        //cas limite
        int des_hygiene2 = 0;
        test_Avatar.setHygiene(des_hygiene2);
        test_Avatar.modifierHygiene(1);
        assertEquals(test_Avatar.getHygiene(), des_hygiene2);
    }

    @Test
    public void test_modifierDivertissement() {
        //cas normal
        Avatar test_Avatar = new Avatar("robot", "antoine", 5, 5, 5, 7, 6, 8);
        int des_divertissement = 10;
        test_Avatar.modifierDivertissement(2);
        assertEquals(test_Avatar.getDivertissement(), des_divertissement);

        //cas erreur
        test_Avatar.modifierDivertissement(-1);
        assertNotEquals(test_Avatar.getDivertissement(), des_divertissement);

        //cas limite
        int des_divertissement2 = 0;
        test_Avatar.setDivertissement(des_divertissement2);
        test_Avatar.modifierDivertissement(1);
        assertEquals(test_Avatar.getDivertissement(), des_divertissement2);
    }

    //+-------------------------------+
    //|    Test des GETTERS           |
    //+-------------------------------+

    @Test
    public void test_getType() {
        //cas normal
        Avatar robot = new Avatar("robot", "antoine");
        String des_type = "robot";
        assertEquals(robot.getType(), des_type);

        //cas erreur
        assertNotEquals(robot.getType(), "chat");
    }

    @Test
    public void test_getNom() {
        //cas normal
        Avatar chat = new Avatar("chat", "felix");
        String des_nom = "felix";
        assertEquals(chat.getNom(), des_nom);

        //cas erreur
        assertNotEquals(chat.getNom(), "andre");
    }

    @Test
    public void test_getSante() {
        //cas normal
        Avatar chien = new Avatar("chien", "rex", 5, 5, 5, 7, 6, 8);
        int des_sante = 5;
        assertEquals(chien.getSante(), des_sante);

        //cas erreur
        assertNotEquals(chien.getSante(), 2);
    }

    @Test
    public void test_getBonheur() {
        //cas normal
        Avatar chien = new Avatar("chien", "pluto", 9, 2, 5, 7, 6, 8);
        int des_bonheur = 2;
        assertEquals(chien.getBonheur(), des_bonheur);

        //cas erreur
        assertNotEquals(chien.getBonheur(), 10);
    }

    @Test
    public void test_getNourriture() {
        //cas normal
        Avatar poulpe = new Avatar("poulpe", "squidward", 2, 9, 8, 7, 6, 8);
        int des_bonheur = 8;
        assertEquals(poulpe.getNourriture(), des_bonheur);

        //cas erreur
        assertNotEquals(poulpe.getNourriture(), 10);
    }
    
    @Test
    public void test_getEnergie() {
        //cas normal
        Avatar chat = new Avatar("chat", "garfield", 10, 7, 8, 6, 6, 8);
        int des_energie = 6;
        assertEquals(chat.getEnergie(), des_energie);

        //cas erreur
        assertNotEquals(chat.getEnergie(), 2);
    }

    @Test
    public void test_getHygiene() {
        //cas normal
        Avatar robot = new Avatar("robot", "cop", 10, 7, 8, 6, 1, 8);
        int des_hygiene = 1;
        assertEquals(robot.getHygiene(), des_hygiene);

        //cas erreur
        assertNotEquals(robot.getHygiene(), 9);
    }

    @Test
    public void test_getDivertissement() {
        //cas normal
        Avatar oiseau = new Avatar("oiseau", "flappy", 10, 7, 8, 6, 1, 8);
        int des_divertissement = 8;
        assertEquals(oiseau.getDivertissement(), des_divertissement);

        //cas erreur
        assertNotEquals(oiseau.getDivertissement(), 7);
    }
}
