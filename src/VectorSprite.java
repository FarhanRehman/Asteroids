                        /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;

/**
 *
 * @author farhan
 */
public class VectorSprite {

    double xspeed;
    double yspeed;
    double xpos;
    double ypos;
    double triX;
    double triY;
    double sxpos;
    double sypos;
    double angle;
    Polygon Shape, drawShape, TriAcc, scoreShip;
    
    double ROTATION;
    double THRUST;
    boolean active;
    int counter;
        
   
    public VectorSprite() {

        
    
    }
    
     
    
    public void paint(Graphics g){
        g.drawPolygon(drawShape);
      
        
        
    }
    
    public void wrapAround(){
        if(xpos>900)
            xpos=0;
        if(xpos<0)
            xpos=900;
        if(ypos>600)
            ypos=0;
        if(ypos<0)
            ypos=600;
    
    }
    
    public void updatePosition(){
        xpos += xspeed;
        ypos += yspeed;
        wrapAround();
        int x,y;
        for(int i =0;i < Shape.npoints;i++){
            //Shape.xpoints[i] += xspeed;
            //Shape.ypoints[i] += yspeed;
            drawShape.xpoints[i] = (int) Math.round(Shape.xpoints[i] * Math.cos(angle) - Shape.ypoints[i] * Math.sin(angle));
            drawShape.ypoints[i] = (int) Math.round(Shape.xpoints[i] * Math.sin(angle) + Shape.ypoints[i] * Math.cos(angle));
            
            
            
        }
        drawShape.invalidate();
        drawShape.translate((int) Math.round(xpos), (int) Math.round(ypos));

        counter++;
        wrapAround(); 
    
    }
}

