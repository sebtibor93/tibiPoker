/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package game.graphics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public final class Kiertekelo
{    
    public Kiertekelo() {}
    
    public static int kiertekel(GOCard[] l)
    {
        GOCard[] la;
        //ROYAL FLUSH
        if((la = royalFlush(l)) != null)
        {            
            return 10000;
        }
        //SZINSOR
        else if((la = szinSor(l)) != null) 
        { 
            return 9000 + (10 * la[0].getLapErtek().getErtek()
                                           + la[1].getLapErtek().getErtek()
                                           + la[2].getLapErtek().getErtek()
                                           + la[3].getLapErtek().getErtek()
                                           + la[4].getLapErtek().getErtek());
            
        }
        //PÓKER
        else if((la = poker(l)) != null) 
        { 
            return 8000 + (20 * la[0].getLapErtek().getErtek()
                                           + la[4].getLapErtek().getErtek());
        }
        //FULL
        else if((la = full(l)) != null)
        {
            return 7000 + (10 * la[0].getLapErtek().getErtek()
                                           + la[3].getLapErtek().getErtek());
        }
        //FLUSH
        else if((la = flush(l)) != null) 
        { 
            return 6000 + la[0].getLapErtek().getErtek();
        }
        //SOR
        else if((la = sor(l)) != null)
        {
            return 5000 + la[0].getLapErtek().getErtek();
        }
        //DRILL
        else if((la = drill(l)) != null)
        {
            return 4000 +(10 * la[0].getLapErtek().getErtek() 
                                          + la[3].getLapErtek().getErtek() 
                                          + la[4].getLapErtek().getErtek());
        }
        //KÉT PÁR
        else if((la = ketPar(l)) != null)
        { 
            return 3000 + (10 * la[0].getLapErtek().getErtek() 
                                           + la[2].getLapErtek().getErtek() 
                                           + la[4].getLapErtek().getErtek());
        }
        //EGY PÁR
        else if((la = egyPar(l)) != null)
        { 
            return 2000 + (10 * la[0].getLapErtek().getErtek() 
                                           + la[2].getLapErtek().getErtek()
                                           + la[3].getLapErtek().getErtek() 
                                           + la[4].getLapErtek().getErtek());
        }
        //MAGAS LAP
        else
        { 
            la = magasGOCard(l);
            return 1000 + la[4].getLapErtek().getErtek() 
                                     + la[3].getLapErtek().getErtek()
                                     + la[2].getLapErtek().getErtek() 
                                     + la[1].getLapErtek().getErtek()
                                     + la[0].getLapErtek().getErtek();
        }
    }
    
    public static void kiertekel(GOPlayer j)
    {
        GOCard[] la;
        //ROYAL FLUSH
        if((la = royalFlush(j.allCards)) != null)
        {
            j.combinationName = la[0].getLapSzin() + " royal flush";
            j.combinationValue = 10000;
            j.combinationArray = la;
        }
        //SZINSOR
        else if((la = szinSor(j.allCards)) != null) 
        { 
            j.combinationName = la[0].getLapErtek() + " szinsor";
            j.combinationValue = 9000 + (10 * la[0].getLapErtek().getErtek()
                                           + la[1].getLapErtek().getErtek()
                                           + la[2].getLapErtek().getErtek()
                                           + la[3].getLapErtek().getErtek()
                                           + la[4].getLapErtek().getErtek());
            j.combinationArray = la;
        }
        //PÓKER
        else if((la = poker(j.allCards)) != null) 
        { 
            j.combinationName = la[0].getLapErtek() + " póker " + la[4].getLapErtek() + " magas lappal";
            j.combinationValue = 8000 + (20 * la[0].getLapErtek().getErtek()
                                           + la[4].getLapErtek().getErtek());
            j.combinationArray = la;
        }
        //FULL
        else if((la = full(j.allCards)) != null)
        {
            j.combinationName = la[0].getLapErtek() + " full " + la[3].getLapErtek() + " párral";
            j.combinationValue = 7000 + (10 * la[0].getLapErtek().getErtek()
                                           + la[3].getLapErtek().getErtek());
            j.combinationArray = la;
        }
        //FLUSH
        else if((la = flush(j.allCards)) != null) 
        { 
            j.combinationName = la[0].getLapSzin() + " flush";
            j.combinationValue = 6000 + la[0].getLapErtek().getErtek();
            j.combinationArray = la;
        }
        //SOR
        else if((la = sor(j.allCards)) != null)
        {
            j.combinationName = la[0].getLapErtek() + " sor";
            j.combinationValue = 5000 + la[0].getLapErtek().getErtek();
            j.combinationArray = la;
        }
        //DRILL
        else if((la = drill(j.allCards)) != null)
        {
            j.combinationName = la[0].getLapErtek() + " drill " + la[3].getLapErtek() + " magas lappal";
            j.combinationValue = 4000 +(10 * la[0].getLapErtek().getErtek() 
                                          + la[3].getLapErtek().getErtek() 
                                          + la[4].getLapErtek().getErtek());
            j.combinationArray = la;
        }
        //KÉT PÁR
        else if((la = ketPar(j.allCards)) != null)
        { 
            j.combinationName = la[0].getLapErtek() + " és " + la[2].getLapErtek() + " pár " + la[4].getLapErtek()
                           + " magas lappal";
            j.combinationValue = 3000 + (10 * la[0].getLapErtek().getErtek() 
                                           + la[2].getLapErtek().getErtek() 
                                           + la[4].getLapErtek().getErtek());
            j.combinationArray = la;
        }
        //EGY PÁR
        else if((la = egyPar(j.allCards)) != null)
        { 
            j.combinationName = la[0].getLapErtek() + " pár " + la[2].getLapErtek() + " magas lappal";
            j.combinationValue = 2000 + (10 * la[0].getLapErtek().getErtek() 
                                           + la[2].getLapErtek().getErtek()
                                           + la[3].getLapErtek().getErtek() 
                                           + la[4].getLapErtek().getErtek());
            j.combinationArray = la;
        }
        //MAGAS LAP
        else
        { 
            la = magasGOCard(j.allCards);
            j.combinationName = la[4].getLapErtek() + " magas lap";
            j.combinationValue = 1000 + la[4].getLapErtek().getErtek() 
                                     + la[3].getLapErtek().getErtek()
                                     + la[2].getLapErtek().getErtek() 
                                     + la[1].getLapErtek().getErtek()
                                     + la[0].getLapErtek().getErtek();
            j.combinationArray = la;
        }
    }
    
    
    //döntetlent kezelni kell
    protected static GOPlayer kiNyert(ArrayList<GOPlayer> players)
    {
        GOPlayer winner = new GOPlayer(true);
        for(GOPlayer j: players)
        {
            if(j.combinationValue > winner.combinationValue)
                winner = j;            
        }
        for(GOPlayer j: players)
        {
            if(j.combinationValue == winner.combinationValue)
                j.winner = true;
        }
        return winner;
    }
    
    //ROYAL FLUSH
    private static GOCard[] royalFlush(GOCard[] lapok)
    {
        GOCard[] l = new GOCard[5];
        
        LinkedList<GOCard> minta = new LinkedList<>();
        for(int i = 1; i != 5; ++i)
        {
            for(int j = 10; j != 15; ++j)
            {
                GOCard la = new GOCard();
                la.suit = LapSzin.getSzin(i);
                la.rank = LapErtek.getErtek(j);
                minta.add(la);
            }
            if(Arrays.asList(lapok).containsAll(minta)) 
            {
                l[0] = minta.pollLast();
                l[1] = minta.pollLast();
                l[2] = minta.pollLast();
                l[3] = minta.pollLast();
                l[4] = minta.pollLast();
                
                return l;
            }
        }
        return null;
    }
    
    //SZINSOR
    private static GOCard[] szinSor(GOCard[] lapok)
    {
        GOCard[] l = new GOCard[5];
        
        Arrays.sort(lapok);
        
        if((l = Utilities.vanSteelWheel(lapok)) != null) { return l; }
        
        int j = 0;
        for(int i = 0; i < 3; ++i)
        {
            if(((lapok[i + 4].getLapErtek().getErtek()) == (1 + lapok[i + 3].getLapErtek().getErtek()))
            && ((lapok[i + 3].getLapErtek().getErtek()) == (1 + lapok[i + 2].getLapErtek().getErtek()))
            && ((lapok[i + 2].getLapErtek().getErtek()) == (1 + lapok[i + 1].getLapErtek().getErtek()))
            && ((lapok[i + 1].getLapErtek().getErtek()) == (1 + lapok[i].getLapErtek().getErtek())))
            {
                l = new GOCard[5];
                l[0] = lapok[i + 4];
                l[1] = lapok[i + 3];
                l[2] = lapok[i + 2];
                l[3] = lapok[i + 1];
                l[4] = lapok[i];
                
                if(Utilities.egyformaSzinuMind(l)) { return l; }
            }
        }
        return null;
    }
    
    //PÓKER
    private static GOCard[] poker(GOCard[] lapok) throws ArrayIndexOutOfBoundsException
    {
        GOCard[] l = new GOCard[5];
        
        Arrays.sort(lapok);
        int j = 0;
        boolean vanPoker = false;
        for(int i = 0; i < 4; ++i)
        {
            if(lapok[i].getLapErtek() == lapok[i + 1].getLapErtek()
            && lapok[i].getLapErtek() == lapok[i + 2].getLapErtek()
            && lapok[i].getLapErtek() == lapok[i + 3].getLapErtek())
            {
                vanPoker = true;
                
                l[j] = lapok[i];
                l[j + 1] = lapok[i + 1];
                l[j + 2] = lapok[i + 2];
                l[j + 3] = lapok[i + 3];
                j += 4;
            }
        }
        if(!vanPoker) { return null; }
        
        for(int i = 6; i >= 0; --i)
        {
            if(lapok[i].getLapErtek() != l[0].getLapErtek()) { l[j] = lapok[i]; break; }
        }
        if(vanPoker) { return l; }
        else { return null; }        
    }
    
    //FULL
    private static GOCard[] full(GOCard[] lapok)
    {
        GOCard[] l = new GOCard[5];
        
        Arrays.sort(lapok);
        boolean vanDrill = false, vanPar = false;
        int drillKezd = -1;
        int j = 0;
        for(int i = 0; i < 5; ++i)
        {
            if(lapok[i].getLapErtek() == lapok[i + 1].getLapErtek()
            && lapok[i].getLapErtek() == lapok[i + 2].getLapErtek())
            {
                if(vanDrill) { j = 0; }
                l[j] = lapok[i];
                l[j + 1] = lapok[i + 1];
                l[j + 2] = lapok[i + 2];
                j += 3;
                drillKezd = i;
                vanDrill = true;
            }
        }
        int i = 0;
        while(i < 6)
        {
            if(lapok[i].getLapErtek() == lapok[i + 1].getLapErtek())
            {
                if(i == drillKezd) { i += 3; }
                else { l[j] = lapok[i]; l[j + 1] = lapok[i + 1]; vanPar = true; }
            }
            ++i;
        }
        if(vanDrill && vanPar) { return l; }
        else { return null; }
    }
    
    //FLUSH
    private static GOCard[] flush(GOCard[] lapok)
    {
        GOCard[] l = new GOCard[5];
        
        LapSzin lsz;
        boolean vanFlush = false;
        Arrays.sort(lapok);
        if((lsz = Utilities.vanOtEgyformaSzin(lapok, true)) != LapSzin.NULLA)
        {
            vanFlush = true;
            
            int j = 0;
            int szinSzamlalo = 0;
            for(int i = 6; i >= 0 ; --i)
            {
                if(lapok[i].getLapSzin() == lsz) 
                {
                    if(szinSzamlalo != 5) { l[j] = lapok[i]; ++j; ++szinSzamlalo; }
                }
            }
        }
        if(vanFlush) { return l; }
        else { return null; }
    }
    
    //SOR
    private static GOCard[] sor(GOCard[] lapok)
    {
        GOCard[] l = new GOCard[5];
        
        if(Utilities.vanAdottErtekuLap(lapok, LapErtek.ÁSZ)
        && Utilities.vanAdottErtekuLap(lapok, LapErtek.KETTŐ)
        && Utilities.vanAdottErtekuLap(lapok, LapErtek.HÁROM)
        && Utilities.vanAdottErtekuLap(lapok, LapErtek.NÉGY)
        && Utilities.vanAdottErtekuLap(lapok, LapErtek.ÖT))
        {
            Arrays.sort(lapok);
            l[4] = lapok[6];
            int j = 0;
            for(int i = 6; i >= 0; --i)
            {
                if(lapok[i].getLapErtek().getErtek() <= LapErtek.ÖT.getErtek())
                {
                    if(!(Utilities.tartalmazAdottErtekuLapot(l, lapok[i]))) { l[j] = lapok[i]; ++j; }
                }
            }
            return l;
        }
        boolean vanSor = false;
        for(int i = 0; i < 3; ++i)
        {
            if(((lapok[i + 4].getLapErtek().getErtek()) == (1 + lapok[i + 3].getLapErtek().getErtek()))
            && ((lapok[i + 3].getLapErtek().getErtek()) == (1 + lapok[i + 2].getLapErtek().getErtek()))
            && ((lapok[i + 2].getLapErtek().getErtek()) == (1 + lapok[i + 1].getLapErtek().getErtek()))
            && ((lapok[i + 1].getLapErtek().getErtek()) == (1 + lapok[i].getLapErtek().getErtek())))
            {
                vanSor = true;
                l[0] = lapok[i + 4];
                l[1] = lapok[i + 3];
                l[2] = lapok[i + 2];
                l[3] = lapok[i + 1];
                l[4] = lapok[i];
            }
        }
        if(vanSor) { return l; }
        else { return null; }
    }
    
    //DRILL
    private static GOCard[] drill(GOCard[] lapok)
    {
        boolean vanDrill = false;
        GOCard[] l = new GOCard[5];
        
        int j = 0;
        Arrays.sort(lapok);
        for(int i = 0; i < 5; ++i)
        {
            if(lapok[i].getLapErtek() == lapok[i + 1].getLapErtek()
            && lapok[i].getLapErtek() == lapok[i + 2].getLapErtek())
            {
                vanDrill = true;
                l[j] = lapok[i];
                l[j + 1] = lapok[i + 1];
                l[j + 2] = lapok[i + 2];
                j += 3;
            }
        }
        if(!vanDrill) { return null; }
        
        for(int i = 6; i != 0; --i)
        {
            if(l[0].getLapErtek().getErtek() != lapok[i].getLapErtek().getErtek())
                { if(j != 5) { l[j] = lapok[i]; ++j; }
                  else { break; } }
        }        
        if(vanDrill) { return l; }
        else { return null; }
    }
    
    //KETPAR
    private static GOCard[] ketPar(GOCard[] lapok)
    {
        GOCard[] l = new GOCard[5];
        
        Arrays.sort(lapok);
        
        byte parSzamlalo = 0;
        int j = 0;
        for(int i = 6; i > 0; --i)
        {
            if(lapok[i].getLapErtek() == lapok[i - 1].getLapErtek())
            {
                if(parSzamlalo != 2)
                {
                    l[j] = lapok[i];
                    l[j + 1] = lapok[i - 1];
                    ++parSzamlalo;
                    j += 2;
                }
                else { break; }
            }
        }
        if(parSzamlalo != 2) { return null; }
        
        for(int i = 6; i != 0; --i)
        {
            if(lapok[i].getLapErtek().getErtek() != l[0].getLapErtek().getErtek() 
            && lapok[i].getLapErtek().getErtek() != l[2].getLapErtek().getErtek()) 
                { l[j] = lapok[i]; break; }
        }
        if(parSzamlalo == 2) { return l; }
        else { return null; }
    }
    
    //EGY PÁR
    private static GOCard[] egyPar(GOCard[] lapok)
    {       
        GOCard[] l = new GOCard[5];
        
        boolean vanPar = false;
        GOCard parElsoGOCardja = new GOCard();
        GOCard parMasodikGOCardja = new GOCard();
        
        Arrays.sort(lapok);     
        for(int i = 0; i < 6; ++i)
        {
            if(lapok[i].getLapErtek() == lapok[i + 1].getLapErtek()) 
            { 
                parElsoGOCardja = lapok[i];
                parMasodikGOCardja = lapok[i + 1];
                vanPar = true;
            }
        }
        if(vanPar)
        {
            l[0] = parElsoGOCardja;
            l[1] = parMasodikGOCardja;
            
            int j = 2;
            for(int i = 6; i != 0; --i)
            {
                if(lapok[i] != parElsoGOCardja && lapok[i] != parMasodikGOCardja)
                { 
                    if(j != 5) { l[j] = lapok[i]; ++j; }
                    else { break; }
                }
            }
        }        
        if(vanPar) { return l; }
        else { return null; }
    }
    
    //MAGAS LAP
    private static GOCard[] magasGOCard(GOCard[] lapok)
    {
        GOCard[] l = new GOCard[5];        
        
        Arrays.sort(lapok);
        for(int i = 2; i < 7; ++i) { l[i - 2] = lapok[i]; }
        
        return l;
    }
}
