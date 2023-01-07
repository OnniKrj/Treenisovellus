package treeni.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import treeni.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2023.01.07 13:45:19 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class SuoritusTest {



  // Generated by ComTest BEGIN
  /** testKirjaa51 */
  @Test
  public void testKirjaa51() {    // Suoritus: 51
    Suoritus treeni1 = new Suoritus(); 
    assertEquals("From: Suoritus line: 53", 0, treeni1.getTreeniNro()); 
    treeni1.kirjaa(); 
    Suoritus treeni2 = new Suoritus(); 
    treeni2.kirjaa(); 
    int tunnus1 = treeni1.getTreeniNro(); 
    int tunnus2 = treeni2.getTreeniNro(); 
    assertEquals("From: Suoritus line: 59", tunnus1 + 1, tunnus2); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testGetPvm118 */
  @Test
  public void testGetPvm118() {    // Suoritus: 118
    Suoritus suoritus = new Suoritus(); 
    suoritus.taytaTreeniTiedoilla(); 
    { String _l_=suoritus.getPvm(),_r_="1.10.2022"; if ( !_l_.matches(_r_) ) fail("From: Suoritus line: 121" + " does not match: ["+ _l_ + "] != [" + _r_ + "]");}; 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testAseta137 */
  @Test
  public void testAseta137() {    // Suoritus: 137
    Suoritus suoritus = new Suoritus(); 
    assertEquals("From: Suoritus line: 139", null, suoritus.aseta(1, "1.1.2023")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testToString241 */
  @Test
  public void testToString241() {    // Suoritus: 241
    Suoritus suoritus = new Suoritus(); 
    suoritus.parse("  1  |  1.10.2022"); 
    assertEquals("From: Suoritus line: 244", true, suoritus.toString().startsWith("1|1.10.2022")); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** testParse263 */
  @Test
  public void testParse263() {    // Suoritus: 263
    Suoritus suoritus = new Suoritus(); 
    suoritus.parse(" 3 |  1.10.2023  "); 
    assertEquals("From: Suoritus line: 266", 3, suoritus.getTreeniNro()); 
    assertEquals("From: Suoritus line: 267", true, suoritus.toString().startsWith("3|1.10.2023")); 
    suoritus.kirjaa(); 
    int n = suoritus.getTreeniNro(); 
    suoritus.parse(""+(n+20)); 
    suoritus.kirjaa(); 
    assertEquals("From: Suoritus line: 273", n+20+1, suoritus.getTreeniNro()); 
  } // Generated by ComTest END


  // Generated by ComTest BEGIN
  /** 
   * testClone288 
   * @throws CloneNotSupportedException when error
   */
  @Test
  public void testClone288() throws CloneNotSupportedException {    // Suoritus: 288
    Suoritus suoritus = new Suoritus(); 
    suoritus.parse("1  |  1.10.2023  "); 
    Suoritus kopio = suoritus.clone(); 
    assertEquals("From: Suoritus line: 293", suoritus.toString(), kopio.toString()); 
    suoritus.parse("2  |  2.10.2023"); 
    assertEquals("From: Suoritus line: 295", false, kopio.toString().equals(suoritus.toString())); 
  } // Generated by ComTest END
}