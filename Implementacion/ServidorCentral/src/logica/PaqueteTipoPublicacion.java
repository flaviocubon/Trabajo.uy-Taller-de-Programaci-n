package logica;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PaqueteTipoPublicacion{
	private String nombre;
    private String descripcion;
    private double descuento;
    private Date fecha;
    private double costo;
    private int periodoValidez;
    private String imagen;
    private Map<String, Agrupa> tiposPublicacion;
    private boolean comprado;
    
    public PaqueteTipoPublicacion(String nombre, String descripcion, double descuento, Date fecha, int PeriodoValidez, String imagen) {
    	this.nombre = nombre;
    	this.descripcion = descripcion;
    	this.descuento = descuento;
    	this.fecha = fecha;
    	this.costo = 0;
    	this.periodoValidez = PeriodoValidez;
    	this.imagen=imagen;
    	this.tiposPublicacion = new HashMap<String, Agrupa>();
    	this.comprado=false;
    }
    
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}
	
	public double getDescuento() {
		return descuento;
	}

	
	public Date getFecha() {
		return fecha;
	}

	
	public double getCosto() {
		return costo;
	}

	
	public int getPeriodoValidez() {
		return periodoValidez;
	}

	
	public Map<String, Agrupa> getTiposPublicacion() {
		Map<String, Agrupa> copiaTipos = new HashMap<String, Agrupa>();
		 for (Map.Entry<String, Agrupa> entry : tiposPublicacion.entrySet()) {
			 copiaTipos.put(entry.getKey(), entry.getValue());
	    } 
		return copiaTipos;
	}

	
	public void agregarTipo(TipoPublicacion tipo, int cant) {
		if (tiposPublicacion.containsKey(tipo.getNombre())) {
			Agrupa tipoExistente = tiposPublicacion.get(tipo.getNombre());
			tipoExistente.setCant(tipoExistente.getCant() + cant);
		} else {
			Agrupa tipoAgregado = new Agrupa(tipo, cant);
			tiposPublicacion.put(tipo.getNombre(), tipoAgregado);
		}
		costo = costo + tipo.getCosto()*cant*(1-descuento/100);
	}
	
	public String getImagen() {
		return imagen;
	}
	
	public void setComprado(boolean valor) {
		this.comprado=valor;
	}

	public boolean getComprado() {
		return comprado;
	}

}
