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
    private GreenfootSound stomp = new GreenfootSound("stomp.wav");

    private int speed;
    private boolean alive;
    private int health;

    public Goomba()
    {
        setImage(goomba);
        alive = true;
        speed = getRandomNumber(-1, 2);
        health = 1;
    }

    public void act() 
    {
        walk();
        checkEdge();
        testFire();
        testSmash();
        checkHealth();
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
                MyWorld world = (MyWorld) getWorld();
                Points counter = world.getPoints();
                counter.addPoints(10);
                world.setGoombaCount(world.getGoombaCount() - 1);
                if(world.getGoombaCount() == 0){
                    world.nextLevel();
                }
                health = 1;
                health--;
            }
        }
        return false;
    }

    public void checkHealth(){
        if (health <= 0){
            die();
        }
    }

    public void die(){ // die!!!
        getWorld().removeObject(this);
    }

    public boolean alive(){  // alive
        if(alive == true){
            return true;
        }
        else{
            return false;
        }
    }

    public void testSmash(){// test for head smashed by mario
        if(testHead()){
            stomp.play();
            MyWorld world = (MyWorld) getWorld();
            Points counter = world.getPoints();
            counter.addPoints(50);
            world.setGoombaCount(world.getGoombaCount() - 1);
            if(world.getGoombaCount() == 0){
                world.nextLevel();
            }
            die();
            alive = false;
        }
    }

    public int getRandomNumber(int start, int end){// random number for speeds of goomba != 0
        int normal = Greenfoot.getRandomNumber(end-start+1);
        if((normal + start) != 0){
            return normal + start;
        }
        else{
            return 1;
        }
    }
}
