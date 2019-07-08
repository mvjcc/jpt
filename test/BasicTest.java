import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

    @Test
    public void aVeryImportantThingToTest() {
    	// Create a new user and save it
        new Contact("Sage", "Wieser", "sage-wieser@truhlar.uk", "Product Engineer", "555-555-5555", "555-555-5555", "5 Boston Ave #88",
        		"Sioux Falls", "SD", "57105", "United States",
        		false, "sage_wieser", "2019-07-08 14:44:29", "@sagewieser").save();
        
        // Retrieve the user with e-mail address bob@gmail.com
        Contact sage = Contact.find("byEmail", "sage-wieser@truhlar.uk").first();
        
        // Test 
        assertNotNull(sage);
        assertEquals("Sage", sage.fname);
    }

}
