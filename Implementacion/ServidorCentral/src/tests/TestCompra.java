package tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import logica.DTCompra;
import logica.DTEmpresa;
import logica.DTHorario;
import logica.DTPaquete;
import logica.DTTipoPublicacion;
import logica.Fabrica;
import logica.ITipos;
import logica.IUsuario;
import logica.ManejadorPaquete;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;

public class TestCompra {
	private static Fabrica fab;
	private static IUsuario ctrlUsuario;
	private static ITipos ctrlTipos;
	
	@BeforeAll
	static void inicializar(){
		fab = Fabrica.getInstance();
		ctrlUsuario = fab.getIUsuario();
		ctrlTipos = fab.getITipos();
	}
	
	@BeforeEach
	void limpiar() {
		ManejadorPaquete mp = ManejadorPaquete.getInstance();
		mp.clear();
		ManejadorTipo mt = ManejadorTipo.getInstance();
		mt.clear();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		mu.clear();
	}
	
	@Test
	void compraPaquete() throws Exception {
		ctrlTipos.ingresarDatosTipoPublicacion("Deluxe", "Paquete economico para pequeñas empresas", 0, new Date(2023,4,12), 0, 0);
		ctrlTipos.ingresarDatosTipoPublicacion("Basico", "Paquete economico para pequeñas empresas", 0, new Date(2023,4,12), 0, 0);
		ctrlTipos.ingresarDatosPaquete("Paquete Basico", "Paquete de Tipo basico", 20, 0.5,  new Date(2021,5,10), "");
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", "Basico", 1);
		Map<String, DTPaquete> dataPaquetes = ctrlTipos.obtenerDataPaquetes();
		ctrlUsuario.ingresarEmpresa("darkest_originalus", "Jimmy", "Wong", "is_not_a_phase_mom@gmail.com", "darkest_originalus", "Descripcion empresa" , "www.google.com", "", "123abc");
		ctrlTipos.comprarPaquete("darkest_originalus", "Paquete Basico");
		ctrlUsuario.ingresarOferta("darkest_originalus", "Basico", "Oferta1", "Descripcion1", new DTHorario("09:00", "18:00"), 0, new Date(), "Montevideo", "Montevideo", new HashSet<String>(), "");
		assertTrue(ctrlTipos.listarPaquetesDisponiblesParaUsuarioYTipo("darkest_originalus", "Basico").contains("Paquete Basico"));
		ctrlTipos.canjearOfertaDePaquete("darkest_originalus", "Oferta1", "Paquete Basico");
		assertTrue(!ctrlTipos.listarPaquetesDisponiblesParaUsuarioYTipo("darkest_originalus", "Basico").contains("Paquete Basico"));
		
	}
	
	@Test
	void obtenerDatosCompraCasoRecienComprado() throws Exception  {
		// Datos de Tipos de publicacion de prueba
		DTTipoPublicacion tipoPublicacionA = new DTTipoPublicacion("Deluxe ultra Fashion",
				"Paquete para la crème de la crème", 5, 365, 100000, new Date(2023, 4, 12));
		DTTipoPublicacion tipoPublicacionB = new DTTipoPublicacion("Basico",
				"Paquete simple para las empresas que les gusta el helado de vainilla y comen ñoquis sin salsa", 0, 30,
				250, new Date(2022, 2, 05));
		
		// Ingresar tipos de publicacacion de prueba
		ctrlTipos.ingresarDatosTipoPublicacion(tipoPublicacionA.getNombre(), tipoPublicacionA.getDescripcion(),
				tipoPublicacionA.getExposicion(), tipoPublicacionA.getFecha(), tipoPublicacionA.getCosto(),
				(int) tipoPublicacionA.getDuracion());
		ctrlTipos.ingresarDatosTipoPublicacion(tipoPublicacionB.getNombre(), tipoPublicacionB.getDescripcion(),
				tipoPublicacionB.getExposicion(), tipoPublicacionB.getFecha(), tipoPublicacionB.getCosto(),
				(int) tipoPublicacionB.getDuracion());
		
		// Ingresar paquete y sus tipos de publicacion
		String nombreDelPaqueteA = "Paquete Basico";
		String imagenDelPaqueteA = "galleta_con_forma_de_ñoqui.jpg";
		ctrlTipos.ingresarDatosPaquete(nombreDelPaqueteA, "Paquete de Tipo basico", 20, 0.5, new Date(2021, 5, 10), imagenDelPaqueteA);
		int cantidadTipoPublicacionA = 1;
		int cantidadTipoPublicacionB = 10;
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", tipoPublicacionA.getNombre(), cantidadTipoPublicacionA);
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", tipoPublicacionB.getNombre(), cantidadTipoPublicacionB);
		
		// Datos empresa de prueba
		DTEmpresa datosEmpresaA = new DTEmpresa("nioqui_farmer", "cookie-nioqui-farmer@gmail.com", "Josüé",
				"Afk-kîng de Herrera", "Cooperativa Nacional de Productores Regionales de Ñoquis",
				"Más que una granja, más que una cooperativa, más que un país, una familia, una pasión",
				"www.galletasdenioqui.com.uy", null, "imagen-picosa.png", new HashSet<String>(), new HashSet<String>());
		
		// Ingresar empresa de prueba
		ctrlUsuario.ingresarEmpresa(datosEmpresaA.getNickname(), datosEmpresaA.getNombre(), datosEmpresaA.getApellido(),
				datosEmpresaA.getMail(), datosEmpresaA.getNombreEmpresa(), datosEmpresaA.getDescripcion(), datosEmpresaA.getLink());
		
		// Realizar compra del paquete de parte de la empresa
		ctrlTipos.comprarPaquete(datosEmpresaA.getNickname(), nombreDelPaqueteA);
		
		
		// Obtener datos de las compras de la empresa
		Set<DTCompra> datosDeCompras = ctrlTipos.obtenerDatosCompra(datosEmpresaA.getNickname());
		
		// En los datos de compras debe estar el paquete de prueba
		Iterator<DTCompra> iteradorDatosCompras= datosDeCompras.iterator();
		DTCompra primerElemento = iteradorDatosCompras.next();
		assertTrue(primerElemento.getComprador().equals(datosEmpresaA.getNickname()));
		assertTrue(primerElemento.getProducto().equals(nombreDelPaqueteA));
		assertTrue(primerElemento.getImagenPaquete().equals(imagenDelPaqueteA));
	}
	
	@Test
	void empresaNoPoseePaqueteQueNoCompro() throws Exception  {
		// Datos de Tipos de publicacion de prueba
		DTTipoPublicacion tipoPublicacionA = new DTTipoPublicacion("Deluxe ultra Fashion",
				"Paquete para la crème de la crème", 5, 365, 100000, new Date(2023, 4, 12));
		DTTipoPublicacion tipoPublicacionB = new DTTipoPublicacion("Basico",
				"Paquete simple para las empresas que les gusta el helado de vainilla y comen ñoquis sin salsa", 0, 30,
				250, new Date(2022, 2, 05));
		
		// Ingresar tipos de publicacacion de prueba
		ctrlTipos.ingresarDatosTipoPublicacion(tipoPublicacionA.getNombre(), tipoPublicacionA.getDescripcion(),
				tipoPublicacionA.getExposicion(), tipoPublicacionA.getFecha(), tipoPublicacionA.getCosto(),
				(int) tipoPublicacionA.getDuracion());
		ctrlTipos.ingresarDatosTipoPublicacion(tipoPublicacionB.getNombre(), tipoPublicacionB.getDescripcion(),
				tipoPublicacionB.getExposicion(), tipoPublicacionB.getFecha(), tipoPublicacionB.getCosto(),
				(int) tipoPublicacionB.getDuracion());
		
		// Ingresar paquete y sus tipos de publicacion
		String nombreDelPaqueteA = "Paquete Basico";
		String imagenDelPaqueteA = "galleta_con_forma_de_ñoqui.jpg";
		ctrlTipos.ingresarDatosPaquete(nombreDelPaqueteA, "Paquete de Tipo basico", 20, 0.5, new Date(2021, 5, 10), imagenDelPaqueteA);
		int cantidadTipoPublicacionA = 1;
		int cantidadTipoPublicacionB = 10;
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", tipoPublicacionA.getNombre(), cantidadTipoPublicacionA);
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", tipoPublicacionB.getNombre(), cantidadTipoPublicacionB);
		
		// Datos empresa de prueba
		DTEmpresa datosEmpresaA = new DTEmpresa("nioqui_farmer", "cookie-nioqui-farmer@gmail.com", "Josüé",
				"Afk-kîng de Herrera", "Cooperativa Nacional de Productores Regionales de Ñoquis",
				"Más que una granja, más que una cooperativa, más que un país, una familia, una pasión",
				"www.galletasdenioqui.com.uy", null, "imagen-picosa.png", new HashSet<String>(), new HashSet<String>());
		
		// Ingresar empresa de prueba
		ctrlUsuario.ingresarEmpresa(datosEmpresaA.getNickname(), datosEmpresaA.getNombre(), datosEmpresaA.getApellido(),
				datosEmpresaA.getMail(), datosEmpresaA.getNombreEmpresa(), datosEmpresaA.getDescripcion(), datosEmpresaA.getLink());
		
		assertTrue(!ctrlTipos.empresaPoseePaquete(datosEmpresaA.getNickname(), nombreDelPaqueteA));
	}

	@Test
	void empresaPoseePaqueteQueCompro() throws Exception  {
		// Datos de Tipos de publicacion de prueba
		DTTipoPublicacion tipoPublicacionA = new DTTipoPublicacion("Deluxe ultra Fashion",
				"Paquete para la crème de la crème", 5, 365, 100000, new Date(2023, 4, 12));
		DTTipoPublicacion tipoPublicacionB = new DTTipoPublicacion("Basico",
				"Paquete simple para las empresas que les gusta el helado de vainilla y comen ñoquis sin salsa", 0, 30,
				250, new Date(2022, 2, 05));
		
		// Ingresar tipos de publicacacion de prueba
		ctrlTipos.ingresarDatosTipoPublicacion(tipoPublicacionA.getNombre(), tipoPublicacionA.getDescripcion(),
				tipoPublicacionA.getExposicion(), tipoPublicacionA.getFecha(), tipoPublicacionA.getCosto(),
				(int) tipoPublicacionA.getDuracion());
		ctrlTipos.ingresarDatosTipoPublicacion(tipoPublicacionB.getNombre(), tipoPublicacionB.getDescripcion(),
				tipoPublicacionB.getExposicion(), tipoPublicacionB.getFecha(), tipoPublicacionB.getCosto(),
				(int) tipoPublicacionB.getDuracion());
		
		// Ingresar paquete y sus tipos de publicacion
		String nombreDelPaqueteA = "Paquete Basico";
		String imagenDelPaqueteA = "galleta_con_forma_de_ñoqui.jpg";
		ctrlTipos.ingresarDatosPaquete(nombreDelPaqueteA, "Paquete de Tipo basico", 20, 0.5, new Date(2021, 5, 10), imagenDelPaqueteA);
		int cantidadTipoPublicacionA = 1;
		int cantidadTipoPublicacionB = 10;
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", tipoPublicacionA.getNombre(), cantidadTipoPublicacionA);
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", tipoPublicacionB.getNombre(), cantidadTipoPublicacionB);
		
		// Datos empresa de prueba
		DTEmpresa datosEmpresaA = new DTEmpresa("nioqui_farmer", "cookie-nioqui-farmer@gmail.com", "Josüé",
				"Afk-kîng de Herrera", "Cooperativa Nacional de Productores Regionales de Ñoquis",
				"Más que una granja, más que una cooperativa, más que un país, una familia, una pasión",
				"www.galletasdenioqui.com.uy", null, "imagen-picosa.png", new HashSet<String>(), new HashSet<String>());
		
		// Ingresar empresa de prueba
		ctrlUsuario.ingresarEmpresa(datosEmpresaA.getNickname(), datosEmpresaA.getNombre(), datosEmpresaA.getApellido(),
				datosEmpresaA.getMail(), datosEmpresaA.getNombreEmpresa(), datosEmpresaA.getDescripcion(), datosEmpresaA.getLink());
		
		// Realizar compra del paquete de parte de la empresa
		ctrlTipos.comprarPaquete(datosEmpresaA.getNickname(), nombreDelPaqueteA);
		

		assertTrue(ctrlTipos.empresaPoseePaquete(datosEmpresaA.getNickname(), nombreDelPaqueteA));
	}
	
	@Test
	void obtenerDatosDePaquenteNoExistenteRetornaNull() {
		assertTrue(null == ctrlTipos.obtenerDatosPaquete("El paquete de Erwin Schrödinger"));
	}

	@Test
	void obtenerDatosDePaquenteRetornaLosValoresCorrectos() throws Exception  {
		// Datos de Tipos de publicacion de prueba
		DTTipoPublicacion tipoPublicacionA = new DTTipoPublicacion("Deluxe ultra Fashion",
				"Paquete para la crème de la crème", 5, 365, 100000, new Date(2023, 4, 12));
		DTTipoPublicacion tipoPublicacionB = new DTTipoPublicacion("Basico",
				"Paquete simple para las empresas que les gusta el helado de vainilla y comen ñoquis sin salsa", 0, 30,
				250, new Date(2022, 2, 05));
		
		// Ingresar tipos de publicacacion de prueba
		ctrlTipos.ingresarDatosTipoPublicacion(tipoPublicacionA.getNombre(), tipoPublicacionA.getDescripcion(),
				tipoPublicacionA.getExposicion(), tipoPublicacionA.getFecha(), tipoPublicacionA.getCosto(),
				(int) tipoPublicacionA.getDuracion());
		ctrlTipos.ingresarDatosTipoPublicacion(tipoPublicacionB.getNombre(), tipoPublicacionB.getDescripcion(),
				tipoPublicacionB.getExposicion(), tipoPublicacionB.getFecha(), tipoPublicacionB.getCosto(),
				(int) tipoPublicacionB.getDuracion());
		
		// Ingresar paquete y sus tipos de publicacion
		String nombreDelPaqueteA = "Paquete Basico";
		String descripcionDelPaqueteA = "Paquete de Tipo basico";
		int validezDelPaqueteA = 20;
		double descuentoDelPaqueteA = 0.5;
		Date fechaAltaDelPaqueteA = new Date(2021, 5, 10);
		String imagenDelPaqueteA = "galleta_con_forma_de_ñoqui.jpg";
		ctrlTipos.ingresarDatosPaquete(
				nombreDelPaqueteA,
				descripcionDelPaqueteA,
				20,
				0.5,
				fechaAltaDelPaqueteA,
				imagenDelPaqueteA
				);
		int cantidadTipoPublicacionA = 1;
		int cantidadTipoPublicacionB = 10;
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", tipoPublicacionA.getNombre(), cantidadTipoPublicacionA);
		ctrlTipos.agregarTipoPublicacion("Paquete Basico", tipoPublicacionB.getNombre(), cantidadTipoPublicacionB);
		
		DTPaquete datosPaquetesDePrueba = ctrlTipos.obtenerDatosPaquete(nombreDelPaqueteA);
		
		assertTrue(datosPaquetesDePrueba.getNombre() == nombreDelPaqueteA);
		assertTrue(datosPaquetesDePrueba.getDescripcion() == descripcionDelPaqueteA);
		assertTrue(datosPaquetesDePrueba.getPeriodoValidez() == validezDelPaqueteA);
		assertTrue(datosPaquetesDePrueba.getDescuento() == descuentoDelPaqueteA);
		assertTrue(datosPaquetesDePrueba.getFecha() == fechaAltaDelPaqueteA);
		assertTrue(datosPaquetesDePrueba.getImagen() == imagenDelPaqueteA);
	}
}
