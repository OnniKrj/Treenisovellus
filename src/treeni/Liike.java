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
     * Alustetaan liike tyhj�ksi
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
        return 6;
    }

    
    /**
     * Eka kentt� joka on mielek�s kysytt�v�ksi
     * @return eknn kent�n indeksi
     */
    @Override
    public int ekaKentta() {
        return 2;
    }

    
    /**
     * Palauttaa k:tta j�senen kentt�� vastaavan kysymyksen
     * @param k kuinka monennen kent�n kysymys palautetaan (0-alkuinen)
     * @return k:netta kentt�� vastaava kysymys
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
     * Antaa k:n kent�n sis�ll�n merkkijonona
     * @param k monenenko kent�n sis�lt� palautetaan
     * @return kent�n sis�lt� merkkijonona
     * @example
     * <pre name="test">
     * Liike liik = new Liike();
     * liik.parse("  1  |  2  | Pystypunnerrus  |  3  |  8  |  30  ");
     * liik.anna(0) === "1";
     * liik.anna(1) === "2";
     * </pre>
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
    
    
    /**
     * @return Kloonattu liike
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Liike liike = new Liike();
     * liike.parse("  1  |  2  | Pystypunnerrus  |  3  |  8  |  30  ");
     * Liike kopio = liike.clone();
     * kopio.toString() === liike.toString();
     * liike.parse("  2  |  12  | Kyykky  |  3  |  6  |  80  ");
     * kopio.toString().equals(liike.toString()) === false;
     * </pre>
     */
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
        liikeNro = nro; // TODO: Kytk�ksi� muualle?? Oli unohtunut lis�t� aikasemmassa vaiheessa t�h�n
        sarjaMaara = 3;
        toistoMaara = 8;
        paino = 70;
        
        
    }
    
    
    /**
     * @param rivi Rivi jolta tiedot otetaan
     * @example
     * <pre name="test">
     *  Liike liike = new Liike();
     *  liike.parse("  1  |  2  | Pystypunnerrus  |  3  |  8  |  30  ");
     *  liike.getLiikeNro() === 2;
     *  liike.toString() === "1|2|Pystypunnerrus|3|8|30.0";
     *  liike.kirjaa();
     *  int n = liike.getTreeniNro();
     *  liike.parse(""+(n+20));
     *  liike.kirjaa();
     *  liike.getTreeniNro() === n+20+1;
     *  liike.toString() === "" + (n+20+1) + "|2|Pystypunnerrus|3|8|30.0";
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
     * Liike liike = new Liike();
     * liike.parse("  1  |  2  | Pystypunnerrus  |  3  |  8  |  30  ");
     * liike.toString() === "1|2|Pystypunnerrus|3|8|30.0";
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
     * @return Antaa liikkeelle seuraavan
     * 
     * <pre name="test">
     * Liike liike1 = new Liike();
     * liike1.getTreeniNro() === 0;
     * liike1.kirjaa();
     * Liike liike2 = new Liike();
     * liike2.kirjaa();
     * int n1 = liike1.getTreeniNro();
     * int n2 = liike2.getTreeniNro();
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
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        Liike liike = new Liike();
        liike.TaytaLiikeTiedoilla(2);
        liike.tulosta(System.out);
        
    }

    /**
     * Asetetaan valitun kent�n sis�lt�. Jos asettaminen onnistuu,
     * palautetaan null.
     * @param k Mink� kent�n sis�lt� asetetaan
     * @param s Asetettava sis�lt� merkkijonona
     * @example
     * <pre name="test">
     * Liike liike = new Liike();
     * liike.aseta(2, "Dippi") === null;
     * liike.aseta(5, "30") === null;
     * </pre>
     */
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
            liikeNimi = Mjonot.erota(sb, '�', getLiikeNimi());
            return null;
        case 3:
            sarjaMaara = Mjonot.erota(sb, '�', getSarjaMaara());
            return null;
        case 4:
            toistoMaara = Mjonot.erota(sb, '�', getToistoMaara());
            return null;
        case 5:
            paino = Mjonot.erota(sb, '�', getPaino());
            return null;
            
        default:
            return "Asd";
        }

    }

}
