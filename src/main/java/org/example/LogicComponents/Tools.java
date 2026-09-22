package org.example.LogicComponents;

import org.example.Enums.State;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL12;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;

import static org.lwjgl.opengl.GL11C.*;

class ImageLoader {
    private static final int BYTES_PER_PIXEL = 4;//3 for RGB, 4 for RGBA
    public static int loadTexture(BufferedImage image){

        int[] pixels = new int[image.getWidth() * image.getHeight()];
        image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());

        ByteBuffer buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * BYTES_PER_PIXEL); //4 for RGBA, 3 for RGB

        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int pixel = pixels[y * image.getWidth() + x];
                buffer.put((byte) ((pixel >> 16) & 0xFF));     // Red component
                buffer.put((byte) ((pixel >> 8) & 0xFF));      // Green component
                buffer.put((byte) (pixel & 0xFF));               // Blue component
                buffer.put((byte) ((pixel >> 24) & 0xFF));    // Alpha component. Only for RGBA
            }
        }

        buffer.flip(); //FOR THE LOVE OF GOD DO NOT FORGET THIS

        // You now have a ByteBuffer filled with the color data of each pixel.
        // Now just create a texture ID and bind it. Then you can load it using
        // whatever OpenGL method you want, for example:

        int textureID = glGenTextures(); //Generate texture ID
        glBindTexture(GL_TEXTURE_2D, textureID); //Bind texture ID

        //Setup wrap mode
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

        //Setup texture scaling filtering
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        //Send texel data to OpenGL
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer);

        //Return the texture ID so we can bind it later again
        return textureID;
    }

    public static BufferedImage loadImage(String loc)
    {
        try {
            return ImageIO.read(new File(loc));
        } catch (IOException e) {
            //Error Handling Here
        }
        return null;
    }
}

class SpriteSheet {
    private BufferedImage spriteSheet;

    //key is the state, value is a list of texture IDs for the images corresponding to that state
    private HashMap<State, ArrayList<Integer>> images;

    int currentImageIndex=0;

    public SpriteSheet(String path) {
        this.spriteSheet = ImageLoader.loadImage(path);
        images = new HashMap<>();
        for (State state : State.values()) {
            images.put(state, new ArrayList<>());
        }
    }

    public BufferedImage getSprite(int x, int y, int width, int height) {
        if (spriteSheet == null) {
            throw new IllegalStateException("Sprite sheet not loaded.");
        }
        return spriteSheet.getSubimage(x, y, width, height);
    }

    //Takes a list of list of coordinates and returns a list of texture IDs for each sprite defined by those coordinates
    public void defineImage(State state, ArrayList<ArrayList<Integer>> coordinates) {
        ArrayList<Integer> textureIDs = new ArrayList<>();
        for (ArrayList<Integer> coord : coordinates) {
            int x = coord.get(0);
            int y = coord.get(1);
            int width = coord.get(2);
            int height = coord.get(3);
            BufferedImage sprite = getSprite(x, y, width, height);
            int textureID = ImageLoader.loadTexture(sprite);
            textureIDs.add(textureID);
        }
        images.put(state, textureIDs);
    }

    public Integer getCurrentImage(State state){
        if (currentImageIndex==images.get(state).size()-1){
            currentImageIndex=0;
        } else {
            currentImageIndex++;
        }
        return images.get(state).get(currentImageIndex);
    }

    public boolean notEmpty(State state){
        Boolean notEmpty = images.containsKey(state) && !images.get(state).isEmpty();
        //System.out.println(notEmpty + " " + images.get(state)+ "state: " + state);
        return notEmpty;
    }

}


