package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;
 
@Entity
@Table(name = "contacts")
public class Contact extends Model {

	public String contactid;		// val="CONTACTID"
	public String fname;			// val="First Name"
	public String lname;			// val="Last Name"
	public String email;			// val="Email"
	public String title;			// val="Title"
	public String phone;			// val="Phone"
	public String mobile;			// val="Mobile"
//	public String fullname;			// val="Full Name"
	public String mailingStreet;	// val="Mailing Street"
	public String mailingCity;		// val="Mailing City"
	public String mailingState;		// val="Mailing State"
	public String mailingZip;		// val="Mailing Zip"
	public String mailingCountry;	// val="Mailing Country"
	public boolean emailOptOut;		// val="Email Opt Out"
	public String skypeId;			// val="Skype ID"
	public String lastActivityTime;	// val="Last Activity Time"
	public String twitter;			// val="Twitter"
//	public String tag;				// val="Tag"
	
//	@ManyToMany
//	Set<Tag> tags;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinTable(
	  name = "contacts_tags", 
	  joinColumns = @JoinColumn(name = "contact_id"), 
	  inverseJoinColumns = @JoinColumn(name = "tag_id"))
	public Set<Tag> tags = new HashSet<>();

	public Contact(
			String contactid,
			String fname,
			String lname,
			String email,
			String title,
			String phone,
			String mobile,
			String mailingStreet,
			String mailingCity,
			String mailingState,
			String mailingZip,
			String mailingCountry,
			boolean emailOptOut,
			String skypeId,
			String lastActivityTime,
			String twitter
			) {
		this.contactid = contactid;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.title = title;
		this.phone = phone;
		this.mobile = mobile;
		this.mailingStreet = mailingStreet;
		this.mailingCity = mailingCity;
		this.mailingState = mailingState;
		this.mailingZip = mailingZip;
		this.mailingCountry = mailingCountry;
		this.emailOptOut = emailOptOut;
		this.skypeId = skypeId;
		this.lastActivityTime = lastActivityTime;
		this.twitter = twitter;
	}
	
	public void save(
			String contactid,
			String fname,
			String lname,
			String email,
			String title,
			String phone,
			String mobile,
			String mailingStreet,
			String mailingCity,
			String mailingState,
			String mailingZip,
			String mailingCountry,
			boolean emailOptOut,
			String skypeId,
			String lastActivityTime,
			String twitter
			) {
        new Contact(
        	contactid,
    		fname,
    		lname,
    		email,
			title,
			phone,
			mobile,
			mailingStreet,
			mailingCity,
			mailingState,
			mailingZip,
			mailingCountry,
			emailOptOut,
			skypeId,
			lastActivityTime,
			twitter
		).save();
    }

}
