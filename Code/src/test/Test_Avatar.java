package test;
import main.model.Avatar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.*;

// IMPORTANT : CHANGER des PAR attendu

public class Test_Avatar {
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

    





}
