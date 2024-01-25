package GUI_p02;

import java.awt.*;

public class Wrog extends Postac{

    boolean visible;

    public Wrog(){
        super();
    }

    public Wrog(int x, int y, int w, int h, int s, String u){
        super(x, y, w, h, s, "");
        visible = true;
        moveRight = true;
    }

    public void move(int direction){
        if(moveLeft==true){
            setX(getX()-getSpeed());
        }
        if(moveRight==true){
            setX(getX()+getSpeed());
        }
    }

    public void draw(Graphics window){
        window.fillRect(getX(), getY(), getWidth(), getHeight());
        window.setColor(Color.RED);
    }
}
