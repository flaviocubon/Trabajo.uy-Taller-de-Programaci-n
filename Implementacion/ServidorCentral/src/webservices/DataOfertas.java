package webservices;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.DTOfertaLaboral;

@XmlAccessorType(XmlAccessType.FIELD)

public class DataOfertas {
	private Set<DTOfertaLaboral> value;
	
	public DataOfertas() {
	
	}
	
	public Set<DTOfertaLaboral> getValue() {
		return value;
	}
	
	public void setValue(Set<DTOfertaLaboral> value) {
		this.value = value;
	}
}
