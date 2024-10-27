//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.2 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.27 a las 01:21:40 AM ART 
//


package soap.users;

import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soap.users package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soap.users
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CargarUsuariosRequest }
     * 
     */
    public CargarUsuariosRequest createCargarUsuariosRequest() {
        return new CargarUsuariosRequest();
    }

    /**
     * Create an instance of {@link CargarUsuariosResponse }
     * 
     */
    public CargarUsuariosResponse createCargarUsuariosResponse() {
        return new CargarUsuariosResponse();
    }

    /**
     * Create an instance of {@link UsuariosCargados }
     * 
     */
    public UsuariosCargados createUsuariosCargados() {
        return new UsuariosCargados();
    }

    /**
     * Create an instance of {@link Errores }
     * 
     */
    public Errores createErrores() {
        return new Errores();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

}
