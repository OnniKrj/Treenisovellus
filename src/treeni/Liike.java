package treeni;

import java.io.OutputStream;
import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Onni
 * @version 30.11.2022
 * Toteutus liike luokalle
 */
public class Liike {

    
    private int liikeNro;
    private int treeniNro;
    private String liikeNimi = "";
    
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
     * Seuraava liikenumero on aina tämänhetkistä suurempi
     * @param lnr Asetettava liikenumero / id
     */
    private void setLiikeNro(int lnr) {
        liikeNro = lnr;
        if (liikeNro >= seuraavaNro) seuraavaNro = liikeNro + 1;
    }
    
    /**
     * Alustetaan liike tyhjäksi
     */
    public Liike() {
        //
    }
    
    /**
     * @param treeniNro treenin viitenumero
     */
    public Liike(int treeniNro) {
        this.treeniNro = treeniNro;
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
     * Apumetodi testiarvoilla täyttämistä varten
     * @param nro Viite liikkeeseen
     */
    public void TaytaLiikeTiedoilla(int nro) {
        treeniNro = nro;
        liikeNimi = "Pystypunnerrus";
        liikeNro = 2; // TODO: Kytköksiä muualle?? Oli unohtunut lisätä aikasemmassa vaiheessa tähän
        
        
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
        var sb = new StringBuilder(rivi);
        setTreeniNro(Mjonot.erota(sb, '|', getTreeniNro()));
        setLiikeNro(Mjonot.erota(sb, '|', getLiikeNro()));
        liikeNimi = Mjonot.erota(sb, '|', liikeNimi);
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
        return "" +
                getTreeniNro() + "|" +
                getLiikeNro() + "|" +
                liikeNimi;
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
        this.liikeNro = seuraavaNro;
        seuraavaNro++;
        return this.liikeNro;
    }
    
    /**
     * @param out Tulostettava tietovirta
     */
    public void tulosta(PrintStream out) {
        out.println(liikeNimi + " " + liikeNro);
        
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
        liike.kirjaa();
        liike.tulosta(System.out);
        
    }
}
