package treeni;

import java.io.PrintStream;

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
    
    public int getTreeniNro() {
        return treeniNro;
    }
    
    /**
     * Apumetodi testiarvoilla täyttämistä varten
     * @param nro Viite liikkeeseen
     */
    public void TaytaLiikeTiedoilla(int nro) {
        liikeNro = nro;
        liikeNimi = "Pystypunnerrus";
        
        
    }
    
    /**
     * @return Täytetään
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
        out.println("Liikkeen nimi: " + liikeNimi);
        out.println("Liikkeen tunnus: " + liikeNro);
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
