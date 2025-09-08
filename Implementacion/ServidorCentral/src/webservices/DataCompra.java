package webservices;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.DTCompra;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataCompra {
	private Set<DTCompra> value;
	
	public DataCompra() {
		
	}
	
	public Set<DTCompra> getValue() {
		return value;
	}
	
	public void setValue(Set<DTCompra> value) {
		this.value = value;
	}
}
