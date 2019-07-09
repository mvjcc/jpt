package models;

import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Contact extends Model {

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
	public String tag;				// val="Tag"
	
	@ManyToMany
	Set<Tag> tags;
//	@ManyToMany
//	@JoinTable(
//	  name = "contacts_tags", 
//	  joinColumns = @JoinColumn(name = "contact_id"), 
//	  inverseJoinColumns = @JoinColumn(name = "tag_id"))
//	Set<Tag> tags;

	public Contact(
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
			String twitter,
			String tag
			) {
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
		this.tag = tag;
	}

}
