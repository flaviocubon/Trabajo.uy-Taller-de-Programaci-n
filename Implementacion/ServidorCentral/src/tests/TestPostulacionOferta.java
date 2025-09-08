package tests;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import excepciones.PostulanteRepetido;
import logica.DTHorario;
import logica.DTOfertaLaboral;
import logica.DTPostulacion;
import logica.Empresa;
import logica.Fabrica;
import logica.IUsuario;
import logica.ManejadorOferta;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Postulante;
import logica.TipoPublicacion;

public class TestPostulacionOferta {
	private static IUsuario ctrlUsuario;
	private static ManejadorUsuario mu;
	private static ManejadorOferta mo;
	
	@BeforeAll
	public static void Iniciar(){
		Fabrica fab = Fabrica.getInstance();
		ctrlUsuario = fab.getIUsuario();
		mu = ManejadorUsuario.getInstance();
		mo = ManejadorOferta.getInstancia();
	}
	
	@BeforeEach
	public void Limpiar() {
		mu.clear();
	}
	@SuppressWarnings("deprecation")
	@Test
	//Test funcionamiento normal
	void PostulacionOfertaOK() throws Exception {
		mu.agregarUsuario(new Postulante("Juan123", "hola@mail.com", "Juan", "Perez", "Uruguayo", null));
		Empresa emp = new Empresa("Artech123", "artech@mail.com", "Rodrigo", "Rodriguez", "Artech SA", "Empresa de tecnologia", "asd");
		mu.agregarUsuario(emp);
		mo.agregarOferta(new OfertaLaboral("Desarrollador", emp, "trabajo junior", "Ciudad", "Mdeo", new DTHorario("08:00", "17:00"), 3000, new Date(2023,3,4), new TipoPublicacion("Oro", "descrip", 1, new Date(2023,5,1), 1000, 5), new HashSet<String>(), ""));
		String postulante = "Juan123";
		String cv = "Juan, soy estudiante.";
		String motivacion = "ganas de trabajar";
		String oferta = "Desarrollador";
		Date fecha = new Date(2023,5,5);
		ctrlUsuario.ingresarDatosPostulacion(postulante, cv, motivacion, oferta, fecha,"");
		DTOfertaLaboral dataOferta = ctrlUsuario.seleccionarOfertaLaboral(oferta);
		Iterator<DTPostulacion> it2 = dataOferta.getPostulaciones().iterator();
		DTPostulacion dataPostulacion = (DTPostulacion) it2.next();
		assertTrue(dataPostulacion.getPostulante() == postulante);
		assertTrue(dataPostulacion.getNombreOferta() == oferta);
		assertTrue(dataPostulacion.getDescripcion() == motivacion);

		}
	@SuppressWarnings("deprecation")
	@Test
	//Test funcionamiento con postulante repetido
	void PostulacionOfertaRepetido() throws Exception {
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Postulante post = new Postulante("Juan123", "hola@mail.com", "Juan", "Perez", "Uruguayo", null);
		mu.agregarUsuario(post);
		Empresa emp = new Empresa("Artech123", "artech@mail.com", "Rodrigo", "Rodriguez", "Artech SA", "Empresa de tecnologia", "asd");
		mu.agregarUsuario(emp);
		OfertaLaboral oferta = new OfertaLaboral("Desarrollador", emp, "trabajo junior", "Ciudad", "Mdeo", new DTHorario("08:00", "17:00"), 3000, new Date(2023,3,4), new TipoPublicacion("Oro", "descrip", 1, new Date(2023,5,1), 1000, 5), new HashSet<String>(), "");
		//oferta.agregarPostulante(post);
		mo.agregarOferta(oferta);
		
		String postulante = "Juan123";
		String cv = "Juan, soy estudiante.";
		String motivacion = "ganas de trabajar";
		String nomOferta = "Desarrollador";
		Date fecha = new Date(2023,5,5);
		ctrlUsuario.ingresarDatosPostulacion(postulante, cv, motivacion, nomOferta, fecha,"");

		assertThrows(PostulanteRepetido.class, ()->{ctrlUsuario.ingresarDatosPostulacion(postulante, cv, motivacion, nomOferta, fecha,"");});
	}
	@SuppressWarnings("deprecation")
	@Test
	void PostulacionOfertaListados() throws PostulanteRepetido {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Postulante post = new Postulante("Juan123", "hola@mail.com", "Juan", "Perez", "Uruguayo", null);
		Postulante post2 = new Postulante("JuanPepe", "holass@mail.com", "Juan", "Perez", "Uruguayo", null);
		mu.agregarUsuario(post);
		mu.agregarUsuario(post2);
		Empresa emp1 = new Empresa("emPrueba1", "prueba@fing.com", "Roberto", "Bedelias", "Empresa Prueba", "Una empresa bien loca", null);
		Empresa emp2 = new Empresa("emPrueba2", "prueba@fing.com", "Roberto", "Bedelias", "Empresa Prueba", "Una empresa bien loca", null);
		mu.agregarUsuario(emp2);
		mu.agregarUsuario(emp1);
		
		
		Set<String> postulantes = ctrlUsuario.listarPostulantes();
		Set<String> empresas = ctrlUsuario.listarEmpresas();
		Assertions.assertEquals(empresas.size(), 2);
		Assertions.assertEquals(postulantes.size(), 2);
		Assertions.assertTrue(empresas.contains("emPrueba1"));
		Assertions.assertTrue(empresas.contains("emPrueba2"));
		Assertions.assertTrue(postulantes.contains("Juan123"));
		Assertions.assertTrue(postulantes.contains("JuanPepe"));
	}
	
}