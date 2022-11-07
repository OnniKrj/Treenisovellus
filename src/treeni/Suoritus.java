/**
 * 
 */
package treeni;

import java.io.PrintStream;

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
