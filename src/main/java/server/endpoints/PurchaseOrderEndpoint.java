package server.endpoints;

import java.time.LocalDateTime;
import java.util.List;

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
        String startRequestDate = request.getStartRequestDate();
        String endRequestDate = request.getEndRequestDate();  

        // Convertir las cadenas a LocalDateTime si no son nulas
        LocalDateTime startDateTime = null;
        LocalDateTime endDateTime = null;
        
        if (startRequestDate != null && !startRequestDate.isEmpty()) {
        	startDateTime = LocalDateTime.parse(startRequestDate);
        }
        if (endRequestDate != null && !endRequestDate.isEmpty()) {
        	endDateTime = LocalDateTime.parse(endRequestDate);
        }

        String status = request.getStatus();
        String storeCode = request.getStoreCode();

        // Buscar órdenes de compra usando el servicio
        List<PurchaseOrder> orders = purchaseOrderService.searchOrders(productCode, startDateTime, endDateTime, status, storeCode);

        // Crear la respuesta
        SearchPurchaseOrdersResponse response = new SearchPurchaseOrdersResponse();
        // Convertir cada PurchaseOrder a PurchaseOrderSoap
        for (PurchaseOrder order : orders) {
            PurchaseOrderSoap orderSoap = new PurchaseOrderSoap();
            orderSoap.setIdPurchaseOrder(order.getIdPurchaseOrder());
            orderSoap.setState(order.getState());
            orderSoap.setObservations(order.getObservations());

            // Asignar las fechas como cadenas
            if (order.getRequestDate() != null) {
                orderSoap.setRequestDate(order.getRequestDate().toString()); // o formatea la fecha como desees
            }
            if (order.getReceiptDate() != null) {
                orderSoap.setReceiptDate(order.getReceiptDate().toString()); // o formatea la fecha como desees
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

        // Imprimir los valores para depuración
        System.err.println("Product Code: " + productCode);
        System.err.println("Start Request Date: " + startRequestDate);
        System.err.println("End Request Date: " + endRequestDate);
        System.err.println("Status: " + status);
        System.err.println("Store Code: " + storeCode);

        return response;
    }
}
