//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.26 a las 11:43:56 PM ART 
//


package soap.catalogs;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.catalogs package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.catalogs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CreateCatalogRequest }
     * 
     */
    public CreateCatalogRequest createCreateCatalogRequest() {
        return new CreateCatalogRequest();
    }

    /**
     * Create an instance of {@link CreateCatalogResponse }
     * 
     */
    public CreateCatalogResponse createCreateCatalogResponse() {
        return new CreateCatalogResponse();
    }

    /**
     * Create an instance of {@link SoapCatalog }
     * 
     */
    public SoapCatalog createSoapCatalog() {
        return new SoapCatalog();
    }

    /**
     * Create an instance of {@link DeleteCatalogRequest }
     * 
     */
    public DeleteCatalogRequest createDeleteCatalogRequest() {
        return new DeleteCatalogRequest();
    }

    /**
     * Create an instance of {@link DeleteCatalogResponse }
     * 
     */
    public DeleteCatalogResponse createDeleteCatalogResponse() {
        return new DeleteCatalogResponse();
    }

    /**
     * Create an instance of {@link AddProductToCatalogRequest }
     * 
     */
    public AddProductToCatalogRequest createAddProductToCatalogRequest() {
        return new AddProductToCatalogRequest();
    }

    /**
     * Create an instance of {@link AddProductToCatalogResponse }
     * 
     */
    public AddProductToCatalogResponse createAddProductToCatalogResponse() {
        return new AddProductToCatalogResponse();
    }

    /**
     * Create an instance of {@link ExportCatalogToPdfRequest }
     * 
     */
    public ExportCatalogToPdfRequest createExportCatalogToPdfRequest() {
        return new ExportCatalogToPdfRequest();
    }

    /**
     * Create an instance of {@link ExportCatalogToPdfResponse }
     * 
     */
    public ExportCatalogToPdfResponse createExportCatalogToPdfResponse() {
        return new ExportCatalogToPdfResponse();
    }

    /**
     * Create an instance of {@link GetCatalogByIdRequest }
     * 
     */
    public GetCatalogByIdRequest createGetCatalogByIdRequest() {
        return new GetCatalogByIdRequest();
    }

    /**
     * Create an instance of {@link GetCatalogByIdResponse }
     * 
     */
    public GetCatalogByIdResponse createGetCatalogByIdResponse() {
        return new GetCatalogByIdResponse();
    }

    /**
     * Create an instance of {@link SoapProduct }
     * 
     */
    public SoapProduct createSoapProduct() {
        return new SoapProduct();
    }

}
