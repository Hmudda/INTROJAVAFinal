import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Goomba here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Goomba extends Actor
{
    /**
     * Act - do whatever the Goomba wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage goomba = new GreenfootImage("goomba.png");
    private int speed = -1;
    boolean alive;
    
    public Goomba()
    {
        setImage(goomba);
        alive = true;
    }
    
    public void act() 
    {
        //if(alive()){
        walk();
        checkEdge();
        //die();
        testFireBall();
        //testFire();
        testSmash();
    //}
    }   
    
    public void walk(){//moves Goomba at speed
        move(speed);
    }
    
    public void checkEdge(){//Turns Goomba Around
        if(isAtEdge()){
           setSpeed(-speed);
        }
    }
    
    public void setSpeed(int number){// set a number to walking speed
        speed = number;
    }
    
    public boolean testHead()// Check for Mario stomping Goomba
    {
        if(alive()){
            Object head = getOneObjectAtOffset(0, getImage().getHeight()-45 , Mario.class);
            return head != null;
    }
        else{
            return false;
        }
    }
    
    public boolean testFire()//Check for Fireball 
    {
        if(alive()){
        Fireball fireball = (Fireball) getOneIntersectingObject(Fireball.class);
            if (fireball != null){
                //getWorld().removeObject(this);
                die();
                alive = false;
            }
        }
        return false;
    }
    
    public void die(){ // die!!!
        //if(testHead()){
            getWorld().removeObject(this);
        //}
    }
    
    public boolean alive(){  
        if(alive == true){
            return true;
        }
        else{
            return false;
        }
    }
    
    public void testSmash(){
        if(testHead()){
            die();
            alive = false;
        }
    }
    
    public void testFireBall(){
        if(testFire()){
            die();
            alive = false;
        }
    }
    
    
}
