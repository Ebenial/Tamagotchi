package main.util;

import main.view.FenetrePrincipale;

import javax.swing.*;
import java.awt.*;

public class PopUp {

    static class BackgroundJPanel extends JPanel{
        BackgroundJPanel(){
            this.setPreferredSize(new Dimension(384, 192));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(new ImageIcon("Code/resources/others/popup.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    private static JDialog d;

    public PopUp(String text, FenetrePrincipale principale) {
        int width = 384;
        int height = 235;

        BackgroundJPanel bJ = new BackgroundJPanel();
        bJ.setLayout(new GridLayout(2,1));
        d = new JDialog(principale, "Nouvel événement !", true);
        d.setMinimumSize(new Dimension(width, height));
        d.setLayout( new FlowLayout() );
        JButton b = new JButton ("OK");
        b.addActionListener (e -> PopUp.d.setVisible(false));
        d.add(bJ);
        d.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (width / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (height / 2));
        d.setSize(new Dimension(384, 192));

        JLabel textLabel = new JLabel(text);
        textLabel.setHorizontalAlignment(SwingConstants.CENTER);
        textLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        textLabel.setFont(CustomFont.customFont13);

        JLabel test = new JLabel();
        test.setLayout(new GridBagLayout());
        test.add(b);

        bJ.add(textLabel);
        bJ.add(test);
        d.setVisible(true);
    }
}