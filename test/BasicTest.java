import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

public class BasicTest extends UnitTest {

	@Test
    public void tag() {
    	new Tag("Test").save();
    	new Tag("Sample").save();
    	
    	Tag tag = Tag.find("byName", "Test").first();
    	
    	// Test 
        assertNotNull(tag);
        assertEquals("Test", tag.name);
    }
	
    @Test
    public void contact() {
    	// Create a new user and save it
        new Contact("Sage", "Wieser", "sage-wieser@truhlar.uk", "Product Engineer", "555-555-5555", "555-555-5555", "5 Boston Ave #88",
        		"Sioux Falls", "SD", "57105", "United States",
        		false, "sage_wieser", "2019-07-08 14:44:29", "@sagewieser", "Test").save();
        new Contact("Sage", "Wieser", "sage-wieser@truhlar.uk", "Product Engineer", "555-555-5555", "555-555-5555", "5 Boston Ave #88",
        		"Sioux Falls", "SD", "57105", "United States",
        		false, "sage_wieser", "2019-07-08 14:44:29", "@sagewieser", "Test").save();
        new Contact("Sage", "Wieser", "sage-wieser@truhlar.uk", "Product Engineer", "555-555-5555", "555-555-5555", "5 Boston Ave #88",
        		"Sioux Falls", "SD", "57105", "United States",
        		false, "sage_wieser", "2019-07-08 14:44:29", "@sagewieser", "").save();
        new Contact("Sage", "Wieser", "sage-wieser@truhlar.uk", "Product Engineer", "555-555-5555", "555-555-5555", "5 Boston Ave #88",
        		"Sioux Falls", "SD", "57105", "United States",
        		false, "sage_wieser", "2019-07-08 14:44:29", "@sagewieser", "").save();
        new Contact("Sage", "Wieser", "sage-wieser@truhlar.uk", "Product Engineer", "555-555-5555", "555-555-5555", "5 Boston Ave #88",
        		"Sioux Falls", "SD", "57105", "United States",
        		false, "sage_wieser", "2019-07-08 14:44:29", "@sagewieser", "Sample").save();
        
        // Retrieve the user with e-mail address bob@gmail.com
        Contact sage = Contact.find("byEmail", "sage-wieser@truhlar.uk").first();
        
        // Test 
        assertNotNull(sage);
        assertEquals("Sage", sage.fname);
        assertEquals("Test", sage.tag);
        
        List<Object> contacts = Contact.find("byTag", "%est").fetch();
        // Test 
        assertNotNull(contacts);
        assertEquals(10, contacts.size());
    }

}
