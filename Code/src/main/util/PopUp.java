package main.util;
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PopUp implements ActionListener {

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
    JOptionPane w;

    // constructor
    public PopUp(String text) {
        // create a window
        w = new JOptionPane();

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
        w.requestFocusInWindow();

        w.setVisible(true);
    }

    // if button is pressed
    public void actionPerformed(ActionEvent evt) {
        w.setVisible(false);
    }
}



*/


/*
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.*;
import javax.imageio.ImageIO;

public class PopUp {

    private void displayGUI() {
        JOptionPane.showConfirmDialog(null,
                getPanel(),
                "JOptionPane Example : ",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);
    }

    private JPanel getPanel() {
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Java Technology Dive Log");
        ImageIcon image = new ImageIcon(new ImageIcon("Code/resources/others/popup.png").getImage().getScaledInstance(384, 192, Image.SCALE_SMOOTH));


        label.setIcon(image);
        panel.add(label);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PopUp().displayGUI();
            }
        });
    }


}
*/
/*
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PopUp extends JOptionPane {
    private BufferedImage img;

    public PopUp(String text) {
        setToolTipText(text);
    }

    @Override
    public void paint(Graphics g) {
        //Pick one of the two painting methods below.

        //Option 1:
        //Define the bounding region to paint based on image size.
        //Be careful, if the image is smaller than the JOptionPane size you
        //will see a solid white background where the image does not reach.
        //g.drawImage(img, 0, 0, img.getWidth(), img.getHeight());

        //Option 2:
        //If the image can be guaranteed to be larger than the JOptionPane's size

        ImageIcon icon = new ImageIcon(new ImageIcon("Code/resources/others/popup.png").getImage().getScaledInstance(384, 192, Image.SCALE_SMOOTH));

        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
         g = bi.createGraphics();
// paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0, 0);
        g.dispose();


        Dimension curSize = this.getSize();
        g.drawImage(img, 0, 0, curSize.width, curSize.height, null);


        //Make sure to paint all the other properties of Swing components.
        super.paint(g);
    }
}*/

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.*;


public class PopUp extends JDialog {

    BufferedImage imgBackground;
    JLabel label;

    public PopUp(String message) {
        makeUI(message);
    }

    private void makeUI(String message) {

        ImageIcon icon = new ImageIcon(new ImageIcon("Code/resources/others/popup.png").getImage().getScaledInstance(384, 192, Image.SCALE_SMOOTH));

        BufferedImage bi = new BufferedImage(
                icon.getIconWidth(),
                icon.getIconHeight(),
                BufferedImage.TYPE_INT_RGB);
        Graphics g = bi.createGraphics();
        // paint the Icon to the BufferedImage.
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        JPanel backgroundPane = new JPanel() {

            @Override
            public void paintComponent(Graphics g) {

                super.paintComponents(g);

                g.drawImage(imgBackground, 0, 0, this);
            }
        };

        label = new JLabel(message);


        label.setForeground(Color.BLACK);
        label.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btn = new JButton("OK");

        btn.setAlignmentX(Component.CENTER_ALIGNMENT);

        btn.addActionListener(e -> {
            dispose();
        });

        backgroundPane.setLayout(new BoxLayout(backgroundPane, BoxLayout.Y_AXIS));

        backgroundPane.add(Box.createRigidArea(new Dimension(0, Math.round(getPreferredSize().height * 0.1f))));
        backgroundPane.add(label);
        backgroundPane.add(Box.createVerticalGlue());
        backgroundPane.add(btn);

        backgroundPane.add(Box.createRigidArea(new Dimension(0, Math.round(getPreferredSize().height * 0.1f))));

        setModal(true);
        setResizable(false);
        setContentPane(backgroundPane);
        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(imgBackground.getWidth(), imgBackground.getHeight());
    }
}