package xml;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "row")
public class Row {

	@Attribute
	public int no;
	
	@ElementList(entry = "FL", inline = true)
	public List<FL> fl;
}
