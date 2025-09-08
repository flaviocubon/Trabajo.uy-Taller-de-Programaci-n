package logica;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ManejadorKeywords {
	private static ManejadorKeywords instance = null;
	private Map<String, Keyword> keywords;
	
	private ManejadorKeywords() {
		keywords = new HashMap<String, Keyword>();
	}
	
	public static ManejadorKeywords getInstance() {
		if (instance == null) {
			instance = new ManejadorKeywords();
		}
		return instance;
	}
	public Set<String> obtenerKeywords(){
		return this.keywords.keySet();
	}
	public void agregarKeyword(String keyword) {
		Keyword key = new Keyword(keyword);
		this.keywords.put(keyword, key);
	}
	
	public Boolean existeKeyword(String keyword) {
		return this.keywords.containsKey(keyword);
	}
	public void clear() {
		this.keywords.clear();
	}
}
