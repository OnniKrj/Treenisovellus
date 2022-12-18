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
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;

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
     * @param suoritus Lisättävä suoritus
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
     * suoritukset.lisaa(treeni1);  #THROWS SailoException
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
     * @param suoritus Lisättävän suorituksen viite
     * @throws SailoException Virhe, jos tietorakenne on jo täynnä
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
     * @return Suoritusten lukumäärä
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
     * @throws SailoException Virheilmoitus jos lukeminen epäonnistuu
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
     * @param hakemisto Tallennettavan tiedoston hakemisto
     * @throws SailoException Jos tallennus epäonnistuu
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
        
    }
    
    /**
     * @param args Ei käytössä
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
         * @return True jos on vielä suorituksia
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
    

     
    /**
     * @param hakuehto Hakuehto
     * @param k Etsittävän kentän indeksi
     * @return Tietorakenne löytyneistä suorituksista
     */
    /*
    @SuppressWarnings("unused")
    public Collection<Suoritus> etsi(String hakuehto, int k) { 
        Collection<Suoritus> loytyneet = new ArrayList<Suoritus>(); 
        for (Suoritus suoritus : this) { 
            loytyneet.add(suoritus);  
        } 
        return loytyneet; 
    }
    */




}
