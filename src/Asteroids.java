/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.Frame;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

/**
 *
 * @author farhan
 */
public class Asteroids extends Applet implements KeyListener, ActionListener {

    Spacecraft ship, TriAcc, scoreShipOne, scoreShipTwo, scoreShipThree;
    Timer Clock;
    Image offscreen;
    Graphics offg;
    int score = 0;

    boolean upKey, leftKey, rightKey, shoot;
    ArrayList <Asteroid> asteroidList;
    ArrayList <Bullet> bulletList;
    ArrayList <Debris> debrisList;
    
    int shipLives = 3;
    
    
    /**
     * Initialization method that will be called after the applet is loaded into
     * the browser.
     */

    @Override
    public void init() {
        // TODO start asynchronous download of heavy resources
        
        this.setSize(900, 600);
        this.addKeyListener(this);
        ship = new Spacecraft();
        
        scoreShipOne = new Spacecraft();
        scoreShipTwo = new Spacecraft();
        scoreShipThree = new Spacecraft();
        TriAcc = new Spacecraft();
        Clock = new Timer(20, this);
        
        offscreen = createImage(this.getWidth(), this.getHeight());
        offg = offscreen.getGraphics();
        
        asteroidList = new ArrayList();
        bulletList = new ArrayList();
        debrisList = new ArrayList();
        
        
        for(int i = 0; i < 6; i++){
            asteroidList.add(new Asteroid());
        }
        
        
        

    }

    @Override
    public void paint(Graphics g) {
        offg.setColor(Color.BLACK);
        offg.fillRect(0, 0, 900, 600);
        offg.setColor(Color.white);
        offg.setFont(new Font("Gravity-Light", Font.PLAIN, 24));  
        shipLives();
        score();
                
        
        if (ship.active){
            ship.paint(offg);
            
            //TriAcc.paint(offg);
        }
        
        
        for(int i = 0; i < asteroidList.size(); i++){
            asteroidList.get(i).paint(offg);
        }
        
        for(int i = 0; i < bulletList.size(); i++){
            bulletList.get(i).paint(offg);
        }
        
        for(int i = 0; i < debrisList.size(); i++){
           debrisList.get(i).paint(offg);
        }
        

        
        g.drawImage(offscreen, 0, 0, this);
        repaint();
    }
    


    public void checkCollisions(){
        for(int i = 0; i < asteroidList.size(); i++){
            if (collision(asteroidList.get(i), ship)){
                debrisList.add(new Debris(ship.xpos, ship.ypos));
                ship.hit();
            }
        
            for (int j = 0; j < bulletList.size(); j++){
                if (collision(asteroidList.get(i), bulletList.get(j))){
                    bulletList.get(j).active = false;
                    asteroidList.get(i).active = false;
                    debrisList.add(new Debris(asteroidList.get(i).xpos, asteroidList.get(i).ypos));
                }
            }
        }
    }
    
    public boolean collision(VectorSprite thing1, VectorSprite thing2){
        for(int i =0;i < thing2.drawShape.npoints;i++){
            int x = thing2.drawShape.xpoints[i];
            int y = thing2.drawShape.ypoints[i];

            if (thing1.drawShape.contains(x, y)){
                return true;
            }
        }

        for(int i =0;i < thing1.drawShape.npoints;i++){
            int x = thing1.drawShape.xpoints[i];
            int y = thing1.drawShape.ypoints[i];
            
            if (thing2.drawShape.contains(x, y)){
                return true;
            }
        }
        
        return false;
    }
         
  

    @Override
    public void update(Graphics g) {
        paint(g);
         
        
    }
    
    public int score(){
        
        offg.drawString("Score: " + Integer.toString(score) , 2, 20);
        for(int i = 0; i < asteroidList.size(); i++){
            for (int j = 0; j < bulletList.size(); j++){
                if (collision(asteroidList.get(i), bulletList.get(j))){
                    score = score + 50;
                }
            }
        }
        return 0;
    }
    
    public void shipLives(){
        //offg.drawString("Lives: " + Integer.toString(shipLives) , 800, 15);
        
        if (shipLives == 3){
            scoreShipOne.paint(offg);
            scoreShipTwo.paint(offg);
            scoreShipThree.paint(offg);

            scoreShipOne.active = false;
            scoreShipTwo.active = false;
            scoreShipThree.active = false;

            scoreShipOne.xpos = 750;
            scoreShipOne.ypos = 25;
            scoreShipOne.angle = -Math.PI/2;

            scoreShipTwo.xpos = 800;
            scoreShipTwo.ypos = 25;
            scoreShipTwo.angle = -Math.PI/2;

            scoreShipThree.xpos = 850;
            scoreShipThree.ypos = 25;
            scoreShipThree.angle = -Math.PI/2;
        }
        
        if (shipLives == 2){
            scoreShipOne.paint(offg);
            scoreShipTwo.paint(offg);

            scoreShipOne.active = false;
            scoreShipTwo.active = false;

            scoreShipOne.xpos = 750;
            scoreShipOne.ypos = 25;
            scoreShipOne.angle = -Math.PI/2;

            scoreShipTwo.xpos = 800;
            scoreShipTwo.ypos = 25;
            scoreShipTwo.angle = -Math.PI/2;

        }
        
        if (shipLives == 1){
            scoreShipOne.paint(offg);

            scoreShipOne.active = false;

            scoreShipOne.xpos = 750;
            scoreShipOne.ypos = 25;
            scoreShipOne.angle = -Math.PI/2;

        }
        
        
        if (shipLives == 0){            
            offg.setFont(new Font("Gravity-Light", Font.PLAIN, 24)); 
            offg.drawString("Game Over", 400, 300);
            ship.active = false;
            upKey = false;
            leftKey = false;
            rightKey = false;
            shoot = false;
            

        }
    }
        

    // TODO overwrite start(), stop() and destroy() methods
    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = true;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upKey = true;
            
        }

//        if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
//            spaceBar = true;
//        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE){
            shoot = true;     
        }
        repaint();

    }
    
    public void respawnShip(){

        if (ship.active == false && ship.counter > 50 && shipLives >= 1 && isRespawnSafe()==true){
            shipLives -= 1;
            ship.reset();
            
        }
    }
    
    
    
    public boolean isRespawnSafe(){
        for(int i = 0; i < asteroidList.size(); i++){
            double a = asteroidList.get(i).xpos - 450,
                   b = asteroidList.get(i).ypos - 300;
            double c = (a*a)+(b*b);
            c = Math.sqrt(c); 

            if (c < 100){
                return false;
            }

        }
        return true;
    }
    
//    public void Retry(MouseEvent e){
//        offg.drawRoundRect(400, 300, 50, 50, 10, 10);
//        if (rect.contains(e.getPoint()) {
//        // Was clicked...
//        }
//    }
//    
    public void respawnAsteroids(){

        if (asteroidList.isEmpty() && shipLives != 0 ){
            for(int i = 0; i < 6; i++){
                asteroidList.add(new Asteroid());
            }
        }
        
    }
    
    
    public void keyCheck(){
        if (upKey){
            ship.accelerate();
            TriAcc.accelerate();
            
        }
        if (leftKey){
            ship.rotateLeft();
        }
        if (rightKey){
            ship.rotateRight();
        }
        if (shoot){
            FireBullet();
                
            
            
        }
    }
    
    
    public void FireBullet(){
        if (ship.counter > 7 && ship.active == true){
            bulletList.add(new Bullet(ship.xpos, ship.ypos, ship.angle));
            ship.counter = 0;

        }
    
    }
    
    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
            rightKey = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
            leftKey = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_UP) {
            upKey = false;
        }
        if (ke.getKeyCode() == KeyEvent.VK_SPACE){
            shoot = false;     
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        isRespawnSafe();
        respawnShip();
        keyCheck();
        ship.updatePosition();
        scoreShipOne.updatePosition();
        scoreShipTwo.updatePosition();
        scoreShipThree.updatePosition();
        score(); 
        shipLives();
        
        respawnAsteroids();
        
        checkCollisions();
        checkAsteroidDestruction();
        
        
        for (int i  = 0; i < bulletList.size(); i++){
            bulletList.get(i).updatePosition();
            
            if (bulletList.get(i).xpos >= 900){
                bulletList.get(i).active = false;}

            if(bulletList.get(i).xpos <= 0){
                bulletList.get(i).active = false;}

            if(bulletList.get(i).ypos >= 600){
                bulletList.get(i).active = false;}

            if(bulletList.get(i).ypos <= 0){
                bulletList.get(i).active = false;}   
            
            if (bulletList.get(i).counter == 50 || bulletList.get(i).active == false) {
                bulletList.remove(i);
            }
        }      
         for (int i  = 0; i < debrisList.size(); i++){
             debrisList.get(i).updatePosition();
             if (ship.counter > 50 && shipLives > 0 ){
                 debrisList.remove(i);                 
             }
             if (ship.counter > 20 && shipLives == 0 ){
                if (debrisList.get(i).xpos >= 900){
                    debrisList.get(i).active = false;}

                if(debrisList.get(i).xpos <= 0){
                    debrisList.get(i).active = false;}

                if(debrisList.get(i).ypos >= 600){
                    debrisList.get(i).active = false;}

                if(debrisList.get(i).ypos <= 0){
                    debrisList.get(i).active = false;}                  
             }
         }
    }
    public void checkAsteroidDestruction() {
        for(int i = 0; i < asteroidList.size(); i++){
            asteroidList.get(i).updatePosition();
            if (asteroidList.get(i).active == false){
                if (asteroidList.get(i).size > 1) {
                    asteroidList.add(new Asteroid(asteroidList.get(i).xpos + 5, asteroidList.get(i).ypos - 5, asteroidList.get(i).size - (int) 1));
                    asteroidList.add(new Asteroid(asteroidList.get(i).xpos + 5, asteroidList.get(i).ypos - 5, asteroidList.get(i).size - (int) 1));
                    
                }
                asteroidList.remove(i);
            }
            
        }    
    }

    
    
    @Override
    public void start() {
        Clock.start();

    }
    @Override
    public void stop() {
        Clock.stop();

    }

    private void setDefaultCloseOperation(int EXIT_ON_CLOSE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void pack() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void setLocationRelativeTo(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
