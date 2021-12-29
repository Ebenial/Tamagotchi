package main.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PopUp implements ActionListener {

    private static class BasicBackgroundPanel extends JPanel {

        private final Image background;

        public BasicBackgroundPanel() {
            background = new ImageIcon(new ImageIcon("Code/resources/others/popup.png").getImage().getScaledInstance(384, 192, Image.SCALE_SMOOTH)).getImage();
            setLayout(new BorderLayout());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.drawImage(background, 0, 0, null);
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(background.getWidth(this), background.getHeight(this));
        }
    }


    // window
    JWindow w;

    // constructor
    PopUp(String text) {
        // create a window
        w = new JWindow();

        // set background of window transparent
        w.setBackground(new Color(0, 0, 0, 0));

        // create a label
        JLabel l = new JLabel(text);

        // create a new button
        JButton b = new JButton("OK");
        b.setHorizontalAlignment(SwingConstants.CENTER);


        // add action listener
        b.addActionListener(this);

        try {
            // set windows look and feel
            UIManager.setLookAndFeel(UIManager
                    .getSystemLookAndFeelClassName());
        } catch (Exception ignored) {
        }

        // create a panel
        BasicBackgroundPanel p = new BasicBackgroundPanel();
        p.setLayout(new GridLayout(2, 1));

        l.setFont(CustomFont.customFont18);
        l.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new GridBagLayout());
        buttonPanel.setOpaque(false);
        buttonPanel.add(b);

        // add contents to panel
        p.add(l);
        p.add(buttonPanel);

        w.add(p);
        int width = 384;
        int height = 192;
        w.setSize(width, height);
        w.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width / 2) - (width / 2), (Toolkit.getDefaultToolkit().getScreenSize().height / 2) - (height / 2));

        w.setVisible(true);
    }

    // if button is pressed
    public void actionPerformed(ActionEvent evt) {
        w.setVisible(false);
    }
}

