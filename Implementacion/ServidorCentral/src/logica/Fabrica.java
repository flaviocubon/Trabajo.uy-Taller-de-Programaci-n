package logica;

public class Fabrica {
	private static Fabrica instance = null;
	
	public static Fabrica getInstance() {
		if (instance == null) {
			instance = new Fabrica();
		}
		return instance;
	}
	
	public IUsuario getIUsuario() {
		return new CtrlUsuario();
	}
	
	public ITipos getITipos() {
		return new CtrlTipos();
	}
	
}
