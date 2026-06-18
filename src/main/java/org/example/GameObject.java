package org.example;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import static org.lwjgl.opengl.GL11.*;

public class GameObject {

    public int xpos, ypos;
    public int height, width;
    private int xspeed=0 , yspeed=0;
    private Color color;

    //public static GameMap gamemap;

    //distance to the closest obstacle
    protected int collisionDistance;

    //how the object act when colliding
    protected boolean bounce=false;


     public GameObject(int x, int y, int height, int width, Color color){
        this.xpos = x;
        this.ypos = y;
        this.height= height;
        this.width=width;
        this.color = color;
      }

      public void draw(){
          glColor3f(color.getRed() / 255f, color.getGreen() / 255f, color.getBlue() / 255f);
          glBegin(GL_QUADS);
          glVertex2f(xpos,ypos);
          glVertex2f(xpos + width, ypos);
          glVertex2f(xpos + width, ypos + height);
         glVertex2f(xpos,     ypos + height);
         glEnd();
     }
}
