//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.25 a las 10:15:26 PM ART 
//


package soap.filters;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.filters package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.filters
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetFiltersRequest }
     * 
     */
    public GetFiltersRequest createGetFiltersRequest() {
        return new GetFiltersRequest();
    }

    /**
     * Create an instance of {@link GetFiltersResponse }
     * 
     */
    public GetFiltersResponse createGetFiltersResponse() {
        return new GetFiltersResponse();
    }

    /**
     * Create an instance of {@link FilterSoap }
     * 
     */
    public FilterSoap createFilterSoap() {
        return new FilterSoap();
    }

    /**
     * Create an instance of {@link SaveFilterRequest }
     * 
     */
    public SaveFilterRequest createSaveFilterRequest() {
        return new SaveFilterRequest();
    }

    /**
     * Create an instance of {@link SaveFilterType }
     * 
     */
    public SaveFilterType createSaveFilterType() {
        return new SaveFilterType();
    }

    /**
     * Create an instance of {@link SaveFilterResponse }
     * 
     */
    public SaveFilterResponse createSaveFilterResponse() {
        return new SaveFilterResponse();
    }

    /**
     * Create an instance of {@link EditFilterRequest }
     * 
     */
    public EditFilterRequest createEditFilterRequest() {
        return new EditFilterRequest();
    }

    /**
     * Create an instance of {@link EditFilterType }
     * 
     */
    public EditFilterType createEditFilterType() {
        return new EditFilterType();
    }

    /**
     * Create an instance of {@link EditFilterResponse }
     * 
     */
    public EditFilterResponse createEditFilterResponse() {
        return new EditFilterResponse();
    }

    /**
     * Create an instance of {@link DeleteFilterRequest }
     * 
     */
    public DeleteFilterRequest createDeleteFilterRequest() {
        return new DeleteFilterRequest();
    }

    /**
     * Create an instance of {@link DeleteFilterResponse }
     * 
     */
    public DeleteFilterResponse createDeleteFilterResponse() {
        return new DeleteFilterResponse();
    }

}
