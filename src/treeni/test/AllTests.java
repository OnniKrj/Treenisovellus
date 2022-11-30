package treeni.test;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

/**
 * @author Onni
 * @version 30.11.2022
 * Ajetaan kaikki testit
 */
@Suite
@SelectClasses({ LiikeTest.class, LiikkeetTest.class, SuorituksetTest.class,
        SuoritusTest.class })
public class AllTests {
    //
}
