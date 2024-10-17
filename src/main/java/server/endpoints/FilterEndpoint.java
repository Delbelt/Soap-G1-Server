package server.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import server.entities.Filter;
import server.entities.User;
import server.services.IFilterService;
import server.services.IUserService;
import server.soap.RouterSoap;
import soap.filters.*;

@Endpoint
public class FilterEndpoint {

	@Autowired
    private final IFilterService filterService;
	@Autowired
    private final IUserService userService;

	public FilterEndpoint(IFilterService filterService, IUserService userService) {
        this.filterService = filterService;
        this.userService = userService;
    }

    @PayloadRoot(namespace = RouterSoap.NAMESPACE_FILTER, localPart = "SaveFilterRequest")
    @ResponsePayload
    public SaveFilterResponse saveFilter(@RequestPayload SaveFilterRequest request) {
        SaveFilterResponse response = new SaveFilterResponse();
        
        // Obtengo el usuario por ID
        User user = userService.findById(request.getFilter().getUser());
        
     // Verificar que el usuario no sea nulo
        if (user == null) {
        	throw new IllegalArgumentException("User not found for ID: " + request.getFilter().getUser());
        }
        
        // Creo y configuro el filtro
        Filter filter = new Filter();
        filter.setUser(user);
        filter.setName(request.getFilter().getName());
        filter.setFilters(request.getFilter().getFilters());

        // Guardo el filtro en la base de datos
        Filter savedFilter = filterService.saveFilter(filter);
        
        // Mapeo de `Filter` a `FilterSoap`
        FilterSoap filterSoap = request.getFilter();
        filterSoap.setId(savedFilter.getId());
       

        // Establezco el filtro guardado en la respuesta
        response.setFilter(filterSoap);
        return response;
    }
    
}
