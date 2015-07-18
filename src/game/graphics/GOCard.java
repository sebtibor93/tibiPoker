package game.graphics;

import static org.lwjgl.opengl.GL11.glColor3f;
import org.newdawn.slick.opengl.Texture;

public class GOCard extends GameObject implements Comparable<GOCard>
{
    public static final int SIZE_X = 75;
    public static final int SIZE_Y = 100;
    
    public static final float VEL_X = 0.5f;
    public static final float VEL_Y = 0.5f;
    
    public boolean SHOW_CARD = false;
    
    public float velX;
    public float velY;
    public float newX;
    public float newY;
    
    public String strSuit;
    public String strRank;
    public LapSzin suit;
    public LapErtek rank;
    
    public Texture t1;
    public Texture t2;
    
    public GOCard()
    {
        this.x = -75;
        this.y = -100;
        
        this.newX = x;
        this.newY = y;
        
        this.sx = SIZE_X;
        this.sy = SIZE_Y;
    }
    
    public GOCard(float x, float y)
    {        
        this.x = x;
        this.y = y;
        
        this.newX = x;
        this.newY = y;
        
        this.sx = SIZE_X;
        this.sy = SIZE_Y;
        
        this.velX = VEL_X;
        this.velY = VEL_Y;
    }
    
    public boolean move(float destX, float destY)
    {
        float epsilon = velX + 0.1f;
        if((Math.abs(x - destX) < epsilon) && (Math.abs(y - destY) < epsilon))
            return true;
        else
        {
            newX = destX;
            newY = destY;
            
            return false;
        }
    }
    
    public LapSzin getLapSzin() { return this.suit; }
    
    public LapErtek getLapErtek() { return this.rank; }
    
    @Override
    public int compareTo(GOCard l) {
        if(this.rank.getErtek() > l.rank.getErtek()) return 1;
        else if(this.rank.getErtek() < l.rank.getErtek()) return -1;
        return 0;
    }
    
    @Override
    public void update()
    {
        float epsilon = velX + 0.1f;
        if(Math.abs(x - newX) > epsilon)
        {
            if(x > newX)
                x += -1 * velX;
            else
                x += velX;
        }
        if(Math.abs(y - newY) > epsilon)
        {
            if(y > newY)    
                y += -1 * velY;
            else
                y += velY;
        }
    }
    
    @Override
    public void render()
    {        
        glColor3f(1f,1f,1f);
        if(SHOW_CARD)
        {
            //Texture t = Util.loadTexture(strSuit + "." + strRank);
            t1.bind();
            Util.polygon(x, y, sx, sy, t1);
        }
        else
        {
            //Texture t = Util.loadTexture("hatlap");
            t2.bind();
            Util.polygon(x, y, sx, sy, t2);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GOCard other = (GOCard) obj;
        if (this.suit != other.suit) {
            return false;
        }
        if (this.rank != other.rank) {
            return false;
        }
        return true;
    }
}