package webservices;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.DAO.OfertaDAO;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataOfertaDAO {
	private List<OfertaDAO> value;
	
	public DataOfertaDAO() {
	
	}
	
	public List<OfertaDAO> getValue() {
		return value;
	}
	
	public void setValue(List<OfertaDAO> value) {
		this.value = value;
	}
}
