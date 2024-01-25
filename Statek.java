package GUI_p02;

import java.awt.*;

public class Statek extends Postac{

    public Statek(){
        super();
    }

    public Statek(int x, int y, int w, int h, int s, String u){
        super(x, y, w, h, s, "ship.png");
    }

    public void draw(Graphics window){
        window.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);

    }

    public void move(int d){
        if(moveLeft && getX()>0){
            setX(getX()-getSpeed());
        }
        if(moveRight && getX()<543){
            setX(getX()+getSpeed());
        }
        if(moveUp && getY()>0){
            setY(getY()-getSpeed());
        }
        if(moveDown && getY()<445){
            setY(getY()+getSpeed());
        }
    }

    public void setLeftRight(int d){
        if(d==37){
            moveLeft = true;
        }
        if(d==39){
            moveRight = true;
        }
        if(d==38){
            moveUp = true;
        }
        if(d==40){
            moveDown = true;
        }
    }

    public void stop(){
        moveLeft = false;
        moveRight = false;
        moveUp = false;
        moveDown = false;
    }
}
