/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.graphics;

import java.util.Iterator;
import java.util.ListIterator;

public class Valoszinuseg //implements Runnable
{
    GOPlayer j1;
    GOPlayer j2;
    
    public Valoszinuseg()
    {
        this.j1 = new GOPlayer();
        this.j2 = new GOPlayer();        
    }    
    
    public static void riverUtaniEsely(GOPlayer j1, GOCard[] l)
    {
        GODeck p = new GODeck();
        p.deckShuffle();
        GOCard[] la;
        
        p.deck.remove(j1.ownCards[0]);
        p.deck.remove(j1.ownCards[1]);
        p.deck.remove(l[0]);
        p.deck.remove(l[1]);
        p.deck.remove(l[2]);
        p.deck.remove(l[3]);
        p.deck.remove(l[4]);
        
        la = new GOCard[7];        
        
        int jobb = 0;
        int rosszabb = 0;
        int dontetlen = 0;
        for(int i1 = 0; i1 < p.deck.size(); i1++)
        {
            for(int i2 = i1 + 1; i2 < p.deck.size(); i2++)
            {
                la[0] = l[0];
                la[1] = l[1];
                la[2] = l[2];
                la[3] = l[3];
                la[4] = l[4];
                la[5] = p.deck.get(i1);
                la[6] = p.deck.get(i2);
                
                int erosseg = Kiertekelo.kiertekel(la);
                if(j1.combinationValue > erosseg) jobb++;
                else if(erosseg > j1.combinationValue) rosszabb++;
                else dontetlen++;
            }
        }
        j1.nyeresiEsely = (jobb / (990f - dontetlen)) * 100;
    }
    
    public static void turnUtaniEsely(GOPlayer j1, GOCard[] l)
    {
        GODeck p = new GODeck();
        p.deckShuffle();
        GOCard[] la;
        GOCard[] laa;
        
        p.deck.remove(j1.ownCards[0]);
        p.deck.remove(j1.ownCards[1]);
        p.deck.remove(l[0]);
        p.deck.remove(l[1]);
        p.deck.remove(l[2]);
        p.deck.remove(l[3]);
        
        la = new GOCard[7];
        laa = new GOCard[7];
        //System.out.println("Size: " + p.deck.size());
        int jobb1 = 0, rosszabb1 = 0, dontetlen1 = 0;
        for(int i1 = 0; i1 < p.deck.size() - 2; i1++)
        {
            for(int i2 = i1; i2 < p.deck.size() - 1; i2++)
            {
                if(i2 == i1) continue;
                for(int i3 = i2; i3 < p.deck.size(); i3++)
                {
                    if(i3 == i2) continue;
                    Kiertekelo k = new Kiertekelo();
                    
                    laa[0] = j1.ownCards[0];
                    laa[1] = j1.ownCards[1];
                    laa[2] = l[0];
                    laa[3] = l[1];
                    laa[4] = l[2];
                    laa[5] = l[3];
                    laa[6] = p.deck.get(i1);
                    
                    j1.combinationValue = k.kiertekel(laa);
                    
                    la[0] = l[0];
                    la[1] = l[1];
                    la[2] = l[2];
                    la[3] = l[3];
                    la[4] = p.deck.get(i1);
                    la[5] = p.deck.get(i2);
                    la[6] = p.deck.get(i3);
                    
                    int erosseg = k.kiertekel(la);
                    //System.out.println(i + ". " + erosseg);
                    if(erosseg > j1.combinationValue) rosszabb1++;
                    else if(erosseg < j1.combinationValue) jobb1++;
                    else dontetlen1++;                    
                }                
            }
        }
        //System.out.println(jobb1);
        //System.out.println(dontetlen1);
        //System.out.println(rosszabb1);
        j1.nyeresiEsely = (jobb1 / (15180f - dontetlen1)) * 100;
        //float szazalek = (jobb1 / (15180f - dontetlen1)) * 100;
        //System.out.println(szazalek + "%");
        //System.out.println();
    }
    
    public static void flopUtaniEsely(GOPlayer j1, GOCard[] l)
    {      
        GODeck p = new GODeck();
        p.loadDeck();
        GOCard[] la;
        GOCard[] laa;    
        
        p.deck.remove(j1.ownCards[0]);
        p.deck.remove(j1.ownCards[1]);
        p.deck.remove(l[0]);
        p.deck.remove(l[1]);
        p.deck.remove(l[2]);
        
        la = new GOCard[7];
        laa = new GOCard[7];
                
        int jobb1 = 0, rosszabb1 = 0, dontetlen1 = 0, i = 1;
        for(int i1 = 0; i1 < p.deck.size() - 3; i1++)
        {
            for(int i2 = i1; i2 < p.deck.size() - 2; i2++)
            {
                if(i2 == i1) continue;
                for(int i3 = i2; i3 < p.deck.size() - 1; i3++)
                {
                    if(i3 == i2) continue;
                    for(int i4 = i3; i4 < p.deck.size(); i4++)
                    {
                        if(i4 == i3) continue;
                        Kiertekelo k = new Kiertekelo();
                        
                        laa[0] = j1.ownCards[0];
                        laa[1] = j1.ownCards[1];
                        laa[2] = l[0];
                        laa[3] = l[1];
                        laa[4] = l[2];
                        laa[5] = p.deck.get(i1);
                        laa[6] = p.deck.get(i2);
                        
                        j1.combinationValue = k.kiertekel(laa);
                        
                        la[0] = l[0];
                        la[1] = l[1];
                        la[2] = l[2];
                        la[3] = p.deck.get(i1);
                        la[4] = p.deck.get(i2);                       
                        la[5] = p.deck.get(i3);
                        la[6] = p.deck.get(i4);
                        
                        //for(int i = 0; i < 7; i++) {
                            //System.out.println(la[i].strSuit + " " + la[i].strRank); }
                        
                        int erosseg = k.kiertekel(la);
                        //System.out.println(i + ". " + erosseg); i++;
                        if(erosseg > j1.combinationValue) rosszabb1++;
                        else if(erosseg < j1.combinationValue) jobb1++;
                        else dontetlen1++;                        
                    }                    
                }                
            }
        }
        //System.out.println(jobb);
        //System.out.println(dontetlen);
        //System.out.println(rosszabb);
        j1.nyeresiEsely = (jobb1 / (178365f - dontetlen1)) * 100;
        //System.out.println((jobb1 / (178365f - dontetlen1)) * 100 + "%");
        //System.out.println();
    }
    
    /*public void osztasUtaniEsely(Jatekos j1, GOCard[] l)
    {
        GODeck p = new GODeck();
        GOCard[] la;
        
        p.deck.remove(j1.sajatGOCardok[0]);
        p.deck.remove(j1.sajatGOCardok[1]);
        
        la = new GOCard[7];
        
        int i = 1;
        for(int i1 = 0; i1 < p.deck.size() - 4; i1++)
        {
            for(int i2 = i1; i2 < p.deck.size() - 3; i2++)
            {
                if(i2 == i1) continue;
                for(int i3 = i2; i3 < p.deck.size() - 2; i3++)
                {
                    if(i3 == i2) continue;
                    for(int i4 = i3; i4 < p.deck.size() - 1; i4++)
                    {
                        if(i4 == i3) continue;
                        for(int i5 = i4; i5 < p.deck.size(); i5++)
                        {
                            if(i5 == i4) continue;
                            la[0] = l[0];
                            la[1] = l[1];
                            la[2] = p.deck.get(i1);
                            la[3] = p.deck.get(i2);
                            la[4] = p.deck.get(i3);
                            la[5] = p.deck.get(i4);
                            la[6] = p.deck.get(i5);
                            Kiertekelo k = new Kiertekelo();
                            int erosseg = k.kiertekel(la);
                            System.out.println(i + ". " + erosseg);
                            if(erosseg > j1.kombinacioErtek) rosszabb++;
                            else if(erosseg < j1.kombinacioErtek) jobb++;
                            else dontetlen++;
                            i++;
                        }                        
                    }                    
                }                
            }
        }
        System.out.println(jobb);
        System.out.println(dontetlen);
        System.out.println(rosszabb);
        float szazalek = (jobb / (2118760f - dontetlen)) * 100;
        System.out.println(szazalek + "%");
    }*/
    
    public void sajatLegjobb(GOPlayer j, GOCard[] l)
    {
        GODeck p = new GODeck();
        GOCard[] la;
        
        p.deck.remove(j.ownCards[0]);
        p.deck.remove(j.ownCards[1]);
        
        switch(l.length)
        {
            case 5:
                p.deck.remove(l[0]);
                p.deck.remove(l[1]);
                p.deck.remove(l[2]);
                
                la = new GOCard[7];
                for(int i1 = 0; i1 < p.deck.size() - 1; i1++)
                {
                    la[0] = j.ownCards[0];
                    la[1] = j.ownCards[1];
                    la[2] = l[0];
                    la[3] = l[1];
                    la[4] = l[2];
                    la[5] = p.deck.get(i1);
                    la[6] = p.deck.get(i1 + 1);
                    
                    Kiertekelo k = new Kiertekelo();
                    int erosseg = k.kiertekel(la);                    
                    if(erosseg > j1.combinationValue) j1.combinationValue = erosseg;
                }
                break;
        }
    }
    
    /*@Override
    public void run()
    {      
        GOCard[] kozosGOCardok;
        
        oszto.decktKever();
        
        j1.setSajatGOCardok(0, oszto.getKovetkezoGOCard());
        j2.setSajatGOCardok(0, oszto.getKovetkezoGOCard());
        
        j1.setSajatGOCardok(1, oszto.getKovetkezoGOCard());
        j2.setSajatGOCardok(1, oszto.getKovetkezoGOCard());
        
        kozosGOCardok = new GOCard[5];
        kozosGOCardok[0] = oszto.getKovetkezoGOCard();
        kozosGOCardok[1] = oszto.getKovetkezoGOCard();
        kozosGOCardok[2] = oszto.getKovetkezoGOCard();
        
        System.out.println(j1.ID + ". játékos lapjai..." );
        System.out.println(j1.sajatGOCardok[0].getGOCardSzin() + " " + j1.sajatGOCardok[0].getGOCardErtek());
        System.out.println(j1.sajatGOCardok[1].getGOCardSzin() + " " + j1.sajatGOCardok[1].getGOCardErtek());
        System.out.println();
        
        System.out.println("FLOP...");
        System.out.println(kozosGOCardok[0].getGOCardSzin() + " " + kozosGOCardok[0].getGOCardErtek());
        System.out.println(kozosGOCardok[1].getGOCardSzin() + " " + kozosGOCardok[1].getGOCardErtek());
        System.out.println(kozosGOCardok[2].getGOCardSzin() + " " + kozosGOCardok[2].getGOCardErtek());
        System.out.println();
        
        kozosGOCardok[3] = oszto.getKovetkezoGOCard();
        
        System.out.println("TURN...");
        System.out.println(kozosGOCardok[3].getGOCardSzin() + " " + kozosGOCardok[3].getGOCardErtek());
        System.out.println();

        kozosGOCardok[4] = oszto.getKovetkezoGOCard();
        
        System.out.println("RIVER...");
        System.out.println(kozosGOCardok[4].getGOCardSzin() + " " + kozosGOCardok[4].getGOCardErtek());
        System.out.println();
        
        j1.setKozosGOCardok(kozosGOCardok);
        j1.setOsszesGOCard();
        
        j2.setKozosGOCardok(kozosGOCardok);
        j2.setOsszesGOCard();
        
        Kiertekelo.kiertekel(j1);
        Kiertekelo.kiertekel(j2);       
        
        riverUtaniEsely(j1, kozosGOCardok);
        riverUtaniEsely(j2, kozosGOCardok);
        
        System.out.println(j1.ID + ". játékos nyerési esélye a river után: " + j1.nyeresiEsely);
        System.out.println(j2.ID + ". játékos nyerési esélye a river után: " + j2.nyeresiEsely + "\n");
        
        System.out.println(j1.ID + ".játékos kombinációja...");
        System.out.println(j1.kombinacioNev + "(" + j1.kombinacioErtek + ")");
        System.out.println();
        
        System.out.println(j2.ID + ".játékos kombinációja...");
        System.out.println(j2.kombinacioNev + "(" + j2.kombinacioErtek + ")");
        System.out.println();
    }*/
}
