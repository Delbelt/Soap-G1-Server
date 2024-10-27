package server.endpoints;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import server.entities.Catalog;
import server.entities.Product;
import server.services.ICatalogService;
import server.services.IPdfExportService;
import server.soap.RouterSoap;
import soap.catalogs.AddProductToCatalogRequest;
import soap.catalogs.AddProductToCatalogResponse;
import soap.catalogs.CreateCatalogRequest;
import soap.catalogs.CreateCatalogResponse;
import soap.catalogs.DeleteCatalogRequest;
import soap.catalogs.DeleteCatalogResponse;
import soap.catalogs.ExportCatalogToPdfRequest;
import soap.catalogs.ExportCatalogToPdfResponse;

import soap.catalogs.GetCatalogByIdRequest;
import soap.catalogs.GetCatalogByIdResponse;
import soap.catalogs.SoapCatalog;
import soap.catalogs.SoapProduct;

@Endpoint
public class CatalogEndpoint {
	@Autowired
    private ICatalogService catalogService;
    
    @Autowired
    private IPdfExportService pdfExportService;
    
    private SoapCatalog createSoapCatalog(Catalog catalog) {
        SoapCatalog soapCatalog = new SoapCatalog();
        soapCatalog.setId(catalog.getId());
        soapCatalog.setName(catalog.getName());
        
        List<SoapProduct> soapProducts = new ArrayList<>();
        for (Product product : catalog.getProducts()) {
            SoapProduct soapProduct = new SoapProduct();
            soapProduct.setCode(product.getCode());
            soapProduct.setName(product.getName());
            soapProduct.setSize(product.getSize());
            soapProduct.setColor(product.getColor());
            soapProduct.setActive(product.isActive());
            soapProduct.setPhoto(product.getPhoto());
            
            soapProducts.add(soapProduct);
        }
        
        soapCatalog.getProducts().addAll(soapProducts);
        
        return soapCatalog;
    }
    
    @PayloadRoot(namespace = RouterSoap.NAMESPACE_CATALOG, localPart = "CreateCatalogRequest")
    @ResponsePayload
    public CreateCatalogResponse createCatalog(@RequestPayload CreateCatalogRequest request) {
        CreateCatalogResponse response = new CreateCatalogResponse();
        
        Catalog catalog = catalogService.createCatalog(request.getName());
        SoapCatalog soapCatalog = createSoapCatalog(catalog);
        
        response.setCatalog(soapCatalog);
        return response;
    }
    
    
    
    @PayloadRoot(namespace = RouterSoap.NAMESPACE_CATALOG, localPart = "GetCatalogByIdRequest")
    @ResponsePayload
    public GetCatalogByIdResponse getCatalogById(@RequestPayload GetCatalogByIdRequest request) {
        GetCatalogByIdResponse response = new GetCatalogByIdResponse();

        // Obtiene el catalogo por ID y lo convierte a SoapCatalog
        Catalog catalog = catalogService.getCatalogById(request.getCatalogId());
        if (catalog != null) {
            SoapCatalog soapCatalog = createSoapCatalog(catalog);
            response.setCatalog(soapCatalog);
        } else {
            response.setCatalog(null); 
        }

        return response;
    }


    // Endpoint para eliminar un catálogo
    @PayloadRoot(namespace = RouterSoap.NAMESPACE_CATALOG, localPart = "DeleteCatalogRequest")
    @ResponsePayload
    public DeleteCatalogResponse deleteCatalog(@RequestPayload DeleteCatalogRequest request) {
        DeleteCatalogResponse response = new DeleteCatalogResponse();

        catalogService.deleteCatalog(request.getCatalogId());
     
        response.setMessage("Catalog deleted successfully"); 
        return response;
    }

    // Endpoint para agregar un producto a un catálogo
    @PayloadRoot(namespace = RouterSoap.NAMESPACE_CATALOG, localPart = "AddProductToCatalogRequest")
    @ResponsePayload
    public AddProductToCatalogResponse addProductToCatalog(@RequestPayload AddProductToCatalogRequest request) {
        AddProductToCatalogResponse response = new AddProductToCatalogResponse();
        
        Catalog catalog = catalogService.addProductToCatalog(request.getCatalogId(), request.getProductCode());
        
     
        response.setCatalog(createSoapCatalog(catalog));
        return response;
    }
    
    @PayloadRoot(namespace = RouterSoap.NAMESPACE_CATALOG, localPart = "ExportCatalogToPdfRequest")
    @ResponsePayload
    public ExportCatalogToPdfResponse exportCatalogToPdf(@RequestPayload ExportCatalogToPdfRequest request) {
    	ExportCatalogToPdfResponse response = new ExportCatalogToPdfResponse();

        // Obtener el catalogo
        Catalog catalog = catalogService.getCatalogById(request.getCatalogId());

        if (catalog != null) {
            // Exportar a PDF
            ByteArrayInputStream pdfStream = pdfExportService.exportCatalogsToPdf(List.of(catalog));
            String pdfFileName = "catalog_" + catalog.getName() + ".pdf";
            String pdfFilePath = "files/" + pdfFileName;

            // Asegurarse de que la carpeta 'files' exista
            File directory = new File("files");
            if (!directory.exists()) {
                directory.mkdir();
            }

            try (FileOutputStream fos = new FileOutputStream(pdfFilePath)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = pdfStream.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytesRead);
                }
                response.setStatus("Export successful");
                response.setPdfUrl("http://localhost:8080/files/" + pdfFileName); // URL para el PDF
            } catch (IOException e) {
                response.setStatus("Export failed: " + e.getMessage());
            }
        } else {
            response.setStatus("Catalog not found");
        }

        return response;
    }
    
    

}
