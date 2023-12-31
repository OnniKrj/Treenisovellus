/**
 * 
 */
package treeni;

import java.io.File;
import java.util.Collection;
import java.util.List;

/**
 * @author Onni
 * @version 7.11.2022
 * CRC-sis�ll�t 
 */
public class Treeni {
    
    private Suoritukset suoritukset = new Suoritukset();
    private Liikkeet liikkeet = new Liikkeet();
    
    
    private String hakemisto = "treeni";
    
    
    /**
     * Lis�t��n uusi suoritus
     * @param suoritus Lis�tt�v� suoritus
     * @throws SailoException Poikkeus jos lis��minen ei onnistu
     */
    public void lisaa(Suoritus suoritus) throws SailoException {
        suoritukset.lisaa(suoritus);
    }
    
    
    /**
     * Korvaa suorituksen tietorakenteessa
     * @param suoritus Lis�tt�v�n suorituksen viite
     * @throws SailoException Virhe, jos tietorakenne on jo t�ynn�
     */
    public void korvaaTaiLisaa(Suoritus suoritus) throws SailoException {
        suoritukset.korvaaTaiLisaa(suoritus);
        
    }
    
    /**
     * Korvaa suorituksen tietorakenteessa
     * @param liike Lis�tt�v�n suorituksen viite
     * @throws SailoException Virhe, jos tietorakenne on jo t�ynn�
     */
    public void korvaaTaiLisaa(Liike liike) throws SailoException {
        liikkeet.korvaaTaiLisaa(liike);
        
    }
    
    /**
     * Lis�t��n uusi liike
     * @param liike Lis�tt�v� liike
     */
    public void lisaa(Liike liike) {
        liikkeet.lisaa(liike);
    }
    
    
    /**
     * @param suoritus a
     * @return b
     */
    public int poista(Suoritus suoritus) {
        if (suoritus == null) return 0;
        int ret = suoritukset.poista(suoritus.getTreeniNro());
        liikkeet.poistaSuorituksenLiikkeet(suoritus.getTreeniNro());
        return ret;
    }
    
    
    /**
     * @param liike a
     */
    public void poistaLiike(Liike liike) {
        liikkeet.poista(liike);
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
     * @param suoritus Suoritus jolle suorituksia haetaan
     * @return Tietorakenne, jossa viitteet l�ydettyihin suorituksiin
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Treeni treeni = new Treeni();
     * Suoritus suoritus1 = new Suoritus(), suoritus2 = new Suoritus(), suoritus3 = new Suoritus();
     * suoritus1.kirjaa(); suoritus2.kirjaa(); suoritus3.kirjaa();
     * int id1 = suoritus1.getTreeniNro();
     * int id2 = suoritus2.getTreeniNro();
     * Liike dippi11 = new Liike(id1); treeni.lisaa(dippi11);
     * Liike dippi12 = new Liike(id1); treeni.lisaa(dippi12);
     * Liike dippi21 = new Liike(id2); treeni.lisaa(dippi21);
     * Liike dippi22 = new Liike(id2); treeni.lisaa(dippi22);
     * Liike dippi23 = new Liike(id2); treeni.lisaa(dippi23);
     * 
     * List<Liike> loytyneet;
     * loytyneet = treeni.annaLiikkeet(suoritus3);
     * loytyneet.size() === 0; 
     * loytyneet = treeni.annaLiikkeet(suoritus1);
     * loytyneet.size() === 2; 
     * loytyneet.get(0) == dippi11 === true;
     * loytyneet.get(1) == dippi12 === true;
     * loytyneet = treeni.annaLiikkeet(suoritus2);
     * loytyneet.size() === 3; 
     * loytyneet.get(0) == dippi21 === true;
     * </pre>
     */
    public List<Liike> annaLiikkeet(Suoritus suoritus) {
        return liikkeet.annaLiikkeet(suoritus.getTreeniNro());
    }
    
    
    /**
     * @param hakuehto Hakutermi
     * @param k indeksi
     * @return hakutuloksen
     * @throws SailoException Jos menee m�nk��n
     */
    public Collection<Suoritus> etsi(String hakuehto, int k) throws SailoException {
        return suoritukset.etsi(hakuehto, k);
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
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        Treeni treeni = new Treeni();
        
        try {
            treeni.lueTiedostosta("treeni");
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
