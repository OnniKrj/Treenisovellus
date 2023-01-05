package treeni;

import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.Tietue;

/**
 * @author Onni
 * @version 30.11.2022
 * Toteutus liike luokalle
 */
public class Liike implements Cloneable, Tietue {

    private int treeniNro; // Suoritus numero?
    private int liikeNro;
    private String liikeNimi = "";
    private int sarjaMaara = 0;
    private int toistoMaara = 0;
    private double paino = 0;
    
    private static int seuraavaNro = 1;
    
    
    /**
     * Seuraava treeninumero on aina tämänhetkistä suurempi
     * @param nr Asetettava treeninumero / id
     */
    private void setTreeniNro(int nr) {
        treeniNro = nr;
        if ( treeniNro >= seuraavaNro ) seuraavaNro = treeniNro + 1;
    }
    
    
    /**
     * Alustetaan liike tyhjäksi
     */
    public Liike() {
        //
    }
    
    /**
     * @param liikeNro treenin viitenumero
     */
    public Liike(int liikeNro) {
        this.liikeNro = liikeNro;
    }
    
    /**
     * @return Liikkeen id
     */
    public int getLiikeNro() {
        return liikeNro;
    }
    
    
    /**
     * @return Liikkeen nimi
     */
    public String getLiikeNimi() {
        return liikeNimi;
    }
    
    
    /**
     * @return Treenin numero
     */
    public int getTreeniNro() {
        return treeniNro;
    }
       
    
    
    /**
     * @return Suorituksen sarjamaara
     */
    public int getSarjaMaara() {
        return sarjaMaara;
    }
    
    
    /**
     * @return Suorituksen toistomäärä
     */
    public int getToistoMaara() {
        return toistoMaara;
    }
    
    /**
     * @return Suorituksen paino kiloina
     */
    public double getPaino() {
        return paino;
    }
    

    
    /**
     * Palauttaa jäsenen kenttien lukumäärän
     * @return kenttien lukumäärä
     */
    @Override
    public int getKenttia() {
        return 6;
    }

    
    /**
     * Eka kenttä joka on mielekäs kysyttäväksi
     * @return eknn kentän indeksi
     */
    @Override
    public int ekaKentta() {
        return 2;
    }

    
    /**
     * Palauttaa k:tta jäsenen kenttää vastaavan kysymyksen
     * @param k kuinka monennen kentän kysymys palautetaan (0-alkuinen)
     * @return k:netta kenttää vastaava kysymys
     */
    @Override
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "treeni id";
        case 1: return "liike id";
        case 2: return "Liikkeen nimi";
        case 3: return "Sarjat";
        case 4: return "Toistot";
        case 5: return "Paino";
        default: return "Asd";

        }
    }
    
    
    /**
     * Antaa k:n kentän sisällön merkkijonona
     * @param k monenenko kentän sisältö palautetaan
     * @return kentän sisältö merkkijonona
     */
    @Override
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + treeniNro;
        case 1: return "" + liikeNro;
        case 2: return "" + liikeNimi;
        case 3: return "" + sarjaMaara;
        case 4: return "" + toistoMaara;
        case 5: return "" + paino;
        default: return "Asd";

        }

    }
    
    @Override
    public Liike clone() throws CloneNotSupportedException {
        return (Liike)super.clone();
    }
    
    /**
     * Apumetodi testiarvoilla täyttämistä varten
     * @param nro Viite liikkeeseen
     */
    public void TaytaLiikeTiedoilla(int nro) {
        treeniNro = nro;
        liikeNimi = "Pystypunnerrus";
        liikeNro = 2; // TODO: Kytköksiä muualle?? Oli unohtunut lisätä aikasemmassa vaiheessa tähän
        sarjaMaara = 3;
        toistoMaara = 8;
        paino = 70;
        
        
    }
    
    
    /**
     * @param rivi Rivi jolta tiedot otetaan
     * @example
     * @example
     * <pre name="test">
     *  Suoritus suoritus = new Suoritus();
     *  suoritus.parse(" 3 |  1  |  3");
     *  suoritus.getTreeniNro() === 3;
     *  suoritus.toString().startsWith("3|1|3|") === true;
     *  
     *  suoritus.kirjaa();
     *  int n = suoritus.getTreeniNro();
     *  suoritus.parse(""+(n+20));
     *  suoritus.kirjaa();
     *  suoritus.getTreeniNro() === n+20+1;
     * </pre>
     */
    public void parse(String rivi) {
        StringBuffer sb = new StringBuffer(rivi);
        for (int k = 0; k < getKenttia(); k++)
            aseta(k, Mjonot.erota(sb, '|'));

        
    }
    
    
    /**
     * @return Suorituksen tiedot merkkijonona
     * @example
     * <pre name="test">
     * Suoritus suoritus = new Suoritus();
     * suoritus.parse(" 3 |  1  |  3");
     * suoritus.toString().startsWith("3|1|3|") === true;
     * </pre>
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("");
        String erotin = "";
        for (int k = 0; k < getKenttia(); k++) {
            sb.append(erotin);
            sb.append(anna(k));
            erotin = "|";
        }
        return sb.toString();

    }
    
    
    
    /**
     * @return Täytetään
     * 
     * <pre name="test">
     * Liike liike1 = new Liike();
     * liike1.getLiikeNro() === 0;
     * liike1.kirjaa();
     * Liike liike2 = new Liike();
     * liike2.kirjaa();
     * int n1 = liike1.getLiikeNro();
     * int n2 = liike2.getLiikeNro();
     * n1 === n2 - 1;
     * </pre>
     */
    public int kirjaa() {
        treeniNro = seuraavaNro;
        seuraavaNro++;
        return treeniNro;
    }
    
    /**
     * @param out Tulostettava tietovirta
     */
    public void tulosta(PrintStream out) {
        out.println(liikeNimi + " Sarjat " + sarjaMaara + " Toistot " + toistoMaara + " Paino " + paino);
        
    }
    
    /**
     * @param os Tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Liike liike = new Liike();
        liike.TaytaLiikeTiedoilla(2);
        liike.tulosta(System.out);
        
    }

    @Override
    public String aseta(int k, String s) {
        String st = s.trim();
        StringBuffer sb = new StringBuffer(st);
        switch (k) {
        case 0:
            setTreeniNro(Mjonot.erota(sb, '$', getTreeniNro()));
            return null;
        case 1:
            liikeNro = Mjonot.erota(sb, '$', liikeNro);
            return null;
        case 2:
            liikeNimi = Mjonot.erota(sb, '§', getLiikeNimi());
            return null;
        case 3:
            sarjaMaara = Mjonot.erota(sb, '§', getSarjaMaara());
            return null;
        case 4:
            toistoMaara = Mjonot.erota(sb, '§', getToistoMaara());
            return null;
        case 5:
            paino = Mjonot.erota(sb, '§', getPaino());
            return null;
            
        default:
            return "Asd";
        }

    }

}
