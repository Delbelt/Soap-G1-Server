//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.25 a las 10:15:26 PM ART 
//


package soap.purchase_orders;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.purchase_orders package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.purchase_orders
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchPurchaseOrdersRequest }
     * 
     */
    public SearchPurchaseOrdersRequest createSearchPurchaseOrdersRequest() {
        return new SearchPurchaseOrdersRequest();
    }

    /**
     * Create an instance of {@link SearchPurchaseOrdersResponse }
     * 
     */
    public SearchPurchaseOrdersResponse createSearchPurchaseOrdersResponse() {
        return new SearchPurchaseOrdersResponse();
    }

    /**
     * Create an instance of {@link PurchaseOrderSoap }
     * 
     */
    public PurchaseOrderSoap createPurchaseOrderSoap() {
        return new PurchaseOrderSoap();
    }

    /**
     * Create an instance of {@link OrderItemSoap }
     * 
     */
    public OrderItemSoap createOrderItemSoap() {
        return new OrderItemSoap();
    }

}
