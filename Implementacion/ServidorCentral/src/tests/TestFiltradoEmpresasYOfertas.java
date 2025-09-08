package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import logica.DTEmpresa;
import logica.DTHorario;
import logica.DTOfertaLaboral;
import logica.DTPostulacion;
import logica.DTPostulante;
import logica.DTTipoPublicacion;
import logica.Estado;
import logica.Fabrica;
import logica.ITipos;
import logica.IUsuario;
import logica.ManejadorOferta;
import logica.ManejadorPaquete;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;

class TestFiltradoEmpresasYOfertas {
	private static IUsuario ctrlUsuario;
	private static ITipos ctrlTipos;
	private static ArrayList<DTEmpresa> listaDatosEmpresasGenericas;
	private static ArrayList<DTPostulante> listaDatosPostulantesGenerico;
	private static ArrayList<DTTipoPublicacion> listaDatosTiposDePublicacionGenericos;
	private static ArrayList<DTOfertaLaboral> listaDatosOfertasGenericas;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Fabrica fab = Fabrica.getInstance();
		ctrlUsuario = fab.getIUsuario();
		ctrlTipos = fab.getITipos();
		listaDatosEmpresasGenericas = new ArrayList<DTEmpresa>();
		listaDatosPostulantesGenerico = new ArrayList<DTPostulante>();
		listaDatosTiposDePublicacionGenericos = new ArrayList<DTTipoPublicacion>();
		listaDatosOfertasGenericas = new ArrayList<DTOfertaLaboral>();
		
		// Aseguro que las colecciones estan vacías
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		ManejadorOferta manejadorOfertasLaborales = ManejadorOferta.getInstancia();
		ManejadorTipo manejadorTipoDePublicaciones = ManejadorTipo.getInstance();
		ManejadorPaquete manejadorPaqueteDeTiposDePublicacion = ManejadorPaquete.getInstance();
		manejadorU.clear();
		manejadorOfertasLaborales.clear();
		manejadorTipoDePublicaciones.clear();
		manejadorPaqueteDeTiposDePublicacion.clear();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		ManejadorOferta manejadorOfertasLaborales = ManejadorOferta.getInstancia();
		ManejadorTipo manejadorTipoDePublicaciones = ManejadorTipo.getInstance();
		ManejadorPaquete manejadorPaqueteDeTiposDePublicacion = ManejadorPaquete.getInstance();
		manejadorU.clear();
		manejadorOfertasLaborales.clear();
		manejadorTipoDePublicaciones.clear();
		manejadorPaqueteDeTiposDePublicacion.clear();
	}

	@SuppressWarnings("deprecation")
	@BeforeEach
	void setUp() throws Exception {
		
		// Datos empresas de prueba
		listaDatosEmpresasGenericas.add(new DTEmpresa("nioqui_farmer", "cookie-nioqui-farmer@gmail.com", "Josüé",
				"Afk-kîng de Herrera", "Cooperativa Nacional de Productores Regionales de Ñoquis",
				"Más que una granja, más que una cooperativa, más que un país, una familia, una pasión",
				"www.galletasdenioqui.com.uy", null, "imagen-picosa.png", new HashSet<String>(), new HashSet<String>()));
		listaDatosEmpresasGenericas.add(new DTEmpresa("choripanMaster", "chori_master@gmail.com", "Brayian",
				"Péres", "El carrito choripan del master brayian",
				"Los mejore chori nieri, te dejan re polenta para apoyar al cuadro ma' grande",
				"www.facebook.com/elmasterchori", null, "elpropiochori.jpg", new HashSet<String>(), new HashSet<String>()));

		// Ingresar empresas de prueba
		for(DTEmpresa dte : listaDatosEmpresasGenericas) {
			ctrlUsuario.ingresarEmpresa(
					dte.getNickname(),
					dte.getNombre(),
					dte.getApellido(),
					dte.getMail(),
					dte.getNombreEmpresa(),
					dte.getDescripcion(),
					dte.getLink());
		}
		
		
		// Datos postulantes decoy
		listaDatosPostulantesGenerico.add(new DTPostulante("peprulo", "pepe_rulos34@gmail.com", "Pepe", "Rulo", "Argentina",
				new Date(5, 12, 1956), new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>()));
		listaDatosPostulantesGenerico.add(new DTPostulante("notChoripanMaster", "no_soy_el_chori_master@gmail.com", "Brayian", "Péres", "Uruguay",
				new Date(5, 12, 1956), new HashSet<String>(), "", new HashSet<String>(), new HashSet<String>()));
		
		// Ingresar postulantes decoy
		for(DTPostulante dtp : listaDatosPostulantesGenerico) {
			ctrlUsuario.ingresarPostulante(
					dtp.getNickname(),
					dtp.getNombre(),
					dtp.getApellido(),
					dtp.getMail(),
					dtp.getFechaNacimiento(),
					dtp.getNacionalidad());
		}
		

		// Datos de Tipos de publicacion de prueba
		listaDatosTiposDePublicacionGenericos.add(new DTTipoPublicacion("Deluxe ultra Fashion",
				"Paquete para la crème de la crème", 5, 365, 100000, new Date(2023, 4, 12)));
		listaDatosTiposDePublicacionGenericos.add(new DTTipoPublicacion("Basico",
				"Paquete simple para las empresas que les gusta el helado de vainilla y comen ñoquis sin salsa", 0, 30,
				250, new Date(2022, 2, 05)));
		
		// Ingresar tipos de publicacacion de prueba
		for(DTTipoPublicacion dttp : listaDatosTiposDePublicacionGenericos) {
			ctrlTipos.ingresarDatosTipoPublicacion(
					dttp.getNombre(),
					dttp.getDescripcion(),
					dttp.getExposicion(),
					dttp.getFecha(),
					dttp.getCosto(),
					(int) dttp.getDuracion());
		}
		
		// Ingresar paquetes de prueba y sus tipos de publicacion
		String nombreDelPaqueteA = "Paquete Basico";
		String imagenDelPaqueteA = "galleta_con_forma_de_ñoqui.jpg";
		ctrlTipos.ingresarDatosPaquete(nombreDelPaqueteA, "Paquete de Tipo basico", 20, 0.5, new Date(2021, 5, 10), imagenDelPaqueteA);
		
		// Agregar los tipos de publicacion a los paquetes
		for(DTTipoPublicacion dttp : listaDatosTiposDePublicacionGenericos) {
			ctrlTipos.agregarTipoPublicacion(nombreDelPaqueteA, dttp.getNombre(), 5);
		}
		
		// Datos ofertas de prueba
		listaDatosOfertasGenericas.add(new DTOfertaLaboral(
				"Ñoquero Semi-Senior",
				listaDatosEmpresasGenericas.get(0).getNickname(),
				"Se requiere de un amasador con 10 años de experiencia de amasar ñoquis y 20 de prender hornos a leña",
				"Santa Lucia",
				"Canelones",
				new DTHorario("03:00", "11:00"),
				45000,
				new Date(),
				listaDatosTiposDePublicacionGenericos.get(1).getNombre(),
				new HashSet<String>(),
				new HashSet<DTPostulacion>(),
				249,
				Estado.Confirmado,
				nombreDelPaqueteA,
				"oferton01.png",
				LocalDate.now(), 0));
		listaDatosOfertasGenericas.add(new DTOfertaLaboral(
				"Repartidor de ñoqui",
				listaDatosEmpresasGenericas.get(0).getNickname(),
				"Se requiere de un repartidor con licencia profesional de motocicleta y que respete los semáforos",
				"Santa Lucia",
				"Canelones",
				new DTHorario("08:00", "14:00"),
				45000,
				new Date(),
				listaDatosTiposDePublicacionGenericos.get(1).getNombre(),
				new HashSet<String>(),
				new HashSet<DTPostulacion>(),
				249,
				Estado.Confirmado,
				nombreDelPaqueteA,
				"oferton01.png",
				LocalDate.now(), 0));
		
		// Ingresar ofertas de prueba
		for(DTOfertaLaboral dto : listaDatosOfertasGenericas) {
			ctrlUsuario.ingresarOferta(dto.getNombreEmpresa(), dto.getTipo(), dto.getNombre(), dto.getDescripcion(), dto.getHorario(),
					dto.getRemuneracion(), dto.getFecha(), dto.getCiudad(), dto.getDepartamento(), dto.getKeywords(), dto.getImagen());
		}
		
	}

	@AfterEach
	void tearDown() throws Exception {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		ManejadorOferta manejadorOfertasLaborales = ManejadorOferta.getInstancia();
		ManejadorTipo manejadorTipoDePublicaciones = ManejadorTipo.getInstance();
		ManejadorPaquete manejadorPaqueteDeTiposDePublicacion = ManejadorPaquete.getInstance();
		manejadorU.clear();
		manejadorOfertasLaborales.clear();
		manejadorTipoDePublicaciones.clear();
		manejadorPaqueteDeTiposDePublicacion.clear();
		listaDatosEmpresasGenericas.clear();
		listaDatosPostulantesGenerico.clear();
		listaDatosOfertasGenericas.clear();
		listaDatosTiposDePublicacionGenericos.clear();
	}

    @Nested
    @DisplayName("Cuando se filtran empresas")
    class AlFiltrarEmpresas {
    	
    	@Test
    	void resultadoNoEsVacioSiNombreCoincideConEmpresa() {
    		ArrayList<DTEmpresa> resultadosBusqueda = ctrlUsuario.buscarPorFiltroEmpresas(listaDatosEmpresasGenericas.get(0).getNombre());
    		assertNotNull(resultadosBusqueda);
    		assertEquals(1, resultadosBusqueda.size());
    	}
    	
    	@Test
    	void encontrarEmpresaPorNombreCompleto() {
    		// Test asume que el nombre es único en el universo del sistema
    		String parametroDeBusqueda = listaDatosEmpresasGenericas.get(0).getNombre();
    		ArrayList<DTEmpresa> resultadosBusqueda = ctrlUsuario.buscarPorFiltroEmpresas(parametroDeBusqueda);
    		assertNotNull(resultadosBusqueda.get(0));
    		assertAll("DT de empresa en resultados", 
    				() -> assertNotNull(ctrlUsuario.getDataEmpresa(listaDatosEmpresasGenericas.get(0).getNickname())),
    				() -> assertTrue(resultadosBusqueda.get(0).getNombre().contains(parametroDeBusqueda)),
    				() -> assertEquals(listaDatosEmpresasGenericas.get(0).getNickname(), resultadosBusqueda.get(0).getNickname()));
    	}
    	
    	
    	@Test
    	void encontrarEmpresaPorNombreCortado() {
    		// Test asume que el nombre es único en el universo del sistema
    		String parametroDeBusqueda = listaDatosEmpresasGenericas.get(0).getNombre().substring(1);
    		ArrayList<DTEmpresa> resultadosBusqueda = ctrlUsuario.buscarPorFiltroEmpresas(parametroDeBusqueda);
    		assertNotNull(resultadosBusqueda.get(0));
    		assertAll("DT de empresa en resultados", 
    				() -> assertNotNull(ctrlUsuario.getDataEmpresa(listaDatosEmpresasGenericas.get(0).getNickname())),
    				() -> assertTrue(resultadosBusqueda.get(0).getNombre().contains(parametroDeBusqueda)),
    				() -> assertEquals(listaDatosEmpresasGenericas.get(0).getNickname(), resultadosBusqueda.get(0).getNickname()));
    	}
    	
    	@Test
    	void noEncontrarEmpresaPorNombreCompleto() {
    		// Test asume que el nombre con la concatenacion no existe en el universo del sistema
    		String parametroDeBusqueda = listaDatosEmpresasGenericas.get(0).getNombre().concat(" contenido extra para hacer fallar el caso");
    		ArrayList<DTEmpresa> resultadosBusqueda = ctrlUsuario.buscarPorFiltroEmpresas(parametroDeBusqueda);
    		assertNotNull(resultadosBusqueda);
    		assertEquals(0, resultadosBusqueda.size());
    	}
    	
    	@Test
    	void noEncontrarPostulantePorNombreCompleto() {
    		// Test asume que el nombre es único en el universo del sistema
    		String parametroDeBusqueda = listaDatosPostulantesGenerico.get(0).getNombre();
    		ArrayList<DTEmpresa> resultadosBusqueda = ctrlUsuario.buscarPorFiltroEmpresas(parametroDeBusqueda);
    		assertNotNull(resultadosBusqueda);
    		assertEquals(0, resultadosBusqueda.size());
    	}
    	
    	@Test
    	void encontrarEmpresaPorDescripcionCompleta() {
    		// Test asume que la descripcion es única en el universo del sistema
    		String parametroDeBusqueda = listaDatosEmpresasGenericas.get(1).getDescripcion();
    		ArrayList<DTEmpresa> resultadosBusqueda = ctrlUsuario.buscarPorFiltroEmpresas(parametroDeBusqueda);
    		assertNotNull(resultadosBusqueda.get(0));
    		assertAll("DT de empresa en resultados", 
    				() -> assertNotNull(ctrlUsuario.getDataEmpresa(listaDatosEmpresasGenericas.get(1).getNickname())),
    				() -> assertTrue(resultadosBusqueda.get(0).getDescripcion().contains(parametroDeBusqueda)),
    				() -> assertEquals(listaDatosEmpresasGenericas.get(1).getNickname(), resultadosBusqueda.get(0).getNickname()));
    	}
    	
    	@Test
    	void encontrarTodasLasEmpresaPorDescripcionCortada() {
    		// Test asume que exactamente 2 descripciones de empresas contienen el string "a pa"
    		String parametroDeBusqueda = "a pa";
    		ArrayList<DTEmpresa> resultadosBusqueda = ctrlUsuario.buscarPorFiltroEmpresas(parametroDeBusqueda);
    		assertEquals(2, resultadosBusqueda.size());
    	}
    }
    
    @Nested
    @DisplayName("Cuando se filtran ofertas")
    class AlFiltrarOfertas {
    	
    	@Test
    	void resultadoNoEsVacioSiNombreCoincideConOferta() {
    		ArrayList<DTOfertaLaboral> resultadosBusqueda = ctrlUsuario.buscarPorFiltroOfertasLaborales(listaDatosOfertasGenericas.get(0).getNombre());
    		assertNotNull(resultadosBusqueda);
    		assertEquals(1, resultadosBusqueda.size());
    	}
		
		@Test
		void encontrarOfertaPorNombreCompleto() {
			// Test asume que el nombre es único en el universo del sistema
			String parametroDeBusqueda = listaDatosOfertasGenericas.get(0).getNombre();
			ArrayList<DTOfertaLaboral> resultadosBusqueda = ctrlUsuario.buscarPorFiltroOfertasLaborales(parametroDeBusqueda);
			assertNotNull(resultadosBusqueda.get(0));
			assertAll("DT de empresa en resultados", 
					() -> assertNotNull(ctrlUsuario.getDataEmpresa(listaDatosEmpresasGenericas.get(0).getNickname())),
					() -> assertTrue(resultadosBusqueda.get(0).getNombre().contains(parametroDeBusqueda)),
					() -> assertEquals(listaDatosOfertasGenericas.get(0).getNombre(), resultadosBusqueda.get(0).getNombre()));
		}
		
		
		@Test
		void encontrarOfertaPorNombreCortado() {
			// Test asume que el nombre es único en el universo del sistema
			String parametroDeBusqueda = listaDatosOfertasGenericas.get(0).getNombre().substring(1);
			ArrayList<DTOfertaLaboral> resultadosBusqueda = ctrlUsuario.buscarPorFiltroOfertasLaborales(parametroDeBusqueda);
			assertNotNull(resultadosBusqueda.get(0));
			assertAll("DT de empresa en resultados", 
					() -> assertNotNull(ctrlUsuario.getDataEmpresa(listaDatosEmpresasGenericas.get(0).getNickname())),
					() -> assertTrue(resultadosBusqueda.get(0).getNombre().contains(parametroDeBusqueda)),
					() -> assertEquals(listaDatosOfertasGenericas.get(0).getNombre(), resultadosBusqueda.get(0).getNombre()));
		}
		
		@Test
		void noEncontrarOfertaPorNombreCompleto() {
			// Test asume que el nombre con la concatenacion no existe en el universo del sistema
			String parametroDeBusqueda = listaDatosOfertasGenericas.get(0).getNombre().concat(" contenido extra para hacer fallar el caso");
			ArrayList<DTOfertaLaboral> resultadosBusqueda = ctrlUsuario.buscarPorFiltroOfertasLaborales(parametroDeBusqueda);
			assertNotNull(resultadosBusqueda);
			assertEquals(0, resultadosBusqueda.size());
		}
    	
    	@Test
    	void encontrarOfertaPorDescripcionCompleta() {
    		// Test asume que la descripcion es única en el universo del sistema
    		String parametroDeBusqueda = listaDatosOfertasGenericas.get(0).getDescripcion();
    		ArrayList<DTOfertaLaboral> resultadosBusqueda = ctrlUsuario.buscarPorFiltroOfertasLaborales(parametroDeBusqueda);
    		assertNotNull(resultadosBusqueda.get(0));
    		assertAll("DT de empresa en resultados", 
    				() -> assertNotNull(ctrlUsuario.seleccionarOfertaLaboral(listaDatosOfertasGenericas.get(0).getNombre())),
    				() -> assertTrue(resultadosBusqueda.get(0).getDescripcion().contains(parametroDeBusqueda)),
    				() -> assertEquals(listaDatosOfertasGenericas.get(0).getNombre(), resultadosBusqueda.get(0).getNombre()));
    	}
    	
    	@Test
    	void encontrarTodasLasOfertasPorDescripcionCortada() {
    		// Test asume que exactamente 2 descripciones de empresas contienen el string "a pa"
    		String parametroDeBusqueda = "Se requiere de un ";
    		ArrayList<DTOfertaLaboral> resultadosBusqueda = ctrlUsuario.buscarPorFiltroOfertasLaborales(parametroDeBusqueda);
    		assertEquals(2, resultadosBusqueda.size());
    	}
    }
}
