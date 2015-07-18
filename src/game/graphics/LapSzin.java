package game.graphics;

public enum LapSzin
{
    NULLA(0),
    TREFF(1),
    KÁRÓ(2),
    KŐR(3),
    PIKK(4);
    
    private final int ertek;
    
    private LapSzin(int ertek) { this.ertek = ertek; }
    
    public int getSzin() { return ertek; }
    
    public static LapSzin getSzin(int ertek)
    {
        switch(ertek)
        {
            case 1:
                return LapSzin.TREFF;
            case 2:
                return LapSzin.KÁRÓ;
            case 3:
                return LapSzin.KŐR;
            case 4:
                return LapSzin.PIKK;
            default:
                return LapSzin.NULLA;
        }
    }
}