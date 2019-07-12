package xml;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

public class FL {
	@Attribute
	public String val;
	
	@Text(required = false)
	public String FL;
}
