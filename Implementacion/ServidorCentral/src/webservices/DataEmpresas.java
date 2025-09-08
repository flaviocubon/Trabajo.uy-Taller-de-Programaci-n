package webservices;

import java.util.ArrayList;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.DTEmpresa;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataEmpresas {
	private ArrayList<DTEmpresa> value;
	
	public DataEmpresas() {
		
	}
	
	public ArrayList<DTEmpresa> getValue() {
		return value;
	}
	
	public void setValue(ArrayList<DTEmpresa> value) {
		this.value = value;
	}
}
