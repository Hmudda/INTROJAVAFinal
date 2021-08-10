import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mario here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mario extends Actor
{
    /**
     * Act - do whatever the Mario wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    private GreenfootImage mario = new GreenfootImage("marioStatic.png");    
    private GreenfootImage marioLeft = new GreenfootImage("marioLeft.png");    
    private GreenfootImage marioRight = new GreenfootImage("marioRight.png");
    private GreenfootImage marioLeft2 = new GreenfootImage("marioLeft2.png");    
    private GreenfootImage marioRight2 = new GreenfootImage("marioRight2.png");
    private GreenfootImage marioJump = new GreenfootImage("marioJump.png");
    
    
    private int reloadCounter;
    private int reloadTime = 50;
    
    private int height;
    
    //private int fireHealth = 100;
    //private int fireCounter;
    
    private int numFireBalls;
    public Mario()
    {
        setImage(mario);
        reloadCounter = 55;
        numFireBalls = 3;
    }
    
    public void act() 
    {
        movement();
        checkFall();
        kill();
        reloadCounter++;
        checkForMushroom();
        //fireCounter++;
        //removeFireBall();
    }
    
    private void movement(){
        setImage(mario);
        if (Greenfoot.isKeyDown("left")){
            moveLeft();
        }
        if (Greenfoot.isKeyDown("right")){
            moveRight();
        }
        if (Greenfoot.isKeyDown("up") && testGround()){   
            moveJump();
        }
        if (Greenfoot.isKeyDown("x") && !Greenfoot.isKeyDown("left")){
            fireball();
        }
        if (Greenfoot.isKeyDown("x") && Greenfoot.isKeyDown("left")){
            fireballLeft();
        }
    }
    
    private void moveLeft(){
        setImage(marioLeft);
        move(-2);
    }
    
    private void moveRight(){
        setImage(marioRight);
        move(2);
    }
    
    //fireball
    
    
    private void fireball(){ // fireball going right
        if(numFireBalls != 0 && reloadCounter >= reloadTime){
            Fireball fireball = new Fireball();
            fireball.setSpeed(2);
            getWorld().addObject (fireball, getX()+ 15, getY());
            numFireBalls--; 
            reloadCounter = 0;
            //fireCounter = 0;
        }
    }
   
    private void fireballLeft(){// fireball going left
        if(numFireBalls != 0 && reloadCounter >= reloadTime){
            Fireball fireball2 = new Fireball();
            fireball2.setSpeed(-2);
            getWorld().addObject (fireball2, getX(), getY());
            numFireBalls--;
            reloadCounter = 0;
            //fireCounter= 0;
        }
    }
    
    public void addFireBalls(int num){
        numFireBalls +=  num;
    }
    //Testing for Goomba interaction
    // V
    
    public boolean testBody()//checks for Goomba
    {
        Object ground = getOneObjectAtOffset(getImage().getWidth() - 15,0 , Goomba.class);
        return ground != null;
    }
    
    public void kill(){//kill mario!!!!
        if(testBody()){
            getWorld().removeObject(this);
        }
    }

    
    //                    Check for Mushroom
    public void checkForMushroom(){// checks for Mario
        Mushroom mushroom = (Mushroom) getOneIntersectingObject(Mushroom.class);
        if (mushroom != null){
            addFireBalls(1);
        } 
    }
    
    //
    //
    //
    //                Jumping/Gravity
    //
    //                       V
    //
    //
    private void moveJump(){// do jump
        setImage(marioJump);
        setHeight(-11);
        moveDown();
    }
    
    private void setHeight(int number){// set jump height to a num
        height = number;
    }
    
    
    private void checkFall(){// checks for ground and if no, implements gravity
       if (testGround()) {
           setHeight(0);
       }
       else {
           moveDown();
       }
    }
    
    private void moveDown(){ // acts like gravity
        setLocation ( getX(), getY() + height);
        height += 1;
    }

    public boolean testGround()// checks for ground under Mario
    {
        Object ground = getOneObjectAtOffset(0, getImage().getHeight()/2 , Platform.class);
        return ground != null;
    }

}
