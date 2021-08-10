import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    private int mushroomCounter = 0;
    private Mushroom firstMushroom;
    private Healthbar theHealthbar;
    private FireballCounter theFireballBar;
    private Points thePointBar;
    private ScoreBoard score;
    private int level = 1;
    private int startGoombas = 1;
    private int goombaCount = 1;

    private GreenfootSound loop = new GreenfootSound("loop.mp3");
    private GreenfootSound gameOverSound = new GreenfootSound("gameOver.wav");

    public MyWorld()
    {    

        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        Mario mario = new Mario();
        addObject(mario,300,100);

        addObject ( new Platform(), 300, 310);

        addGoombas(startGoombas);

        firstMushroom = new Mushroom();        
        addObject(firstMushroom,getRandomNumber(0, 600), getRandomNumber(50, 200));

        theHealthbar = new Healthbar();
        addObject(theHealthbar, 60, 20);

        theFireballBar = new FireballCounter();
        addObject(theFireballBar, 60, 50);

        thePointBar = new Points();
        addObject(thePointBar, 70, 80);

    }

    public Points getPoints(){
        return thePointBar;
    }

    public Healthbar getHealth(){
        return theHealthbar;
    }

    public FireballCounter getFireballCounter(){
        return theFireballBar;
    }

    public int getRandomNumber(int start, int end){
        int normal = Greenfoot.getRandomNumber(end-start+1);
        return normal + start;
    }

    private void addGoombas(int num) 
    {
        for(int i = 0; i < num; i++) 
        {
            int x = Greenfoot.getRandomNumber(getWidth()/2);
            if (goombaCount % 2 == 0){
                x += 10;
            }
            int y = 210;
            addObject(new Goomba(), x, y);
        }
    }

    public void nextLevel(){
        int total;
        total = startGoombas + level;
        addGoombas(total);
        level++;
        goombaCount = total;
    }

    public int getGoombaCount(){
        return goombaCount;
    }

    public void setGoombaCount(int num){
        goombaCount = num;
    }

    public void playLoop(){
        loop.playLoop();
    }

    public void gameOver(){
        loop.stop();
        gameOverSound.play();
        addObject(new ScoreBoard(thePointBar.getPoints()), getWidth()/2, getHeight()/2);

    }

}
