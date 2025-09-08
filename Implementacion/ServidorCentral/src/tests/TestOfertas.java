package tests;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import excepciones.noExisteOferta;
import logica.DTHorario;
import logica.DTOfertaLaboral;
import logica.Empresa;
import logica.Postulante;
import logica.Estado;
import logica.Fabrica;
import logica.IUsuario;
import logica.ManejadorOferta;
import logica.ManejadorTipo;
import logica.ManejadorUsuario;
import logica.OfertaLaboral;
import logica.Postulacion;
import logica.TipoPublicacion;
import logica.DAO.*;

public class TestOfertas {
	private static Fabrica fab;
	private static IUsuario cu;
	@BeforeAll
	static void limpiarOfertas() throws Exception{
		
		fab = Fabrica.getInstance();
		cu = fab.getIUsuario();
		ManejadorTipo mt = ManejadorTipo.getInstance();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		ManejadorOferta manejadorO = ManejadorOferta.getInstancia();
		manejadorO.clear();
		TipoPublicacion tipo1 = new TipoPublicacion("Tipo Prueba", "Este tipo es bueno", 2, new Date(2023,8,5), 500, 5);
		mt.agregar(tipo1);
		Empresa emp1 = new Empresa("emPrueba", "prueba@fing.com", "Roberto", "Bedelias", "Empresa Prueba", "Una empresa bien loca", null);
		Postulante post1 = new Postulante("postPrueba", "asd@fing.com","Juan","Perez", "uruguaya",new Date(1990,5,3));
		mu.agregarUsuario(emp1);
		mu.agregarUsuario(post1);
		Set<String> setk = new HashSet<>(Arrays.asList("K1", "K2", "K3"));
		cu.ingresarOferta("emPrueba", "Tipo Prueba", "OfertaDos", "Tremenda oferta", new DTHorario("10:30","16:15"), 0, new Date (123,8,8), "La Paloma", "Rocha", setk, "");
	}

	@SuppressWarnings("deprecation")
	@Test
	//Test finalizar oferta
	void FinalizarOferta() throws Exception {
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		ManejadorTipo mt = ManejadorTipo.getInstance();
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		
		Set<String> setk = new HashSet<>(Arrays.asList("K1", "K2", "K3"));
		Boolean exito = cu.ingresarOferta("emPrueba", "Tipo Prueba", "Oferta Test", "Tremenda oferta", new DTHorario("10:30","16:15"), 0, new Date (123,8,8), "La Paloma", "Rocha", setk, "");
		cu.aceptarRechazarOferta("Oferta Test", Estado.Confirmado);
		Postulante post1 = (Postulante)mu.getUsuario("postPrueba");
		post1.postularAOferta(mo.obtenerOferta("Oferta Test"), new Date(), "cv prueba", "motivacion prueba", "");
		cu.finalizarOferta("Oferta Test");
		Assertions.assertTrue(cu.existeUsuario("emPrueba"));
		Assertions.assertTrue(cu.existeEmail("prueba@fing.com"));
		Assertions.assertTrue(exito);
		Assertions.assertTrue(!mo.existeOferta("Oferta Test"));
	}
	
	@SuppressWarnings("deprecation")
	@Test
	//Test persistencia correcta
	void ObtenerPersistencia() throws noExisteOferta{
		List<OfertaDAO> lista = cu.ObtenerOfertasFinalizadas("emPrueba");
		OfertaDAO ofertaFin = null;
		ofertaFin = cu.ObtenerUnaOfertaFinalizada("Oferta Test");
		List<PostulacionDAO> postOferta = cu.ObtenerPostulacionesOferta("Oferta Test");
		List<PostulacionDAO> postUsuario = cu.ObtenerPostulacionesUsuario("postPrueba");
		PostulacionDAO postulacion = null;
		postulacion = cu.ObtenerUnaPostulacionUsuario("postPrueba", "Oferta Test");
		Assertions.assertTrue(lista.size()>0);
		Assertions.assertTrue(postOferta.size()>0);
		Assertions.assertTrue(postUsuario.size()>0);
		Assertions.assertTrue(ofertaFin != null);
		Assertions.assertTrue(postulacion != null);
	}
	
	@Test
	//Test persistencia incorrecta
	void obtenerFinalizadasError(){
		List<OfertaDAO> lista = null;
		try {
			lista = cu.ObtenerOfertasFinalizadas("NoExiste");
		}catch(noExisteOferta ex) {
			
		}
		Assertions.assertTrue(lista.size()==0);
	}
	@Test
	//Test persistencia incorrecta
	void obtenerUnaFinalizadaError(){
		Assertions.assertThrows(noExisteOferta.class , ()->cu.ObtenerUnaOfertaFinalizada("NoExiste"));
	}
	@Test
	//Test persistencia incorrecta
	void obtenerPostulacionOfertaError(){
		List<PostulacionDAO> postOferta = null;
		try {
			postOferta = cu.ObtenerPostulacionesOferta("NoExiste");
		}catch(noExisteOferta ex) {
			
		}
		Assertions.assertTrue(postOferta.size()==0);		
	}
	@Test
	//Test persistencia incorrecta
	void obtenerPostulacionUsuarioError(){

		List<PostulacionDAO> postUsuario = null;
		try {
			postUsuario = cu.ObtenerPostulacionesUsuario("NoExiste");
		}catch(noExisteOferta ex) {

		}
		Assertions.assertTrue(postUsuario.size()==0);

	}
	@Test
	//Test persistencia incorrecta
	void obtenerUnaPostulacionError(){
		Assertions.assertThrows(noExisteOferta.class , ()->cu.ObtenerUnaPostulacionUsuario("postPrueba", "noExiste"));
	}
	@Test
	//quitarOfertaFavorita
	void quitarOfertaFavorita() throws Exception{
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		cu.agregarOfertaFavorita("postPrueba", "OfertaDos");
		Postulante post = (Postulante) mu.getUsuario("postPrueba");
		Assertions.assertTrue(post.esOfertaFavorita("OfertaDos"));
		cu.agregarOfertaFavorita("postPrueba", "OfertaDos");
		Assertions.assertFalse(post.esOfertaFavorita("OfertaDos"));
	}
	
	@Test 
	//obtenerMasVisitadas
	void obtenerMasVisitadas() {
		ArrayList<DTOfertaLaboral> lista = null;
		for(int i = 1; i<4; i++) {
			cu.agregarVisita("OfertaDos");
		}
		lista = cu.obtenerMasVisitadas();
		Assertions.assertTrue(lista.get(0).getVisitas() == 3);
	}
	@Test
	//Test elegirOrden
	void ElegirOrden() {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		ManejadorOferta mo = ManejadorOferta.getInstancia();
		OfertaLaboral of = mo.obtenerOferta("OfertaDos");
		Postulante post = (Postulante) mu.getUsuario("postPrueba");
		post.postularAOferta(of, new Date(), "cv vacio", "una descr", "");
		cu.elegirOrden("postPrueba", "OfertaDos", 1);
		Postulacion postulacion = post.getPostulaciones().get("OfertaDos");
		Assertions.assertTrue(postulacion.getOrden() == 1);
	}
	@Test
	//test de seguidores y seguidos
	void seguidores() {
		ManejadorUsuario mu = ManejadorUsuario.getInstance();
		Empresa emp = (Empresa)mu.getUsuario("emPrueba");
		Postulante post = (Postulante)mu.getUsuario("postPrueba");
		cu.follow("emPrueba", "postPrueba");
		Assertions.assertTrue(emp.getSeguidos().containsKey(post.getNickname()));
		Assertions.assertTrue(post.getSeguidores().containsKey(emp.getNickname()));
		Assertions.assertTrue(cu.esSeguidor("emPrueba", "postPrueba"));
		cu.unfollow("emPrueba", "postPrueba");
		Assertions.assertTrue(!emp.getSeguidos().containsKey(post.getNickname()));
		Assertions.assertTrue(!post.getSeguidores().containsKey(emp.getNickname()));
	}
}
