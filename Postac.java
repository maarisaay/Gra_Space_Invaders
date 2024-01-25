package GUI_p02;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Image;
import java.security.spec.ECField;
import java.net.URL;

abstract class Postac {
    int x;
    int y;
    int width;
    int height;
    private int speed;
    private Image image;
    boolean moveLeft = false;
    boolean moveRight = false;
    boolean moveUp = false;
    boolean moveDown = false;

    public Postac(){
        x = 0;
        y = 0;
    }

    public Postac(int x, int y, int width, int height, int speed, String u){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

        try{
            URL url = getClass().getResource(u);
            image = ImageIO.read(url);
        }catch (Exception e){

        }
    }

    public abstract void move(int direction);

    public abstract void draw(Graphics window);

    public String toString(){
        return getX() + " " + getY() + " " + getWidth() + " " + getHeight();
    }

    public void setPos(int x, int y){
        x = getX();
        y = getY();
    }

    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setWidth(int w){
        this.width = w;
    }

    public void setHeight(int h){
        this.height = h;
    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public void setSpeed(int speed){
        this.speed = speed;
    }

    public int getSpeed(){
        return speed;
    }

    public Image getImage(){
        return image;
    }

}
