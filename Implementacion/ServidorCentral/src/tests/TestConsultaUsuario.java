package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.PostulanteRepetido;
import logica.DTEmpresa;
import logica.DTHorario;
import logica.DTOfertaLaboral;
import logica.DTPostulante;
import logica.DTUsuario;
import logica.Empresa;
import logica.Fabrica;
import logica.IUsuario;
import logica.ManejadorOferta;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Postulante;
import logica.TipoPublicacion;

public class TestConsultaUsuario {
	private static IUsuario ctrlUsuario;
	
	@BeforeAll
	public static void Iniciar(){
		Fabrica fab = Fabrica.getInstance();
		ctrlUsuario = fab.getIUsuario();
		
	}
	@SuppressWarnings("deprecation")

	@Test
	void checkConsultaPostulante() throws Exception {
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Date fecha = new Date(2023,3,4);
		LocalDate fecha2= LocalDate.of(2132, 1, 3); 
		Postulante post = new Postulante("Juan123", "hola@mail.com", "Juan", "Perez", "Uruguayo", fecha );
		Empresa emp = new Empresa("Artech123", "artech@mail.com", "Rodrigo", "Rodriguez", "Artech SA", "Empresa de tecnologia", "asd");
		mu.agregarUsuario(post);
		mu.agregarUsuario(emp);
		DTHorario horario = new DTHorario("08:00", "17:00");
		TipoPublicacion tipo = new TipoPublicacion("Oro", "descrip", 1, new Date(2023,5,1), 1000, 5);
		OfertaLaboral oferta = new OfertaLaboral("Desarrollador", null, "trabajo junior", "Ciudad", "Mdeo", horario, 3000, fecha, tipo, new HashSet<String>(), null);
		mo.agregarOferta(oferta);
		ctrlUsuario.ingresarDatosPostulacion("Juan123", "CV", "motivacion", "Desarrollador", fecha,"");
		DTPostulante datos = (DTPostulante) ctrlUsuario.mostrarDatosUsuario("Juan123");
		assertEquals(datos.getNombre(), "Juan");
		assertEquals(datos.getApellido(), "Perez");
		assertEquals(datos.getFechaNacimiento(), fecha);
		assertEquals(datos.getMail(), "hola@mail.com");
		assertEquals(datos.getNacionalidad(), "Uruguayo");
		assertEquals(datos.getNickname(), "Juan123");
		Set<String> nombreOfertas= datos.getNombreOfertas();
		HashSet<String> nombres = new HashSet<String>();
		nombres.add("Desarrollador");
		assertEquals(nombreOfertas.containsAll(nombres), true);
		assertEquals(nombres.containsAll(nombreOfertas), true);
	}
	@Test
	void checkConsultaEmpresa() {
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Empresa emp = new Empresa("Artech123", "artech@mail.com", "Rodrigo", "Rodriguez", "Artech SA", "Empresa de tecnologia", "asd");
		mu.agregarUsuario(emp);
		DTHorario horario = new DTHorario("08:00", "17:00");
		Date fecha = new Date(2023,3,4);
		TipoPublicacion tipo = new TipoPublicacion("Oro", "descrip", 1, new Date(2023,5,1), 1000, 5);
		OfertaLaboral oferta = new OfertaLaboral("Desarrollador", emp, "trabajo junior", "Ciudad", "Mdeo", horario, 3000, fecha, tipo, new HashSet<String>(), "");
		emp.agregarOferta(oferta);
		mo.agregarOferta(oferta);
		DTEmpresa datos = (DTEmpresa) ctrlUsuario.mostrarDatosUsuario("Artech123");
		assertEquals(datos.getNombre(), "Rodrigo");
		assertEquals(datos.getApellido(), "Rodriguez");
		assertEquals(datos.getDescripcion(), "Empresa de tecnologia");
		assertEquals(datos.getMail(), "artech@mail.com");
		assertEquals(datos.getLink(), "asd");
		assertEquals(datos.getNickname(), "Artech123");
		Set<String> nombreOfertas= datos.getNombreOfertas();
		assertEquals(nombreOfertas.size(),1);
		HashSet<String> nombres = new HashSet<String>();
		nombres.add("Desarrollador");
		assertEquals(nombreOfertas.containsAll(nombres), true);
		assertEquals(nombres.containsAll(nombreOfertas), true);

	}
	@Test
	void construirDataTypeDeEmpresaPasandoUnaEmpresa() {
		Empresa empresaValida = new Empresa(
				"Artech123",
				"artech@mail.com",
				"Rodrigo",
				"Rodriguez",
				"Artech SA",
				"Empresa de tecnologia",
				"www.superTechempresa.com",
				"imagen_confidencia.png",
				"passSegura1234#1234");
		DTEmpresa dataTypeEmpresa = new DTEmpresa(empresaValida);
		
		assertEquals(empresaValida.getNombre(), dataTypeEmpresa.getNombre());
		assertEquals(empresaValida.getApellido(), dataTypeEmpresa.getApellido());
		assertEquals(empresaValida.getDescripcion(), dataTypeEmpresa.getDescripcion());
		assertEquals(empresaValida.getMail(), dataTypeEmpresa.getMail());
		assertEquals(empresaValida.getLink(), dataTypeEmpresa.getLink());
		assertEquals(empresaValida.getNickname(), dataTypeEmpresa.getNickname());
		
	}
}