/**
 * 
 */
package treeni;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

import fi.jyu.mit.ohj2.Mjonot;
import kanta.PvmTarkistus;
import kanta.Tietue;

/**
 * @author Onni
 * @version 7.11.2022
 * CRC-kortin sis�lt�
 */
public class Suoritus implements Cloneable, Tietue {
    
    private int treeniNro; // suoritus numero

    private String pvm = new SimpleDateFormat("dd.MM.yyyy").format(new Date());;
    private PvmTarkistus pvmt = new PvmTarkistus();
    
    private static int seuraavaNro = 1;
    
    /**
     * Alustetaan suoritus tyhj�ksi
     */
    public Suoritus() {
        //
    }
    
    /**
     * @param out Tulostettava tietovirta
     */
    public void tulosta(PrintStream out) {
        out.println(String.format("%03d", treeniNro));
        //out.println("Liikkeen tunnus: " + liikeNro);
        //out.println("Sarjojen m��r�: " + sarjaMaara);
        //out.println("Toistojen m��r�: " + toistoMaara);
        //out.println("Sarja paino: " + paino + "kg");
        out.println("P�iv�m��r�: " + pvm + "\n");
    }
    
    /**
     * @return Treenin id-numero
     * @example
     * <pre name="test">
     * Suoritus treeni1 = new Suoritus();
     * treeni1.getTreeniNro() === 0;
     * treeni1.kirjaa();
     * Suoritus treeni2 = new Suoritus();
     * treeni2.kirjaa();
     * int tunnus1 = treeni1.getTreeniNro();
     * int tunnus2 = treeni2.getTreeniNro();
     * tunnus2 === tunnus1 + 1;
     * </pre>
     */
    public int kirjaa() {
        this.treeniNro = seuraavaNro;
        seuraavaNro++;
        return this.treeniNro;
    }
    
    
    /**
     * @return Treenin treeni-id
     */
    public int getTreeniNro() {
        return treeniNro;
    }
    
    
    /**
     * Palauttaa j�senen kenttien lukum��r�n
     * @return kenttien lukum��r�
     */
    @Override
    public int getKenttia() {
        return 2;
    }

    
    /**
     * Eka kentt� joka on mielek�s kysytt�v�ksi
     * @return eknn kent�n indeksi
     */
    @Override
    public int ekaKentta() {
        return 1;
    }

    
    /**
     * Palauttaa k:tta j�senen kentt�� vastaavan kysymyksen
     * @param k kuinka monennen kent�n kysymys palautetaan (0-alkuinen)
     * @return k:netta kentt�� vastaava kysymys
     */
    @Override
    public String getKysymys(int k) {
        switch ( k ) {
        case 0: return "treeninro";
        case 1: return "P�iv�m��r�";
        
        default: return "Asd";

        }
    }
    
    
    
    /**
     * @return P�iv�ys
     * @example
     * <pre name="test">
     * Suoritus suoritus = new Suoritus();
     * suoritus.taytaTreeniTiedoilla();
     * suoritus.getPvm() =R= "1.10.2022";
     * </pre>
     */
    public String getPvm() {
        return pvm;
    }
    
    
    
    
    
    /**
     * @param k Kuinka monennes kentt�
     * @param jono Asetetaan kent�n arvoksi
     * @return null jos asettaminen onnistuu, muuten virhe
     * @example
     * <pre name="test">
     * Suoritus suoritus = new Suoritus();
     * suoritus.aseta(1, "1.1.2023") === null;
     * </pre>
     */
    @Override
    public String aseta(int k, String jono) {
        String tjono = jono.trim();
        StringBuffer sb = new StringBuffer(tjono);
        switch (k) {
        case 0:
            setTreeniNro(Mjonot.erota(sb, '�', getTreeniNro()));
            return null;
        case 1:
            String virhe = pvmt.tarkista(jono);
            if (virhe != null) return virhe;
            pvm = tjono;
            return null;
        default:
            break;
        }
        return null;
    }
    
    
    /**
     * Antaa k:n kent�n sis�ll�n merkkijonona
     * @param k monenenko kent�n sis�lt� palautetaan
     * @return kent�n sis�lt� merkkijonona
     */
    @Override
    public String anna(int k) {
        switch ( k ) {
        case 0: return "" + treeniNro;
        case 1: return "" + pvm;
        
        default: return "Asd";

        }

    }
    
    
    /**
     * Toteutus hakutulosten vertailua varten
     * @author Onni
     * @version 21.12.2022
     */
    public static class Vertailija implements Comparator<Suoritus> {
        private int k;
        
        @SuppressWarnings("javadoc")
        public Vertailija(int k) {
            this.k = k;
        }
        
        @Override
        public int compare(Suoritus suoritus1, Suoritus suoritus2) {
            return suoritus1.getAvain(k).compareToIgnoreCase(suoritus2.getAvain(k));
        }
    }
    
    
    /**
     * Antaa k:n sis�ll�n merkkijonona
     * @param k Momemnko kent�n sis�lt� palautetaan
     * @return kent�n sis�lt� merkkijonona
     */
    public String getAvain(int k) {
        
        switch ( k ) {
        case 0: return "" + Mjonot.fmt(treeniNro, 10);
        case 1: return "" + pvm;
        default: return "asd";
        }
    }
    
    
    /**
     * Seuraava treeninumero on aina t�m�nhetkist� suurempi
     * @param nr Asetettava treeninumero
     */
    private void setTreeniNro(int nr) {
        treeniNro = nr;
        if ( treeniNro >= seuraavaNro ) seuraavaNro = treeniNro + 1;
    }
  
    
    
    /**
     * Apumetodi testiarvoilla t�ytt�mist� varten
     * TODO: Poista kun kaikki toimii
     */
    public void taytaTreeniTiedoilla() {
        
        //liikeNro = 1;
        //sarjaMaara = 3;
        //toistoMaara = 8;
        //paino = 60;
        pvm = "1.10.2022";
    }
    /**
     * @return Suorituksen tiedot merkkijonona
     * @example
     * <pre name="test">
     * Suoritus suoritus = new Suoritus();
     * suoritus.parse("  1  |  1.10.2022");
     * suoritus.toString().startsWith("1|1.10.2022") === true;
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
     * @param rivi Rivi jolta tiedot otetaan
     * @example
     * @example
     * <pre name="test">
     *  Suoritus suoritus = new Suoritus();
     *  suoritus.parse(" 3 |  1.10.2023  ");
     *  suoritus.getTreeniNro() === 3;
     *  suoritus.toString().startsWith("3|1.10.2023") === true;
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
     * Tehd��n klooni suorituksesta
     * @return Kloonattu suoritus
     * @example
     * <pre name="test">
     * #THROWS CloneNotSupportedException
     * Suoritus suoritus = new Suoritus();
     * suoritus.parse("1  |  1.10.2023  ");
     * Suoritus kopio = suoritus.clone();
     * kopio.toString() === suoritus.toString();
     * suoritus.parse("2  |  2.10.2023");
     * kopio.toString().equals(suoritus.toString()) === false;
     * </pre>
     */
    @Override
    public Suoritus clone() throws CloneNotSupportedException {
        Suoritus uusi;
        uusi = (Suoritus) super.clone();
        return uusi;
    }


    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        Suoritus treeni1 = new Suoritus();
        Suoritus treeni2 = new Suoritus();
        
        treeni1.kirjaa();
        treeni2.kirjaa();
        
        treeni1.tulosta(System.out);
        treeni2.tulosta(System.out);
        
        treeni1.taytaTreeniTiedoilla();
        treeni2.taytaTreeniTiedoilla();
        
        treeni1.tulosta(System.out);
        treeni2.tulosta(System.out);
    
    }

 

}
