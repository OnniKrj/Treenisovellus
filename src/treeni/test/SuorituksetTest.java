package treeni.test;
// Generated by ComTest BEGIN
import static org.junit.Assert.*;
import org.junit.*;
import treeni.*;
// Generated by ComTest END

/**
 * Test class made by ComTest
 * @version 2022.12.21 15:03:50 // Generated by ComTest
 *
 */
@SuppressWarnings({ "all" })
public class SuorituksetTest {



  // Generated by ComTest BEGIN
  /** 
   * testLisaa46 
   * @throws SailoException when error
   */
  @Test
  public void testLisaa46() throws SailoException {    // Suoritukset: 46
    Suoritukset suoritukset = new Suoritukset(); 
    Suoritus treeni1 = new Suoritus(), treeni2 = new Suoritus(); 
    assertEquals("From: Suoritukset line: 50", 0, suoritukset.getLkm()); 
    suoritukset.lisaa(treeni1); assertEquals("From: Suoritukset line: 51", 1, suoritukset.getLkm()); 
    suoritukset.lisaa(treeni2); assertEquals("From: Suoritukset line: 52", 2, suoritukset.getLkm()); 
    suoritukset.lisaa(treeni1); assertEquals("From: Suoritukset line: 53", 3, suoritukset.getLkm()); 
    assertEquals("From: Suoritukset line: 54", treeni1, suoritukset.anna(0)); 
    assertEquals("From: Suoritukset line: 55", treeni2, suoritukset.anna(1)); 
    assertEquals("From: Suoritukset line: 56", treeni1, suoritukset.anna(2)); 
    assertEquals("From: Suoritukset line: 57", false, suoritukset.anna(1) == treeni1); 
    assertEquals("From: Suoritukset line: 58", true, suoritukset.anna(1) == treeni2); 
    try {
    assertEquals("From: Suoritukset line: 59", treeni1, suoritukset.anna(3)); 
    fail("Suoritukset: 59 Did not throw IndexOutOfBoundsException");
    } catch(IndexOutOfBoundsException _e_){ _e_.getMessage(); }
    suoritukset.lisaa(treeni1); assertEquals("From: Suoritukset line: 60", 4, suoritukset.getLkm()); 
    suoritukset.lisaa(treeni1); assertEquals("From: Suoritukset line: 61", 5, suoritukset.getLkm()); 
    suoritukset.lisaa(treeni1); 
  } // Generated by ComTest END
}