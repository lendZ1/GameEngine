import java.awt.*;
import javax.swing.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.HashMap;
import java.util.ArrayList;




      
public class GameObject{
    public ArrayList<GameObject> layerObjects; // ArrayList to hold GameObjects in the same layer
    private int layer;
    public int xpos, ypos;
    private int xfart=0;
    private int yfart=0;
    private Color farge;
    private boolean bounce = true; // For å sjekke om GameObject skal sprette tilbake når den treffer kanten av vinduet
    private boolean movable;    //if the GO can be moved or not
    //bounce can be true while movable is false in case the object hits another immovable object
    public int høyde, bredde;
    public static GameMap gamemap; 
    private HashMap<String, ArrayList<BufferedImage>> images =new HashMap<>(); 
    private int imageIndex=0;   //index to help cycle through the animations
    private boolean hasImage=false;
    public String state="idle"; //determinesthe state of the object: idle, up, down, left, right

    public GameObject(int xpos, int ypos, int høyde, int bredde, int xfart, int yfart, Color farge, int layer, boolean movable) {
        this.xpos = xpos;
        this.ypos = ypos;
        this.høyde = høyde;
        this.bredde = bredde;
        this.farge = farge;
        this.layer = layer;
        this.xfart = xfart;
        this.yfart = yfart;
        this.movable = movable;

        
        images.put("idleImages", new ArrayList<BufferedImage>());
        images.put("upImages", new ArrayList<BufferedImage>());
        images.put("downImages", new ArrayList<BufferedImage>());
        images.put("leftImages", new ArrayList<BufferedImage>());
        images.put("rightImages", new ArrayList<BufferedImage>());

    }

    public void addImage(String state, String filePath){
        BufferedImage image=ImageLoader.load(filePath);
        image=ImageLoader.scale(image, bredde, høyde);
        switch (state){
            case "idle":
                images.get("idleImages").add(image);
                break;
            case "up":
                images.get("upImages").add(image);
                break;
            case "down":
                images.get("downImages").add(image);
                break;
            case "left":
                images.get("leftImages").add(image);
                break;
            case "right":
                images.get("rightImages").add(image);
                break;
        }
        hasImage=true;
    }

    public void setLayerObjects(ArrayList<GameObject> layerObjects) {
        this.layerObjects = layerObjects;
    }

    public void økXfart(int xfart) {
        this.xfart += xfart;

        if (this.xfart>0){
            state="right";
        }
        else if (this.xfart<0){
            state="left";
        }
    }

    public void økYfart(int yfart) {
        this.yfart += yfart;

        if (this.yfart>0){
            state="down";
        }
        else if (this.yfart<0){
            state="up";
        }
    }
    public void settFart(int xfart, int yfart) {
        this.xfart = xfart;
        this.yfart = yfart;

        if (this.yfart>0){
            state="down";
        }
        else if (this.yfart<0){
            state="up";
        }
        else if (this.xfart>0){
            state="right";
        }
        else if (this.xfart<0){
            state="left";
        }
    }   

    public void oppdaterPosisjon(){
            xpos += xfart;
            ypos += yfart;
    }


    public void tegn(Graphics g) { // Metode for å tegne GameObject på vinduet
        if (!hasImage){
            g.setColor(farge);
            g.fillRect(xpos, ypos, bredde, høyde);
            return;
        } 

        switch(state){
            case "idle":
                getImage(g, "idleImages");
                break;
            
            case "up":
                getImage(g, "upImages");
                break;

            case "down":
                getImage(g, "downImages");
                break;

            case "left":
                getImage(g, "leftImages");
                break;

            case "right":
                getImage(g, "rightImages");
                break;
        }
    }

    private void getImage(Graphics g, String state){ //called in tegn() method to draw the correct image
        String imageList=state;
        if (images.get(imageList).size()==0){   //checks if there is an image for the correct animation, if not then gets another image from the list, checks idle first.
            for (String s: images.keySet()){
                if (images.get(s).size()!=0){
                    imageList=s;
                    break;
                }
            }
        }
        if (imageIndex>=images.get(imageList).size()){ //if the index is bigger than the array, then it is goes back to 0
            imageIndex=0;
        }
            g.drawImage(images.get(imageList).get(imageIndex), xpos, ypos, null);
            imageIndex++;

            System.out.print(imageIndex);
    }


    public void sjekkKollisjon() {
        if (KollisjonHorisontal()) {
            if (bounce) {
                xfart = -xfart; // Spretter tilbake
            } else {
                xfart = 0; // Stopper horisontal bevegelse
        }

        }
        if (KollisjonVertikal()) {
            if (bounce) {
                yfart = -yfart; // Spretter tilbake
            } else {
                yfart = 0; // Stopper vertikal bevegelse
            }
        }
    }


    public boolean KollisjonHorisontal(){
        if (xpos <=0) {
            registrerKollisjon();
            return true;
        } else if (xpos + bredde + this.xfart >= gamemap.bredde) {
            return true;
        }

        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.xpos + this.xfart < obj.xpos + obj.bredde &&
                    this.xpos + this.bredde + this.xfart > obj.xpos &&
                    this.ypos < obj.ypos + obj.høyde &&
                    this.ypos + this.høyde > obj.ypos) {
                    registrerKollisjon();   // Kall registrerKollisjon ved kollisjon
                    if (!obj.isMovable() && !isMovable()){  //if the other object is not movable and this object is neither
                        return true;                        //then collision will be registered
                    }  else if(isMovable()){    //Otherwise if this object is movable, collision works as usual
                        return true;        
                    }                                     
                }
            }
        }
        return false;
    }

    public boolean KollisjonVertikal(){
        if (ypos <= 0) {
            registrerKollisjon();
            return true;
        } else if (ypos + høyde + this.yfart >= gamemap.høyde) {
            return true;
        }
        for (GameObject obj : layerObjects) {
            if (this != obj) { // Sjekker at det ikke er samme GameObject
                if (this.ypos + this.yfart < obj.ypos + obj.høyde &&
                    this.ypos + this.høyde + this.yfart > obj.ypos &&
                    this.xpos < obj.xpos + obj.bredde &&
                    this.xpos + this.bredde > obj.xpos) {
                    registrerKollisjon();   //Kall registrerKollisjon ved kollisjon
                    if (!obj.isMovable() && !isMovable()){  //if the other object is not movable and this object is neither
                        return true;                        //then collision will be registered
                    } else if(isMovable()){    //Otherwise if this object is movable, collision works as usual
                        return true;        
                    }                                        
                }
            }
        }
        return false;
    }

    public void setBounce(boolean bounce) {
        this.bounce = bounce;
    }  

    public void registrerKollisjon(){   //metode for at kollisjoner skal ha en effekt feks poeng
        //endre hva som skjer for ønsket effekt
    }

    public boolean isMovable(){
        return movable;
    }

}