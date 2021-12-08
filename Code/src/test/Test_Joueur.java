package test;
import main.model.Joueur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.*;

public class Test_Joueur {

    //+-------------------------------+
    //|    Test des CONSTRUCTEURS     |
    //+-------------------------------+

        @Test
        public void testJoueur_Cst1() {
            String expected_nom = "Pierre";

            Joueur test_joueur = new Joueur("Pierre");
            assertNotNull(test_joueur);
            assertEquals(expected_nom, test_joueur.getNom());
        }

    //+-------------------------------+
    //|    Test des GETTERS           |
    //+-------------------------------+

        @Test
        public void test_getNom() {

            //Cas normal
            Joueur test_Joueur = new Joueur("Louis");
            String expected_nom = "Louis";

            assertEquals(expected_nom, test_Joueur.getNom());
            
            //Cas erreur
            String false_name = "Andre";
            assertNotEquals(false_name, test_Joueur.getNom());
        }
    
    
}
