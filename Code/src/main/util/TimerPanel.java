package main.util;

import main.view.FenetrePrincipale;

import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * Panneau affichant un chronomètre
 */
public class TimerPanel extends JPanel {
    JLabel label;
    Timer timer;
    private long count;
    FenetrePrincipale principale;

    /**
     * Constructeur du panneau
     * @param principale - la fenêtre dans laquelle l'afficher
     */
    public TimerPanel(FenetrePrincipale principale) {
        this.principale = principale;
        this.count = principale.getTempsTotal();
        this.setOpaque(false);
        label = new JLabel("...");
        setLayout(new GridBagLayout());
        label.setFont(CustomFont.customFont50_PLAIN);
        add(label);
        timer = new Timer(1000, e -> {
            count++;

            int sec = (int) count % 60;
            int min = (int) (count / 60) % 60;
            int hours = (int) ((count / 60) / 60)%24;
            int days = (int) (((count / 60) / 60) / 24);

            String strSec = (sec < 10) ? "0" + sec : Integer.toString(sec);
            String strmin = (min < 10) ? "0" + min : Integer.toString(min);
            String strHours = (hours < 10) ? "0" + hours : Integer.toString(hours);
            String strDays = (days < 10) ? "0" + days : Integer.toString(days);

            label.setText(strDays + "j | " + strHours + "h | " + strmin + "m | " + strSec+"s");
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    /**
     * Accesseur retournant le temps affiché sur le compteur
     * @return - le temps affiché sur le compteur
     */
    public long getCount() {
        return this.count;
    }
}
