package webservices;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class WrapperListString {
	private List<String> value;
	
	public WrapperListString(){
		
	}
	
	public List<String> getValue() {
		return value;
	}
	
	public void setValue(List<String> value) {
		this.value = value;
	}
}
