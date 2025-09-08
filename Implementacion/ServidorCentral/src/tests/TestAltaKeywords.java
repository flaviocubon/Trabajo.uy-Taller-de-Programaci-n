package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import logica.Fabrica;
import logica.ITipos;
import logica.ManejadorKeywords;

public class TestAltaKeywords {
	private static Fabrica fab;
	private static ITipos ctrlTipos;
	
	public TestAltaKeywords(){
		fab = Fabrica.getInstance();
		ctrlTipos = fab.getITipos();
	}
	
	@Test
	//Funcionamiento normal
	void AltaKeywordTest1() {
		ManejadorKeywords mk = ManejadorKeywords.getInstance();
		Boolean exito = ctrlTipos.ingresarKeyword("K1");
		Assertions.assertTrue(exito);
		Assertions.assertTrue(mk.existeKeyword("K1"));
	}
	
	@Test
	//Keyword repetida
	void AltaKeywordTest2() {
		ManejadorKeywords mk = ManejadorKeywords.getInstance();
		ctrlTipos.ingresarKeyword("K2");
		Boolean exito = ctrlTipos.ingresarKeyword("K2");
		Assertions.assertFalse(exito);
	}
}
