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

    private GreenfootSound jump = new GreenfootSound("jump.wav");
    private GreenfootSound fireball = new GreenfootSound("fireball.wav");
    private GreenfootSound mushroomSound = new GreenfootSound("mushroom.wav");
    private GreenfootSound bump = new GreenfootSound("bump.wav");

    private int reloadCounter;
    private int reloadTime;

    private int height;
    private int health;
    private int invincible;

    private Mushroom mushroomCheck;

    private int numFireBalls;
    private int fireballBuffer;

    private boolean alive;

    private int Lframe;
    private int Rframe;
    public Mario()
    {
        setImage(mario);
        reloadTime = 50;
        reloadCounter = 55;
        numFireBalls = 3;
        alive = true;
        health = 3;
        invincible = 10;
        fireballBuffer = 1;
    }

    public void act() 
    {
        movement();
        checkFall();
        kill();
        reloadCounter++;
        checkForMushroom();
        invincible--;
        if(fireballBuffer >= 0){
            fireballBuffer--;
        }
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

    private void moveLeft(){// basic movement animation
        leftAnimate();
        move(-2);
    }

    private void leftAnimate(){
        if(Lframe >= 1 && Lframe < 10){
            setImage(marioLeft);
        }
        else if (Lframe >= 10 && Lframe < 20){
            setImage(marioLeft2);
        }
        else if(Lframe == 20){
            setImage(marioLeft);
            Lframe = 1;
            return;
        }
        Lframe++;
    }

    private void moveRight(){ // basic movement animation
        rightAnimate();
        move(2);
    }

    private void rightAnimate(){
        if(Rframe >= 1 && Rframe < 10){
            setImage(marioRight);
        }
        else if (Rframe >= 10 && Rframe < 20){
            setImage(marioRight2);
        }
        else if(Rframe == 20){
            setImage(marioRight);
            Rframe = 1;
            return;
        }
        Rframe++;
    }

    //fireballs
    private void fireball(){ // fireball going right
        if(numFireBalls != 0 && reloadCounter >= reloadTime){
            fireball.play();
            Fireball fireball = new Fireball();
            fireball.setSpeed(2);
            getWorld().addObject (fireball, getX()+ 15, getY());
            MyWorld world = (MyWorld) getWorld();
            FireballCounter counter = world.getFireballCounter();
            counter.subtractFireballs();
            numFireBalls--; 
            reloadCounter = 0;
        }
    }

    private void fireballLeft(){// fireball going left
        if(numFireBalls != 0 && reloadCounter >= reloadTime){
            fireball.play();
            Fireball fireball2 = new Fireball();
            fireball2.setSpeed(-2);
            getWorld().addObject (fireball2, getX(), getY());
            MyWorld world = (MyWorld) getWorld();
            FireballCounter counter = world.getFireballCounter();
            counter.subtractFireballs();
            numFireBalls--;
            reloadCounter = 0;
        }
    }

    public void addFireBall(){// adds fire balls
        numFireBalls++;
    }

    //
    //Testing for Goomba interaction
    // 

    public boolean testBody()//checks for Goomba
    {
        Object ground = getOneObjectAtOffset(getImage().getWidth() - 15,0 , Goomba.class);
        return ground != null;
    }

    public void kill(){//kill mario!!!!
        if(testBody() && invincible <= 0){
            bump.play();
            MyWorld world = (MyWorld) getWorld();
            Healthbar healthbar = world.getHealth();
            healthbar.subtractHealth();
            Points counter = world.getPoints();
            counter.subtractPoints(100);
            health--;
            invincible = 100;
            if(health == 0){
                getWorld().removeObject(this);
                alive = false;
                world.gameOver();
            }
        }
    }
    //
    //
    //                    Check for Mushroom
    public void checkForMushroom(){// checks for Mario
        if(alive()){
            Mushroom mushroom = (Mushroom) getOneIntersectingObject(Mushroom.class);
            if (mushroom != null){
                if(fireballBuffer <= 0){
                    mushroomSound.play();
                    addFireBall();
                    MyWorld world = (MyWorld) getWorld();
                    FireballCounter counter = world.getFireballCounter();
                    counter.addFireballs();
                    fireballBuffer = 5;
                }
            } 
        }
    }

    public boolean alive(){  // sets alive
        if(alive == true){
            return true;
        }
        else{
            return false;
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
        jump.play();
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
        MyWorld world = (MyWorld) getWorld();
        world.playLoop();
    }

    public boolean testGround()// checks for ground under Mario
    {
        Object ground = getOneObjectAtOffset(0, getImage().getHeight()/2 , Platform.class);
        return ground != null;
    }

}
