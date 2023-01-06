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
     * 
     */
    public void lisaa(Liike liike) {
        alkiot.add(liike);
        muutettu = true;
    }
    
    
    /**
     * 
     * @param liike Lisättävän liikkeen viite
     * @throws SailoException Virhe, jos tietorakenne on jo täynnä
     * @example
     * <pre name="test">
     * #THROWS SailoException, CloneNotSupportedException
     * #PACKAGEIMPORT
     * #import java.util.*;
     * Liikkeet liikkeet = new Liikkeet();
     * Liike liike1 = new Liike(), liike2 = new Liike();
     * liike1.kirjaa(); liike2.kirjaa();
     * liikkeet.getLkm() === 0;
     * liikkeet.korvaaTaiLisaa(liike1); liikkeet.getLkm() === 1;
     * liikkeet.korvaaTaiLisaa(liike2); liikkeet.getLkm() === 2;
     * Liike liike3 = liike1.clone();
     * liike3.aseta(3, "kkk");
     * Iterator<Liike> i2 = liikkeet.iterator();
     * i2.next() === liike1;
     * liikkeet.korvaaTaiLisaa(liike3); liikkeet.getLkm() === 2;
     * i2 = liikkeet.iterator();
     * Liike l = i2.next();
     * l === liike3;
     * l == liike3 === true;
     * l == liike1 === false;
     * </pre>
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
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.io.File;
     * Liikkeet liikkeet = new Liikkeet();
     * Liike dippi21 = new Liike(); dippi21.TaytaLiikeTiedoilla(2);
     * Liike dippi11 = new Liike(); dippi11.TaytaLiikeTiedoilla(1);
     * Liike dippi22 = new Liike(); dippi22.TaytaLiikeTiedoilla(2);
     * Liike dippi12 = new Liike(); dippi12.TaytaLiikeTiedoilla(1);
     * Liike dippi23 = new Liike(); dippi23.TaytaLiikeTiedoilla(2);
     * liikkeet.lisaa(dippi21);
     * liikkeet.lisaa(dippi11);
     * liikkeet.lisaa(dippi22);
     * liikkeet.lisaa(dippi12);
     * liikkeet.poista(dippi23) === false ; liikkeet.getLkm() === 4;
     * liikkeet.poista(dippi11) === true; liikkeet.getLkm() === 3;
     * List<Liike> l = liikkeet.annaLiikkeet(1);
     * l.size() === 1;
     * l.get(0) === dippi12;
     * </pre>
     */
    public boolean poista(Liike liike) {
        boolean ret = alkiot.remove(liike);
        if (ret) muutettu = true;
        return ret;
    }
    
    
    
    /**
     * @param treeniNro a
     * @return b
     * @example
     * <pre name="test">
     * Liikkeet liikkeet = new Liikkeet();
     * Liike dippi21 = new Liike(); dippi21.TaytaLiikeTiedoilla(2);
     * Liike dippi11 = new Liike(); dippi11.TaytaLiikeTiedoilla(1);
     * Liike dippi22 = new Liike(); dippi22.TaytaLiikeTiedoilla(2);
     * Liike dippi12 = new Liike(); dippi12.TaytaLiikeTiedoilla(1);
     * Liike dippi23 = new Liike(); dippi23.TaytaLiikeTiedoilla(2);
     * liikkeet.lisaa(dippi21);
     * liikkeet.lisaa(dippi11);
     * liikkeet.lisaa(dippi22);
     * liikkeet.lisaa(dippi12);
     * liikkeet.lisaa(dippi23);
     * liikkeet.poistaSuorituksenLiikkeet(2) === 3; liikkeet.getLkm() === 2;
     * liikkeet.poistaSuorituksenLiikkeet(3) === 0; liikkeet.getLkm() === 2;
     * List<Liike> l = liikkeet.annaLiikkeet(2);
     * l.size() === 0;
     * l = liikkeet.annaLiikkeet(1);
     * l.get(0) === dippi11;
     * l.get(1) === dippi12;
     * </pre>
     */
    public int poistaSuorituksenLiikkeet(int treeniNro) {
        int n = 0;
        for (Iterator<Liike> it = alkiot.iterator(); it.hasNext();) {
            Liike liike = it.next();
            if ( liike.getLiikeNro() == treeniNro ) {
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
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Liikkeet liikkeet = new Liikkeet();
     * Liike dippi21 = new Liike(2); liikkeet.lisaa(dippi21);
     * Liike dippi11 = new Liike(1); liikkeet.lisaa(dippi11);
     * Liike dippi22 = new Liike(2); liikkeet.lisaa(dippi22);
     * Liike dippi12 = new Liike(1); liikkeet.lisaa(dippi12);
     * Liike dippi23 = new Liike(2); liikkeet.lisaa(dippi23);
     * Liike dippi51 = new Liike(5); liikkeet.lisaa(dippi51);
     * List<Liike> loytyneet;
     * loytyneet = liikkeet.annaLiikkeet(3);
     * loytyneet.size() === 0; 
     * loytyneet = liikkeet.annaLiikkeet(1);
     * loytyneet.size() === 2; 
     * loytyneet.get(0) == dippi11 === true;
     * loytyneet.get(1) == dippi12 === true;
     * loytyneet = liikkeet.annaLiikkeet(5);
     * loytyneet.size() === 1; 
     * loytyneet.get(0) == dippi51 === true;
     * </pre>
     */
    public List<Liike> annaLiikkeet(int treeninro) {
        List<Liike> loydetyt = new ArrayList<Liike>();
        for (Liike liike : alkiot)
            if (liike.getLiikeNro() == treeninro) loydetyt.add(liike);
        return loydetyt;
    }
    
    
    /**
     * 
     * @param hakemisto Luettava tiedosto
     * @throws SailoException Virheilmoitus jos lukeminen epäonnistuu
     * @example
     * <pre name="test">
     * #THROWS SailoException
     * #import java.io.File;
     * Liikkeet liikkeet = new Liikkeet();
     * Liike dippi21 = new Liike(); dippi21.TaytaLiikeTiedoilla(2);
     * Liike dippi11 = new Liike(); dippi11.TaytaLiikeTiedoilla(1);
     * Liike dippi22 = new Liike(); dippi22.TaytaLiikeTiedoilla(2); 
     * Liike dippi12 = new Liike(); dippi12.TaytaLiikeTiedoilla(1); 
     * Liike dippi23 = new Liike(); dippi23.TaytaLiikeTiedoilla(2); 
     * String tiedNimi = "treeni";
     * File ftied = new File(tiedNimi + "/liikkeet.dat");
     * ftied.delete();
     * liikkeet.lueTiedostosta(tiedNimi); #THROWS SailoException
     * liikkeet.lisaa(dippi21);
     * liikkeet.lisaa(dippi11);
     * liikkeet.lisaa(dippi22);
     * liikkeet.lisaa(dippi12);
     * liikkeet.lisaa(dippi23);
     * liikkeet.tallenna(tiedNimi);
     * liikkeet = new Liikkeet();
     * liikkeet.lueTiedostosta(tiedNimi);
     * Iterator<Liike> i = liikkeet.iterator();
     * i.next().toString() === dippi21.toString();
     * i.next().toString() === dippi11.toString();
     * i.next().toString() === dippi22.toString();
     * i.next().toString() === dippi12.toString();
     * i.next().toString() === dippi23.toString();
     * i.hasNext() === false;
     * liikkeet.lisaa(dippi23);
     * liikkeet.tallenna(tiedNimi);
     * ftied.delete() === true;
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
     * <pre>
     * 1|2|Penkkipunnerrus|3|8|35
     * 2|3|Kyykky|3|6|80
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
     * @return liikeiteraattori
     * @example
     * <pre name="test">
     * #import java.util.*;
     * Liikkeet liikkeet = new Liikkeet();
     * Liike dippi21 = new Liike(2); liikkeet.lisaa(dippi21);
     * Liike dippi11 = new Liike(1); liikkeet.lisaa(dippi11);
     * Liike dippi22 = new Liike(2); liikkeet.lisaa(dippi22);
     * Liike dippi12 = new Liike(1); liikkeet.lisaa(dippi12);
     * Liike dippi23 = new Liike(2); liikkeet.lisaa(dippi23);
     * Iterator<Liike> i2 = liikkeet.iterator();
     * i2.next() === dippi21;
     * i2.next() === dippi11;
     * i2.next() === dippi22;
     * i2.next() === dippi12;
     * i2.next() === dippi23;
     * i2.next() === dippi12;  #THROWS NoSuchElementException
     * 
     * int n = 0;
     * int jnrot[] = {2,1,2,1,2};
     * 
     * for (Liike liik : liikkeet) {
     *  liik.getLiikeNro() === jnrot[n]; n++;
     * }
     * 
     * n === 5;
     * </pre>
     */
    @Override
    public Iterator<Liike> iterator() {
        return alkiot.iterator();
    }
}
