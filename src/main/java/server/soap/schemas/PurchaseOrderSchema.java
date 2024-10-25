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
public class PurchaseOrderSchema {
	
	// Definir el WSDL: http://localhost:8080/ws/purchase_orders.wsdl
    @Bean(name = "purchase_orders")
    public DefaultWsdl11Definition defaultWsdl11Definition(SimpleXsdSchema purchaseOrdersSchema) {

        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();

        wsdl11Definition.setPortTypeName("PurchaseOrderPort");
        wsdl11Definition.setLocationUri(RouterSoap.URLMAPPINGS);
        wsdl11Definition.setTargetNamespace(RouterSoap.NAMESPACE_PURCHASE_ORDER);
        wsdl11Definition.setSchema(purchaseOrdersSchema);

        return wsdl11Definition;
    }

    @Bean
    public SimpleXsdSchema purchaseOrdersSchema() {
        // Ruta del archivo XSD para PurchaseOrders
        return new SimpleXsdSchema(new ClassPathResource(RouterSoap.RESOURCE_PURCHASE_ORDER));
    }

}
