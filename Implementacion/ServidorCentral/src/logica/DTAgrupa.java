package logica;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTAgrupa {
	private String tipo;
	private int cant;
	
	public DTAgrupa() {
		
	};
	
	public DTAgrupa(int cant, TipoPublicacion tipo) {
		this.tipo = tipo.getNombre();
		this.cant = cant;
	}
	
	public String getNombreTipo() {
		return tipo;
	}
	
	public int getCant() {
		return cant;
	}
	
	public void setTipo(String tipo) {
		
	}

	public String getTipo() {
		return tipo;
	}

	public void setCant(int cant) {
		this.cant = cant;
	}
}
