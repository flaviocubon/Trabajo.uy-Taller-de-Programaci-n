
package webservices;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ofertaDAO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="ofertaDAO">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="idOferta" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="nombreOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="empresa" type="{http://webservices/}empresaDAO" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horarioInicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horarioFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="remuneracion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="tipoPublicacion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaBaja" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="paquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ofertaDAO", propOrder = {
    "idOferta",
    "nombreOferta",
    "empresa",
    "descripcion",
    "ciudad",
    "departamento",
    "horarioInicio",
    "horarioFin",
    "remuneracion",
    "fechaAlta",
    "tipoPublicacion",
    "fechaBaja",
    "costo",
    "paquete",
    "imagen"
})
public class OfertaDAO {

    protected int idOferta;
    protected String nombreOferta;
    protected EmpresaDAO empresa;
    protected String descripcion;
    protected String ciudad;
    protected String departamento;
    protected String horarioInicio;
    protected String horarioFin;
    protected int remuneracion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAlta;
    protected String tipoPublicacion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaBaja;
    protected double costo;
    protected String paquete;
    protected String imagen;

    /**
     * Obtiene el valor de la propiedad idOferta.
     * 
     */
    public int getIdOferta() {
        return idOferta;
    }

    /**
     * Obtiene el valor de la propiedad nombreOferta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreOferta() {
        return nombreOferta;
    }

    /**
     * Define el valor de la propiedad nombreOferta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreOferta(String value) {
        this.nombreOferta = value;
    }

    /**
     * Obtiene el valor de la propiedad empresa.
     * 
     * @return
     *     possible object is
     *     {@link EmpresaDAO }
     *     
     */
    public EmpresaDAO getEmpresa() {
        return empresa;
    }

    /**
     * Define el valor de la propiedad empresa.
     * 
     * @param value
     *     allowed object is
     *     {@link EmpresaDAO }
     *     
     */
    public void setEmpresa(EmpresaDAO value) {
        this.empresa = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
    }

    /**
     * Obtiene el valor de la propiedad ciudad.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * Define el valor de la propiedad ciudad.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiudad(String value) {
        this.ciudad = value;
    }

    /**
     * Obtiene el valor de la propiedad departamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Define el valor de la propiedad departamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartamento(String value) {
        this.departamento = value;
    }

    /**
     * Obtiene el valor de la propiedad horarioInicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorarioInicio() {
        return horarioInicio;
    }

    /**
     * Define el valor de la propiedad horarioInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorarioInicio(String value) {
        this.horarioInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad horarioFin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHorarioFin() {
        return horarioFin;
    }

    /**
     * Define el valor de la propiedad horarioFin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHorarioFin(String value) {
        this.horarioFin = value;
    }

    /**
     * Obtiene el valor de la propiedad remuneracion.
     * 
     */
    public int getRemuneracion() {
        return remuneracion;
    }

    /**
     * Define el valor de la propiedad remuneracion.
     * 
     */
    public void setRemuneracion(int value) {
        this.remuneracion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAlta(XMLGregorianCalendar value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoPublicacion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoPublicacion() {
        return tipoPublicacion;
    }

    /**
     * Define el valor de la propiedad tipoPublicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoPublicacion(String value) {
        this.tipoPublicacion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaBaja.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Define el valor de la propiedad fechaBaja.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaBaja(XMLGregorianCalendar value) {
        this.fechaBaja = value;
    }

    /**
     * Obtiene el valor de la propiedad costo.
     * 
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     */
    public void setCosto(double value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad paquete.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaquete() {
        return paquete;
    }

    /**
     * Define el valor de la propiedad paquete.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaquete(String value) {
        this.paquete = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

}
