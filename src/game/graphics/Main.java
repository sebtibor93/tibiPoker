package game.graphics;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import java.io.*;

public class Main
{
    public static Game game;
    
    public static void main(String args[]) throws FileNotFoundException, IOException
    {
        initDisplay();
        initGL();
        gameLoop();
        cleanUp();
    }
    
    public static void initDisplay()
    {
        try
        {
            Display.setDisplayMode(new DisplayMode(1280, 720));
            Display.create();
        }
        catch(LWJGLException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void initGL()
    {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0,Display.getWidth(),0,Display.getHeight(),-1,1);
        glMatrixMode(GL_MODELVIEW);
        
        glClearColor(0,0,0,1);
        
        glDisable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
    }
    
    public static void gameLoop()
    {
        game = new Game();
        while(!Display.isCloseRequested())
        {
            game.getInput();
            game.update();
            game.render();
            
            Display.update();
        }
    }
    
    public static void cleanUp()
    {
        Display.destroy();
    }  
}