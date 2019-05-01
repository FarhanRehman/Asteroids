
import java.awt.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author farhan
 */
public class Debris extends VectorSprite {
    
    public Debris(double x, double y){
        Shape = new Polygon();
        Shape.addPoint(1, 1);
        Shape.addPoint(-1, -1);
        Shape.addPoint(-1, 1);
        Shape.addPoint(1, -1);        
        
        
        drawShape = new Polygon();
        drawShape.addPoint(1, 1);
        drawShape.addPoint(-1, -1);
        drawShape.addPoint(-1, 1);
        drawShape.addPoint(1, -1);
        
        xpos = x;
        ypos = y;
        angle = Math.random() * 2 * Math.PI;
        THRUST = 10;
        
        xspeed = Math.cos(angle) * angle;
        yspeed = Math.sin(angle) * angle;
        
        active = true;
       
    
    
    
    
    }
    
    
}
