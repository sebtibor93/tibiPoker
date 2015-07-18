package game.graphics;

import java.util.ArrayList;
import org.lwjgl.input.Keyboard;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import org.newdawn.slick.opengl.Texture;

public class Game
{
    public static boolean NEW_ROUND_PREPARE = true;
    public static boolean END_ROUND_CLEAN_UP = false;
    
    public static boolean FLOP_READY = false;
    public static boolean TURN_READY = false;
    public static boolean RIVER_READY = false;
    
    private ArrayList<GameObject> objects;
    public ArrayList<GOPlayer> players;
    
    GOTable table;
    GODeck deck;
    
    private int indexOfSmallBlind;
    private int indexOfBigBlind;
    public static Texture[] f;
    
    public Integer pot;
    
    public Game()
    {
        objects = new ArrayList<GameObject>();
        table = new GOTable();
        players = new ArrayList<GOPlayer>();
        players.add(new GOPlayer());
        players.add(new GOPlayer());
        players.add(new GOPlayer());
        players.add(new GOPlayer());
        
        this.indexOfSmallBlind = -1;
        this.indexOfBigBlind = 0;
        this.pot = 0;
        
        this.deck = new GODeck();
        
        objects.add(table);
        objects.add(deck);
        
        String[] lapSzin = new String[] {"treff", "karo", "kor", "pikk"};
        String[] lapErtek = new String[] {"ketto", "harom", "negy", 
                                          "ot", "hat", "het", "nyolc", "kilenc",
                                          "tiz", "bubi", "dama", "kiraly", "asz"};
        
        f = new Texture[56];
        f[0] = Util.loadTexture("asztal_2");
        f[1] = Util.loadTexture("big_blind");
        f[2] = Util.loadTexture("small_blind");
        f[3] = Util.loadTexture("hatlap");
        int i = 4;
        for(int i1 = 0; i1 < 4; i1++)
        {           
            for(int i2 = 0; i2 < 13; i2++)
            {
                f[i] = Util.loadTexture(lapSzin[i1] + "." + lapErtek[i2]);
                GOCard c = new GOCard(350, 450);
                c.strSuit = lapSzin[i1];
                c.strRank = lapErtek[i2];
                c.suit = LapSzin.getSzin(i1 + 1);
                c.rank = LapErtek.getErtek(i2 + 1);
                c.t1 = f[i];
                c.t2 = f[3];
                i++;
                deck.deck.add(c);
            }            
        }
    }
    
    public void newRound()
    {
        deck.reset();
        //deck.deckShuffle();
        calcBlinds(players);
        GOTable.FIRST_DEALING = true;
        GOTable.FLOP = false;
        GOTable.TURN = false;
        GOTable.RIVER = false;
        GOTable.SHOWDOWN = false;
        Game.FLOP_READY = false;
        Game.TURN_READY = false;
        Game.RIVER_READY = false;
        NEW_ROUND_PREPARE = false;
        table.m = 0;
        for(GOPlayer p : players)
            p.winner = false;        
    }
    
    public void calcBlinds(ArrayList<GOPlayer> p)
    {
        for(GOPlayer pl : p)
        {
            pl.IS_BIG_BLIND = false;
            pl.IS_SMALL_BLIND = false;
        }
        p.get(getNextBigBlind()).IS_BIG_BLIND = true;
        p.get(getNextSmallBlind()).IS_SMALL_BLIND = true;
        
        //kisvak, nagyvak értékének kiszámítása
        switch(players.size())
        {
            case 4:
                GOTable.smallBlind = 5;
                GOTable.bigBlind = 10;
                break;
            case 3:
                GOTable.smallBlind = 10;
                GOTable.bigBlind = 20;
                break;
            case 2:
                GOTable.smallBlind = 50;
                GOTable.bigBlind = 100;
                break;
        }
        //potba beteszi a kisvakot és a nagyvakot
        for(GOPlayer pl : p)
        {
            if(pl.IS_SMALL_BLIND)
            {
                pl.chips -= GOTable.smallBlind;
                pot += GOTable.smallBlind;
            }
            else if(pl.IS_BIG_BLIND)
            {
                pl.chips -= GOTable.bigBlind;
                pot += GOTable.bigBlind;
            }
        }
        //tesztelés
        //for(GOPlayer pl : p)
            //System.out.println(pl.chips);
    }
    
    private int getNextSmallBlind()
    {
        if(indexOfSmallBlind == players.size() - 1)
        {
            indexOfSmallBlind = 0;
            return 0;
        }
        return ++indexOfSmallBlind;
    }
    
    private int getNextBigBlind()
    {
        if(indexOfBigBlind == players.size() - 1)
        {
            indexOfBigBlind = 0;
            return 0;
        }
        return ++indexOfBigBlind;
    }
    
    private void endRound()
    {
        for(GOPlayer p: players)
            if(p.winner)
                p.chips += pot;
        
        this.pot = 0;
        
        if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
            NEW_ROUND_PREPARE = true;
            System.out.println("Játékosok zetonjai:");
            for(GOPlayer p: players)
            System.out.println(p.chips);}
        
        
        
        for(GOPlayer p : players)
            p.SHOW_CARD = false;
    }
    
    public void getInput()
    {
        if(NEW_ROUND_PREPARE)
            newRound();
        else if(GOTable.FIRST_DEALING)
            table.firstDealing(players, deck);
        else if(GOTable.FIRST_BETTING) 
            table.firstBetting(players, this);
        else if(GOTable.FLOP)
        {
            table.flop(players, deck);
            if(FLOP_READY)
            {
                int i = 0;
                System.out.println();
                System.out.println("Flop utáni esélyek:");
                for(GOPlayer p : players)
                {
                    Valoszinuseg.flopUtaniEsely(p, table.flop);                    
                    System.out.println(++i + ". játékos: " + p.nyeresiEsely);
                }
            }
        }
        else if(GOTable.TURN)
        {
            table.turn(players, deck);
            if(TURN_READY)
            {
                GOCard[] temp = new GOCard[4];
                temp[0] = table.flop[0];
                temp[1] = table.flop[1];
                temp[2] = table.flop[2];
                temp[3] = table.turn;
                
                int i = 0;
                System.out.println();
                System.out.println("Turn utáni esélyek:");
                for(GOPlayer p : players)
                {
                    Valoszinuseg.turnUtaniEsely(p, temp);                    
                    System.out.println(++i + ". játékos: " + p.nyeresiEsely);
                }
            }
        }
        else if(GOTable.RIVER)
        {
            table.river(players, deck);
            if(RIVER_READY)
            {
                GOCard[] temp = new GOCard[5];
                temp[0] = table.flop[0];
                temp[1] = table.flop[1];
                temp[2] = table.flop[2];
                temp[3] = table.turn;
                temp[4] = table.river;
                int i = 0;
                System.out.println();
                System.out.println("River utáni esélyek:");
                for(GOPlayer p : players)
                {
                    Valoszinuseg.riverUtaniEsely(p, temp);                    
                    System.out.println(++i + ". játékos: " + p.nyeresiEsely);
                }
            }
        }
        else if(GOTable.SHOWDOWN)
            table.showdown(players, this);
        else if(END_ROUND_CLEAN_UP)
            endRound();
    }
    
    public void update()
    {
        for(GameObject go : objects)
            go.update();        
    }
    
    public void render()
    {
        glClear(GL_COLOR_BUFFER_BIT);
        for(GameObject go : objects)
            go.render();
        for(GOPlayer p : players)
            p.render();
    }
}