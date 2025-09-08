package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.text.html.HTMLDocument.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.nombreTipoPublicacionRepetido;
import logica.DTTipoPublicacion;
import logica.Fabrica;
import logica.ITipos;

public class TestAltaTipoPublicacion {
	
	private static Fabrica fab;
	private static ITipos ctrlTipos;
	
	public TestAltaTipoPublicacion(){
		fab = Fabrica.getInstance();
		ctrlTipos = fab.getITipos();
	}
	
	@BeforeEach
	void clear() {
		ctrlTipos.borrarTipos();
	}
	
	@Test
	//Test nombre repetido
	void AltaTipoPublicacionTest1(){
		@SuppressWarnings("deprecation")
		nombreTipoPublicacionRepetido thrown = Assertions.assertThrows(nombreTipoPublicacionRepetido.class, () -> {
			ctrlTipos.ingresarDatosTipoPublicacion("Deluxe", "Paquete economico para pequeñas empresas", 0, new Date(2023,4,12), 0, 0);
			ctrlTipos.ingresarDatosTipoPublicacion("Deluxe", "Paquete economico para medianas empresas", 0, new Date(2023,8,9), 1, 2);
		});
		Assertions.assertEquals("Ya existe un tipo de publicacion de nombre Deluxe", thrown.getMessage());
	}
	
	@SuppressWarnings("deprecation")
	@Test
	//Test construccion correcta de objetos
	void AltaTipoPublicacionTest2() throws nombreTipoPublicacionRepetido {
		ctrlTipos.ingresarDatosTipoPublicacion("Basico", "Paquete economico para pequeñas empresas", 0, new Date(2023,4,12), 0, 0);
		ctrlTipos.ingresarDatosTipoPublicacion("Estandar", "Paquete economico para medianas empresas", 0, new Date(2023,8,9), 1, 2);
		ctrlTipos.ingresarDatosTipoPublicacion("Premium", "Paquete economico para grandes empresas", 0, new Date(2021,4,6), 1, 2);
		
		Map<String, DTTipoPublicacion> retorno = ctrlTipos.obtenerDataTipos();
		Map<String, DTTipoPublicacion> esperado = new HashMap<String, DTTipoPublicacion>();
		esperado.put("Basico", new DTTipoPublicacion("Basico", "Paquete economico para pequeñas empresas", 0, 0, 0, new Date(2023,4,12)));
		esperado.put("Estandar", new DTTipoPublicacion("Estandar", "Paquete economico para medianas empresas", 0, 2, 1, new Date(2023,8,9)));
		esperado.put("Premium", new DTTipoPublicacion("Premium", "Paquete economico para grandes empresas", 0, 2, 1, new Date(2021,4,6)));	
		
		//Ocurrencias de las keys
		Assertions.assertTrue(retorno.containsKey("Basico"));
		Assertions.assertTrue(retorno.containsKey("Estandar"));
		Assertions.assertTrue(retorno.containsKey("Premium"));
		
		Assertions.assertTrue(retorno.get("Basico").compare(esperado.get("Basico")));
		Assertions.assertTrue(retorno.get("Estandar").compare(esperado.get("Estandar")));
		Assertions.assertTrue(retorno.get("Premium").compare(esperado.get("Premium")));
	}
	
	@Test
	//No existen Tipos de Publicacion
	void AltaTipoPublicacionTest3() {
		Map<String, DTTipoPublicacion> retorno = ctrlTipos.obtenerDataTipos();
		Assertions.assertTrue(retorno.isEmpty());
	}
}



