/**
 * 
 */
package treeni;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import fi.jyu.mit.ohj2.WildChars;

/**
 * @author Onni
 * @version 7.11.2022
 * CRC-kortin tiedot
 */
public class Suoritukset implements Iterable<Suoritus>{
    
    private static final int MAX_SUORITUKSIA = 5;
    private int lkm = 0;
    private Suoritus alkiot[] = new Suoritus[MAX_SUORITUKSIA];
    private boolean muutettu = false;

    
    /**
     * Luodaan alustava taulukko
     */
    public Suoritukset() {
        alkiot = new Suoritus[MAX_SUORITUKSIA];
    }
    
    
    /**
     * @param suoritus Lis�tt�v� suoritus
     * @throws SailoException Poikkeus jos taulukossa ei ole tilaa
     * <pre name="test">
     * #THROWS SailoException 
     * Suoritukset suoritukset = new Suoritukset();
     * Suoritus treeni1 = new Suoritus(), treeni2 = new Suoritus();
     * suoritukset.getLkm() === 0;
     * suoritukset.lisaa(treeni1); suoritukset.getLkm() === 1;
     * suoritukset.lisaa(treeni2); suoritukset.getLkm() === 2;
     * suoritukset.lisaa(treeni1); suoritukset.getLkm() === 3;
     * suoritukset.anna(0) === treeni1;
     * suoritukset.anna(1) === treeni2;
     * suoritukset.anna(2) === treeni1;
     * suoritukset.anna(1) == treeni1 === false;
     * suoritukset.anna(1) == treeni2 === true;
     * suoritukset.anna(3) === treeni1; #THROWS IndexOutOfBoundsException 
     * suoritukset.lisaa(treeni1); suoritukset.getLkm() === 4;
     * suoritukset.lisaa(treeni1); suoritukset.getLkm() === 5;
     * suoritukset.lisaa(treeni1);
     * </pre>
     */
    public void lisaa(Suoritus suoritus) throws SailoException {
        
        if (lkm >= alkiot.length) alkiot = Arrays.copyOf(alkiot, alkiot.length + 5);
        this.alkiot[this.lkm] = suoritus;
        lkm++;
        muutettu = true;
    }
    
    /**
     * 
     * @param suoritus Lis�tt�v�n suorituksen viite
     * @throws SailoException Virhe, jos tietorakenne on jo t�ynn�
     * @example
     * <pre name="test">
     * #THROWS SailoException, CloneNotSupportedException
     * #PACKAGEIMPORT
     * #import java.util.*;
     * Suoritukset suoritukset = new Suoritukset();
     * Suoritus treeni1 = new Suoritus(), treeni2 = new Suoritus();
     * treeni1.kirjaa(); treeni2.kirjaa();
     * suoritukset.getLkm() === 0;
     * suoritukset.korvaaTaiLisaa(treeni1); suoritukset.getLkm() === 1;
     * suoritukset.korvaaTaiLisaa(treeni2); suoritukset.getLkm() === 2;
     * Suoritus treeni3 = treeni1.clone();
     * treeni3.taytaTreeniTiedoilla();
     * Iterator<Suoritus> it = suoritukset.iterator();
     * it.next() == treeni1 === true;
     * suoritukset.korvaaTaiLisaa(treeni3); suoritukset.getLkm() === 2;
     * it = suoritukset.iterator();
     * Suoritus treeni0 = it.next();
     * treeni0 === treeni3;
     * treeni0 == treeni3 === true;
     * treeni0 == treeni1 === false;
     * </pre>
     */
    public void korvaaTaiLisaa(Suoritus suoritus) throws SailoException {
        int id = suoritus.getTreeniNro();
        for (int i = 0; i < lkm; i++) {
            if (alkiot[i].getTreeniNro() == id) {
                alkiot[i] = suoritus;
                muutettu = true;
                return;
            }
        }
        lisaa(suoritus);
        
    }
    
    
    /**
     * @return Suoritusten lukum��r�
     */
    public int getLkm() {
        return lkm;
    }
    
    
    /**
     * Palauttaa viitteen i:teen suoritukseen
     * @param i Monennenko suorituksen viite halutaan
     * @return Viite suoritukseen, jonka indeksi on i
     * @throws IndexOutOfBoundsException jos i ei ole sallitulla alueella
     */
    public Suoritus anna(int i) throws IndexOutOfBoundsException {
        if (i < 0 || this.lkm <= i)
            throw new IndexOutOfBoundsException("Laiton indeksi: " + i);
        
        return alkiot[i];
    }
    
    /**
     * 
     * @param hakemisto Luettava tiedosto
     * @throws SailoException Virheilmoitus jos lukeminen ep�onnistuu
     */
    public void lueTiedostosta(String hakemisto) throws SailoException {
        String nimi = hakemisto + "/suoritukset.dat";
        File ftied = new File(nimi);
        
        try (Scanner fi = new Scanner(new FileInputStream(ftied))) {
            while (fi.hasNext()) {
                String s = fi.nextLine();
                if (s == null || s.equals("") || s.charAt(0) == ';') continue;
                Suoritus suoritus = new Suoritus();
                suoritus.parse(s);
                lisaa(suoritus);
            }
        } catch (FileNotFoundException e) {
            throw new SailoException("Ei saa luettua tiedostoa " + nimi);
        }
        muutettu = false;
    }
    
    
    /**
     * Tallentaa suorituksen tiedostoon
     * @param hakemisto Tallennettavan tiedoston hakemisto
     * @throws SailoException Jos tallennus ep�onnistuu
     * @example
     * <pre>
     * 1|1.1.2023
     * 2|3.1.2023
     * </pre>
     */
    public void tallenna(String hakemisto) throws SailoException {
        if (!muutettu) return;
        File ftied = new File(hakemisto + "/suoritukset.dat");
        try (PrintStream fo = new PrintStream(new FileOutputStream(ftied, false))) {
            for (int i = 0; i < this.getLkm(); i++) {
                Suoritus suoritus = this.anna(i);
                fo.println(suoritus.toString());
            }
        } catch (FileNotFoundException ex) {
            throw new SailoException("Tiedosto " + ftied.getAbsolutePath() + " ei aukea");
        }
        
        muutettu = false;
    }
    
    
    /**
     * @param hakuehto Hakutermi
     * @param k indeksi
     * @return L�ytyneet
     */
    public Collection<Suoritus> etsi(String hakuehto, int k) {
        String ehto = "*";
        if ( hakuehto != null && hakuehto.length() > 0 ) ehto = hakuehto;
        int hk = k;
        if ( hk < 0 ) hk = 1;
        List<Suoritus> loytyneet = new ArrayList<Suoritus>();
        for (Suoritus suoritus : this) {
            if (WildChars.onkoSamat(suoritus.anna(hk), ehto)) loytyneet.add(suoritus);
        }
        Collections.sort(loytyneet, new Suoritus.Vertailija(hk));
        return loytyneet;
    }

    
    /**
     * @param id Haettavan suorituksen id
     * @return tulos
     */
    public int etsiId(int id) {
        for (int i = 0; i < lkm; i++)
            if (id == alkiot[i].getTreeniNro()) return i;
        return -1;
    }
    
    
    /**
     * @param id a
     * @return b
     */
    public int poista(int id) {
        int ind = etsiId(id);
        if (ind < 0) return 0;
        lkm--;
        for (int i = ind; i < lkm; i++)
            alkiot[i] = alkiot[i + 1];
        alkiot[lkm] = null;
        muutettu = true;
        return 1;
    }
    
    
    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args){
        
      Suoritukset suoritukset = new Suoritukset();
      
      try {
          suoritukset.lueTiedostosta("treeni");
      } catch (SailoException ex) {
          System.err.println(ex.getMessage());
      }
      
      
      Suoritus treeni1 = new Suoritus();
      Suoritus treeni2 = new Suoritus();
      
      treeni1.kirjaa();
      treeni1.taytaTreeniTiedoilla();
      treeni2.kirjaa();
      treeni2.taytaTreeniTiedoilla();
      
      try {
        suoritukset.lisaa(treeni1);
        suoritukset.lisaa(treeni2);
        
        System.out.println("==========Suoritukset testi===========");
        
        for (int i = 0; i < suoritukset.getLkm(); i++) {
            Suoritus suoritus = suoritukset.anna(i);
            System.out.println("Suoritus indeksi " + i);
            suoritus.tulosta(System.out);
      }
        
      } catch (SailoException e) {
          System.err.println(e.getMessage());
        
    }
     try {
         suoritukset.tallenna("treeni"); 
     } catch (SailoException e) {
         //e.printStackTrace();
         System.err.println(e.getMessage());
     }
     
      
    
    }
    
    /**
     * @author Onni
     * @version 8.12.2022
     *
     */
    public class SuorituksetIterator implements Iterator<Suoritus> {

        private int kohdalla = 0;
        
        
        /**
         * Onko seuraavaa suoritusta olemassa?
         * @return True jos on viel� suorituksia
         */
        @Override
        public boolean hasNext() {
            
            return kohdalla < getLkm();
        }

        /**
         * Annetaan seuraava suoritus
         * @return seuraava suoritus
         */
        @Override
        public Suoritus next() throws NoSuchElementException {
            if (!hasNext()) throw new NoSuchElementException("Ei ole");
            return anna(kohdalla++);
        }
        
    }

    /**
     * Palautetaan iteraattori suorituksistaan
     * @return Suoritus iteraattori
     */
    @Override
    public Iterator<Suoritus> iterator() {
        return new SuorituksetIterator();
    }
    

}
