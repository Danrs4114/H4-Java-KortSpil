package dk.zbc.h4.kortspil;
import java.util.ArrayList;

/**
 *
 * @author runra
 */
public abstract class KortspilMgr {

    protected int minPlayers;
	protected int activePlayerIndex = -1;
    public abstract void startSpil();
    public abstract void slutSpil();
    public ArrayList<Kort> blandKort(ArrayList<Kort> deck) {return null;}
    protected ArrayList<Spiller> spillerListe = null;

    public void tilfojSpiller(Spiller spiller) {
        spillerListe.add(spiller);
    }
    public boolean spilStartet() {
        if(activePlayerIndex != -1) {
            return true;
        }else{
            return false;
        }
    }

    public int findSpillerIndexByID(String uid) {

        int index = -1;
        for(Spiller spiller : spillerListe) {
            if(spiller.getUserID().equals(uid)) {
                index = spillerListe.indexOf(spiller);
                break;
            }
        }
        return index;
    }

    public void spillerKlar(String uid) {
        int spillerIndex = this.findSpillerIndexByID(uid);
        spillerListe.get(spillerIndex).setKlar(true);

        this.startSpil();
    }

    public boolean erAlleSpillereKlar() {
        boolean spillereErKlar = true;
        for(Spiller spiller : spillerListe) {
            if(spiller.getKlar() != true) {
                spillereErKlar = false;
            }
        }
        return spillereErKlar;
    }
}
