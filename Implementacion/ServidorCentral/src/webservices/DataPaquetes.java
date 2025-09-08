package webservices;

import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.DTPaquete;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPaquetes {
	private Map<String, DTPaquete> value;
	
	public DataPaquetes() {
		
	}
	
	public Map<String, DTPaquete> getValue(){
		return value;
	}
	
	public void setValue(Map<String, DTPaquete> value) {
		this.value = value;
	}
}
