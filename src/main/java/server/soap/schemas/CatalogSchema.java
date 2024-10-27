package server.soap.schemas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

import server.soap.RouterSoap;

@Configuration
@EnableWs
public class CatalogSchema {
	
	// Para ver si funciona: http://localhost:8080/ws/catalogs.wsdl
	
	@Bean(name = "catalogs")
    public DefaultWsdl11Definition catalogsWsdl11Definition(SimpleXsdSchema catalogsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("CatalogsPort");
        wsdl11Definition.setLocationUri(RouterSoap.URLMAPPINGS);
        wsdl11Definition.setTargetNamespace(RouterSoap.NAMESPACE_CATALOG);
        wsdl11Definition.setSchema(catalogsSchema);
        return wsdl11Definition;
    }

    @Bean
    public SimpleXsdSchema catalogsSchema() {
        return new SimpleXsdSchema(new ClassPathResource(RouterSoap.RESOURCE_CATALOG));
    }
    
}
