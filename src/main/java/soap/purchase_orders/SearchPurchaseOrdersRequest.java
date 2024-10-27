//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.26 a las 06:19:57 PM ART 
//


package soap.purchase_orders;

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
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="startRequestDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="endRequestDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="storeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "productCode",
    "startRequestDate",
    "endRequestDate",
    "status",
    "storeCode"
})
@XmlRootElement(name = "SearchPurchaseOrdersRequest")
public class SearchPurchaseOrdersRequest {

    protected String productCode;
    protected String startRequestDate;
    protected String endRequestDate;
    protected String status;
    protected String storeCode;

    /**
     * Obtiene el valor de la propiedad productCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Define el valor de la propiedad productCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Obtiene el valor de la propiedad startRequestDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartRequestDate() {
        return startRequestDate;
    }

    /**
     * Define el valor de la propiedad startRequestDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartRequestDate(String value) {
        this.startRequestDate = value;
    }

    /**
     * Obtiene el valor de la propiedad endRequestDate.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndRequestDate() {
        return endRequestDate;
    }

    /**
     * Define el valor de la propiedad endRequestDate.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndRequestDate(String value) {
        this.endRequestDate = value;
    }

    /**
     * Obtiene el valor de la propiedad status.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatus() {
        return status;
    }

    /**
     * Define el valor de la propiedad status.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(String value) {
        this.status = value;
    }

    /**
     * Obtiene el valor de la propiedad storeCode.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreCode() {
        return storeCode;
    }

    /**
     * Define el valor de la propiedad storeCode.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreCode(String value) {
        this.storeCode = value;
    }

}
