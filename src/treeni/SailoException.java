package treeni;

/**
 * @author Onni
 * @version 7.11.2022
 * Poikkeusluokka tietorakenteessa ilmaantuville poikkeuksille
 */
public class SailoException extends Exception {
    
    private static final long serialVersionUID = 1L;

    
    /**
     * Poikkeuksen muodostaja, jolle tuodaan poikkeuksessa
     * k�ytett�v� viesti
     * @param viesti Poikkeusilmoituksessa luke viesti
     */
    public SailoException(String viesti) {
        super(viesti);
    }
}
