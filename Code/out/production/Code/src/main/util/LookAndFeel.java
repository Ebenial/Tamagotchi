package main.util;

import javax.swing.UIManager;

/**
 * Permet de choisir l'aspect des composants de la fenêtre
 */
public class LookAndFeel {

    final static String LOOKANDFEEL = "Nimbus";

    /**
     * Initialise le LookAndFeel
     */
    public static void initLookAndFeel() {
        String lookAndFeel = null;

        if(LOOKANDFEEL != null) {
            switch(LOOKANDFEEL){
                case "Metal":lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
                break;
                case "System":lookAndFeel = UIManager.getSystemLookAndFeelClassName();
                break;
                case "Motif":lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
                break;
                case "Nimbus":lookAndFeel = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
                break;
            }
            try {
                UIManager.setLookAndFeel(lookAndFeel);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
}
