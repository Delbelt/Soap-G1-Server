package server.soap.schemas;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

import server.soap.RouterSoap;

@Configuration
@EnableWs
public class FilterSchema {
	// Para ver si funciona: http://localhost:8080/ws/filters.wsdl
	
		@Bean(name = "filters")
		public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema filtersSchema) {

			DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();

			wsdl11Definition.setPortTypeName("FilterPort");
			wsdl11Definition.setLocationUri(RouterSoap.URLMAPPINGS);
			wsdl11Definition.setTargetNamespace(RouterSoap.NAMESPACE_FILTER);
			wsdl11Definition.setSchema(filtersSchema);

			return wsdl11Definition;
		}

		@Bean
		public XsdSchema filtersSchema() {
			return new SimpleXsdSchema(new ClassPathResource(RouterSoap.RESOURCE_FILTER));
		}

}
