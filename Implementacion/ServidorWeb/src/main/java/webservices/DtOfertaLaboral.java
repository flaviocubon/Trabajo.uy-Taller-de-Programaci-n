
package webservices;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtOfertaLaboral complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtOfertaLaboral">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreEmpresa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ciudad" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="departamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horario" type="{http://webservices/}dtHorario" minOccurs="0"/>
 *         <element name="remuneracion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="postulaciones" type="{http://webservices/}dtPostulacion" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="keywords" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="estado" type="{http://webservices/}estado" minOccurs="0"/>
 *         <element name="paquete" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaVencimiento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="cant_favoritos" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="visitas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtOfertaLaboral", propOrder = {
    "nombre",
    "nombreEmpresa",
    "descripcion",
    "ciudad",
    "departamento",
    "horario",
    "remuneracion",
    "fecha",
    "costo",
    "postulaciones",
    "tipo",
    "keywords",
    "imagen",
    "estado",
    "paquete",
    "fechaVencimiento",
    "cant_favoritos",
    "visitas"
})
public class DtOfertaLaboral {

    protected String nombre;
    protected String nombreEmpresa;
    protected String descripcion;
    protected String ciudad;
    protected String departamento;
    protected DtHorario horario;
    protected int remuneracion;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    protected double costo;
    @XmlElement(nillable = true)
    protected List<DtPostulacion> postulaciones;
    protected String tipo;
    @XmlElement(nillable = true)
    protected List<String> keywords;
    protected String imagen;
    @XmlSchemaType(name = "string")
    protected Estado estado;
    protected String paquete;
    protected String fechaVencimiento;
    protected int cant_favoritos;
    protected int visitas;

    /**
     * Gets the value of the nombre property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sets the value of the nombre property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }
    
    public int getCant_favoritos() {
    	return this.cant_favoritos;
    }
    
    public void setCant_favoritos(int value) {
    	this.cant_favoritos=value;
    }

    /**
     * Gets the value of the nombreEmpresa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    /**
     * Sets the value of the nombreEmpresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEmpresa(String value) {
        this.nombreEmpresa = value;
    }

    /**
     * Gets the value of the descripcion property.
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
     * Sets the value of the descripcion property.
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
     * Gets the value of the ciudad property.
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
     * Sets the value of the ciudad property.
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
     * Gets the value of the departamento property.
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
     * Sets the value of the departamento property.
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
     * Gets the value of the horario property.
     * 
     * @return
     *     possible object is
     *     {@link DtHorario }
     *     
     */
    public DtHorario getHorario() {
        return horario;
    }

    /**
     * Sets the value of the horario property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtHorario }
     *     
     */
    public void setHorario(DtHorario value) {
        this.horario = value;
    }

    /**
     * Gets the value of the remuneracion property.
     * 
     */
    public int getRemuneracion() {
        return remuneracion;
    }

    /**
     * Sets the value of the remuneracion property.
     * 
     */
    public void setRemuneracion(int value) {
        this.remuneracion = value;
    }

    /**
     * Gets the value of the fecha property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Sets the value of the fecha property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Gets the value of the costo property.
     * 
     */
    public double getCosto() {
        return costo;
    }

    /**
     * Sets the value of the costo property.
     * 
     */
    public void setCosto(double value) {
        this.costo = value;
    }

    /**
     * Gets the value of the postulaciones property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postulaciones property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getPostulaciones().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPostulacion }
     * </p>
     * 
     * 
     * @return
     *     The value of the postulaciones property.
     */
    public List<DtPostulacion> getPostulaciones() {
        if (postulaciones == null) {
            postulaciones = new ArrayList<>();
        }
        return this.postulaciones;
    }

    /**
     * Gets the value of the tipo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Sets the value of the tipo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Gets the value of the keywords property.
     * 
     * <p>This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keywords property.</p>
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * </p>
     * <pre>
     * getKeywords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * </p>
     * 
     * 
     * @return
     *     The value of the keywords property.
     */
    public List<String> getKeywords() {
        if (keywords == null) {
            keywords = new ArrayList<>();
        }
        return this.keywords;
    }

    /**
     * Gets the value of the imagen property.
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
     * Sets the value of the imagen property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

    /**
     * Gets the value of the estado property.
     * 
     * @return
     *     possible object is
     *     {@link Estado }
     *     
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Sets the value of the estado property.
     * 
     * @param value
     *     allowed object is
     *     {@link Estado }
     *     
     */
    public void setEstado(Estado value) {
        this.estado = value;
    }

    /**
     * Gets the value of the paquete property.
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
     * Sets the value of the paquete property.
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
     * Gets the value of the fechaVencimiento property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    /**
     * Sets the value of the fechaVencimiento property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaVencimiento(String value) {
        this.fechaVencimiento = value;
    }

    /**
     * Gets the value of the visitas property.
     * 
     */
    public int getVisitas() {
        return visitas;
    }

    /**
     * Sets the value of the visitas property.
     * 
     */
    public void setVisitas(int value) {
        this.visitas = value;
    }

}
