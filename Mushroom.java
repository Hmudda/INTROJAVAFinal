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
    private int speed;
    private int mushroomHealth;
    private boolean alive;
    private boolean touched;
    private int mushroomCounter;
    private int timer;
    public Mushroom(){
        setImage(mushroom);
        speed = 1;
        alive = true;
        mushroomHealth = 3;
        mushroomCounter = 0;
        touched = false;
        timer = 1;
    }

    public void act() 
    {
        if(alive()){
            if (mushroomHealth <=0){
                removeMushroom();
            }
            else{
                checkFall();
                checkEdge();
                randomizeMushroom();
                moveMushroom();
                checkForMario();
            }
        }
        if (touched == true){
            timer--;
            if (timer <= 0){
                randomItem();
                timer = 2;
            }
        }
    }    

    
    public void randomItem(){// spawns a mushroom in a random location above platform
        Mushroom mushroom = new Mushroom();
        MyWorld world = (MyWorld) getWorld();
        world.addObject(mushroom,Greenfoot.getRandomNumber(600), getRandomNumberBetween(50, 200));
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

    public void checkEdge(){//Turns Mushroom Around if at edge and if alive
        if(alive){
            if(isAtEdge()){
                setSpeed(-speed);
            }
        }
    }

    public void randomizeMushroom(){// randomized Mushroom 1 in 22 odds it will move down
        if(alive){
            int height = Greenfoot.getRandomNumber(5);
            if(Greenfoot.getRandomNumber(22) <= 1){
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
                touched = true;
            } 
        }
    }

    public void removeMushroom(){ // removes mushroom
        if(alive()){
            getWorld().removeObject(this);
        }
    }

    public int getRandomNumberBetween(int start, int end){ // make a random number between start and end
        int normal = Greenfoot.getRandomNumber(end-start+1);
        return normal + start;
    }

    private void checkFall(){// checks for ground if yes, removes mushroom
        if (testGround()) {
            setMushroomHealth(0);
            touched = true;
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
