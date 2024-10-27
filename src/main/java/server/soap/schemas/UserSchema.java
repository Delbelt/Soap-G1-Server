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
public class UserSchema {
//http://localhost:8080/ws/users.wsdl
	@Bean(name = "users")
    public DefaultWsdl11Definition usersWsdl11Definition(SimpleXsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        
        wsdl11Definition.setPortTypeName("UsersPort");
        wsdl11Definition.setLocationUri(RouterSoap.URLMAPPINGS);
        wsdl11Definition.setTargetNamespace(RouterSoap.NAMESPACE_USER);
        wsdl11Definition.setSchema(usersSchema);

        return wsdl11Definition;
    }

    @Bean
    public SimpleXsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource(RouterSoap.RESOURCE_USER));
    }
}
