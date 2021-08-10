import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Fireball here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fireball extends Mario
{
    private GreenfootImage fireball = new GreenfootImage("fireball.png");
    private int speed;
    private int fireHealth;
    /**
     * Act - do whatever the Fireball wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public Fireball(){
        setImage(fireball);
        fireHealth = 200;
    }
    
    public void act() 
    {
        if (fireHealth <=0){
            removeFire();
        }
        else{
            fireHealth--;
            rotateFire();
            moveFire();
            checkFireBall();
        }
    }    
    
    public void rotateFire(){
        fireball.rotate(8);
    }
    
    public void moveFire(){// moves fire
        move(speed);
    }
    
    public void setSpeed(int number){// sets speed of fireball
        speed = number;
    }
    
    public void removeFire(){// for when fireball timer goes down
        getWorld().removeObject(this);
    }
    
    public void checkFireBall(){// checks for Goomba
        Goomba goomba = (Goomba) getOneIntersectingObject(Goomba.class);
        if (goomba != null){
            setHealth(1);
        } 
    }
    
    public int getHealth(){
        return fireHealth;
    }
    
    public void setHealth(int health){
        this.fireHealth = health;
    }
}
