import org.junit.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import java.util.*;
import play.test.*;
import models.*;
import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.libs.XPath;

public class BasicTest extends UnitTest {

	@Test
    public void tag() {
    	new Tag("Test").save();
    	new Tag("Sample").save();
    	new Tag("Lorem").save();
    	new Tag("Ipsum").save();
    	new Tag("Dolor").save();
    	
    	Tag tag = Tag.find("byName", "Test").first();
    	Long x = tag.id;
    	tag.name = "Test";
    	tag.save();
    	
    	tag = null;
    	tag = Tag.findById(x);
    	
    	// Test 
        assertNotNull(tag);
        assertEquals("Test", tag.name);
    }
	
    @Test
    public void contact() {
    	// Create a new user and save it
        new Contact("11000000001", "Sage", "Wieser", "sage-wieser@truhlar.uk", "Product Engineer", "555-555-5555", "555-555-5555", "5 Boston Ave #88",
        		"Sioux Falls", "SD", "57105", "United States",
        		false, "sage_wieser", "2019-07-08 14:44:29", "@sagewieser").save();
        
        new Contact("11000000002", "Jame", "Bell", "jbell@truhlar.uk", "Sales Engineer", "666-666-666", "666-666-666", "Neri Street",
        		"Sioux Falls", "SD", "57105", "United States",
        		false, "james_bell", "2019-07-08 14:44:29", "@jbell").save();
        
        // Retrieve a user
        Contact sage = Contact.find("byContactid", "11000000001").first();
        
        // Test 
        assertNotNull(sage);
        assertEquals("Sage", sage.fname);
        assertEquals((Long)(long)1, sage.id);
    }

	@Test
    public void contactTag() {
		List<Contact> contacts = Contact.find("byLname", "Wieser").fetch();
		assertNotNull(contacts);
		Tag tag = Tag.find("byName", "Lorem").first();
		assertNotNull(tag);
		for(Contact contact : contacts) {
			contact.tags.add(tag);
			tag.contacts.add(contact);
		}
		
		Contact contact = Contact.find("byLname", "Wieser").first();
		assertNotNull(contact);
		
		Set<Tag> tags = contact.tags;
		
		assertNotNull(tags);
        assertEquals(1, tags.size());
        assertTrue(tags.contains(tag));
    }

}
