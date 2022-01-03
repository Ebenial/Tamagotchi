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
        assertEquals(test_Avatar.getSante(), 10);
        assertEquals(test_Avatar.getBonheur(), 10);
        assertEquals(test_Avatar.getNourriture(), 10);
        assertEquals(test_Avatar.getEnergie(), 10);
        assertEquals(test_Avatar.getHygiene(), 10);
        assertEquals(test_Avatar.getDivertissement(), 10);
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

        Avatar test_Avatar = new Avatar("chat", "felix", 10, 9, 8, 7, 6, 5);
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

    @Test
    public void test_getCanEat() {
        //cas normal
        Avatar oiseau = new Avatar("oiseau", "flappy", 10, 7, 8, 6, 1, 8);
        boolean des_canEat = true;
        assertEquals(des_canEat, oiseau.getCanEat());
    }

    @Test
    public void test_getCanShower() {
        //cas normal
        Avatar oiseau = new Avatar("oiseau", "flappy", 10, 7, 8, 6, 1, 8);
        boolean des_canShower = true;
        assertEquals(des_canShower, oiseau.getCanShower());
    }

    @Test
    public void test_getCanPlay() {
        //cas normal
        Avatar oiseau = new Avatar("oiseau", "flappy", 10, 7, 8, 6, 1, 8);
        boolean des_canPlay = true;
        assertEquals(des_canPlay, oiseau.getCanPlay());
    }

    @Test
    public void test_getCanSleep() {
        //cas normal
        Avatar oiseau = new Avatar("oiseau", "flappy", 10, 7, 8, 6, 1, 8);
        boolean des_canSleep = true;
        assertEquals(des_canSleep, oiseau.getCanSleep());
    }

    @Test
    public void test_setCanEat() {
        //cas normal
        Avatar chien = new Avatar("chien", "Rhobalas", 10, 7, 8, 6, 1, 8);
        boolean des_canEat = true;
        assertEquals(des_canEat, chien.getCanSleep());
        chien.setCanEat(false);
        assertEquals(false, chien.getCanEat());
    }

    @Test
    public void test_setCanShower() {
        //cas normal
        Avatar chien = new Avatar("chien", "Rhobalas", 10, 7, 8, 6, 1, 8);
        boolean des_canShower = true;
        assertEquals(des_canShower, chien.getCanShower());
        chien.setCanShower(false);
        assertEquals(false, chien.getCanShower());
    }

    @Test
    public void test_setCanPlay() {
        //cas normal
        Avatar chien = new Avatar("chien", "Rhobalas", 10, 7, 8, 6, 1, 8);
        boolean des_canPlay = true;
        assertEquals(des_canPlay, chien.getCanPlay());
        chien.setCanPlay(false);
        assertEquals(false, chien.getCanPlay());
    }

    @Test
    public void test_setCanSleep() {
        //cas normal
        Avatar chien = new Avatar("chien", "Rhobalas", 10, 7, 8, 6, 1, 8);
        boolean des_canSleep = true;
        assertEquals(des_canSleep, chien.getCanSleep());
        chien.setCanSleep(false);
        assertEquals(false, chien.getCanSleep());
    }


}
