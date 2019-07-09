package models;

import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
public class Tag extends Model {

	public String name;
	
	@ManyToMany
	Set<Contact> contacts;

	public Tag(String name) {
		this.name = name;
	}

}
