package treeni.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import treeni.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.11.07 19:45:47 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class SuoritusTest {



  // Generated by ComTest BEGIN
  /** testKirjaa46 */
  @Test
  public void testKirjaa46() {    // Suoritus: 46
    Suoritus treeni1 = new Suoritus(); 
    assertEquals("From: Suoritus line: 48", 0, treeni1.getTreeniNro()); 
    treeni1.kirjaa(); 
    Suoritus treeni2 = new Suoritus(); 
    treeni2.kirjaa(); 
    int tunnus1 = treeni1.getTreeniNro(); 
    int tunnus2 = treeni2.getTreeniNro(); 
    assertEquals("From: Suoritus line: 54", tunnus1 + 1, tunnus2); 
  } // Generated by ComTest END
}