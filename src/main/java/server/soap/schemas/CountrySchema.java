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
public class CountrySchema {
	
	// Para ver si funciona: http://localhost:8080/ws/countries.wsdl
	
	@Bean(name = "countries")
	public DefaultWsdl11Definition defaultWsdl11Definition(SimpleXsdSchema countriesSchema) {

		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();

		wsdl11Definition.setPortTypeName("CountryPort");
		wsdl11Definition.setLocationUri(RouterSoap.URLMAPPINGS);
		wsdl11Definition.setTargetNamespace(RouterSoap.NAMESPACE_COUNTRY);
		wsdl11Definition.setSchema(countriesSchema);

		return wsdl11Definition;
	}

	@Bean
	public SimpleXsdSchema countriesSchema() {
		
		return new SimpleXsdSchema(new ClassPathResource(RouterSoap.RESOURCE_COUNTRY));
	}
}
