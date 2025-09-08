package logica;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateSerializer extends XmlAdapter<String, LocalDate>{
	
	public String marshal(LocalDate value) throws Exception{
		return value.toString();
	}
	
	public LocalDate unmarshal(String value) throws Exception{
		return LocalDate.parse(value);
	}
}
