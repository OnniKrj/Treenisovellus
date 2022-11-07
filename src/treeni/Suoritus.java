/**
 * 
 */
package treeni;

import java.io.PrintStream;

/**
 * @author Onni
 * @version 7.11.2022
 * CRC-kortin sisältö
 */
public class Suoritus {
    
    /**
     * @param out Tulostettava tietovirta
     */
    public void tulosta(PrintStream out) {
        out.println("Treeni1");
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        Suoritus treeni1 = new Suoritus();
        Suoritus treeni2 = new Suoritus();
        
        //treeni1.kirjaa();
        //treeni2.kirjaa();
        
        treeni1.tulosta(System.out);
        treeni2.tulosta(System.out);
        
        //treeni1.taytaTreeniTiedoilla();
        //treeni2.taytaTreeniTiedoilla();
    
    }

}
