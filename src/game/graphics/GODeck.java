package game.graphics;

import static game.graphics.Util.loadTexture;
import static game.graphics.Util.polygon;
import java.util.Collections;
import java.util.LinkedList;
import org.newdawn.slick.opengl.Texture;

public class GODeck extends GameObject
{
    public static final int SIZE_X = 80;
    public static final int SIZE_Y = 105;
    
    public static Texture f;
    
    LinkedList<GOCard> deck;
    
    public GODeck()
    {
        this.x = 350.f;
        this.y = 450.f;
        this.sx = SIZE_X;
        this.sy = SIZE_Y;
        
        deck = new LinkedList<>();
        f = loadTexture("hatlap");
    }
    
    public void loadDeck()
    {
        String[] lapSzin = new String[] {"treff", "karo", "kor", "pikk"};
        String[] lapErtek = new String[] {"ketto", "harom", "negy", 
                                          "ot", "hat", "het", "nyolc", "kilenc",
                                          "tiz", "bubi", "dama", "kiraly", "asz"};
        
        for(int i1 = 0; i1 < 4; i1++)
        {           
            for(int i2 = 0; i2 < 13; i2++)
            {
                GOCard c = new GOCard(350, 450);
                c.strSuit = lapSzin[i1];
                c.strRank = lapErtek[i2];
                c.suit = LapSzin.getSzin(i1 + 1);
                c.rank = LapErtek.getErtek(i2 + 1);
                deck.add(c);
            }            
        }
    }
    
    public void deckShuffle()
    {
        deck.removeAll(deck);
        String[] lapSzin = new String[] {"treff", "karo", "kor", "pikk"};
        String[] lapErtek = new String[] {"ketto", "harom", "negy", 
                                          "ot", "hat", "het", "nyolc", "kilenc",
                                          "tiz", "bubi", "dama", "kiraly", "asz"};
        
        for(int i1 = 0; i1 < 4; i1++)
        {           
            for(int i2 = 0; i2 < 13; i2++)
            {
                GOCard c = new GOCard(350, 450);
                c.strSuit = lapSzin[i1];
                c.strRank = lapErtek[i2];
                c.suit = LapSzin.getSzin(i1 + 1);
                c.rank = LapErtek.getErtek(i2 + 1);
                deck.add(c);
            }            
        }
        Collections.shuffle(deck);
    }
    
    public void reset()
    {
        Collections.shuffle(deck);
        for(GOCard c : deck)
        {
            c.x = x;
            c.y = y;
            c.newX = x;
            c.newY = y;
            c.SHOW_CARD = false;
        }
    }
    
    @Override
    public void update()
    {
        for(GOCard c : deck)
            c.update();
    }
    
    @Override
    public void render()
    {
        for(GOCard c : deck)
            c.render();
        
        f.bind();
        polygon(x, y, sx, sy, f);
    }
}