package game.graphics;

public enum LapErtek
{
    NULLA(0),
    KETTŐ(1),
    HÁROM(2),
    NÉGY(3),
    ÖT(4),
    HAT(5),
    HÉT(6),
    NYOLC(7),
    KILENC(8),
    TÍZ(9),
    BUBI(10),
    DÁMA(11),
    KIRÁLY(12),
    ÁSZ(13);
    
    private final int ertek;
    
    private LapErtek(int ertek) { this.ertek = ertek; }
    
    public int getErtek() { return this.ertek; }
    
    public static LapErtek getErtek(int ertek)
    {
        switch(ertek)
        {
            case 1:
                return LapErtek.KETTŐ;
            case 2:
                return LapErtek.HÁROM;
            case 3:
                return LapErtek.NÉGY;
            case 4:
                return LapErtek.ÖT;
            case 5:
                return LapErtek.HAT;
            case 6:
                return LapErtek.HÉT;
            case 7:
                return LapErtek.NYOLC;
            case 8:
                return LapErtek.KILENC;
            case 9:
                return LapErtek.TÍZ;
            case 10:
                return LapErtek.BUBI;
            case 11:
                return LapErtek.DÁMA;
            case 12:
                return LapErtek.KIRÁLY;
            case 13:
                return LapErtek.ÁSZ;
            default:
                return LapErtek.NULLA;
        }
    }
}