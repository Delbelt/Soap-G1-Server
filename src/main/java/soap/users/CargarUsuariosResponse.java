//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.27 a las 01:21:40 AM ART 
//


package soap.users;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="usuariosCargados" type="{http://soap/users}UsuariosCargados"/&gt;
 *         &lt;element name="errores" type="{http://soap/users}Errores"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "usuariosCargados",
    "errores"
})
@XmlRootElement(name = "CargarUsuariosResponse")
public class CargarUsuariosResponse {

    @XmlElement(required = true)
    protected UsuariosCargados usuariosCargados;
    @XmlElement(required = true)
    protected Errores errores;

    /**
     * Obtiene el valor de la propiedad usuariosCargados.
     * 
     * @return
     *     possible object is
     *     {@link UsuariosCargados }
     *     
     */
    public UsuariosCargados getUsuariosCargados() {
        return usuariosCargados;
    }

    /**
     * Define el valor de la propiedad usuariosCargados.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuariosCargados }
     *     
     */
    public void setUsuariosCargados(UsuariosCargados value) {
        this.usuariosCargados = value;
    }

    /**
     * Obtiene el valor de la propiedad errores.
     * 
     * @return
     *     possible object is
     *     {@link Errores }
     *     
     */
    public Errores getErrores() {
        return errores;
    }

    /**
     * Define el valor de la propiedad errores.
     * 
     * @param value
     *     allowed object is
     *     {@link Errores }
     *     
     */
    public void setErrores(Errores value) {
        this.errores = value;
    }

}
