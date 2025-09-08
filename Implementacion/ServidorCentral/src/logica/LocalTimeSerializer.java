package logica;

import java.time.LocalTime;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalTimeSerializer extends XmlAdapter<String, LocalTime>{

	@Override
	public String marshal(LocalTime value) throws Exception {
		return value.toString();
	}

	@Override
	public LocalTime unmarshal(String value) throws Exception {
		return LocalTime.parse(value);
	}
}
