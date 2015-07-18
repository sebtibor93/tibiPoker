/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.graphics;

import java.util.ArrayList;
import org.lwjgl.opengl.Display;

public class GOTable extends GameObject
{
    public static final int SIZEX = Display.getWidth();
    public static final int SIZEY = Display.getHeight();
    
    public static boolean FIRST_DEALING = true;
    public static boolean FLOP = false;
    public static boolean TURN = false;
    public static boolean RIVER = false;
    public static boolean SHOWDOWN = false;
    
    public static int raising;
    
    public static boolean FIRST_BETTING = false;
    
    public static float[] POS_FLOP = new float[] {450.f, 325.f, 550.f, 325.f, 650.f, 325.f};
    public static float[] POS_TURN = new float[] {750.f, 325.f};
    public static float[] POS_RIVER = new float[] {850.f, 325.f};
    
    public GOCard[] flop;
    public GOCard turn;
    public GOCard river;
    
    public static int smallBlind;
    public static int bigBlind;
    
    public  int i, j, k, l, m, z;
    
    public float[] t;
    
    //konstruktor
    public GOTable()
    {        
        //this.x = Display.getWidth() / 2 - 500;
        //this.y = Display.getHeight() / 2 - 250;
        this.x = 0;
        this.y = 0;
        this.sx = SIZEX;
        this.sy = SIZEY;  
        
        this.flop = new GOCard[3];
    }
    
    @Override
    public void update()
    {
        //deck.update();
    }
    
    @Override
    public void render()
    {
        //Texture t = Util.loadTexture("asztal_2");
        Game.f[0].bind();
        Util.polygon(x, y, sx, sy, Game.f[0]);
        
        //glClear(GL_COLOR_BUFFER_BIT);
        //glColor3f(0.5f,0.35f,0.05f);
        //rect(x,y,sx,sy);
        //glColor3f(0.2f,0.4f,0.05f);
        //rect(x + 25, y + 25, sx - 50, sy - 50);
        
        //deck.render();
    }
    
    public void firstDealing(ArrayList<GOPlayer> players, GODeck deck)
    {
        //igazzal tér vissza ha a kártya a helyén van
        if(deck.deck.get(i).move(players.get(k).posCard[j], players.get(k).posCard[j + 1]))
        {            
            players.get(k).ownCards[l] = deck.deck.get(i);
            l++;
            if(j == 2)
            {                
                i++;
                k++;
                j = 0;
                l = 0;
                players.get(0).SHOW_CARD = true;
            }
            else
            {
                i++;
                j += 2;
            }
            
            if(k == GOPlayer.PLAYER_NUMBER)
            {
                j = 0;
                k = 0;
                FIRST_DEALING = false;
                FIRST_BETTING = true;
            }
        }       
    }
    
    public void flop(ArrayList<GOPlayer> players, GODeck deck)
    {
        deck.deck.get(i).SHOW_CARD = true;
        if(deck.deck.get(i).move(POS_FLOP[j], POS_FLOP[j + 1]))
        {
            flop[z] = deck.deck.get(i);
            z++;
            for(GOPlayer p : players)
                p.boardCards[m] = deck.deck.get(i);
            m++;
            i++;
            j += 2;
            
            if(j == 6)
            {
                j = 0;
                m = 0;
                FLOP = false;
                TURN = true;                
                Game.FLOP_READY = true;
                Utilities.sleep(1500);
            }
        }
    }
    
    public void turn(ArrayList<GOPlayer> players, GODeck deck)
    {
        m = 3;
        z = 0;
        deck.deck.get(i).SHOW_CARD = true;
        if(deck.deck.get(i).move(POS_TURN[j], POS_TURN[j + 1]))
        {
            turn = deck.deck.get(i);
            for(GOPlayer p : players)
                p.boardCards[m] = deck.deck.get(i);
            i++;
            j = 0;
            TURN = false;
            RIVER = true;
            Game.TURN_READY = true;
            Utilities.sleep(1500);
        }
    }
    
    public void river(ArrayList<GOPlayer> players, GODeck deck)
    {
        m = 4;
        deck.deck.get(i).SHOW_CARD = true;
        if(deck.deck.get(i).move(POS_RIVER[j], POS_RIVER[j + 1]))
        {
            river = deck.deck.get(i);
            for(GOPlayer p : players)
                p.boardCards[m] = deck.deck.get(i);
            j = 0;
            i = 0;
            RIVER = false;
            SHOWDOWN = true;
            Game.RIVER_READY = true;
            Utilities.sleep(1500);
        }
    }
    
    public void showdown(ArrayList<GOPlayer> players, Game g)
    {
        Utilities.sleep(2000);
        for(GOPlayer p : players)
        {
            p.setAllCards();
            Kiertekelo.kiertekel(p);
            p.SHOW_CARD = true;
        }
        
        GOPlayer winner = new GOPlayer(true);
        winner = Kiertekelo.kiNyert(players);
        
        for(GOPlayer p: players)
        {
            if(p.equals(winner))
                p.winner = true;
        }
        
        kiemel(winner);
        
        SHOWDOWN = false;
        Game.END_ROUND_CLEAN_UP = true;
    }
    
    public void kiemel(GOPlayer p)
    {
        //if(!(Arrays.asList(p.ownCards).contains(p.combinationArray[0])))
            p.combinationArray[0].move(p.combinationArray[0].x, p.combinationArray[0].y + 50);
        //if(!(Arrays.asList(p.ownCards).contains(p.combinationArray[1])))
            p.combinationArray[1].move(p.combinationArray[1].x, p.combinationArray[1].y + 50);
        //if(!(Arrays.asList(p.ownCards).contains(p.combinationArray[2])))
            p.combinationArray[2].move(p.combinationArray[2].x, p.combinationArray[2].y + 50);
        //if(!(Arrays.asList(p.ownCards).contains(p.combinationArray[3])))
            p.combinationArray[3].move(p.combinationArray[3].x, p.combinationArray[3].y + 50);
        //if(!(Arrays.asList(p.ownCards).contains(p.combinationArray[4])))
            p.combinationArray[4].move(p.combinationArray[4].x, p.combinationArray[4].y + 50);
    }
    
    public void firstBetting(ArrayList<GOPlayer> p, Game g)
    {
        FIRST_BETTING = false;
        FLOP = true;
    }
}