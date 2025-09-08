package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.CtrlUsuario;
import logica.DTEmpresa;
import logica.DTPostulante;
import logica.Empresa;
import logica.Fabrica;
import logica.IUsuario;
import logica.ManejadorUsuario;
import logica.Postulante;

import java.util.Date;
import java.util.HashSet;

public class TestAltaUsuario {

	@BeforeEach
	void limpiar() {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		manejadorU.clear();
	}
	
	@Test
	void constructorPostulanteCasoValido() {
		@SuppressWarnings("deprecation")
		DTPostulante datosValidos = new DTPostulante("peprulo", "pepe_rulos34@gmail.com", "Pepe", "Rulo", "Argentina",
				new Date(5, 12, 1956), new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		Postulante postulanteValido = new Postulante(datosValidos.getNickname(), datosValidos.getMail(),
				datosValidos.getNombre(), datosValidos.getApellido(), datosValidos.getNacionalidad(),
				datosValidos.getFechaNacimiento());

		assertEquals(datosValidos.getNickname(), postulanteValido.getNickname());
		assertEquals(datosValidos.getMail(), postulanteValido.getMail());
		assertEquals(datosValidos.getNombre(), postulanteValido.getNombre());
		assertEquals(datosValidos.getApellido(), postulanteValido.getApellido());
		assertEquals(datosValidos.getNacionalidad(), postulanteValido.getNacionalidad());
		assertEquals(datosValidos.getFechaNacimiento(), postulanteValido.getFechaNacimiento());
	}

	@Test
	void constructorEmpresaCasoValido() {
		DTEmpresa datosValidos = new DTEmpresa("mcrulo", "pepe_rulos34@gmail.com", "Mc'Pepe", "Rulo Herrara",
				"Rulería MC'Peperulo",
				"Empresa de manufactura de rulos de diversos materiales, tamaños y funcionalidades.",
				"https://www.google.com.uy", new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());
		Empresa empresaValido = new Empresa(datosValidos.getNickname(), datosValidos.getMail(),
				datosValidos.getNombre(), datosValidos.getApellido(), datosValidos.getNombreEmpresa(),
				datosValidos.getDescripcion(), datosValidos.getLink());
		assertEquals(datosValidos.getNickname(), empresaValido.getNickname());
		assertEquals(datosValidos.getMail(), empresaValido.getMail());
		assertEquals(datosValidos.getNombre(), empresaValido.getNombre());
		assertEquals(datosValidos.getApellido(), empresaValido.getApellido());
		assertEquals(datosValidos.getNombreEmpresa(), empresaValido.getNombreEmpresa());
		assertEquals(datosValidos.getDescripcion(), empresaValido.getDescripcion());
		assertEquals(datosValidos.getLink(), empresaValido.getLink());
	}

	@Test
	void ingresarPostulanteCasoValido() {
		DTPostulante datosValidos = new DTPostulante("peprulo", "pepe_rulos34@gmail.com", "Pepe", "Rulo", "Argentina",
				new Date(5, 12, 1956), new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		Fabrica fab = Fabrica.getInstance();
		IUsuario ctrlUsuario = fab.getIUsuario();

		try {
			ctrlUsuario.ingresarPostulante(datosValidos.getNickname(), datosValidos.getNombre(),
					datosValidos.getApellido(), datosValidos.getMail(), datosValidos.getFechaNacimiento(),
					datosValidos.getNacionalidad());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		DTPostulante datosPostulanteCreado = (DTPostulante) ctrlUsuario.mostrarDatosUsuario(datosValidos.getNickname());

		assertEquals(datosValidos.getNickname(), datosPostulanteCreado.getNickname());
		assertEquals(datosValidos.getMail(), datosPostulanteCreado.getMail());
		assertEquals(datosValidos.getNombre(), datosPostulanteCreado.getNombre());
		assertEquals(datosValidos.getApellido(), datosPostulanteCreado.getApellido());
		assertEquals(datosValidos.getNacionalidad(), datosPostulanteCreado.getNacionalidad());
		assertEquals(datosValidos.getFechaNacimiento(), datosPostulanteCreado.getFechaNacimiento());
	}

	@Test
	void ingresarEmpresaCasoValido() {
		DTEmpresa datosValidos = new DTEmpresa("mcrulo", "ruleria-herrera-hnos@gmail.com", "Mc'Pepe", "Rulo Herrara",
				"Rulería MC'Peperulo",
				"Empresa de manufactura de rulos de diversos materiales, tamaños y funcionalidades.",
				"https://www.google.com.uy", new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		Fabrica fab = Fabrica.getInstance();
		IUsuario ctrlUsuario = fab.getIUsuario();
		try {
			ctrlUsuario.ingresarEmpresa(datosValidos.getNickname(), datosValidos.getNombre(),
					datosValidos.getApellido(), datosValidos.getMail(), datosValidos.getNombreEmpresa(),
					datosValidos.getDescripcion(), datosValidos.getLink());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		DTEmpresa datosEmpresaCreado = (DTEmpresa) ctrlUsuario.mostrarDatosUsuario(datosValidos.getNickname());
		assertEquals(datosValidos.getNickname(), datosEmpresaCreado.getNickname());
		assertEquals(datosValidos.getMail(), datosEmpresaCreado.getMail());
		assertEquals(datosValidos.getNombre(), datosEmpresaCreado.getNombre());
		assertEquals(datosValidos.getApellido(), datosEmpresaCreado.getApellido());
		assertEquals(datosValidos.getNombreEmpresa(), datosEmpresaCreado.getNombreEmpresa());
		assertEquals(datosValidos.getDescripcion(), datosEmpresaCreado.getDescripcion());
		assertEquals(datosValidos.getLink(), datosEmpresaCreado.getLink());
	}

	@Test
	void ingresarPostulanteCasoInvalidoNickRepetido() {
		@SuppressWarnings("deprecation")
		DTPostulante datosValidos = new DTPostulante("xX_the_original_Xx", "so_original@gmail.com", "Juan", "Perez",
				"Uruguay", new Date(1, 1, 2000), new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		@SuppressWarnings("deprecation")
		DTPostulante datosNickRepetido = new DTPostulante("xX_the_original_Xx", "so_original2000@gmail.com", "Luis",
				"Rodriguez", "Chile", new Date(1, 1, 2000), new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		Fabrica fab = Fabrica.getInstance();
		IUsuario ctrlUsuario = fab.getIUsuario();

		try {
			ctrlUsuario.ingresarPostulante(datosValidos.getNickname(), datosValidos.getNombre(),
					datosValidos.getApellido(), datosValidos.getMail(), datosValidos.getFechaNacimiento(),
					datosValidos.getNacionalidad());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Exception thrown = assertThrows(Exception.class, () -> {
			ctrlUsuario.ingresarPostulante(datosNickRepetido.getNickname(), datosNickRepetido.getNombre(),
					datosNickRepetido.getApellido(), datosNickRepetido.getMail(),
					datosNickRepetido.getFechaNacimiento(), datosNickRepetido.getNacionalidad());
		});

		assertEquals("Nickname ya existe", thrown.getMessage());

	}

	@Test
	void ingresarEmpresaCasoInvalidoNickRepetido() {
		DTEmpresa datosValidos = new DTEmpresa("ofertasImperdibles", "super_ofertas@gmail.com", "José", "Washington",
				"Ofertas Imperdibles", "La cadena de supermercados con los mejores precios del mundo",
				"https://www.supimperdibles.com.uy", new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		DTEmpresa datosNickRepetido = new DTEmpresa("ofertasImperdibles", "ofertas_imperdibles@gmail.com", "Álvaro",
				"Bosques", "Super Ofertas Imperdibles", "Cadenas de supermercados con precios insuperables",
				"https://www.mejorandotuscompras.com.uy", new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		Fabrica fab = Fabrica.getInstance();
		IUsuario ctrlUsuario = fab.getIUsuario();

		try {
			ctrlUsuario.ingresarEmpresa(datosValidos.getNickname(), datosValidos.getNombre(),
					datosValidos.getApellido(), datosValidos.getMail(), datosValidos.getNombreEmpresa(),
					datosValidos.getDescripcion(), datosValidos.getLink());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Exception thrown = assertThrows(Exception.class, () -> {
			ctrlUsuario.ingresarEmpresa(datosNickRepetido.getNickname(), datosNickRepetido.getNombre(),
					datosNickRepetido.getApellido(), datosNickRepetido.getMail(), datosNickRepetido.getNombreEmpresa(),
					datosNickRepetido.getDescripcion(), datosNickRepetido.getLink());
		});

		assertEquals("Nickname ya existe", thrown.getMessage());

	}

	@Test
	void ingresarPostulanteCasoInvalidoMailRepetido() {
		@SuppressWarnings("deprecation")
		DTPostulante datosValidos = new DTPostulante("darkest_originalus", "is_not_a_phase_mom@gmail.com", "Jimmy",
				"Wong", "United States of America", new Date(1, 1, 2000), new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		@SuppressWarnings("deprecation")
		DTPostulante datosMailRepetido = new DTPostulante("xX-the-baddest-demon-Xx", "is_not_a_phase_mom@gmail.com",
				"Timmy", "Jhones", "United States of America", new Date(1, 1, 2000), new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		Fabrica fab = Fabrica.getInstance();
		IUsuario ctrlUsuario = fab.getIUsuario();

		try {
			ctrlUsuario.ingresarPostulante(datosValidos.getNickname(), datosValidos.getNombre(),
					datosValidos.getApellido(), datosValidos.getMail(), datosValidos.getFechaNacimiento(),
					datosValidos.getNacionalidad());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Exception thrown = assertThrows(Exception.class, () -> {
			ctrlUsuario.ingresarPostulante(datosMailRepetido.getNickname(), datosMailRepetido.getNombre(),
					datosMailRepetido.getApellido(), datosMailRepetido.getMail(),
					datosMailRepetido.getFechaNacimiento(), datosMailRepetido.getNacionalidad());
		});

		assertEquals("Correo electrónico ya existe", thrown.getMessage());

	}

	@Test
	void ingresarEmpresaCasoInvalidoMailRepetido() {
		DTEmpresa datosValidos = new DTEmpresa("tata-consultancy-services", "tata@gmail.com", "Emir", "Sharma",
				"Tata Consultancy Services", "We are not a supermarket", "https://www.tcs.com", new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		DTEmpresa datosMailRepetido = new DTEmpresa("tata-supermercado", "tata@gmail.com", "Gustavo", "Dominguez",
				"Super Ofertas Imperdibles", "Cadenas de supermercados con precios insuperables",
				"https://www.tata.com.uy", new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>());

		Fabrica fab = Fabrica.getInstance();
		IUsuario ctrlUsuario = fab.getIUsuario();

		try {
			ctrlUsuario.ingresarEmpresa(datosValidos.getNickname(), datosValidos.getNombre(),
					datosValidos.getApellido(), datosValidos.getMail(), datosValidos.getNombreEmpresa(),
					datosValidos.getDescripcion(), datosValidos.getLink());
		} catch (Exception e) {
			fail(e.getMessage());
		}

		Exception thrown = assertThrows(Exception.class, () -> {
			ctrlUsuario.ingresarEmpresa(datosMailRepetido.getNickname(), datosMailRepetido.getNombre(),
					datosMailRepetido.getApellido(), datosMailRepetido.getMail(), datosMailRepetido.getNombreEmpresa(),
					datosMailRepetido.getDescripcion(), datosMailRepetido.getLink());
		});

		assertEquals("Correo electrónico ya existe", thrown.getMessage());

	}
	
	@Test 
	void inicioSesionPostulante() throws Exception {
		Fabrica fab = Fabrica.getInstance();
		IUsuario ctrlUsuario = fab.getIUsuario();
		ctrlUsuario.ingresarPostulante("darkest_originalus", "Jimmy", "Wong", "is_not_a_phase_mom@gmail.com", new Date() , "United States", "", "123abc");
		DTPostulante dataObtenida = (DTPostulante) ctrlUsuario.iniciarSesion("darkest_originalus", "123abc");
		DTPostulante dataEsperada = ctrlUsuario.getDataPostulante("darkest_originalus");
		assertEquals(dataObtenida.getNickname(), dataEsperada.getNickname());
		assertEquals(dataObtenida.getNombre(), dataEsperada.getNombre());
		assertEquals(dataObtenida.getApellido(), dataEsperada.getApellido());
	}
	
	@Test
	void inicioSesionEmpresa() throws Exception {
		Fabrica fab = Fabrica.getInstance();
		IUsuario ctrlUsuario = fab.getIUsuario();
		ctrlUsuario.ingresarEmpresa("darkest_originalus", "Jimmy", "Wong", "is_not_a_phase_mom@gmail.com", "darkest_originalus", "Descripcion empresa" , "www.google.com", "", "123abc");
		DTEmpresa dataObtenida = (DTEmpresa) ctrlUsuario.iniciarSesion("darkest_originalus", "123abc");
		DTEmpresa dataObtenida2 = (DTEmpresa) ctrlUsuario.iniciarSesion("is_not_a_phase_mom@gmail.com", "123abc");
		DTEmpresa dataEsperada = ctrlUsuario.getDataEmpresa("darkest_originalus");
		assertEquals(dataObtenida.getNickname(), dataEsperada.getNickname());
		assertEquals(dataObtenida.getNombre(), dataEsperada.getNombre());
		assertEquals(dataObtenida.getApellido(), dataEsperada.getApellido());
		assertEquals(dataObtenida2.getNickname(), dataEsperada.getNickname());
		assertEquals(dataObtenida2.getNombre(), dataEsperada.getNombre());
		assertEquals(dataObtenida2.getApellido(), dataEsperada.getApellido());
	}
}
