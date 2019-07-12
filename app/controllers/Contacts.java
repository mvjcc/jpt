package controllers;

import play.*;
import play.data.validation.Required;
import play.db.jpa.GenericModel.JPAQuery;
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

public class Contacts extends Controller {

    public static void index() throws Exception {
    	List<Tag> tags = Tag.findAll();
    	
    	render(tags);
    }
    
    public static void show(Long id) {
    	List<Tag> tags = Tag.findAll();
    	Tag tag = Tag.findById(id);
    	Set<Contact> contacts = tag.contacts;
    	
    	render(tags, contacts);
    }
    
}