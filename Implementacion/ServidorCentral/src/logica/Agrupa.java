package logica;

public class Agrupa {
	private TipoPublicacion tipo;
	private int cant;
	
	public Agrupa(TipoPublicacion tipo, int cant) {
		this.tipo = tipo;
		this.cant = cant;
	}
	
	public int getCant() {
		return cant;
	}

	public TipoPublicacion getTipo() {
		return tipo;
	}

	public void setCant(int val) {
		cant = val;
	}
}
