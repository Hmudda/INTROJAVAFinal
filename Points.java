import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Points here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Points extends Actor
{
    /**
     * Act - do whatever the Points wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */

    int points;
    private String pointsString;
    private GreenfootImage background = new GreenfootImage("Counter.png");
    Color trans = new Color(0, 0, 0, 0);
    private GreenfootImage PointsNum;

    public Points(){
        points = 0;
        GreenfootImage PointsNum= new GreenfootImage(new GreenfootImage("Points: " + points, 24, Color.BLACK, trans));
        background.scale(PointsNum.getWidth() + 30, background.getHeight());
        background.drawImage(PointsNum, 5, 0);
        setImage(background);
    }

    public void act() 
    {
        
    }   

    public int getPoints(){
        return points;
    }

    public void subtractPoints(int num){
        points -= num;
        reDrawHealth();
    }

    public void addPoints(int num){
        points += num;
        reDrawHealth();
    }

    public void reDrawHealth(){
        background.clear();
        GreenfootImage background = new GreenfootImage("Counter.png");
        GreenfootImage PointsNum= new GreenfootImage(new GreenfootImage("Points: " + points, 24, Color.BLACK, trans));
        background.scale(PointsNum.getWidth() + 30, background.getHeight());
        background.drawImage(PointsNum, 5, 0);
        setImage(background);
    }
}
