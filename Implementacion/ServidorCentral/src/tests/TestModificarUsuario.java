package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.PostulanteRepetido;
import logica.DTEmpresa;
import logica.DTHorario;
import logica.DTPostulante;
import logica.Empresa;
import logica.Fabrica;
import logica.IUsuario;
import logica.ManejadorOferta;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Postulante;
import logica.TipoPublicacion;

public class TestModificarUsuario {
	private static IUsuario ctrlUsuario;
	
	@BeforeAll
	public static void Iniciar(){
		Fabrica fab = Fabrica.getInstance();
		ctrlUsuario = fab.getIUsuario();
		
	}
	
	@Test
	void checkModUser1() throws Exception {
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Date fecha = new Date(2023,3,4);
		LocalDate fecha2= LocalDate.of(2132, 1, 3); 
		Postulante post = new Postulante("Juan123", "hola@mail.com", "Juan", "Perez", "Uruguayo", fecha );
		DTHorario horario = new DTHorario("08:00", "17:00");
		TipoPublicacion tipo = new TipoPublicacion("Oro", "descrip", 1, new Date(2023,5,1), 1000, 5);
		Empresa emp = new Empresa("Artech123", "artech@mail.com", "Rodrigo", "Rodriguez", "Artech SA", "Empresa de tecnologia", "asd");
		mu.agregarUsuario(emp);
		OfertaLaboral oferta = new OfertaLaboral("Desarrollador", emp, "trabajo junior", "Ciudad", "Mdeo", horario, 3000, fecha, tipo, new HashSet<String>(), null);
		mo.agregarOferta(oferta);
		mu.agregarUsuario(post);
		ctrlUsuario.ingresarDatosPostulacion("Juan123", "CV", "motivacion", "Desarrollador", fecha,"");
		ctrlUsuario.editarDatosBasicos("Juan123", "hola@mail.com", "Guillermo", "Gutierrez", "Ghana", new Date(1999, 7, 1), "Programador experimentado", "");
		DTPostulante datos = (DTPostulante) ctrlUsuario.mostrarDatosUsuario("Juan123");
		Date fechaaux = new Date(1999, 7, 1);
		assertEquals(datos.getNombre(), "Guillermo");
		assertEquals(datos.getApellido(), "Gutierrez");
		assertEquals(datos.getFechaNacimiento(), fechaaux);
		assertEquals(datos.getMail(), "hola@mail.com");
		assertEquals(datos.getNacionalidad(), "Ghana");
		assertEquals(datos.getNickname(), "Juan123");
		Set<String> nombreOfertas= datos.getNombreOfertas();
		HashSet<String> nombres = new HashSet<String>();
		nombres.add("Desarrollador");
		assertEquals(nombreOfertas.containsAll(nombres), true);
		assertEquals(nombres.containsAll(nombreOfertas), true);
	}
	
	@Test
	void checkModUser2() {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Empresa post = new Empresa("Juan123", "hola@mail.com", "Juan", "Perez", "ANTEL", "Empresa de telecomunicaciones", "antel.com.uy" );
		mu.agregarUsuario(post);
		ctrlUsuario.editarDatosBasicos("Juan123", "hola@mail.com", "Guillermo", "Gutierrez", "", null, "Chofer experimentado", "copsa.com.uy");
		DTEmpresa datos = (DTEmpresa) ctrlUsuario.mostrarDatosUsuario("Juan123");
		assertEquals(datos.getNombre(), "Guillermo");
		assertEquals(datos.getApellido(), "Gutierrez");
		assertEquals(datos.getMail(), "hola@mail.com");
		assertEquals(datos.getNickname(), "Juan123");
		assertEquals(datos.getDescripcion(), "Chofer experimentado");
		assertEquals(datos.getLink(), "copsa.com.uy");
	}
	@Test
	void checkModUserWeb() {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Empresa post = new Empresa("Juan123", "hola@mail.com", "Juan", "Perez", "ANTEL", "Empresa de telecomunicaciones", "antel.com.uy", "imagen", "pass");
		mu.agregarUsuario(post);
		ctrlUsuario.editarDatosBasicos("Juan123", "hola@mail.com", "ejemploPass", "Guillermo", "Gutierrez", "", null, "Chofer experimentado", "copsa.com.uy", "imagenEjemplo");
		DTEmpresa datos = (DTEmpresa) ctrlUsuario.mostrarDatosUsuario("Juan123");
		Set<String> ofertasdeEmpresa = ctrlUsuario.obtenerOfertasDeEmpresa("noExiste");
		assertEquals(datos.getNombre(), "Guillermo");
		assertEquals(datos.getApellido(), "Gutierrez");
		assertEquals(datos.getMail(), "hola@mail.com");
		assertEquals(datos.getNickname(), "Juan123");
		assertEquals(datos.getDescripcion(), "Chofer experimentado");
		assertEquals(datos.getLink(), "copsa.com.uy");
		assertEquals(datos.getImagen(), "imagenEjemplo");
		assertTrue(ofertasdeEmpresa.size() == 0);
	}
	@Test
	@SuppressWarnings("deprecation")
	void checkModUserWebPostulante() {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		mu.clear();
		Postulante post = new Postulante("Juan123", "hola@mail.com", "Juan", "Perez", "Uruguaya", new Date(1985,12,12), "imagen", "pass");
		mu.agregarUsuario(post);
		ctrlUsuario.editarDatosBasicos("Juan123", "hola@mail.com", "ejemploPass", "Guillermo", "Gutierrez", "Argentina", new Date(1990,12,12), "", "", "imagenEjemplo");
		DTPostulante datos = (DTPostulante) ctrlUsuario.mostrarDatosUsuario("Juan123");
		List<String> lista = ctrlUsuario.listarUsuarios();
		assertTrue(lista.size() > 0);
		assertEquals(datos.getNombre(), "Guillermo");
		assertEquals(datos.getApellido(), "Gutierrez");
		assertEquals(datos.getMail(), "hola@mail.com");
		assertEquals(datos.getNickname(), "Juan123");
		assertEquals(datos.getFechaNacimiento(), new Date(1990,12,12));
		assertEquals(datos.getNacionalidad(), "Argentina");
		assertEquals(datos.getImagen(), "imagenEjemplo");
	}
}
