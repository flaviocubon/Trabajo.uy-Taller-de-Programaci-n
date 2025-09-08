package webservices;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import logica.DAO.PostulacionDAO;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataPostulacionDAO {
	private List<PostulacionDAO> value;
	
	public DataPostulacionDAO() {
	
	}
	
	public List<PostulacionDAO> getValue() {
		return value;
	}
	
	public void setValue(List<PostulacionDAO> value) {
		this.value = value;
	}
}