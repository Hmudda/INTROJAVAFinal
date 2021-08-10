import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class FireballCounter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireballCounter extends Actor
{
    /**
     * Act - do whatever the FireballCounter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    private int fireballs;
    private String fireballString;
    private GreenfootImage background = new GreenfootImage("Counter.png");
    Color trans = new Color(0, 0, 0, 0);
    private GreenfootImage FireballNum;

    public FireballCounter(){
        fireballs = 3;
        GreenfootImage FireballNum= new GreenfootImage(new GreenfootImage("FBalls: " + fireballs, 24, Color.BLACK, trans));
        background.drawImage(FireballNum, 5, 0);
        setImage(background);
    }

    public void act() 
    {
    }   

    public int getFireballs(){
        return fireballs;
    }

    public void subtractFireballs(){
        fireballs--;
        reDrawHealth();
    }

    public void addFireballs(){
        fireballs++;
        reDrawHealth();
    }

    public void reDrawHealth(){
        background.clear();
        GreenfootImage background = new GreenfootImage("Counter.png");
        GreenfootImage FireballNum= new GreenfootImage(new GreenfootImage("FBalls: " + fireballs, 24, Color.BLACK, trans));
        background.drawImage(FireballNum, 5, 0);
        setImage(background);
    }
}
