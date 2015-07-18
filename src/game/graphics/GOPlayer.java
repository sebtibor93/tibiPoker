package game.graphics;

import org.newdawn.slick.opengl.Texture;
import static game.graphics.Util.drawCircle;

public class GOPlayer extends GameObject
{
    public static final float SIZEX = 250;
    public static final float SIZEY = 175;
    public static int PLAYER_NUMBER = 0;
    
    public boolean IS_BIG_BLIND = false;
    public boolean IS_SMALL_BLIND = false;
    public boolean SHOW_CARD = false;
    
    public boolean winner = false;
    
    public float nyeresiEsely;
    
    public Texture t;
    
    private final int id;
    
    protected GOCard[] ownCards;
    public GOCard[] boardCards;
    public GOCard[] allCards;
    
    public String combinationName;
    public int combinationValue;
    public GOCard[] combinationArray;
    
    public float[] posCard;
    public float[] posBlind;
    
    public int chips;
    
    public GOPlayer(boolean tmp) 
    {
        this.id = 0;
    }
    
    public GOPlayer()
    {
        PLAYER_NUMBER++;
        this.id = PLAYER_NUMBER;
        this.ownCards = new GOCard[2];
        this.boardCards = new GOCard[5];
        this.allCards = new GOCard[7];
        this.combinationArray = new GOCard[5];
        this.posCard = new float[4];
        this.posBlind = new float[2];
        
        switch(id)
        {
            case 1:
                posCard[0] = 553.5f;
                posCard[1] = 50.f;
                posCard[2] = 652.5f;
                posCard[3] = 50.f;
                posBlind[0] = 750.f;
                posBlind[1] = 225.f;
                break;
            case 2:
                posCard[0] = 100.f;
                posCard[1] = 300.f;
                posCard[2] = 200.f;
                posCard[3] = 300.f;
                posBlind[0] = 325.f;
                posBlind[1] = 425.f;
                break;
            case 3:
                posCard[0] = 652.5f;
                posCard[1] = 550.f;
                posCard[2] = 553.5f;
                posCard[3] = 550.f;
                posBlind[0] = 750.f;
                posBlind[1] = 475.f;
                break;
            case 4:
                posCard[0] = 1150.f;
                posCard[1] = 300.f;
                posCard[2] = 1050.f;
                posCard[3] = 300.f;
                posBlind[0] = 1000.f;
                posBlind[1] = 425.f;
                break;
        }
        this.chips = 1000;
    }
    
    public int getId()
    {
        return this.id;
    }
    
    public void setAllCards()
    {
        allCards[0] = ownCards[0];
        allCards[1] = ownCards[1];
        allCards[2] = boardCards[0];
        allCards[3] = boardCards[1];
        allCards[4] = boardCards[2];
        allCards[5] = boardCards[3];
        allCards[6] = boardCards[4];
    }
    
    @Override
    public void update()
    {
        
    }
    
    @Override
    public void render()
    {
        if(IS_BIG_BLIND)
        {
            //glColor3f(1f,1f,1f);
            t = Game.f[1];
            drawCircle(posBlind[0], posBlind[1], 30, t);
            //polygon(posBlind[0], posBlind[1], 40, 40, t);
        }   
        
        else if(IS_SMALL_BLIND)
        {
            //glColor3f(1f,1f,1f);
            t = Game.f[2];       
            drawCircle(posBlind[0], posBlind[1], 30, t);
        }
        
        if(SHOW_CARD)
        {
            ownCards[0].SHOW_CARD = true;
            ownCards[1].SHOW_CARD = true;
        }
    }
}