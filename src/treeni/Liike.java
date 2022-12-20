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
     * Seuraava treeninumero on aina t�m�nhetkist� suurempi
     * @param nr Asetettava treeninumero / id
     */
    private void setTreeniNro(int nr) {
        treeniNro = nr;
        if ( treeniNro >= seuraavaNro ) seuraavaNro = treeniNro + 1;
    }
    
    /**
     * Seuraava liikenumero on aina t�m�nhetkist� suurempi
     * @param lnr Asetettava liikenumero / id
     */
    private void setLiikeNro(int lnr) {
        liikeNro = lnr;
        if (liikeNro >= seuraavaNro) seuraavaNro = liikeNro + 1;
    }
    
    /**
     * Alustetaan liike tyhj�ksi
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
     * @return Suorituksen sarjamaara
     */
    public int getSarjaMaara() {
        return sarjaMaara;
    }
    
    
    /**
     * @return Suorituksen toistom��r�
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
     * Palauttaa j�senen kenttien lukum��r�n
     * @return kenttien lukum��r�
     */
    @Override
    public int getKenttia() {
        return 5;
    }

    
    /**
     * Eka kentt� joka on mielek�s kysytt�v�ksi
     * @return eknn kent�n indeksi
     */
    @Override
    public int ekaKentta() {
        return 0;
    }

    
    /**
     * Palauttaa k:tta j�senen kentt�� vastaavan kysymyksen
     * @param k kuinka monennen kent�n kysymys palautetaan (0-alkuinen)
     * @return k:netta kentt�� vastaava kysymys
     */
    @Override
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "Liikkeen id";
        case 1: return "Liikkeen nimi";
        case 2: return "Sarjat";
        case 3: return "Toistot";
        case 4: return "Paino";
        default: return "Asd";

        }
    }
    
    
    /**
     * Antaa k:n kent�n sis�ll�n merkkijonona
     * @param k monenenko kent�n sis�lt� palautetaan
     * @return kent�n sis�lt� merkkijonona
     */
    @Override
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + liikeNro;
        case 1: return "" + liikeNimi;
        case 2: return "" + sarjaMaara;
        case 3: return "" + toistoMaara;
        case 4: return "" + paino;
        default: return "Asd";

        }

    }
    
    @Override
    public Liike clone() throws CloneNotSupportedException {
        return (Liike)super.clone();
    }
    
    /**
     * Apumetodi testiarvoilla t�ytt�mist� varten
     * @param nro Viite liikkeeseen
     */
    public void TaytaLiikeTiedoilla(int nro) {
        treeniNro = nro;
        liikeNimi = "Pystypunnerrus";
        liikeNro = 2; // TODO: Kytk�ksi� muualle?? Oli unohtunut lis�t� aikasemmassa vaiheessa t�h�n
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
        var sb = new StringBuilder(rivi);
        setTreeniNro(Mjonot.erota(sb, '|', getTreeniNro()));
        setLiikeNro(Mjonot.erota(sb, '|', getLiikeNro()));
        liikeNimi = Mjonot.erota(sb, '|', liikeNimi);
        sarjaMaara = Mjonot.erota(sb, '|', sarjaMaara);
        toistoMaara = Mjonot.erota(sb, '|', toistoMaara);
        paino = Mjonot.erota(sb, '|', paino);
        
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
                liikeNimi + "|" +
                sarjaMaara + "|" +
                toistoMaara + "|" +
                paino;
    }
    
    
    
    /**
     * @return T�ytet��n
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
        out.println("id " + liikeNro + " " + liikeNimi + " Sarjat " + sarjaMaara + " Toistot " + toistoMaara + " Paino " + paino);
        
    }
    
    /**
     * @param os Tietovirta johon tulostetaan
     */
    public void tulosta(OutputStream os) {
        tulosta(new PrintStream(os));
    }
    
    
    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        Liike liike = new Liike();
        liike.TaytaLiikeTiedoilla(2);
        liike.kirjaa();
        liike.tulosta(System.out);
        
    }

    @Override
    public String aseta(int k, String s) {
        String st = s.trim();
        StringBuffer sb = new StringBuffer(st);
        switch (k) {
        case 0:
            setLiikeNro(Mjonot.erota(sb, '|', getLiikeNro()));
            return null;
        case 1:
            liikeNimi = Mjonot.erota(sb, '|', getLiikeNimi());
            return null;
        case 2:
            sarjaMaara = Mjonot.erota(sb, '|', getSarjaMaara());
            return null;
        case 3:
            toistoMaara = Mjonot.erota(sb, '|', getToistoMaara());
            return null;
        case 4:
            paino = Mjonot.erota(sb, '|', getPaino());
            return null;
            
        default:
            return "Asd";
        }

    }
}
