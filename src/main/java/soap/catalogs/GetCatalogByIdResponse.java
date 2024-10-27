//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.26 a las 11:43:56 PM ART 
//


package soap.catalogs;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="catalog" type="{http://soap/catalogs}SoapCatalog" minOccurs="0"/&gt;
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
    "catalog"
})
@XmlRootElement(name = "GetCatalogByIdResponse")
public class GetCatalogByIdResponse {

    protected SoapCatalog catalog;

    /**
     * Obtiene el valor de la propiedad catalog.
     * 
     * @return
     *     possible object is
     *     {@link SoapCatalog }
     *     
     */
    public SoapCatalog getCatalog() {
        return catalog;
    }

    /**
     * Define el valor de la propiedad catalog.
     * 
     * @param value
     *     allowed object is
     *     {@link SoapCatalog }
     *     
     */
    public void setCatalog(SoapCatalog value) {
        this.catalog = value;
    }

}
