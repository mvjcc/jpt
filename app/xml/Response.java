package xml;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root(name = "response")
public class Response {
	
	@Path("result")
	@ElementList
	public List<Row> Contacts;
	
	@Attribute
	public String uri;
	
}
