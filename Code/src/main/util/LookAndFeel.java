package main.util;

import javax.swing.UIManager;

/**
 * Permet de choisir l'aspect des composants de la fenêtre
 */
public class LookAndFeel {
    /**
     * Initialise le LookAndFeel
     */
    public static void initLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
