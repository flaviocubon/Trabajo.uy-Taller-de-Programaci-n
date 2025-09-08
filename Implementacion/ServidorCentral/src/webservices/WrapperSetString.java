package webservices;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperSetString {
	private Set<String> value;
	
	public WrapperSetString(){
		
	}
	
	public Set<String> getValue() {
		return value;
	}
	
	public void setValue(Set<String> value) {
		this.value = value;
	}
}
