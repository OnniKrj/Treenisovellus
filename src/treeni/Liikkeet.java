package treeni;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Onni
 * @version 30.11.2022
 * Liikkeet -luokan toteutus
 */
public class Liikkeet implements Iterable<Liike> {

    private String tiedostonNimi = "";
    
    /** Taulukko liikkeistä **/
    private final Collection<Liike> alkiot = new ArrayList<Liike>();
    
    
    /**
     * Alustetaan liikkeet
     */
    public Liikkeet() {
        //
    }
    
    
    /**
     * @param liike Lisättävä liike
     */
    public void lisaa(Liike liike) {
        alkiot.add(liike);
    }
    
    
    
    /**
     * @param treeninro viite treeniin
     * @return tietorakenne jossa on viitteet löydettyihin liikkeisiin
     * @example
     */
    public List<Liike> annaLiikkeet(int treeninro) {
        List<Liike> loydetyt = new ArrayList<Liike>();
        for (Liike liike : alkiot)
            if (liike.getTreeniNro() == treeninro) loydetyt.add(liike);
        return loydetyt;
    }
    
    /**
     * @param hakemisto Tiedoston hakemisto
     * @throws SailoException Ilmoitus jos epäonnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        tiedostonNimi = hakemisto + ".liike";
        throw new SailoException("Ei osata vielä lukea tiedostoa " + tiedostonNimi);
    }
    
    
    /**
     * @throws SailoException Ilmoitus, jos epäonnistuu
     */
    public void talleta() throws SailoException {
        throw new SailoException("Ei osata vielä tallettaa tiedostoa " + tiedostonNimi);
    }
    
    
    /**
     * @return Liikkeiden lukumäärä
     */
    public int getLkm() {
        return alkiot.size();
    }
    
    /**
     * @param args Testiohjelma liikkeille
     */
    public static void main(String[] args) {
        Liikkeet liikkeet = new Liikkeet();
        Liike punnerrus1 = new Liike();
        punnerrus1.TaytaLiikeTiedoilla(2);
        Liike punnerrus2 = new Liike();
        punnerrus2.TaytaLiikeTiedoilla(1);
        Liike punnerrus3 = new Liike();
        punnerrus3.TaytaLiikeTiedoilla(2);
        Liike punnerrus4 = new Liike();
        punnerrus4.TaytaLiikeTiedoilla(2);
        
        liikkeet.lisaa(punnerrus1);
        liikkeet.lisaa(punnerrus2);
        liikkeet.lisaa(punnerrus3);
        //liikkeet.lisaa(punnerrus2);
        liikkeet.lisaa(punnerrus4);
        
        System.out.println("========================= Liikkeet testi ==========================");
        
        var liikkeet2 = liikkeet.annaLiikkeet(1);
        
        for (Liike liike : liikkeet2) {
            System.out.println(liike.getTreeniNro() + "");
            liike.tulosta(System.out);
        }
        
    }
    
    /**

     */
    @Override
    public Iterator<Liike> iterator() {
        // TODO Auto-generated method stub
        return alkiot.iterator();
    }
}
