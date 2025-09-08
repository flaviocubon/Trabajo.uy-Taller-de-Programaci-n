
package webservices;

import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para postulacionDAO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="postulacionDAO">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="postulante" type="{http://webservices/}postulanteDAO" minOccurs="0"/>
 *         <element name="oferta" type="{http://webservices/}ofertaDAO" minOccurs="0"/>
 *         <element name="fechaPostulacion" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="resumenCV" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "postulacionDAO", propOrder = {
    "postulante",
    "oferta",
    "fechaPostulacion",
    "resumenCV",
    "descripcion"
})
public class PostulacionDAO {

    protected PostulanteDAO postulante;
    protected OfertaDAO oferta;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaPostulacion;
    protected String resumenCV;
    protected String descripcion;

    /**
     * Obtiene el valor de la propiedad postulante.
     * 
     * @return
     *     possible object is
     *     {@link PostulanteDAO }
     *     
     */
    public PostulanteDAO getPostulante() {
        return postulante;
    }

    /**
     * Define el valor de la propiedad postulante.
     * 
     * @param value
     *     allowed object is
     *     {@link PostulanteDAO }
     *     
     */
    public void setPostulante(PostulanteDAO value) {
        this.postulante = value;
    }

    /**
     * Obtiene el valor de la propiedad oferta.
     * 
     * @return
     *     possible object is
     *     {@link OfertaDAO }
     *     
     */
    public OfertaDAO getOferta() {
        return oferta;
    }

    /**
     * Define el valor de la propiedad oferta.
     * 
     * @param value
     *     allowed object is
     *     {@link OfertaDAO }
     *     
     */
    public void setOferta(OfertaDAO value) {
        this.oferta = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaPostulacion.
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
     * Define el valor de la propiedad fechaPostulacion.
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
     * Obtiene el valor de la propiedad resumenCV.
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
     * Define el valor de la propiedad resumenCV.
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

}
