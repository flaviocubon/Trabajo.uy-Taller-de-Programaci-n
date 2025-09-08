
package webservices;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtHorario complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtHorario">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="horarioInicio" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="horarioFin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtHorario", propOrder = {
    "horarioInicio",
    "horarioFin"
})
public class DtHorario {

    protected String horarioInicio;
    protected String horarioFin;

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

}
