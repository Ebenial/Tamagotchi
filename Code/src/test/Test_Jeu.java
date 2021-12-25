
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
}
