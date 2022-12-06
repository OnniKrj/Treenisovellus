/**
 * 
 */
package treeni;

import java.io.File;
import java.util.List;

/**
 * @author Onni
 * @version 7.11.2022
 * CRC-sisällöt 
 */
public class Treeni {
    
    private Suoritukset suoritukset = new Suoritukset();
    private Liikkeet liikkeet = new Liikkeet();
    
    
    private String hakemisto = "treeni";
    
    
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
     * @param nimi Hakemisto
     * @throws SailoException Ilmoitus, jos virhe
     */
    public void lueTiedostosta(String nimi) throws SailoException {
        File dir = new File(nimi);
        dir.mkdir();
        suoritukset = new Suoritukset();
        liikkeet = new Liikkeet();
        
        hakemisto = nimi;
        suoritukset.lueTiedostosta(nimi);
        liikkeet.lueTiedostosta(nimi);
    }

    
    /**
     * Tallennetaan treenin tiedot tiedostoon
     * @throws SailoException Virhe jos tallentamisessa on ongelmia
     */
    public void tallenna() throws SailoException {
        String virhe = "";
        try {
            suoritukset.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe = ex.getMessage();
        }
        
        try {
            liikkeet.tallenna(hakemisto);
        } catch (SailoException ex) {
            virhe += ex.getMessage();
        }
        if (!"".equals(virhe)) throw new SailoException(virhe);
    }
    

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Treeni treeni = new Treeni();
        
        try {
            treeni.lueTiedostosta("koetreeni");
        } catch (SailoException ex) {
            System.out.println(ex.getMessage());
        }
        
        Suoritus treeni1 = new Suoritus();
        Suoritus treeni2 = new Suoritus();
        treeni1.kirjaa();
        treeni1.taytaTreeniTiedoilla();
        treeni2.kirjaa();
        treeni2.taytaTreeniTiedoilla();
        
        try {
            treeni.lisaa(treeni1);
            treeni.lisaa(treeni2);
            
            for (int i = 0; i < treeni.getSuorituksia(); i++) {
                Suoritus suoritus = treeni.annaSuoritus(i);
                suoritus.tulosta(System.out);
            }
            
            treeni.tallenna();
            
        } catch (SailoException e) {
            System.err.println(e.getMessage());
        }
        
        

        
    }

}
