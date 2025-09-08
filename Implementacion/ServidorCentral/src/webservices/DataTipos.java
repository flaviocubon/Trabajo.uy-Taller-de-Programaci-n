package webservices;

import java.util.Map;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.DTTipoPublicacion;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataTipos {
	private Map<String, DTTipoPublicacion> value;
	
	public DataTipos() {
		
	}
	
	public Map<String, DTTipoPublicacion> getValue() {
		return value;
	}
	
	public void setValue(Map<String, DTTipoPublicacion> value) {
		this.value = value;
	}
}
