package main.util;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel personnalisé
 */
public class CustomJPanel extends JPanel {

    Image image;
    Dimension dimension;

    /**
     * Construit un JPanel personnalisé
     * @param image - l'image de fond du panneau
     * @param dimension - les dimensions du panneau
     */
    public CustomJPanel(Image image, Dimension dimension){
        this.image = image;
        this.dimension = dimension;
        this.setPreferredSize(dimension);
        this.setMinimumSize(dimension);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        g.drawImage(image,0, 0, this.dimension.width, this.dimension.height, this);
    }
}
