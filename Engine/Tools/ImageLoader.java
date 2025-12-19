package Engine.Tools;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.RenderingHints;


public class ImageLoader{
    public static BufferedImage load (String filePath){
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static BufferedImage scale(BufferedImage original, int width, int height) {
        // Create a new BufferedImage with the desired size
        BufferedImage scaled = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = scaled.createGraphics();

        // Choose the interpolation method (for pixel art, use NEAREST_NEIGHBOR)
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        // Draw the scaled image
        g2d.drawImage(original, 0, 0, width, height, null);
        g2d.dispose();

        return scaled;
        }
}