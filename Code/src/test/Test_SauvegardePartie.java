package test;

import main.model.Avatar;
import main.model.Jeu;
import main.model.Joueur;
import main.view.FenetrePrincipale;
import main.model.SauvegardePartie;
import java.io.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.*;

public class Test_SauvegardePartie {

    @Test
    public void test_SauvegardePartie_cst1() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals("Pierre", test_sav.getNomJoueur());
            assertEquals("Felix", test_sav.getNomAvatar());
            assertEquals("chat", test_sav.getTypeAvatar());
            assertEquals(test_d, test_sav.getTempsJeu());
            assertEquals(test_av.getSante(), test_sav.getSanteAvatar());
            assertEquals(test_av.getNourriture(), test_sav.getNourritureAvatar());
            assertEquals(test_av.getEnergie(), test_sav.getEnergieAvatar());
            assertEquals(test_av.getHygiene(), test_sav.getHygieneAvatar());
            assertEquals(test_sav.getDivertissementAvatar(), test_sav.getDivertissementAvatar());
            assertEquals(test_av.getBonheur(), test_sav.getBonheurAvatar());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_getTempsDeJeu() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals(test_d, test_sav.getTempsJeu());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test_getNomJoueur() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals("Pierre", test_sav.getNomJoueur());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test_getNomAvatar() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals(test_av.getNom(), test_sav.getNomAvatar());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test_getTypeAvatar() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals(test_av.getType(), test_sav.getTypeAvatar());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test_getSanteAvatar() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals(test_av.getSante(), test_sav.getSanteAvatar());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test_getNourritureAvatar() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals(test_av.getNourriture(), test_sav.getNourritureAvatar());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test_getEnergieAvatar() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals(test_av.getEnergie(), test_sav.getEnergieAvatar());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test_getHygieneAvatar() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals(test_av.getHygiene(), test_sav.getHygieneAvatar());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test_getDivertissementAvatar() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals(test_av.getDivertissement(), test_sav.getDivertissementAvatar());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test_getBonheurAvatar() {
        long millis = System.currentTimeMillis();
        Date test_d = new Date(millis);
        Avatar test_av = new Avatar("chat", "Felix");
        try{
            SauvegardePartie test_sav = new SauvegardePartie("Pierre", test_av, test_d);
            assertNotNull(test_sav);
            assertEquals(test_av.getBonheur(), test_sav.getBonheurAvatar());
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
