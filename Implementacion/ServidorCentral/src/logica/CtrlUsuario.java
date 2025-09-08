package logica;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import excepciones.PostulanteRepetido;
import excepciones.noExisteOferta;
import excepciones.nombreOfertaRepetido;
import jakarta.persistence.*;
import logica.DAO.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;


public class CtrlUsuario implements IUsuario {
	

	public void ingresarDatosPostulacion(String postulante, String curriculum, String motivacion, String oferta, Date fecha, String video) throws PostulanteRepetido {
		ManejadorOferta manejadorOf = ManejadorOferta.getInstancia();
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		OfertaLaboral ofertaLab = manejadorOf.obtenerOferta(oferta);
		if (ofertaLab != null) {
			if (ofertaLab.existePostulante(postulante)) {
				throw new PostulanteRepetido("El usuario " + postulante + " ya se encuentra postulado a la oferta laboral seleccionada");
			}else {
				((Postulante) manejadorU.getUsuario(postulante)).postularAOferta(ofertaLab, fecha, curriculum, motivacion,video);
			}
		}
	}

	public ArrayList<String> listarUsuarios(){
		ManejadorUsuario manejador=ManejadorUsuario.getInstance();
		return (ArrayList<String>) manejador.getNicknames();
	}
	
	public void editarDatosBasicos(String nickname, String email, String nombre, String apellido, String nacionalidad, Date fecha, String descripcion, String link) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Usuario user = manejadorU.getUsuario(nickname);
		user.setNombre(nombre);
		user.setApellido(apellido);
		if (user instanceof Postulante) {
			((Postulante) user).setNacionalidad(nacionalidad);
			((Postulante) user).setFechaNacimiento(fecha);
		} else if (user instanceof Empresa) {
			((Empresa) user).setDescripcion(descripcion);
			((Empresa) user).setLink(link);
		}
	}
	
	public void editarDatosBasicos(String nickname, String email, String pass, String nombre, String apellido, String nacionalidad, Date fecha, String descripcion, String link, String imagen) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Usuario user = manejadorU.getUsuario(nickname);
		if (!nombre.equals(""))user.setNombre(nombre);
		if (!apellido.equals("")) user.setApellido(apellido);
		if (!pass.equals("")) user.setPass(pass);
		if (!imagen.equals("")) user.setImagen(imagen);
		if (user instanceof Postulante) {
			if (!nacionalidad.equals("")) ((Postulante) user).setNacionalidad(nacionalidad);
			if (fecha != null) ((Postulante) user).setFechaNacimiento(fecha);
		} else if (user instanceof Empresa) {
			if (!descripcion.equals("")) ((Empresa) user).setDescripcion(descripcion);
			((Empresa) user).setLink(link);
		}
	}
	
	//Solo recibe nombre de postulante
	public DTPostulante getDataPostulante(String nombre) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Postulante user = (Postulante) manejadorU.getUsuario(nombre);
		DTPostulante dataPostulante = new DTPostulante(user.getNickname(), user.getMail(), user.getNombre(), user.getApellido(),
				user.getNacionalidad(), user.getFechaNacimiento(), user.getPostulaciones().keySet(), user.getImagen(), user.getPostulaciones(), new HashSet<String>(user.getSeguidos().keySet()), new HashSet<String>(user.getSeguidores().keySet()), user.getOfertasFavoritas());
		return dataPostulante;
	}

	
	//Solo recibe nombre de empresa
	public DTEmpresa getDataEmpresa(String nombre) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Empresa user = (Empresa) manejadorU.getUsuario(nombre);
		DTEmpresa dataEmpresa = new DTEmpresa(user.getNickname(), user.getMail(), user.getNombre(), user.getApellido(),
				user.getNombreEmpresa(), user.getDescripcion(), user.getLink(), user.getOfertas().keySet(), user.getImagen(), new HashSet<String>(user.getSeguidos().keySet()), new HashSet<String>(user.getSeguidores().keySet()));
		return dataEmpresa;
	}
	public Boolean existeUsuario(String nickname) {
		ManejadorUsuario mUsuario = ManejadorUsuario.getInstance();
		return mUsuario.existeUsuario(nickname);
	}
	public Boolean existeEmail(String email) {
		ManejadorUsuario mUsuario = ManejadorUsuario.getInstance();
		return mUsuario.existeEmailDeUsuario(email);
	}
	public DTUsuario mostrarDatosUsuario(String nickname) {
		ManejadorUsuario manejador=ManejadorUsuario.getInstance();
		Usuario user= manejador.getUsuario(nickname);
		if (user == null) user = manejador.getUsuarioPorMail(nickname);
		DTUsuario datosUsuario;
		ManejadorOferta manejadorOf= ManejadorOferta.getInstancia();
		Map<String, OfertaLaboral> ofertasLaborales= manejadorOf.getOfertas();
		if (user instanceof Empresa) {
			Empresa emp= (Empresa) user;
			Set<String> nombreOfertasEmpresa= emp.getOfertas().keySet();
			Set<String> compras = emp.getCompras().keySet();
			datosUsuario=new DTEmpresa(emp.getNickname(), emp.getMail(), emp.getNombre(), emp.getApellido(), emp.getNombreEmpresa(), emp.getDescripcion(),
					emp.getLink(), nombreOfertasEmpresa, emp.getImagen(), compras, new HashSet<String>(emp.getSeguidos().keySet()), new HashSet<String>(emp.getSeguidores().keySet()));
		} else {
			Postulante post= (Postulante) user;
			Map<String, Postulacion> postulaciones = post.getPostulaciones();
			Set<String> nombreOfertasPostulante= post.getPostulaciones().keySet();
			datosUsuario=new DTPostulante(post.getNickname(), post.getMail(), post.getNombre(), post.getApellido(), post.getNacionalidad(),
					post.getFechaNacimiento(), nombreOfertasPostulante, post.getImagen(), postulaciones, new HashSet<String>(post.getSeguidos().keySet()), new HashSet<String>(post.getSeguidores().keySet()), post.getOfertasFavoritas());
		}
		return datosUsuario;
	}

	public DTOfertaLaboral seleccionarOfertaLaboral(String nombre) throws noExisteOferta {
		ManejadorOferta manejadorOf= ManejadorOferta.getInstancia();
		OfertaLaboral oferta = manejadorOf.obtenerOferta(nombre);
		if (oferta==null) throw new noExisteOferta("No existe una oferta laboral de nombre: "+nombre);
		String nombreTipo=oferta.getTipo().getNombre();
		Map<String, Postulante> postulantes= oferta.getPostulantes();
		Set<DTPostulacion> postulaciones = new HashSet<>();
		
		Iterator<String> iter = postulantes.keySet().iterator();
		while (iter.hasNext()){
		    String clave = iter.next();
		    Postulante post = postulantes.get(clave);
		    if (post.getPostulaciones().containsKey(nombre)) {
		    	Postulacion postulacion = post.getPostulaciones().get(nombre);
		    	DTPostulacion dataPostulacion= new DTPostulacion(postulacion.getFechaPostulacion(), postulacion.getResumenCV(), postulacion.getDescripcion(), nombre, post.getNickname(),postulacion.getVideo(),postulacion.getOrden(),postulacion.getFechaOrden(), oferta.getImagen());
				postulaciones.add(dataPostulacion);
		    }
		}
		Date fecha=oferta.getFecha();

		LocalDate fechaAlta=fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		String nombrePaquete= oferta.getCompra()!=null? oferta.getCompra().getNombre(): null;
		DTOfertaLaboral dataOferta= new DTOfertaLaboral(oferta.getNombre(), oferta.getPublicante().getNickname() , oferta.getDescripcion(),
				oferta.getCiudad(), oferta.getDepartamento(), oferta.getHorario(), oferta.getRemuneracion(), fecha, nombreTipo, oferta.getKeywords(), postulaciones, oferta.getCosto(), oferta.getEstado(), nombrePaquete, oferta.getImagen(), fechaAlta.plusDays(oferta.getTipo().getDuracion()),oferta.getCantFavoritos());
		return dataOferta;
	}
	public Set<String> listarEmpresas(){
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		return manejadorU.obtenerEmpresas();
	}
	public Set<String> listarPostulantes(){
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		return manejadorU.obtenerPostulantes();
	}
	public Set<String> listarTiposDePublicacion(){
		ManejadorTipo manejadorT = ManejadorTipo.getInstance();
		return manejadorT.obtenerTipos();
	}
	
	public Boolean ingresarOferta(String nickname, String nombreTipo, String nombre, String descripcion, DTHorario horario, int remuneracion, Date fecha, String ciudad, String departamento, Set<String> keyword, String imagen) throws nombreOfertaRepetido {
		ManejadorOferta manejadorO = ManejadorOferta.getInstancia();
		Boolean res = !manejadorO.existeOferta(nombre);
		
		if (res) {
			ManejadorTipo manejadorT = ManejadorTipo.getInstance();
			TipoPublicacion tipo = manejadorT.obtenerTipo(nombreTipo);
			ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
			Empresa emp = (Empresa) manejadorU.getUsuario(nickname);
			OfertaLaboral oferta = new OfertaLaboral(nombre, emp, descripcion, ciudad, departamento, horario, remuneracion, fecha, tipo, keyword, imagen);
			emp.agregarOferta(oferta);
			manejadorO.agregarOferta(oferta);
		}
		else {
			throw new nombreOfertaRepetido("Ya existe una oferta laboral llamada: "+nombre);
		}
		return res;
	}
	public Set<String> listarKeywords(){
		ManejadorKeywords manejadorKey = ManejadorKeywords.getInstance();
		return manejadorKey.obtenerKeywords();
	}
	public Set<String> obtenerOfertasDeEmpresa(String empresa){
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		if (manejadorU.existeUsuario(empresa)) {
			return ((Empresa) manejadorU.getUsuario(empresa)).getNombreOfertas();
		}else {
			return new HashSet<String>();
		}
	}
	
	private void checkUnicidad(String email, String nick) throws Exception {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		if (manejadorUsuario.existeEmailDeUsuario(email)) {
			throw new Exception("Correo electrónico ya existe");
		}
		if (manejadorUsuario.existeUsuario(nick)) {
			throw new Exception("Nickname ya existe");
		}
	}
	
	public void ingresarPostulante(String nickName, String nombre, String apellido, String correoElectronico,
		Date fechaNacimiento, String nacionalidad) throws Exception {
		
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		checkUnicidad(correoElectronico, nickName);
		manejadorUsuario.agregarUsuario(new Postulante(nickName, correoElectronico, nombre, apellido, nacionalidad, fechaNacimiento));
	}
	
	public void ingresarPostulante(String nickName, String nombre, String apellido, String correoElectronico,
			Date fechaNacimiento, String nacionalidad, String imagen, String pass) throws Exception {
			
			ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
			checkUnicidad(correoElectronico, nickName);
			manejadorUsuario.agregarUsuario(new Postulante(nickName, correoElectronico, nombre, apellido, nacionalidad, fechaNacimiento, imagen, pass));
		}
	
	public void ingresarEmpresa(String nickName, String nombre, String apellido, String correoElectronico, String nombreEmpresa, String descripcion,
		String link) throws Exception {
		
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		checkUnicidad(correoElectronico, nickName);
		manejadorUsuario.agregarUsuario(new Empresa(nickName, correoElectronico, nombre, apellido, nombreEmpresa, descripcion, link));
	}
	
	public void ingresarEmpresa(String nickName, String nombre, String apellido, String correoElectronico, String nombreEmpresa, String descripcion,
			String link, String imagen, String pass) throws Exception {
			
			ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
			checkUnicidad(correoElectronico, nickName);
			manejadorUsuario.agregarUsuario(new Empresa(nickName, correoElectronico, nombre, apellido, nombreEmpresa, descripcion, link, imagen, pass));
		}
	
	public Set<DTOfertaLaboral> listarOfertasLaborales(){
		ManejadorOferta manejadorOfertas= ManejadorOferta.getInstancia();
		Map<String, OfertaLaboral> ofertas= manejadorOfertas.getOfertas();
		Set<String> nombreOfertas= ofertas.keySet();
		
		Set<DTOfertaLaboral> dataOfertas= new HashSet<DTOfertaLaboral>();
		for (String nombre:nombreOfertas) {
			try {
				dataOfertas.add(seleccionarOfertaLaboral(nombre));
			} catch (noExisteOferta e) {
				e.printStackTrace();
			}
		}
		return dataOfertas;
	}
	public Set<DTOfertaLaboral> listarOfertasPorKeyword(String keyword){
		ManejadorOferta manejadorOfertas= ManejadorOferta.getInstancia();
		Map<String, OfertaLaboral> mapOfertas= manejadorOfertas.getOfertas();
		Collection<OfertaLaboral> ofertas=mapOfertas.values();
		Set<DTOfertaLaboral> dataOfertas= new HashSet<DTOfertaLaboral>();
		for (OfertaLaboral oferta:ofertas) {
			if (oferta.getKeywords().contains(keyword)) {
				try {
					dataOfertas.add(seleccionarOfertaLaboral(oferta.getNombre()));
				} catch (noExisteOferta e) {
					e.printStackTrace();
				}
			}
		}
		return dataOfertas;
	}
	public DTUsuario iniciarSesion(String nickname, String pass) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Usuario user = manejadorU.getUsuario(nickname);
		if (user == null) {
			user = manejadorU.getUsuarioPorMail(nickname);
		}
		DTUsuario datosUsuario = null;
		if (user != null) {
			if (user.getPass().equals(pass)) {
				datosUsuario = mostrarDatosUsuario(nickname);
			}
		}
		return datosUsuario;
	}
	public Set<String> listarOfertasIngresadas(String nickEmpresa) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Empresa empresa = (Empresa) manejadorU.getUsuario(nickEmpresa);
		Set<String> ofertasIngresadas = new HashSet<String>();
		Collection<OfertaLaboral> ofertas = empresa.getOfertas().values();
		for (OfertaLaboral oferta:ofertas) {
			if (oferta.getEstado()==Estado.Ingresado) {
				ofertasIngresadas.add(oferta.getNombre());
			}
		}
		return ofertasIngresadas;
	}
	public void aceptarRechazarOferta(String nomOferta, Estado cambio) {
		ManejadorOferta manejadorOf = ManejadorOferta.getInstancia();
		OfertaLaboral oferta = manejadorOf.obtenerOferta(nomOferta);
		oferta.setEstado(cambio);
	}
	
	public void finalizarOferta(String nomOferta) {
		ManejadorOferta manejadorOf = ManejadorOferta.getInstancia();
		OfertaLaboral oferta = manejadorOf.obtenerOferta(nomOferta);
		
		Date fechaBaja= new Date();
		
    	EntityManagerFactory entitymf = null;
    	entitymf = Persistence.createEntityManagerFactory("TrabajouyDB");
    	final EntityManager entityManager = entitymf.createEntityManager();
    	EntityTransaction transaction = entityManager.getTransaction();
    	Map<String,Postulante> postulantes= oferta.getPostulantes();
    	//persistir
    	try {
    		transaction.begin();
    		String nombreEmpresa= oferta.getPublicante().getNickname();
    		TypedQuery<EmpresaDAO> typedQueryE = entityManager.createQuery("SELECT e FROM EmpresaDAO e WHERE e.nickname = :id", EmpresaDAO.class);
    		typedQueryE.setParameter("id", nombreEmpresa);
    		List<EmpresaDAO> results=typedQueryE.getResultList();
    		EmpresaDAO empresaAPersistir;
    		if(results.isEmpty()) {
    			empresaAPersistir =new EmpresaDAO(oferta.getPublicante());
    		}
    		else {
    			empresaAPersistir= results.get(0);
    		}
    		OfertaDAO ofertaAPersistir= new OfertaDAO(oferta, empresaAPersistir, fechaBaja);
    		List<PostulacionDAO> postulacionesAPersistir= new ArrayList<PostulacionDAO>();
    		List<PostulanteDAO> postulantesAPersistir= new ArrayList<PostulanteDAO>();
    		
    		postulantes.values().forEach((Postulante postulante)->{
    			TypedQuery<PostulanteDAO> typedQueryP = entityManager.createQuery("SELECT p FROM PostulanteDAO p", PostulanteDAO.class);
        		List<PostulanteDAO> resultsP=typedQueryP.getResultList();
        		PostulanteDAO postDAO= null;
        		int i = 0;
        		while(i<resultsP.size()) {
        			if(resultsP.get(i).getNickname().equals(postulante.getNickname())) {
        				postDAO= resultsP.get(i);
        				i=resultsP.size();
        			}
        			i++;
        		}
    			postDAO= postDAO==null? new PostulanteDAO(postulante): postDAO;
    			postulantesAPersistir.add(postDAO);
    			postulacionesAPersistir.add(new PostulacionDAO(postulante.getPostulaciones().get(nomOferta),  postDAO, ofertaAPersistir));	
    		});
    		
    		entityManager.persist(ofertaAPersistir);
    		for(PostulacionDAO postus: postulacionesAPersistir) {
    			entityManager.persist(postus);
    		}
	    	transaction.commit();
    	}catch(Exception e) {
    		e.printStackTrace();
    		entityManager.getTransaction().rollback();
    	}finally {
    		entityManager.close();
    		entitymf.close();
    	}
    	
    	//remover oferta
    	ManejadorUsuario manejadorUsuario= ManejadorUsuario.getInstance();
    	Set<String> nickPostulantes =manejadorUsuario.obtenerPostulantes();
    	for(String nick:nickPostulantes) {
    		Postulante postu= (Postulante) manejadorUsuario.getUsuario(nick);
    		postu.quitarFavorito(nomOferta);
    	}
		
		for (Postulante postulante: postulantes.values()) postulante.removerPostulacion(nomOferta);
		manejadorOf.removerOferta(nomOferta);
		oferta.getPublicante().removerOferta(nomOferta);
	}
	
	public void elegirOrden(String nomPostulante,String nomOferta,int Orden) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Postulante actual = (Postulante) manejadorU.getUsuario(nomPostulante);
		Postulacion post =actual.getPostulaciones().get(nomOferta);
		post.setOrden(Orden);
		post.setFechaOrden(LocalDate.now());
	}
	
	public void follow(String seguidor, String seguir) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Usuario userSeguidor = manejadorU.getUsuario(seguidor);
		Usuario userSeguir = manejadorU.getUsuario(seguir);
		userSeguidor.addSeguido(userSeguir);
		userSeguir.addSeguidor(userSeguidor);
	}
	
	public void unfollow(String seguidor, String seguir) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		Usuario userSeguidor = manejadorU.getUsuario(seguidor);
		Usuario userSeguir = manejadorU.getUsuario(seguir);
		userSeguidor.borrarSeguido(seguir);
		userSeguir.borrarSeguidor(seguidor);
	}
	
	public boolean esSeguidor(String seguidor, String seguir) {
		ManejadorUsuario manejadorU = ManejadorUsuario.getInstance();
		return manejadorU.getUsuario(seguidor).getSeguidos().containsKey(seguir);
	}
	
	public void agregarVisita(String nomOferta) {
		ManejadorOferta manejadorO = ManejadorOferta.getInstancia();
		manejadorO.obtenerOferta(nomOferta).agregarVisita();
	}
	
	public ArrayList<DTOfertaLaboral> obtenerMasVisitadas() {
		ManejadorOferta manejadorO = ManejadorOferta.getInstancia();
		Map<String,OfertaLaboral> ofertas = manejadorO.getOfertas();
		ArrayList<OfertaLaboral> ofertasOrdenadas = new ArrayList<OfertaLaboral>(ofertas.values());
		  ofertasOrdenadas.sort(Comparator.comparing(OfertaLaboral::getVisitas).reversed());
		  ArrayList<DTOfertaLaboral> res = new ArrayList<DTOfertaLaboral>();
		  int i=0;
		  for (OfertaLaboral ofer:ofertasOrdenadas) {
			  DTOfertaLaboral dtOferta = new DTOfertaLaboral();
			  dtOferta.setNombre(ofer.getNombre());
			  dtOferta.setNombreEmpresa(ofer.getPublicante().getNickname());
			  dtOferta.setTipo(ofer.getTipo().getNombre());
			  dtOferta.setVisitas(ofer.getVisitas());
			  res.add(i, dtOferta);
			  i++;
			  if (i==5) {
				  break;
			  }
		  }
		  return res;
	}
	public List<OfertaDAO> ObtenerOfertasFinalizadas(String nickname)throws noExisteOferta{
		EntityManagerFactory entitymf = null;
	    EntityManager entityManager = null;
	    List<OfertaDAO> listaOfertas = new ArrayList<OfertaDAO>();
		try {
	    	entitymf = Persistence.createEntityManagerFactory("TrabajouyDB");
	    	entityManager = entitymf.createEntityManager();
	    	String nomParametro = "nick";
	    	TypedQuery<OfertaDAO> typedQ = entityManager.createQuery("SELECT o FROM OfertaDAO o WHERE o.empresa.nickname = :" + nomParametro, OfertaDAO.class);
	    	typedQ.setParameter(nomParametro, nickname);
	    	listaOfertas = typedQ.getResultList();
		}catch(Exception ex) {
			throw new noExisteOferta("No existen ofertas de la empresa: "+ nickname);
		}finally {
			entityManager.close();
    		entitymf.close();
		}
		return listaOfertas;
	}
	public OfertaDAO ObtenerUnaOfertaFinalizada(String nombre)throws noExisteOferta{
		EntityManagerFactory entitymf = null;
	    EntityManager entityManager = null;
	    OfertaDAO oferta = null;
	    try {
	    	entitymf = Persistence.createEntityManagerFactory("TrabajouyDB");
	    	entityManager = entitymf.createEntityManager();
	    	String parametro = "nombre";
	    	Query qry = entityManager.createQuery("SELECT o FROM OfertaDAO o WHERE o.nombreOferta = :" + parametro);
	    	qry.setParameter(parametro, nombre);
	    	oferta = (OfertaDAO) qry.getSingleResult();
	    }catch(Exception ex) {
	    	throw new noExisteOferta("No existe la oferta: "+ nombre);
	    }finally {
			entityManager.close();
    		entitymf.close();
		}
	    return oferta;
	}
	public List<PostulacionDAO> ObtenerPostulacionesOferta(String nombreOferta)throws noExisteOferta{
		EntityManagerFactory entitymf = null;
	    EntityManager entityManager = null;
	    List<PostulacionDAO> lista = new ArrayList<PostulacionDAO>();
	    try {
	    	entitymf = Persistence.createEntityManagerFactory("TrabajouyDB");
	    	entityManager = entitymf.createEntityManager();
	    	String parametro = "nombre";
	    	TypedQuery<PostulacionDAO> qry = entityManager.createQuery("SELECT p FROM PostulacionDAO p WHERE p.oferta.nombreOferta = :" + parametro, PostulacionDAO.class);
	    	qry.setParameter(parametro, nombreOferta);
	    	lista =  qry.getResultList();
	    }catch(Exception ex) {
	    	throw new noExisteOferta("No existe la oferta: "+ nombreOferta);
	    }finally {
	    	entityManager.close();
	    	entitymf.close();
	    }
	    return lista;
	}
	public List<PostulacionDAO> ObtenerPostulacionesUsuario(String nickname) throws noExisteOferta{
		EntityManagerFactory entitymf = null;
	    EntityManager entityManager = null;
	    List<PostulacionDAO> lista = new ArrayList<PostulacionDAO>();
	    try {
	    	entitymf = Persistence.createEntityManagerFactory("TrabajouyDB");
	    	entityManager = entitymf.createEntityManager();
	    	String parametro = "nombre";
	    	TypedQuery<PostulacionDAO> qry = entityManager.createQuery("SELECT p FROM PostulacionDAO p WHERE p.postulante.nickname = :" + parametro, PostulacionDAO.class);
	    	qry.setParameter(parametro, nickname);
	    	lista =  qry.getResultList();
	    }catch(Exception ex) {
	    	throw new noExisteOferta("No existe postulacion asociada a: "+ nickname);
	    }finally {
	    	entityManager.close();
	    	entitymf.close();
	    }
	    return lista;
	}
	public void agregarOfertaFavorita( String nickPostulante,String nombreOferta) {
		ManejadorUsuario manejadorUsuario= ManejadorUsuario.getInstance();
		ManejadorOferta manejadorOferta= ManejadorOferta.getInstancia();
		Postulante postulante= (Postulante)manejadorUsuario.getUsuario(nickPostulante);
		if(postulante.esOfertaFavorita(nombreOferta)) {
			postulante.quitarFavorito(nombreOferta);
		}
		else {
			postulante.agregarFavorito(nombreOferta);
		}
		manejadorOferta.obtenerOferta(nombreOferta).agregarQuitarFavorito(nickPostulante);
	}
	public PostulacionDAO ObtenerUnaPostulacionUsuario(String nickname, String oferta) throws noExisteOferta{
		EntityManagerFactory entitymf = null;
	    EntityManager entityManager = null;
	    PostulacionDAO post = null;
	    try {
	    	entitymf = Persistence.createEntityManagerFactory("TrabajouyDB");
	    	entityManager = entitymf.createEntityManager();
	    	String nick = "nick";
	    	String nomOferta = "nomOferta";
	    	Query qry = entityManager.createQuery("SELECT p FROM PostulacionDAO p WHERE p.postulante.nickname = :" + nick + " AND p.oferta.nombreOferta = :" + nomOferta);
	    	qry.setParameter(nick, nickname);
	    	qry.setParameter(nomOferta, oferta);
	    	post = (PostulacionDAO) qry.getSingleResult();
	    }catch(Exception ex) {
	    	throw new noExisteOferta("No existe postulacion asociada a: "+ nickname);
	    }finally {
	    	entityManager.close();
	    	entitymf.close();
	    }
	    return post;
	}

	@Override
	public ArrayList<DTEmpresa> buscarPorFiltroEmpresas(String terminosDeBusqueda) {
		ManejadorUsuario manejadorUsuario = ManejadorUsuario.getInstance();
		Set<String> listadoEmpresas = manejadorUsuario.obtenerEmpresas();
		 
		// Filtro empresas en base al contenido de la busqueda
		Set<Empresa> empresasFiltradas = new HashSet<Empresa>();
		for (String nickname : listadoEmpresas) {
		 Usuario usuario = manejadorUsuario.getUsuario(nickname);
		    if (usuario instanceof Empresa) {
		    	Empresa empresa = (Empresa) usuario;
		    	if(empresa.getNombre().contains(terminosDeBusqueda)  || empresa.getDescripcion().contains(terminosDeBusqueda)) {
		    		empresasFiltradas.add(empresa);
		    	}
		    	
		    }
		}
		
		// Creo los datatype de las empresas
		ArrayList<DTEmpresa> DTEmpresasFiltradas = new ArrayList<DTEmpresa>();
		for (Empresa empresa : empresasFiltradas) {
			DTEmpresasFiltradas.add(new DTEmpresa(empresa));
		}
		return DTEmpresasFiltradas;
	}

	@Override
	public ArrayList<DTOfertaLaboral> buscarPorFiltroOfertasLaborales(String terminosDeBusqueda) {
		ManejadorOferta manejadorOfertaLaboral = ManejadorOferta.getInstancia();
		Map<String,OfertaLaboral> listadoOfertasLaborales = manejadorOfertaLaboral.getOfertas(); 
		 
		// Filtro ofertas laborales en base al contenido de la busqueda
		ArrayList<DTOfertaLaboral> ofertasFiltradas = new ArrayList<DTOfertaLaboral>();
		listadoOfertasLaborales.forEach((key,ofertaLaboral) ->{
	    	if(ofertaLaboral.getNombre().contains(terminosDeBusqueda)  || ofertaLaboral.getDescripcion().contains(terminosDeBusqueda)) {
	    		ofertasFiltradas.add(new DTOfertaLaboral(ofertaLaboral));
	    	}
		    	
		});
		
		return ofertasFiltradas;
	}
}
