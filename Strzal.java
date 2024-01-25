package GUI_p02;

import java.awt.*;

public class Strzal extends Postac{

    public Strzal(){
        super();
    }

    boolean goUp;

    public Strzal(int x, int y, int w, int h, int s, String u){
        super(x, y, w, h, s, "shot.png");
        setY(600);
        goUp = false;
    }

    public void move(int d){
        if(getY() < 0){
            goUp = false;
            setY(600);
        }

        if(goUp){
            setY(getY()-getSpeed());
        }
    }

    public void draw(Graphics window){
        window.drawImage(getImage(), getX(), getY(), getWidth(), getHeight(), null);
//        window.fillRect(getX(), getY(), getWidth(), getHeight());
//        window.setColor(Color.YELLOW);
    }

    public void setLeftRight(int d){
        if(d==37){
            moveLeft = true;
        }
        if(d==39){
            moveRight = true;
        }
    }

    public void stop(){
        moveLeft = false;
        moveRight = false;
    }

}
