package logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ManejadorPaquete {
	private static ManejadorPaquete instance = null;
	private Map<String, PaqueteTipoPublicacion> paquetes;
	
	private ManejadorPaquete() {
		paquetes = new HashMap<String, PaqueteTipoPublicacion>();
	}
	
	public static ManejadorPaquete getInstance() {
		if (instance == null) {
			instance = new ManejadorPaquete();
		}
		return instance;
	}

	public Set<String> obtenerPaquetes() {
		return paquetes.keySet();
	}
	
	public PaqueteTipoPublicacion obtenerPaquete(String nombrePaquete) {
		return paquetes.get(nombrePaquete);
	}
	
	public boolean existePaquete(String nombreP) {
		return paquetes.containsKey(nombreP);
	}

	public void clear() {
		paquetes.clear();
	}

	public void agregarPaquete(PaqueteTipoPublicacion nuevoPaquete) {
		paquetes.put(nuevoPaquete.getNombre(), nuevoPaquete);
	}

	public Map<String, DTPaquete> obtenerDataPaquetes() {
		Map<String, DTPaquete> dataPaquetes = new HashMap<String, DTPaquete>();
		paquetes.forEach((nombre, paquete)-> {
			dataPaquetes.put(paquete.getNombre(), new DTPaquete(paquete));
			});
		return dataPaquetes;
	}

}
