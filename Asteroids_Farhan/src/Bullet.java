
import java.awt.Polygon;
import java.util.ArrayList;

public class Bullet extends VectorSprite {
    
    
    public Bullet(double x, double y, double a){
        
        Shape = new Polygon();
        Shape.addPoint(0, 0);
        Shape.addPoint(0, 0);
        Shape.addPoint(0, 0);
        Shape.addPoint(0, 0);        
        
        
        drawShape = new Polygon();
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        drawShape.addPoint(0, 0);
        
        xpos = x;
        ypos = y;
        angle = a;
        THRUST = 10;
        
        xspeed = Math.cos(angle) * THRUST;
        yspeed = Math.sin(angle) * THRUST;
        
        active = true;
       
    }
    
    
    
    
    
}
