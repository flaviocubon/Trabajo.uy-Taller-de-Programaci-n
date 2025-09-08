package logica;
import java.time.LocalTime;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTHorario {
	@XmlElement
	@XmlJavaTypeAdapter(LocalTimeSerializer.class)
	private LocalTime horarioInicio;
	@XmlElement
	@XmlJavaTypeAdapter(LocalTimeSerializer.class)
	private LocalTime horarioFin;
	
	public DTHorario() {
		
	}
	
	public void setHorarioInicio(LocalTime horarioInicio) {
		this.horarioInicio = horarioInicio;
	}

	public void setHorarioFin(LocalTime horarioFin) {
		this.horarioFin = horarioFin;
	}

	public DTHorario(String horIn, String horFin) {
		this.horarioInicio=LocalTime.parse(horIn);
		this.horarioFin=LocalTime.parse(horFin);
	}
	public LocalTime getInicio() {
		return this.horarioInicio;
	}
	public LocalTime getFin() {
		return this.horarioFin;
	}

	public LocalTime getHorarioInicio() {
		return horarioInicio;
	}

	public LocalTime getHorarioFin() {
		return horarioFin;
	}
}
