/**
 * 
 */
package kanta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Onni
 * @version 18.12.2022
 *
 */
public class PvmTarkistus {

    /**
     * @param pvm tarkistettava pvm
     * @return true jos kelpaa
     */
    public String tarkista(String pvm) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(pvm.trim());
        } catch (ParseException pe) {
            return "Ilmoita p‰iv‰m‰‰r‰ muodossa pp.kk.vvvv";
        }
        return null;
    }

}
