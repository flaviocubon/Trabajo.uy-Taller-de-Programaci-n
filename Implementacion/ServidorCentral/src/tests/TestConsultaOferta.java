package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import logica.DTHorario;
import logica.DTOfertaLaboral;
import logica.DTPostulacion;
import logica.Empresa;
import logica.Estado;
import logica.Fabrica;
import logica.ITipos;
import logica.IUsuario;
import logica.ManejadorKeywords;
import logica.ManejadorOferta;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Postulante;
import logica.TipoPublicacion;

public class TestConsultaOferta {
	private static IUsuario ctrlUsuario;
	
	@BeforeAll
	public static void Iniciar(){
		Fabrica fab = Fabrica.getInstance();
		ctrlUsuario = fab.getIUsuario();
	}
	@SuppressWarnings("deprecation")
	@Test
	//Test funcionamiento normal
	void ConsultaOfertaOK() throws Exception  {
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Postulante post = new Postulante("Juan123", "hola@mail.com", "Juan", "Perez", "Uruguayo", null);
		Empresa emp = new Empresa("Artech123", "artech@mail.com", "Rodrigo", "Rodriguez", "Artech SA", "Empresa de tecnologia", "asd");
		mu.agregarUsuario(post);
		mu.agregarUsuario(emp);
		DTHorario horario = new DTHorario("08:00", "17:00");
		Date fecha = new Date(2023,3,4);
		TipoPublicacion tipo = new TipoPublicacion("Oro", "descrip", 1, new Date(2023,5,1), 1000, 5);
		OfertaLaboral oferta = new OfertaLaboral("Desarrollador", emp, "trabajo junior", "Ciudad", "Mdeo", horario, 3000, fecha, tipo, new HashSet<String>(), null);
		emp.agregarOferta(oferta);
		mo.agregarOferta(oferta);
		DTOfertaLaboral datos = ctrlUsuario.seleccionarOfertaLaboral("Desarrollador");
		assertEquals(datos.getNombre(), "Desarrollador");
		assertEquals(datos.getDescripcion(), "trabajo junior");
		assertEquals(datos.getCiudad(), "Ciudad");
		assertEquals(datos.getDepartamento(), "Mdeo");
		assertEquals(datos.getHorario(), horario);
		assertEquals(datos.getRemuneracion(), 3000);
		assertEquals(datos.getFecha(), fecha);
		assertEquals(datos.getTipo(), tipo.getNombre());
		assertEquals(datos.getCosto(), 1000);
		assertEquals(datos.getNombreEmpresa(), "Artech123");
		assertEquals(datos.getImagen(), "");
		assertEquals(datos.getEstado(), Estado.Ingresado);
		assertEquals(datos.getPaquete(), null);
	}
	@SuppressWarnings("deprecation")
	@Test
	void ConsultaOfertaListado() throws Exception  {
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Postulante post = new Postulante("Juan123", "hola@mail.com", "Juan", "Perez", "Uruguayo", null);
		Empresa emp = new Empresa("Artech123", "artech@mail.com", "Rodrigo", "Rodriguez", "Artech SA", "Empresa de tecnologia", "asd");
		mu.agregarUsuario(post);
		mu.agregarUsuario(emp);
		DTHorario horario = new DTHorario("08:00", "17:00");
		Date fecha = new Date(2023,3,4);
		TipoPublicacion tipo = new TipoPublicacion("Oro", "descrip", 1, new Date(2023,5,1), 1000, 5);
		OfertaLaboral oferta = new OfertaLaboral("Desarrollador", emp, "trabajo junior", "Ciudad", "Mdeo", horario, 3000, fecha, tipo, new HashSet<String>(), "");
		emp.agregarOferta(oferta);
		mo.agregarOferta(oferta);
		String postulante = "Juan123";
		String cv = "Juan, soy estudiante.";
		String motivacion = "ganas de trabajar";
		String ofertan = "Desarrollador";
		ctrlUsuario.ingresarDatosPostulacion(postulante, cv, motivacion, ofertan, fecha,"");
		DTOfertaLaboral dt= ctrlUsuario.seleccionarOfertaLaboral(ofertan);
		Set<DTPostulacion> postu = dt.getPostulaciones();
		Set<String> Ofertas = ctrlUsuario.obtenerOfertasDeEmpresa("Artech123");
		Assertions.assertEquals(postu.size(), 1);
		Assertions.assertEquals(Ofertas.size(), 1);
		Assertions.assertTrue(Ofertas.contains("Desarrollador"));
		
	}
	@SuppressWarnings("deprecation")
	@Test
	//Test funcionamiento normal
	void ConsultaOfertaPorKeyWord() throws Exception  {
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		ManejadorKeywords mk = ManejadorKeywords.getInstance();
		mk.agregarKeyword("NuevaKey");
		Postulante post = new Postulante("Juan123", "hola@mail.com", "Juan", "Perez", "Uruguayo", null);
		Empresa emp = new Empresa("Artech123", "artech@mail.com", "Rodrigo", "Rodriguez", "Artech SA", "Empresa de tecnologia", "asd");
		mu.agregarUsuario(post);
		mu.agregarUsuario(emp);
		DTHorario horario = new DTHorario("08:00", "17:00");
		Date fecha = new Date(2023,3,4);
		TipoPublicacion tipo = new TipoPublicacion("Oro", "descrip", 1, new Date(2023,5,1), 1000, 5);
		Set<String> keys = new HashSet<String>();
		keys.add("NuevaKey");
		OfertaLaboral oferta = new OfertaLaboral("Desarrollador", emp, "trabajo junior", "Ciudad", "Mdeo", horario, 3000, fecha, tipo, keys, null);
		emp.agregarOferta(oferta);
		mo.agregarOferta(oferta);
		Set<DTOfertaLaboral> datos = ctrlUsuario.listarOfertasPorKeyword("NuevaKey");
		Iterator<DTOfertaLaboral> iterAux = datos.iterator();
		DTOfertaLaboral iter = iterAux.next();
		assertEquals(iter.getNombre(), "Desarrollador");
		assertEquals(iter.getDescripcion(), "trabajo junior");
		assertEquals(iter.getCiudad(), "Ciudad");
		assertEquals(iter.getDepartamento(), "Mdeo");
		assertEquals(iter.getHorario(), horario);
		assertEquals(iter.getRemuneracion(), 3000);
		assertEquals(iter.getFecha(), fecha);
		assertEquals(iter.getTipo(), tipo.getNombre());
		assertTrue(iter.getKeywords().contains("NuevaKey"));
	}
}