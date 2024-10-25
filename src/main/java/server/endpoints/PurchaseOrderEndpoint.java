package server.endpoints;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import server.entities.OrderItem;
import server.entities.PurchaseOrder;
import server.services.implementations.PurchaseOrderService;
import server.soap.RouterSoap;
import soap.purchase_orders.OrderItemSoap;
import soap.purchase_orders.PurchaseOrderSoap;
import soap.purchase_orders.SearchPurchaseOrdersRequest;
import soap.purchase_orders.SearchPurchaseOrdersResponse;

@Endpoint
public class PurchaseOrderEndpoint {
	
	@Autowired
    private PurchaseOrderService purchaseOrderService;
	
	@PayloadRoot(namespace = RouterSoap.NAMESPACE_PURCHASE_ORDER, localPart = "SearchPurchaseOrdersRequest")
    @ResponsePayload
    public SearchPurchaseOrdersResponse searchOrders(@RequestPayload SearchPurchaseOrdersRequest request) {
		 // Obtener parámetros de la solicitud
        String productCode = request.getProductCode();
        LocalDateTime startRequestDate = null;
        LocalDateTime endRequestDate= null;
        
        if(request.getStartRequestDate() != null) {
        	startRequestDate = request.getStartRequestDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
        }
        if(request.getEndRequestDate() != null) {
        	endRequestDate = request.getEndRequestDate().toGregorianCalendar().toZonedDateTime().toLocalDateTime();
        }
     
        String status = request.getStatus();
        String storeCode = request.getStoreCode();
        
        // Buscar órdenes de compra usando el servicio
        List<PurchaseOrder> orders = purchaseOrderService.searchOrders(productCode, startRequestDate, endRequestDate, status, storeCode);

        // Crear la respuesta
        SearchPurchaseOrdersResponse response = new SearchPurchaseOrdersResponse();
        
        // Convertir cada PurchaseOrder a PurchaseOrderSoap
        for (PurchaseOrder order : orders) {
            PurchaseOrderSoap orderSoap = new PurchaseOrderSoap();
            orderSoap.setIdPurchaseOrder(order.getIdPurchaseOrder());
            orderSoap.setState(order.getState());
            orderSoap.setObservations(order.getObservations());
            
         // Usar el método de conversión para las fechas
            try {
            	if (startRequestDate != null) {
            		GregorianCalendar calendarRequestDate = GregorianCalendar.from(order.getRequestDate().atZone(ZoneId.systemDefault()));
            		orderSoap.setRequestDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendarRequestDate));
            	}
            	if (endRequestDate != null) {
            		GregorianCalendar calendarReceiptDate = GregorianCalendar.from(order.getReceiptDate().atZone(ZoneId.systemDefault()));
            		orderSoap.setReceiptDate(DatatypeFactory.newInstance().newXMLGregorianCalendar(calendarReceiptDate));
            	}
			} catch (DatatypeConfigurationException e) {
				e.printStackTrace();
			}
           
         
            orderSoap.setStoreCode(order.getStore().getCode());

            // Si hay una lista de items, convertir cada uno a OrderItemSoap
            if (order.getItems() != null) {
                
                for (OrderItem item : order.getItems()) {
                	
                	OrderItemSoap itemSoap = new OrderItemSoap();
                    itemSoap.setCode(item.getCode());
                    itemSoap.setQuantity(item.getQuantity());

                    // Añadir cada itemSoap a la lista
                    orderSoap.getItems().add(itemSoap);

                }
                
            }
            // Agregar el orderSoap a la respuesta
            response.getPurchaseOrders().add(orderSoap);
        }

        return response;
    }
        
}
 