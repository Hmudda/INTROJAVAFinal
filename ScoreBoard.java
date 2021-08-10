import greenfoot.*;

/**
 * Write a description of class ScoreBoard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ScoreBoard extends Actor
{

    public ScoreBoard()
    {
        this(100);
    }

    public ScoreBoard(int score)
    {
        makeImage("Game Over", "Your Score: ", score);
    }

    private void makeImage(String gameOver, String scoreList, int score)
    {
        GreenfootImage image = new GreenfootImage(400, 400);

        image.setColor(Color.BLACK);
        image.fillRect(0, 0, 400, 400);
        image.setColor(Color.WHITE);
        image.fillRect(5, 5, 400-10, 400-10);
        image.setColor(Color.BLACK);
        image.drawString(gameOver, 60, 100);
        image.drawString(scoreList + score, 60, 200);
        setImage(image);
    }
}
