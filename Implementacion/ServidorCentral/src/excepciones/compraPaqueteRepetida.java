package excepciones;

@SuppressWarnings("serial")
public class compraPaqueteRepetida extends Exception{
	public compraPaqueteRepetida(String s) {
		super(s);
	};
}