package main.util;

import main.model.BoucleJeu;
import main.view.FenetrePrincipale;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class TimerPanel extends JPanel {
    JLabel label;
    Timer timer;
    private long count;
    FenetrePrincipale principale;

    public TimerPanel(FenetrePrincipale principale) {
        this.principale = principale;
        this.count = principale.getTempsTotal();
        this.setOpaque(false);
        label = new JLabel("...");
        setLayout(new GridBagLayout());
        label.setFont(CustomFont.customFont50_PLAIN);
        add(label);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;
                //System.out.println("count : "  + count);

                int sec = (int) count % 60;
                int min = (int) (count / 60) % 60;
                int hours = (int) ((count / 60) / 60)%24;
                int days = (int) (((count / 60) / 60) / 24);

                String strSec = (sec < 10) ? "0" + sec : Integer.toString(sec);
                String strmin = (min < 10) ? "0" + min : Integer.toString(min);
                String strHours = (hours < 10) ? "0" + hours : Integer.toString(hours);
                String strDays = (days < 10) ? "0" + days : Integer.toString(days);

                //System.out.println(strHours + ":" + strmin + ":" + strSec);

                label.setText(strDays + ":" + strHours + ":" + strmin + ":" + strSec);
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    public void setCount(long count) {
        this.count = count;
    }

}
