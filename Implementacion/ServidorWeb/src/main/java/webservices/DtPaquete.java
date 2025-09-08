
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
 * <p>Clase Java para dtPaquete complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPaquete">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descuento" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         <element name="periodoValidez" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="tiposPublicacion">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="value" type="{http://webservices/}dtAgrupa" minOccurs="0"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
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
@XmlType(name = "dtPaquete", propOrder = {
    "nombre",
    "descripcion",
    "descuento",
    "fecha",
    "costo",
    "periodoValidez",
    "tiposPublicacion",
    "imagen"
})
public class DtPaquete {

    protected String nombre;
    protected String descripcion;
    protected double descuento;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    protected double costo;
    protected int periodoValidez;
    @XmlElement(required = true)
    protected DtPaquete.TiposPublicacion tiposPublicacion;
    protected String imagen;

    /**
     * Obtiene el valor de la propiedad nombre.
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
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
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
     * Obtiene el valor de la propiedad descuento.
     * 
     */
    public double getDescuento() {
        return descuento;
    }

    /**
     * Define el valor de la propiedad descuento.
     * 
     */
    public void setDescuento(double value) {
        this.descuento = value;
    }

    /**
     * Obtiene el valor de la propiedad fecha.
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
     * Define el valor de la propiedad fecha.
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
     * Obtiene el valor de la propiedad periodoValidez.
     * 
     */
    public int getPeriodoValidez() {
        return periodoValidez;
    }

    /**
     * Define el valor de la propiedad periodoValidez.
     * 
     */
    public void setPeriodoValidez(int value) {
        this.periodoValidez = value;
    }

    /**
     * Obtiene el valor de la propiedad tiposPublicacion.
     * 
     * @return
     *     possible object is
     *     {@link DtPaquete.TiposPublicacion }
     *     
     */
    public DtPaquete.TiposPublicacion getTiposPublicacion() {
        return tiposPublicacion;
    }

    /**
     * Define el valor de la propiedad tiposPublicacion.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPaquete.TiposPublicacion }
     *     
     */
    public void setTiposPublicacion(DtPaquete.TiposPublicacion value) {
        this.tiposPublicacion = value;
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


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="value" type="{http://webservices/}dtAgrupa" minOccurs="0"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class TiposPublicacion {

        protected List<DtPaquete.TiposPublicacion.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DtPaquete.TiposPublicacion.Entry }
         * 
         * 
         * @return
         *     The value of the entry property.
         */
        public List<DtPaquete.TiposPublicacion.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<>();
            }
            return this.entry;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence>
         *         <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="value" type="{http://webservices/}dtAgrupa" minOccurs="0"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected DtAgrupa value;

            /**
             * Obtiene el valor de la propiedad key.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Define el valor de la propiedad key.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link DtAgrupa }
             *     
             */
            public DtAgrupa getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link DtAgrupa }
             *     
             */
            public void setValue(DtAgrupa value) {
                this.value = value;
            }

        }

    }

}
