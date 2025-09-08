package logica;

import java.util.HashMap;
import java.util.Map;


public class ManejadorOferta {
	
	 private static ManejadorOferta instancia=null;
	 private Map<String, OfertaLaboral> ofertas;
	 
	 private ManejadorOferta() {
		 this.ofertas = new HashMap<String, OfertaLaboral>();
	 }
	 public static ManejadorOferta getInstancia() {
		 if (instancia==null) {
			 instancia=new ManejadorOferta();
		 }
		 return instancia;
	 }
	 public OfertaLaboral obtenerOferta(String nomOferta) {
		 return this.ofertas.get(nomOferta);
	 }
	 public void agregarOferta(OfertaLaboral oferta) {
		 this.ofertas.put(oferta.getNombre(), oferta);
	 }
	 public Boolean existeOferta(String nomOferta) {
		 return this.ofertas.containsKey(nomOferta);
	 }
	 public Map<String, OfertaLaboral> getOfertas(){
		 return ofertas;
	 }
	 public void removerOferta(String nombreOferta) {
		 ofertas.remove(nombreOferta);
	 }
	 public void clear() {
		 this.ofertas.clear();
	 }
}
