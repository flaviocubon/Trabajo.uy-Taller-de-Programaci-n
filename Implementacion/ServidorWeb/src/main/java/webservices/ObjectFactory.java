
package webservices;

import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DtEmpresaNombreEmpresa_QNAME = new QName("", "nombreEmpresa");
    private final static QName _DtEmpresaDescripcion_QNAME = new QName("", "descripcion");
    private final static QName _DtEmpresaLink_QNAME = new QName("", "link");
    private final static QName _DtEmpresaNombreOfertas_QNAME = new QName("", "nombreOfertas");
    private final static QName _DtEmpresaCompras_QNAME = new QName("", "compras");
    private final static QName _DtEmpresaImagen_QNAME = new QName("", "imagen");
    private final static QName _DtPostulanteNacionalidad_QNAME = new QName("", "nacionalidad");
    private final static QName _DtPostulanteFechaNacimiento_QNAME = new QName("", "fechaNacimiento");
    private final static QName _DtPostulantePostulaciones_QNAME = new QName("", "postulaciones");
    private final static QName _CompraPaqueteRepetida_QNAME = new QName("http://webservices/", "compraPaqueteRepetida");
    private final static QName _NoExisteOferta_QNAME = new QName("http://webservices/", "noExisteOferta");
    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DataTipos }
     * 
     * @return
     *     the new instance of {@link DataTipos }
     */
    public DataTipos createDataTipos() {
        return new DataTipos();
    }

    /**
     * Create an instance of {@link DataTipos.Value }
     * 
     * @return
     *     the new instance of {@link DataTipos.Value }
     */
    public DataTipos.Value createDataTiposValue() {
        return new DataTipos.Value();
    }

    /**
     * Create an instance of {@link DtCompra }
     * 
     * @return
     *     the new instance of {@link DtCompra }
     */
    public DtCompra createDtCompra() {
        return new DtCompra();
    }

    /**
     * Create an instance of {@link DtCompra.TiposNoCanjeados }
     * 
     * @return
     *     the new instance of {@link DtCompra.TiposNoCanjeados }
     */
    public DtCompra.TiposNoCanjeados createDtCompraTiposNoCanjeados() {
        return new DtCompra.TiposNoCanjeados();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtPaquete.TiposPublicacion }
     * 
     * @return
     *     the new instance of {@link DtPaquete.TiposPublicacion }
     */
    public DtPaquete.TiposPublicacion createDtPaqueteTiposPublicacion() {
        return new DtPaquete.TiposPublicacion();
    }

    /**
     * Create an instance of {@link DataPaquetes }
     * 
     * @return
     *     the new instance of {@link DataPaquetes }
     */
    public DataPaquetes createDataPaquetes() {
        return new DataPaquetes();
    }

    /**
     * Create an instance of {@link DataPaquetes.Value }
     * 
     * @return
     *     the new instance of {@link DataPaquetes.Value }
     */
    public DataPaquetes.Value createDataPaquetesValue() {
        return new DataPaquetes.Value();
    }

    /**
     * Create an instance of {@link CompraPaqueteRepetida }
     * 
     * @return
     *     the new instance of {@link CompraPaqueteRepetida }
     */
    public CompraPaqueteRepetida createCompraPaqueteRepetida() {
        return new CompraPaqueteRepetida();
    }

    /**
     * Create an instance of {@link DtAgrupa }
     * 
     * @return
     *     the new instance of {@link DtAgrupa }
     */
    public DtAgrupa createDtAgrupa() {
        return new DtAgrupa();
    }

    /**
     * Create an instance of {@link DataCompra }
     * 
     * @return
     *     the new instance of {@link DataCompra }
     */
    public DataCompra createDataCompra() {
        return new DataCompra();
    }

    /**
     * Create an instance of {@link WrapperSetString }
     * 
     * @return
     *     the new instance of {@link WrapperSetString }
     */
    public WrapperSetString createWrapperSetString() {
        return new WrapperSetString();
    }

    /**
     * Create an instance of {@link DtTipoPublicacion }
     * 
     * @return
     *     the new instance of {@link DtTipoPublicacion }
     */
    public DtTipoPublicacion createDtTipoPublicacion() {
        return new DtTipoPublicacion();
    }

    /**
     * Create an instance of {@link DataTipos.Value.Entry }
     * 
     * @return
     *     the new instance of {@link DataTipos.Value.Entry }
     */
    public DataTipos.Value.Entry createDataTiposValueEntry() {
        return new DataTipos.Value.Entry();
    }

    /**
     * Create an instance of {@link DtCompra.TiposNoCanjeados.Entry }
     * 
     * @return
     *     the new instance of {@link DtCompra.TiposNoCanjeados.Entry }
     */
    public DtCompra.TiposNoCanjeados.Entry createDtCompraTiposNoCanjeadosEntry() {
        return new DtCompra.TiposNoCanjeados.Entry();
    }

    /**
     * Create an instance of {@link DtPaquete.TiposPublicacion.Entry }
     * 
     * @return
     *     the new instance of {@link DtPaquete.TiposPublicacion.Entry }
     */
    public DtPaquete.TiposPublicacion.Entry createDtPaqueteTiposPublicacionEntry() {
        return new DtPaquete.TiposPublicacion.Entry();
    }

    /**
     * Create an instance of {@link DataPaquetes.Value.Entry }
     * 
     * @return
     *     the new instance of {@link DataPaquetes.Value.Entry }
     */
    public DataPaquetes.Value.Entry createDataPaquetesValueEntry() {
        return new DataPaquetes.Value.Entry();
    }

        /**
     * Create an instance of {@link DtPostulante }
     * 
     * @return
     *     the new instance of {@link DtPostulante }
     */
    public DtPostulante createDtPostulante() {
        return new DtPostulante();
    }

    /**
     * Create an instance of {@link DtPostulante.Postulaciones }
     * 
     * @return
     *     the new instance of {@link DtPostulante.Postulaciones }
     */
    public DtPostulante.Postulaciones createDtPostulantePostulaciones() {
        return new DtPostulante.Postulaciones();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     * @return
     *     the new instance of {@link Exception }
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link DtPostulacion }
     * 
     * @return
     *     the new instance of {@link DtPostulacion }
     */
    public DtPostulacion createDtPostulacion() {
        return new DtPostulacion();
    }

    /**
     * Create an instance of {@link DataOfertas }
     * 
     * @return
     *     the new instance of {@link DataOfertas }
     */
    public DataOfertas createDataOfertas() {
        return new DataOfertas();
    }

    /**
     * Create an instance of {@link DtOfertaLaboral }
     * 
     * @return
     *     the new instance of {@link DtOfertaLaboral }
     */
    public DtOfertaLaboral createDtOfertaLaboral() {
        return new DtOfertaLaboral();
    }

    /**
     * Create an instance of {@link DtHorario }
     * 
     * @return
     *     the new instance of {@link DtHorario }
     */
    public DtHorario createDtHorario() {
        return new DtHorario();
    }

    /**
     * Create an instance of {@link LocalTime }
     * 
     * @return
     *     the new instance of {@link LocalTime }
     */
    public LocalTime createLocalTime() {
        return new LocalTime();
    }

    /**
     * Create an instance of {@link LocalDate }
     * 
     * @return
     *     the new instance of {@link LocalDate }
     */
    public LocalDate createLocalDate() {
        return new LocalDate();
    }

    /**
     * Create an instance of {@link WrapperListString }
     * 
     * @return
     *     the new instance of {@link WrapperListString }
     */
    public WrapperListString createWrapperListString() {
        return new WrapperListString();
    }

    /**
     * Create an instance of {@link DtEmpresa }
     * 
     * @return
     *     the new instance of {@link DtEmpresa }
     */
    public DtEmpresa createDtEmpresa() {
        return new DtEmpresa();
    }

    /**
     * Create an instance of {@link DtPostulante.Postulaciones.Entry }
     * 
     * @return
     *     the new instance of {@link DtPostulante.Postulaciones.Entry }
     */
    public DtPostulante.Postulaciones.Entry createDtPostulantePostulacionesEntry() {
        return new DtPostulante.Postulaciones.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "nombreEmpresa", scope = DtEmpresa.class)
    public JAXBElement<String> createDtEmpresaNombreEmpresa(String value) {
        return new JAXBElement<>(_DtEmpresaNombreEmpresa_QNAME, String.class, DtEmpresa.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "descripcion", scope = DtEmpresa.class)
    public JAXBElement<String> createDtEmpresaDescripcion(String value) {
        return new JAXBElement<>(_DtEmpresaDescripcion_QNAME, String.class, DtEmpresa.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "link", scope = DtEmpresa.class)
    public JAXBElement<String> createDtEmpresaLink(String value) {
        return new JAXBElement<>(_DtEmpresaLink_QNAME, String.class, DtEmpresa.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "nombreOfertas", scope = DtEmpresa.class)
    public JAXBElement<String> createDtEmpresaNombreOfertas(String value) {
        return new JAXBElement<>(_DtEmpresaNombreOfertas_QNAME, String.class, DtEmpresa.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "compras", scope = DtEmpresa.class)
    public JAXBElement<String> createDtEmpresaCompras(String value) {
        return new JAXBElement<>(_DtEmpresaCompras_QNAME, String.class, DtEmpresa.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "imagen", scope = DtEmpresa.class)
    public JAXBElement<String> createDtEmpresaImagen(String value) {
        return new JAXBElement<>(_DtEmpresaImagen_QNAME, String.class, DtEmpresa.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "nacionalidad", scope = DtPostulante.class)
    public JAXBElement<String> createDtPostulanteNacionalidad(String value) {
        return new JAXBElement<>(_DtPostulanteNacionalidad_QNAME, String.class, DtPostulante.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "fechaNacimiento", scope = DtPostulante.class)
    public JAXBElement<XMLGregorianCalendar> createDtPostulanteFechaNacimiento(XMLGregorianCalendar value) {
        return new JAXBElement<>(_DtPostulanteFechaNacimiento_QNAME, XMLGregorianCalendar.class, DtPostulante.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "nombreOfertas", scope = DtPostulante.class)
    public JAXBElement<String> createDtPostulanteNombreOfertas(String value) {
        return new JAXBElement<>(_DtEmpresaNombreOfertas_QNAME, String.class, DtPostulante.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "imagen", scope = DtPostulante.class)
    public JAXBElement<String> createDtPostulanteImagen(String value) {
        return new JAXBElement<>(_DtEmpresaImagen_QNAME, String.class, DtPostulante.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DtPostulante.Postulaciones }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DtPostulante.Postulaciones }{@code >}
     */
    @XmlElementDecl(namespace = "", name = "postulaciones", scope = DtPostulante.class)
    public JAXBElement<DtPostulante.Postulaciones> createDtPostulantePostulaciones(DtPostulante.Postulaciones value) {
        return new JAXBElement<>(_DtPostulantePostulaciones_QNAME, DtPostulante.Postulaciones.class, DtPostulante.class, value);
    }


    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CompraPaqueteRepetida }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link CompraPaqueteRepetida }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "compraPaqueteRepetida")
    public JAXBElement<CompraPaqueteRepetida> createCompraPaqueteRepetida(CompraPaqueteRepetida value) {
        return new JAXBElement<>(_CompraPaqueteRepetida_QNAME, CompraPaqueteRepetida.class, null, value);
    }
	/**
     * Create an instance of {@link DataOfertaDAO }
     * 
     * @return
     *     the new instance of {@link DataOfertaDAO }
     */
    public DataOfertaDAO createDataOfertaDAO() {
        return new DataOfertaDAO();
    }

    /**
     * Create an instance of {@link OfertaDAO }
     * 
     * @return
     *     the new instance of {@link OfertaDAO }
     */
    public OfertaDAO createOfertaDAO() {
        return new OfertaDAO();
    }
/**
     * Create an instance of {@link EmpresaDAO }
     * 
     * @return
     *     the new instance of {@link EmpresaDAO }
     */
    public EmpresaDAO createEmpresaDAO() {
        return new EmpresaDAO();
    }

    /**
     * Create an instance of {@link UsuarioDAO }
     * 
     * @return
     *     the new instance of {@link UsuarioDAO }
     */
    public UsuarioDAO createUsuarioDAO() {
        return new UsuarioDAO();
    }
    /**
     * Create an instance of {@link DataPostulacionDAO }
     * 
     * @return
     *     the new instance of {@link DataPostulacionDAO }
     */
    public DataPostulacionDAO createDataPostulacionDAO() {
        return new DataPostulacionDAO();
    }

    /**
     * Create an instance of {@link PostulacionDAO }
     * 
     * @return
     *     the new instance of {@link PostulacionDAO }
     */
    public PostulacionDAO createPostulacionDAO() {
        return new PostulacionDAO();
    }

    /**
     * Create an instance of {@link PostulanteDAO }
     * 
     * @return
     *     the new instance of {@link PostulanteDAO }
     */
    public PostulanteDAO createPostulanteDAO() {
        return new PostulanteDAO();
    }
    /**
     * Create an instance of {@link NoExisteOferta }
     * 
     * @return
     *     the new instance of {@link NoExisteOferta }
     */
    public NoExisteOferta createNoExisteOferta() {
        return new NoExisteOferta();
    }
   /**
     * Create an instance of {@link JAXBElement }{@code <}{@link NoExisteOferta }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link NoExisteOferta }{@code >}
     */
    @XmlElementDecl(namespace = "http://webservices/", name = "noExisteOferta")
    public JAXBElement<NoExisteOferta> createNoExisteOferta(NoExisteOferta value) {
        return new JAXBElement<>(_NoExisteOferta_QNAME, NoExisteOferta.class, null, value);
    }
}
