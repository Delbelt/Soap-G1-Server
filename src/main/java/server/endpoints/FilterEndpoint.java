package server.endpoints;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import server.entities.Filter;
import server.services.implementations.FilterService;
import server.soap.RouterSoap;
import soap.filters.DeleteFilterRequest;
import soap.filters.DeleteFilterResponse;
import soap.filters.EditFilterRequest;
import soap.filters.EditFilterResponse;
import soap.filters.EditFilterType;
import soap.filters.FilterSoap;
import soap.filters.GetFiltersRequest;
import soap.filters.GetFiltersResponse;
import soap.filters.SaveFilterRequest;
import soap.filters.SaveFilterResponse;
import soap.filters.SaveFilterType;

@Endpoint
public class FilterEndpoint {

	@Autowired
    private FilterService filterService;

    @PayloadRoot(namespace = RouterSoap.NAMESPACE_FILTER, localPart = "getFiltersRequest")
    @ResponsePayload
    public GetFiltersResponse getFilters(@RequestPayload GetFiltersRequest request) {
        GetFiltersResponse response = new GetFiltersResponse();
        List<Filter> filters = filterService.getFiltersByUserId(request.getUserId());

        List<FilterSoap> filterSoaps = filters.stream()
                .map(this::convertToFilterSoap)
                .collect(Collectors.toList());

        response.getFilters().addAll(filterSoaps);
        return response;
    }
    

    private FilterSoap convertToFilterSoap(Filter filter) {
        FilterSoap filterSoap = new FilterSoap();
        filterSoap.setId(filter.getId());
        filterSoap.setName(filter.getName());
        filterSoap.setProductCode(filter.getProductCode());
        
        // Convierto de LocalDateTime a XML
        filterSoap.setStartRequestDate(convertToXMLGregorianCalendar(filter.getStartRequestDate()));
        filterSoap.setEndRequestDate(convertToXMLGregorianCalendar(filter.getEndRequestDate()));
        filterSoap.setStartReceiptDate(convertToXMLGregorianCalendar(filter.getStartReceiptDate()));
        filterSoap.setEndReceiptDate(convertToXMLGregorianCalendar(filter.getEndReceiptDate()));
        
        filterSoap.setStatus(filter.getStatus());
        filterSoap.setStore(filter.getStore());
        return filterSoap;
    }

    private XMLGregorianCalendar convertToXMLGregorianCalendar(LocalDateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        try {
            GregorianCalendar calendar = GregorianCalendar.from(dateTime.atZone(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } catch (Exception e) {
            throw new RuntimeException("Error converting LocalDateTime to XMLGregorianCalendar", e);
        }
    }
    
    ///
    @PayloadRoot(namespace = RouterSoap.NAMESPACE_FILTER, localPart = "saveFilterRequest")
    @ResponsePayload
    public SaveFilterResponse saveFilter(@RequestPayload SaveFilterRequest request) {
        Filter filter = convertToFilterEntity(request.getFilter());

        // Guardar el filtro y devolver el resultado
        Filter savedFilter = filterService.saveFilter(filter, request.getFilter().getUserId());

        SaveFilterResponse response = new SaveFilterResponse();
        response.setFilter(convertToFilterSoap(savedFilter));
        return response;
    }

    private Filter convertToFilterEntity(SaveFilterType filterSoap) {
        Filter filter = new Filter();

        filter.setName(filterSoap.getName());
        filter.setProductCode(filterSoap.getProductCode());
        filter.setStartRequestDate(convertToLocalDateTime(filterSoap.getStartRequestDate()));
        filter.setEndRequestDate(convertToLocalDateTime(filterSoap.getEndRequestDate()));
        filter.setStartReceiptDate(convertToLocalDateTime(filterSoap.getStartReceiptDate()));
        filter.setEndReceiptDate(convertToLocalDateTime(filterSoap.getEndReceiptDate()));
        filter.setStatus(filterSoap.getStatus());
        filter.setStore(filterSoap.getStore());
        return filter;
    }

    private Filter convertToEditFilterEntity(EditFilterType editFilterType) {
        Filter filter = new Filter();

        // Asignar el ID del filtro
        filter.setId(editFilterType.getId());

        // Asignar los demás parámetros
        filter.setName(editFilterType.getName());
        filter.setProductCode(editFilterType.getProductCode());
        filter.setStartRequestDate(convertToLocalDateTime(editFilterType.getStartRequestDate()));
        filter.setEndRequestDate(convertToLocalDateTime(editFilterType.getEndRequestDate()));
        filter.setStartReceiptDate(convertToLocalDateTime(editFilterType.getStartReceiptDate()));
        filter.setEndReceiptDate(convertToLocalDateTime(editFilterType.getEndReceiptDate()));
        filter.setStatus(editFilterType.getStatus());
        filter.setStore(editFilterType.getStore());

        return filter;
    }




    private LocalDateTime convertToLocalDateTime(XMLGregorianCalendar xmlCalendar) {
        return xmlCalendar != null ? xmlCalendar.toGregorianCalendar().toZonedDateTime().toLocalDateTime() : null;
    }

    
    @PayloadRoot(namespace = RouterSoap.NAMESPACE_FILTER, localPart = "editFilterRequest")
    @ResponsePayload
    public EditFilterResponse editFilter(@RequestPayload EditFilterRequest request) {
        Filter filter = convertToEditFilterEntity(request.getFilter());
        Filter updatedFilter = filterService.editFilter(filter);

        EditFilterResponse response = new EditFilterResponse();
        response.setFilter(convertToFilterSoap(updatedFilter));
        return response;
    }

    @PayloadRoot(namespace = RouterSoap.NAMESPACE_FILTER, localPart = "deleteFilterRequest")
    @ResponsePayload
    public DeleteFilterResponse deleteFilter(@RequestPayload DeleteFilterRequest request) {
        boolean success = filterService.deleteFilter(request.getId());

        DeleteFilterResponse response = new DeleteFilterResponse();
        response.setSuccess(success);
        return response;
    }

    
}
