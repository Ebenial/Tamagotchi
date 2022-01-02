package main.util;

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
    int count;

    public TimerPanel() {
        this.setOpaque(false);
        label = new JLabel("...");
        setLayout(new GridBagLayout());
        label.setFont(CustomFont.customFont28);
        add(label);
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                count++;

                int sec = count % 60;
                int min = (count / 60) % 60;
                int hours = (count / 60) / 60;

                String strSec = (sec < 10) ? "0" + sec : Integer.toString(sec);
                String strmin = (min < 10) ? "0" + min : Integer.toString(min);
                String strHours = (hours < 10) ? "0" + hours : Integer.toString(hours);

                System.out.println(strHours + ":" + strmin + ":" + strSec);

                label.setText(strHours + ":" + strmin + ":" + strSec);
            }
        });
        timer.setInitialDelay(0);
        timer.start();
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
