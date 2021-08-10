import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Healthbar here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Healthbar extends Actor
{
    /**
     * Write a description of class Healthbar here.
     * 
     * @author (your name) 
     * @version (a version number or a date)
     */
    int health;
    private String healthString;
    private GreenfootImage background = new GreenfootImage("Counter.png");
    Color trans = new Color(0, 0, 0, 0);
    private GreenfootImage healthbar;

    public Healthbar(){
        health = 3;
        GreenfootImage HealthbarNum= new GreenfootImage(new GreenfootImage("Health: " + health, 24, Color.BLACK, trans));
        background.drawImage(HealthbarNum, 5, 0);
        setImage(background);
    }

    public void act() 
    {
    }   

    public int getHealth(){
        return health;
    }

    public void subtractHealth(){
        health--;
        reDrawHealth();
    }

    public void reDrawHealth(){
        background.clear();
        GreenfootImage background = new GreenfootImage("Counter.png");
        GreenfootImage HealthbarNum= new GreenfootImage(new GreenfootImage("Health: " + health, 24, Color.BLACK, trans));
        background.drawImage(HealthbarNum, 5, 0);
        setImage(background);
    }
}
