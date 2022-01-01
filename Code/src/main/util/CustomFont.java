package main.util;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CustomFont{
    public static Font customFont100 = null;
    public static Font customFont100_PLAIN = null;
    public static Font customFont50 = null;
    public static Font customFont50_PLAIN = null;
    public static Font customFont40 = null;
    public static Font customFont35 = null;
    public static Font customFont28 = null;
    public static Font customFont18 = null;

    public static void initFont(){
        try {
            String fontPath = "Code/resources/font/PixelArt.ttf";

            customFont100 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.BOLD,100f);
            customFont100_PLAIN = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN,100f);
            customFont50 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.BOLD,50f);
            customFont50_PLAIN = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN,50f);
            customFont40 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, 40f);
            customFont35 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, 35f);
            customFont28 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, 28f);
            customFont18 = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, 18f);


            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont100);
            ge.registerFont(customFont100_PLAIN);
            ge.registerFont(customFont50);
            ge.registerFont(customFont50_PLAIN);
            ge.registerFont(customFont40);
            ge.registerFont(customFont35);
            ge.registerFont(customFont28);
            ge.registerFont(customFont18);

        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }
}
