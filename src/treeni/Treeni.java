/**
 * 
 */
package treeni;

import java.util.List;

/**
 * @author Onni
 * @version 7.11.2022
 * CRC-sisällöt 
 */
public class Treeni {
    
    private Suoritukset suoritukset = new Suoritukset();
    private Liikkeet liikkeet = new Liikkeet();
    
    
    /**
     * Lisätään uusi suoritus
     * @param suoritus Lisättävä suoritus
     * @throws SailoException Poikkeus jos lisääminen ei onnistu
     */
    public void lisaa(Suoritus suoritus) throws SailoException {
        suoritukset.lisaa(suoritus);
    }
    
    
    /**
     * Lisätään uusi liike
     * @param liike Lisättävä liike
     */
    public void lisaa(Liike liike) {
        liikkeet.lisaa(liike);
    }
    
    /**
     * @return Suorituksien lukumäärä
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
     * @param suoritus Suoritus jolle suorituksia haetaan
     * @return Tietorakenne, jossa viitteet löydettyihin suorituksiin
     * @example
     */
    public List<Liike> annaLiikkeet(Suoritus suoritus) {
        return liikkeet.annaLiikkeet(suoritus.getTreeniNro());
    }
    
    /**
     * @param hakemisto Hakemisto
     * @throws SailoException Ilmoitus, jos virhe
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        //suoritukset.lueTiedostosta(hakemisto);
        liikkeet.lueTiedostosta(hakemisto);
    }

    

    /**
     * @param args Ei käytössä
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
