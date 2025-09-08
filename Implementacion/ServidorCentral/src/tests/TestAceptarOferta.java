package tests;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import logica.DTHorario;
import logica.Empresa;
import logica.Estado;
import logica.Fabrica;
import logica.ITipos;
import logica.IUsuario;
import logica.ManejadorOferta;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;

public class TestAceptarOferta {
	private static Fabrica fab;
	private static IUsuario cu;
	private static ITipos ct;
	
	public TestAceptarOferta(){
		fab = Fabrica.getInstance();
		cu = fab.getIUsuario();
		ct = fab.getITipos();
	}
	@SuppressWarnings("deprecation")
	@Test
	//Test funcionamiento normal
	void AceptarRechazarOferta() throws Exception{
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		ct.ingresarDatosTipoPublicacion("tipo Prueba", "Este tipo es bueno", 2, new Date(2023,8,5), 500, 5);
		cu.ingresarEmpresa("EmPrueba", "Roberto", "Bedelias", "Prueba@fing.com", "Empresa Prueba", "Una empresa bien loca",null);
		Set<String> setk = new HashSet<>(Arrays.asList("K1", "K2", "K3"));
		cu.ingresarOferta("EmPrueba", "tipo Prueba", "oferta Test", "Tremenda oferta", new DTHorario("10:30","16:15"), 0, new Date (2023,8,8), "La Paloma", "Rocha", setk, "");
		cu.ingresarOferta("EmPrueba", "tipo Prueba", "oferta Test2", "Tremenda oferta", new DTHorario("10:30","16:15"), 0, new Date (2023,8,8), "La Paloma", "Rocha", setk, "");
		cu.ingresarOferta("EmPrueba", "tipo Prueba", "oferta Test3", "Tremenda oferta", new DTHorario("10:30","16:15"), 0, new Date (2023,8,8), "La Paloma", "Rocha", setk, "");
		cu.aceptarRechazarOferta("oferta Test",Estado.Confirmado);
		cu.aceptarRechazarOferta("oferta Test2", Estado.Rechazado);
		OfertaLaboral oferta1 = mo.obtenerOferta("oferta Test");
		OfertaLaboral oferta2 = mo.obtenerOferta("oferta Test2");
		OfertaLaboral oferta3 = mo.obtenerOferta("oferta Test3");
		Empresa empresa = (Empresa)mu.getUsuario("EmPrueba");
		Assertions.assertEquals(oferta1.getEstado(), Estado.Confirmado);
		Assertions.assertEquals(oferta2.getEstado(), Estado.Rechazado);
		Assertions.assertEquals(oferta3.getEstado(), Estado.Ingresado);
		Assertions.assertEquals(empresa.getOfertas().get("oferta Test").getEstado(),Estado.Confirmado);
		Assertions.assertEquals(empresa.getOfertas().get("oferta Test2").getEstado(),Estado.Rechazado);
		Assertions.assertEquals(empresa.getOfertas().get("oferta Test3").getEstado(),Estado.Ingresado);
	}
	@SuppressWarnings("deprecation")
	@Test
	void listarOfertas() throws Exception{
		ct.ingresarDatosTipoPublicacion("tipo Prueba2", "Este tipo es bueno", 2, new Date(2023,8,5), 500, 5);
		cu.ingresarEmpresa("EmPrueba2", "Robertos", "Bedelias", "Pruebas@fing.com", "Empresa Pruebas", "Una empresa bien loca",null);
		Set<String> setk = new HashSet<>(Arrays.asList("K1", "K2", "K3"));
		cu.ingresarOferta("EmPrueba2", "tipo Prueba2", "oferta Test1.1", "Tremenda oferta", new DTHorario("10:30","16:15"), 0, new Date (2023,8,8), "La Paloma", "Rocha", setk, "");
		cu.ingresarOferta("EmPrueba2", "tipo Prueba2", "oferta Test2.1", "Tremenda oferta", new DTHorario("10:30","16:15"), 0, new Date (2023,8,8), "La Paloma", "Rocha", setk, "");
		cu.ingresarOferta("EmPrueba2", "tipo Prueba2", "oferta Test3.1", "Tremenda oferta", new DTHorario("10:30","16:15"), 0, new Date (2023,8,8), "La Paloma", "Rocha", setk, "");
		Set<String> res = cu.listarOfertasIngresadas("EmPrueba2");
		Assertions.assertTrue(res.contains("oferta Test1.1"));
		Assertions.assertTrue(res.contains("oferta Test2.1"));
		Assertions.assertTrue(res.contains("oferta Test3.1"));
		cu.aceptarRechazarOferta("oferta Test1.1", Estado.Confirmado);
		Set<String> res2 = cu.listarOfertasIngresadas("EmPrueba2");
		Assertions.assertFalse(res2.contains("oferta Test1.1"));
		Assertions.assertTrue(res2.contains("oferta Test2.1"));
		Assertions.assertTrue(res2.contains("oferta Test3.1"));
	}
}