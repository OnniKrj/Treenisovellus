/**
 * 
 */
package treeni;

/**
 * @author Onni
 * @version 7.11.2022
 * CRC-sis�ll�t 
 */
public class Treeni {
    
    private Suoritukset suoritukset = new Suoritukset();
    
    
    /**
     * Lis�t��n uusi suoritus
     * @param suoritus Lis�tt�v� suoritus
     * @throws SailoException Poikkeus jos lis��minen ei onnistu
     */
    public void lisaa(Suoritus suoritus) throws SailoException {
        suoritukset.lisaa(suoritus);
    }
    
    /**
     * @return Suorituksien lukum��r�
     */
    public int getSuorituksia() {
        return suoritukset.getLkm();
    }
    
    
    /**
     * Antaa treenin i:n suorituksen
     * @param i Monesko suoritus
     * @return Suoritus paikasta i
     */
    public Suoritus annaSuoritus(int i) {
        return suoritukset.anna(i);
    }
    

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        Treeni treeni = new Treeni();
        
        Suoritus treeni1 = new Suoritus();
        Suoritus treeni2 = new Suoritus();
        treeni1.kirjaa();
        treeni1.taytaTreeniTiedoilla();
        treeni2.kirjaa();
        treeni2.taytaTreeniTiedoilla();
        
        try {
            treeni.lisaa(treeni1);
            treeni.lisaa(treeni2);
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        
        for (int i = 0; i < treeni.getSuorituksia(); i++) {
            Suoritus suoritus = treeni.annaSuoritus(i);
            suoritus.tulosta(System.out);
        }
        
    }

}
