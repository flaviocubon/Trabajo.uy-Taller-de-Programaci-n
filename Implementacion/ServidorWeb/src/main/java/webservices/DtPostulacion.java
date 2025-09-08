
package webservices;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtPostulacion complex type</p>.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.</p>
 * 
 * <pre>{@code
 * <complexType name="dtPostulacion">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaPostulacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="resumenCV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="nombreOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="postulante" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagenOferta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="video" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="orden" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaOrden" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPostulacion", propOrder = {
    "fechaPostulacion",
    "resumenCV",
    "descripcion",
    "nombreOferta",
    "postulante",
    "imagenOferta",
    "video",
    "orden",
    "fechaOrden"
})
public class DtPostulacion {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaPostulacion;
    protected String resumenCV;
    protected String descripcion;
    protected String nombreOferta;
    protected String postulante;
    protected String imagenOferta;
    protected String video;
    protected int orden;
    protected String fechaOrden;

    /**
     * Gets the value of the fechaPostulacion property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaPostulacion() {
        return fechaPostulacion;
    }

    /**
     * Sets the value of the fechaPostulacion property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaPostulacion(XMLGregorianCalendar value) {
        this.fechaPostulacion = value;
    }

    /**
     * Gets the value of the resumenCV property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResumenCV() {
        return resumenCV;
    }

    /**
     * Sets the value of the resumenCV property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResumenCV(String value) {
        this.resumenCV = value;
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
     * Gets the value of the nombreOferta property.
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
     * Sets the value of the nombreOferta property.
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
     * Gets the value of the postulante property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostulante() {
        return postulante;
    }

    /**
     * Sets the value of the postulante property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostulante(String value) {
        this.postulante = value;
    }

    /**
     * Gets the value of the imagenOferta property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagenOferta() {
        return imagenOferta;
    }

    /**
     * Sets the value of the imagenOferta property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagenOferta(String value) {
        this.imagenOferta = value;
    }

    /**
     * Gets the value of the video property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideo() {
        return video;
    }

    /**
     * Sets the value of the video property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideo(String value) {
        this.video = value;
    }

    /**
     * Gets the value of the orden property.
     * 
     */
    public int getOrden() {
        return orden;
    }

    /**
     * Sets the value of the orden property.
     * 
     */
    public void setOrden(int value) {
        this.orden = value;
    }

    /**
     * Gets the value of the fechaOrden property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaOrden() {
        return fechaOrden;
    }

    /**
     * Sets the value of the fechaOrden property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaOrden(String value) {
        this.fechaOrden = value;
    }

}
