
import java.awt.Polygon;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author farhan
 */
public class Asteroid extends VectorSprite {
    int size;
    public Asteroid(){
        size = 2;
        initAsteroid();

        
        
    }
    public void updatePosition(){
        angle += ROTATION;
        super.updatePosition();
    
    
    }
    public Asteroid(double x, double y, int s){
        size = s;
        initAsteroid();
        xpos = x;
        ypos = y;
        

    }
    public void initAsteroid() {
        
        Shape = new Polygon();
        Shape.addPoint(30 * size, 3 * size);
        Shape.addPoint(5 * size, 35 * size);
        Shape.addPoint(-25 * size, 10 * size);
        Shape.addPoint(-17 * size, -15 * size);
        Shape.addPoint(20 * size, -35 * size);
        

        drawShape = new Polygon();
        drawShape.addPoint(30 * size, 3 * size);
        drawShape.addPoint(5 * size, 35 * size);
        drawShape.addPoint(-25 * size, 10 * size);
        drawShape.addPoint(-17 * size, -15 * size);
        drawShape.addPoint(20 * size, -35 * size);
        
        
        
        ROTATION = 0.15;
        THRUST = 0.5;
        double h, a;
        h = Math.random()+ 1;
        a = Math.random()*2*Math.PI;

        xspeed = Math.cos(a) * h;
        yspeed = Math.sin(a) * h;
        h = Math.random()*400+100;
        a = Math.random()*2*Math.PI;
        xpos = Math.cos(a)*h +450;
        ypos = Math.cos(a)*h +300;
        active = true;
        
        
        
    }

}

    




