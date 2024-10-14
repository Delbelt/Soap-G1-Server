package server.soap;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;

@Configuration
@EnableWs
public class WebServiceConfig extends WsConfigurerAdapter {
	
	// Para ver si funciona: http://localhost:8080/ws/countries.wsdl

	@Bean
	public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {

		MessageDispatcherServlet servlet = new MessageDispatcherServlet();

		servlet.setApplicationContext(applicationContext);
		servlet.setTransformWsdlLocations(true);

		return new ServletRegistrationBean<>(servlet, RouterSoap.URLMAPPINGS);
	}

	@Bean(name = "countries")
	public DefaultWsdl11Definition defaultWsdl11Definition(SimpleXsdSchema countriesSchema) {

		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();

		wsdl11Definition.setPortTypeName("CountryPort");
		wsdl11Definition.setLocationUri("/ws/*");
		wsdl11Definition.setTargetNamespace(RouterSoap.NAMESPACE_COUNTRY);
		wsdl11Definition.setSchema(countriesSchema);

		return wsdl11Definition;
	}

	@Bean
	public SimpleXsdSchema countriesSchema() {
		
		return new SimpleXsdSchema(new ClassPathResource("xsd/countries.xsd"));
	}
}