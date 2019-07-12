package models;

import java.util.*;
import javax.persistence.*;
 
import play.db.jpa.*;
 
@Entity
@Table(name = "tags")
public class Tag extends Model {

	public String name;
	
	@ManyToMany(mappedBy = "tags", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	public Set<Contact> contacts = new HashSet<>();

	public Tag(String name) {
		this.name = name;
	}
	
	public void save(String name) {
        new Tag(name).save();
    }

}
