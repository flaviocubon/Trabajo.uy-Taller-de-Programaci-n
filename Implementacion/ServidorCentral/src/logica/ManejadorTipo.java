package logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ManejadorTipo {
	private static ManejadorTipo instance = null;
	private Map<String, TipoPublicacion> tiposPublicacion;
	
	private ManejadorTipo() {
		tiposPublicacion = new HashMap<String, TipoPublicacion>();
	}
	
	public static ManejadorTipo getInstance() {
		if (instance == null) {
			instance = new ManejadorTipo();
		}
		return instance;
	}
	
	public boolean existeTipoPublicacion(String nombre) {
		return tiposPublicacion.containsKey(nombre);
	}
	
	public void agregar(TipoPublicacion tipoPub) {
		tiposPublicacion.put(tipoPub.getNombre(), tipoPub);
	}

	public Set<String> obtenerTipos(){
		return this.tiposPublicacion.keySet();
	}
	
	public TipoPublicacion obtenerTipo(String nomTipo) {
		return this.tiposPublicacion.get(nomTipo);
	}
	
	public Map<String, DTTipoPublicacion> obtenerDataTipos(){
		Map<String, DTTipoPublicacion> datatipos = new HashMap<String, DTTipoPublicacion>();
		tiposPublicacion.forEach((nombre, tipoP)-> {
			datatipos.put(tipoP.getNombre(), new DTTipoPublicacion(tipoP.getNombre(), tipoP.getDescripcion(),
					tipoP.getExposicion(), tipoP.getDuracion(), tipoP.getCosto(), tipoP.getFecha()));
		});
		return datatipos;
	}
	
	public void clear() {
		tiposPublicacion.clear();
	}
}
