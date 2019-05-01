
import java.awt.Polygon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author farha
 */
public class Spacecraft extends VectorSprite {
    public Spacecraft(){
        Shape = new Polygon();
        Shape.addPoint(-10, -15);
        Shape.addPoint(20,0);
        Shape.addPoint(-10, 15);
  
        
        drawShape = new Polygon();
        drawShape.addPoint(-10, -15);
        drawShape.addPoint(20,0);
        drawShape.addPoint(-10, 15);
        
        TriAcc = new Polygon();
        TriAcc.addPoint(drawShape.xpoints[0] - 5, drawShape.ypoints[0]);
        TriAcc.addPoint(drawShape.xpoints[1] - 35, drawShape.ypoints[1]);
        TriAcc.addPoint(drawShape.xpoints[2] + 5, drawShape.ypoints[0]);
        
        
        
        xpos = 450;
        ypos = 300;
        triX = xpos - 50;
        triY = ypos - 50;
        ROTATION = 0.15;
        THRUST = 0.5;
        
        active = true;
}
    
    public void accelerate(){
        
        this.xspeed += Math.cos(this.angle) * this.THRUST;
        this.yspeed += Math.sin(this.angle) * this.THRUST;
                
        for (int i=0;i < TriAcc.npoints;i++){
            TriAcc.xpoints[i] = (int) Math.round(TriAcc.xpoints[i] * Math.cos(angle) - TriAcc.ypoints[i] * Math.sin(angle));
            TriAcc.ypoints[i] = (int) Math.round(TriAcc.xpoints[i] * Math.sin(angle) + TriAcc.ypoints[i] * Math.cos(angle));
        }
        TriAcc.translate((int) Math.round(triX), (int) Math.round(triY));
        
    }

    
    
    
    public void rotateRight(){
        angle += ROTATION;
    }
    
    public void rotateLeft (){
        angle -= ROTATION;
    
    }
    
    public void hit(){        
        active = false;
        counter = 0;
    
    
    }
    
    public void reset(){
        if (active == false){
        xpos = 450;
        ypos = 300;
        xspeed = 0;
        yspeed = 0;
        angle = -Math.PI/2;
        active = true;

    }
    
    
    
    }
   
}
