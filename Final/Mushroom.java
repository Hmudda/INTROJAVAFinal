import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mushroom here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mushroom extends Actor
{
    /**
     * Act - do whatever the Mushroom wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private GreenfootImage mushroom = new GreenfootImage("mushroom.png");
    private int speed = 1;
    private int mushroomHealth = 3;
    private boolean alive;
    public Mushroom(){
        setImage(mushroom);
        alive = true;
    }
    
    public void act() 
    {
        if(alive){
        if (mushroomHealth <=0){
            removeMushroom();
        }
        else{
            //mushroomHealth--;
            checkFall();
            checkEdge();
            randomizeMushroom();
            moveMushroom();
            checkForMario();
        }
    }
    }    
    
    public void setMushroomHealth(int num){ // sets mushroom health
        mushroomHealth = num;
    }
    
    public void moveMushroom(){// moves the Mushroom
        move(speed);
    }
    
    public void setSpeed(int num){// sets the speed
        speed = num;
    }
    
    public void checkEdge(){//Turns Mushroom Around
        if(alive){
        if(isAtEdge()){
           setSpeed(-speed);
        }
    }
    }
    
    public void randomizeMushroom(){// randomized Mushroom 1 in 22 odds it will move down
        if(alive){
        int height = getRandomNumber(0, 5);
        if(getRandomNumber(0, 22) <= 1){
        if (height > 2){
           setLocation ( getX(), getY() + height);
        }
    }
    }
    }
    
    public void checkForMario(){// checks for Mario
        if(alive){
        Mario mario = (Mario) getOneIntersectingObject(Mario.class);
        if (mario != null){
            setMushroomHealth(0);
        } 
    }
    }
    
    public void removeMushroom(){ // removes mushroom
        getWorld().removeObject(this);
    }
    
    public int getRandomNumber(int start, int end){
        int normal = Greenfoot.getRandomNumber(end-start+1);
        return normal + start;
    }
    
    private void checkFall(){// checks for ground if yes, removes mushroom
       if (testGround()) {
           removeMushroom();
           alive = false;
       }
    }
    
    public boolean alive(){  
        if(alive == true){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean testGround()// checks for ground under Mushroom
    {
        Object ground = getOneObjectAtOffset(0, getImage().getHeight()/2 , Platform.class);
        return ground != null;
    }
}
