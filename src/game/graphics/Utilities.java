/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.graphics;

import java.util.logging.Level;
import java.util.logging.Logger;
//import org.newdawn.slick.opengl.Texture;
//import org.newdawn.slick.opengl.TextureLoader;

public final class Utilities
{
    /**
     * Megszámolja, hogy a paraméterként kapott lapok közül hány treff színű lap
     * található.
     * @param lapok
     * @return 
    */
    protected static int treffSzamol(GOCard[] lapok)
    {
        int treffDarab = 0;
        for(GOCard l: lapok)
        {
            if(l.getLapSzin().equals(LapSzin.TREFF))
                ++treffDarab;
        }
        return treffDarab;
    }
    
    protected static int karoSzamol(GOCard[] lapok)
    {
        int karoDarab = 0;
        for(GOCard l: lapok)
        {
            if(l.getLapSzin().equals(LapSzin.TREFF))
                ++karoDarab;
        }
        return karoDarab;
    }
    
    protected static int korSzamol(GOCard[] lapok)
    {
        int korDarab = 0;
        for(GOCard l: lapok)
        {
            if(l.getLapSzin().equals(LapSzin.TREFF))
                ++korDarab;
        }
        return korDarab;
    }
    
    protected static int pikkSzamol(GOCard[] lapok)
    {
        int pikkDarab = 0;
        for(GOCard l: lapok)
        {
            if(l.getLapSzin().equals(LapSzin.TREFF))
                ++pikkDarab;
        }
        return pikkDarab;
    }
    
    protected static boolean vanAdottErtekuLap(GOCard[] lapok, LapErtek milyenErtekuLap)
    {
        for(GOCard l: lapok)
        {
            if(l.getLapErtek() == milyenErtekuLap) { return true; }
        }
        return false;
    }
    
    protected static GOCard vanAdottSzinuErtekuLap(GOCard[] lapok, LapSzin milyenSzinuLap, LapErtek milyenErtekuLap)
    {
        for(GOCard l: lapok)
        {
            if(l.getLapErtek() == milyenErtekuLap && l.getLapSzin() == milyenSzinuLap) { return l; }
        }
        return null;
    }  
    
    //megnézi, hogy van-e ADOTT színű ász a lapok között
    protected static GOCard vanAdottSzinuAsz(GOCard[] lapok, LapSzin milyenSzinu)
    {
        for(GOCard l: lapok)
        {
            if(l.getLapErtek() == LapErtek.ÁSZ && l.getLapSzin() == milyenSzinu)
                return l;
        }
        return null;
    }
    
    protected static GOCard[] vanSteelWheel(GOCard[] lapok)
    {
        LapSzin szin = Utilities.vanOtEgyformaSzin(lapok, true);
        
        GOCard[] ujLapok = new GOCard[5];
        if((ujLapok[0] = Utilities.vanAdottSzinuErtekuLap(lapok, szin, LapErtek.ÖT))    != null
        && (ujLapok[1] = Utilities.vanAdottSzinuErtekuLap(lapok, szin, LapErtek.NÉGY))  != null
        && (ujLapok[2] = Utilities.vanAdottSzinuErtekuLap(lapok, szin, LapErtek.HÁROM)) != null
        && (ujLapok[3] = Utilities.vanAdottSzinuErtekuLap(lapok, szin, LapErtek.KETTŐ)) != null
        && (ujLapok[4] = Utilities.vanAdottSzinuErtekuLap(lapok, szin, LapErtek.ÁSZ))   != null)
        {
            return ujLapok;
        }
        return null;
    }
    
    protected static boolean vanOtEgyformaSzin(GOCard[] lapok)
    {
        int treff = 0, karo = 0, kor = 0, pikk = 0;
        for(GOCard l: lapok)
        {            
            switch(l.getLapSzin())
            {
                case TREFF: ++treff; break;
                case KÁRÓ:  ++karo;  break;
                case KŐR:   ++kor;   break;
                case PIKK:  ++pikk;  break;
            }
        }
        return treff >= 5 || karo >= 5 || kor >= 5 || pikk >= 5;
    }
    
    protected static LapSzin vanOtEgyformaSzin(GOCard[] lapok, boolean szinKell)
    {
        int treff = 0, karo = 0, kor = 0, pikk = 0;
        for(GOCard l: lapok)
        {            
            switch(l.getLapSzin())
            {
                case TREFF: ++treff; break;
                case KÁRÓ:  ++karo;  break;
                case KŐR:   ++kor;   break;
                case PIKK:  ++pikk;  break;
            }
        }
        if(treff >= 5)     return LapSzin.TREFF;
        else if(karo >= 5) return LapSzin.KÁRÓ;
        else if(kor >= 5)  return LapSzin.KŐR;
        else if(pikk >=5)  return LapSzin.PIKK;
        else               return LapSzin.NULLA;
    }
    
    //eldönti, hogy a paraméterként kapott lap tömb elemei ugyanolyan színűek-e
    protected static boolean egyformaSzinuMind(GOCard[] lapok)
    {
        for(int i = 0; i < lapok.length - 1; ++i)
        {
            if(lapok[i].getLapSzin() != lapok[i + 1].getLapSzin()) { return false; }
            
        }
        return true;
    }
    
    protected static boolean tartalmazAdottErtekuLapot(GOCard[] lapok, GOCard lap)
    {
        for(GOCard l: lapok)
        {
            if(l  != null && l.getLapErtek() == lap.getLapErtek()) { return true; }
        }
        return false;
    }
    
    /*public static Texture loadTexture(String key)
    {
        try
        {
            return TextureLoader.getTexture("png", new FileInputStream(new File("res/" + key + ".png")));
        }
        catch(IOException ex)
        {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);        
        }
        return null;
    }*/
    
    public static void sleep(int ido)
    {
        try
        {
            Thread.sleep(ido);
        }
        catch(InterruptedException ex)
        {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
