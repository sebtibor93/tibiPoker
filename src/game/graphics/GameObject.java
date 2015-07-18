/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.graphics;

public abstract class GameObject
{
    protected float x;
    protected float y;
    protected float sx;
    protected float sy;
    
    abstract void update();
    abstract void render();
    
    public float getX()
    {
        return x;
    }
    
    public void setX(float x)
    {
        this.x = x;
    }
    
    public float getY()
    {
        return y;
    }
    
    public void setY(float y)
    {
        this.y = y;
    }
    
    public float getSX()
    {
        return sx;
    }
    
    public float getSY()
    {
        return sy;
    }
}
