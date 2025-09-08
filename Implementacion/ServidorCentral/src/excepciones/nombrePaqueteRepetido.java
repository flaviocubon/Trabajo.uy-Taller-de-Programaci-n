package excepciones;

@SuppressWarnings("serial")
public class nombrePaqueteRepetido extends Exception{
	public nombrePaqueteRepetido(String s) {
		super(s);
	};
}