package treeni;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * @author Onni
 * @version 30.11.2022
 * Liikkeet -luokan toteutus
 */
public class Liikkeet implements Iterable<Liike> {

    
    /** Taulukko liikkeistä **/
    private final List<Liike> alkiot = new ArrayList<Liike>();
    private boolean muutettu = false;
    
    
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
        muutettu = true;
    }
    
    
    /**
     * 
     * @param liike Lisättävä liike
     * @throws SailoException Virhe, jos tietorakenne on jo täynnä
     */
    public void korvaaTaiLisaa(Liike liike) throws SailoException {
        int id = liike.getTreeniNro();
        for (int i = 0; i < getLkm(); i++) {
            if (alkiot.get(i).getTreeniNro() == id) {
                alkiot.set(i, liike);
                muutettu = true;
                return;

            }

        }
        lisaa(liike);
        
    }
    
    
    /**
     * @param liike a
     * @return b
     */
    public boolean poista(Liike liike) {
        boolean ret = alkiot.remove(liike);
        if (ret) muutettu = true;
        return ret;
    }
    
    
    
    /**
     * @param treeniNro a
     * @return b
     */
    public int poistaSuorituksenLiikkeet(int treeniNro) {
        int n = 0;
        for (Iterator<Liike> it = alkiot.iterator(); it.hasNext();) {
            Liike liike = it.next();
            if ( liike.getTreeniNro() == treeniNro ) {
                it.remove();
                n++;
            }
        }
        if (n > 0) muutettu = true;
        return n;
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
     * 
     * @param hakemisto Luettava tiedosto
     * @throws SailoException Virheilmoitus jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * 
     * </pre>
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/liikkeet.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = fi.nextLine();
                if (s == null || s.equals("") || s.charAt(0) == ';') continue;
                Liike liike = new Liike();
                liike.parse(s);
                lisaa(liike);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        }
        muutettu = false;
    }
    
    
    /**
     * @param hakemisto Tallennettavan tiedoston hakemisto
     * @throws SailoException Jos tallennus epäonnistuu
     * @example
     * <pre name="test">
     * 
     * </pre>
     */
    public void tallenna(String hakemisto) throws SailoException {
        if ( !muutettu ) return;
        File ftied = new File(hakemisto + "/liikkeet.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (var lkt : alkiot) { //liikkeet
                fo.println(lkt.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea");
        }
        muutettu = false;
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
        
        try {
            liikkeet.lueTiedostosta("treeni");
        } catch (SailoException ex) {
            System.err.println(ex.getMessage());
        }
        
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
        
        try {
            liikkeet.tallenna("treeni"); 
        } catch (SailoException e) {
            //e.printStackTrace();
            System.err.println(e.getMessage());
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
