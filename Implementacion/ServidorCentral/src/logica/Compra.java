package logica;
import java.time.LocalDate;
import java.util.Map;

public class Compra {
	private Empresa comprador;
	private PaqueteTipoPublicacion producto;
	private LocalDate fechaCompra;
	private Map<String, Agrupa> tiposNoCanjeados;
	private double costo;
	private LocalDate fechaVencimiento;
	
	public Compra(Empresa comprador, PaqueteTipoPublicacion producto, LocalDate fecha){
		this.producto=producto;
		this.comprador=comprador;
		this.fechaCompra= fecha;
		this.costo= producto.getCosto();
		this.fechaVencimiento= fechaCompra.plusDays(producto.getPeriodoValidez());
		this.tiposNoCanjeados=producto.getTiposPublicacion();
	}

	public Empresa getComprador() {
		return comprador;
	}
	public PaqueteTipoPublicacion getProducto() {
		return producto;
	}
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}
	
	public void canjearTipo(String nombreTipo) {
		Agrupa agr =tiposNoCanjeados.get(nombreTipo);
		if (agr !=null) {
			int nuevaCantidad=agr.getCant()-1;
			if (nuevaCantidad==0) {
				tiposNoCanjeados.remove(nombreTipo);
			}else {
				agr.setCant(nuevaCantidad);
			}
		}
	}
	public Map<String, Agrupa> getTiposNoCanjeados(){
		return tiposNoCanjeados;
	}
	
	public double getCosto() {
		return this.costo;
	};
}