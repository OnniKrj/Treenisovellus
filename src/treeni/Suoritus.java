/**
 * 
 */
package treeni;

import java.io.PrintStream;

import fi.jyu.mit.ohj2.Mjonot;

/**
 * @author Onni
 * @version 7.11.2022
 * CRC-kortin sis�lt�
 */
public class Suoritus {
    
    private int treeniNro;
    private int liikeNro = 0;
    private int sarjaMaara = 0;
    private int toistoMaara = 0;
    private double paino = 0;
    private String pvm = "";
    
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
        out.println("Liikkeen tunnus: " + liikeNro);
        out.println("Sarjojen m��r�: " + sarjaMaara);
        out.println("Toistojen m��r�: " + toistoMaara);
        out.println("Sarja paino: " + paino + "kg");
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
     * @return Suorituksen liikenro
     */
    public int getLiikeNro() {
        return liikeNro;
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
     * @return P�iv�ys
     */
    public String getPvm() {
        return pvm;
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
        
        liikeNro = 1;
        sarjaMaara = 3;
        toistoMaara = 8;
        paino = 60;
        pvm = "1.10.2022";
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
                liikeNro + "|" +
                sarjaMaara + "|" +
                toistoMaara + "|" +
                paino + "|" +
                pvm;
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
        liikeNro = Mjonot.erota(sb, '|', liikeNro);
        sarjaMaara = Mjonot.erota(sb, '|', sarjaMaara);
        toistoMaara = Mjonot.erota(sb, '|', toistoMaara);
        pvm = Mjonot.erota(sb, '|', pvm);
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
