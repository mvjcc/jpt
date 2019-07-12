package controllers;

import play.*;
import play.cache.Cache;
import play.data.validation.Required;
import play.db.jpa.JPABase;
import play.libs.WS;
import play.libs.XPath;
import play.libs.WS.HttpResponse;
import play.mvc.*;
import xml.FL;
import xml.Response;
import xml.Row;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.*;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import models.Contact;
import models.Tag;
import oauth.signpost.http.HttpRequest;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void show(@Required(message="Tag is required") String tag) {
    	validation.equals(
    		tag.equals("") || tag.equals(" "), false
    	).message("Invalid code. Please type it again");
    	if(validation.hasErrors()) {
    		render("Application/index.html");
    	}

    	String command = "curl -X GET https://crm.zoho.com/crm/private/xml/Contacts/searchRecords?authtoken=114d468ba9560bf5cc3b6b11c3819dd2&scope=crmapi&criteria=(Tag:" + tag + ")";
    	Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Serializer serializer = new Persister();

        Response res = null;
		try {
			res = serializer.read(Response.class, process.getInputStream());
		} catch (Exception e) {
			render();
		}
        
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        Tag tag1 = null;
    	Contact con = null;
    	String contactid = "", fname = "", lname = "", email = "", title = "", phone = "", mobile = "";
    	String mailingStreet = "", mailingCity = "", mailingState = "", mailingZip = "";
    	String mailingCountry = "", skypeId = "", lastActivityTime = "", twitter = "";
    	boolean emailOptOut = false;
    	
    	// check if tag exists
    	tag1 = Tag.find("byName", tag).first();
    	if(tag1 == null) {
    		tag1 = new Tag(tag).save();
    	}
    	
    	for(Row contact : res.Contacts) {
    		contactid = contact.fl.get(0).FL;
    		fname = contact.fl.get(4).FL;
    		lname = contact.fl.get(5).FL;
    		email = contact.fl.get(8).FL;
    		title = contact.fl.get(9).FL;
    		phone = contact.fl.get(11).FL;
    		mobile = contact.fl.get(12).FL;
        	mailingStreet = contact.fl.get(20).FL;
        	mailingCity = contact.fl.get(21).FL;
        	mailingState = contact.fl.get(22).FL;
        	mailingZip = contact.fl.get(23).FL;
        	mailingCountry = contact.fl.get(24).FL;
        	emailOptOut = contact.fl.get(25).FL.equals("true");
        	skypeId = contact.fl.get(26).FL;
        	lastActivityTime = contact.fl.get(27).FL;
        	twitter = contact.fl.get(28).FL;

    		con = Contact.find("byContactid", contactid).first();
    		if(con == null) {
    			con = new Contact(
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
				);
    		} else {
    			con.fname = fname;
    			con.lname = lname;
    			con.email = email;
    			con.title = title;
    			con.phone = phone;
    			con.mobile = mobile;
    			con.mailingStreet = mailingStreet;
    			con.mailingCity = mailingCity;
    			con.mailingState = mailingState;
    			con.mailingZip = mailingZip;
    			con.mailingCountry = mailingCountry;
    			con.emailOptOut = emailOptOut;
    			con.skypeId = skypeId;
    			con.lastActivityTime = lastActivityTime;
    			con.twitter = twitter;
    		}
    		
    		con.tags.add(tag1);
			con.save();
    		
    		tag1.contacts.add(con);
    		tag1.save();
    	}
        // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    	
    	render(res, tag);
    }
    
    public static void addTagAndContacts(String tagInput, Response res) throws Exception {
    	index();
    	Tag tag = null;
    	Contact con = null;
    	String contactid = "", fname = "", lname = "", email = "", title = "", phone = "", mobile = "";
    	String mailingStreet = "", mailingCity = "", mailingState = "", mailingZip = "";
    	String mailingCountry = "", skypeId = "", lastActivityTime = "", twitter = "";
    	boolean emailOptOut = false;
    	
    	// check if tag exists
    	tag = Tag.find("byName", tagInput).first();
    	if(tag == null) {
    		tag = new Tag(tagInput).save();
    	}
    	
    	for(Row contact : res.Contacts) {
    		contactid = contact.fl.get(0).FL;
    		fname = contact.fl.get(4).FL;
    		lname = contact.fl.get(5).FL;
    		email = contact.fl.get(8).FL;
    		title = contact.fl.get(9).FL;
    		phone = contact.fl.get(11).FL;
    		mobile = contact.fl.get(12).FL;
        	mailingStreet = contact.fl.get(20).FL;
        	mailingCity = contact.fl.get(21).FL;
        	mailingState = contact.fl.get(22).FL;
        	mailingZip = contact.fl.get(23).FL;
        	mailingCountry = contact.fl.get(24).FL;
        	emailOptOut = contact.fl.get(25).FL.equals("true");
        	skypeId = contact.fl.get(26).FL;
        	lastActivityTime = contact.fl.get(27).FL;
        	twitter = contact.fl.get(28).FL;

    		con = Contact.find("byContactid", contactid).first();
    		if(con == null) {
    			con = new Contact(
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
				);
    		} else {
    			con.fname = fname;
    			con.lname = lname;
    			con.email = email;
    			con.title = title;
    			con.phone = phone;
    			con.mobile = mobile;
    			con.mailingStreet = mailingStreet;
    			con.mailingCity = mailingCity;
    			con.mailingState = mailingState;
    			con.mailingZip = mailingZip;
    			con.mailingCountry = mailingCountry;
    			con.emailOptOut = emailOptOut;
    			con.skypeId = skypeId;
    			con.lastActivityTime = lastActivityTime;
    			con.twitter = twitter;
    		}
    		
    		con.tags.add(tag);
			con.save();
    		
    		tag.contacts.add(con);
    		tag.save();
    	}
    }
    
    public static void test() {
    	List<Contact> contacts = Contact.findAll();
    	
    	render(contacts);
    }
    
    public static void showContacts(Long id) {
    	Tag tag = Tag.findById(id);
    	
    	render(tag);
    }
    
}