package server.services.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import server.entities.Catalog;
import server.entities.Product;
import server.repositories.ICatalogRepository;
import server.repositories.IProductRepository;
import server.services.ICatalogService;

@Slf4j
@Service
public class CatalogService implements ICatalogService{
	@Autowired
	private ICatalogRepository catalogRepository;
	
	@Autowired
	private IProductRepository productRepository;
	
	@Override
	@Transactional(readOnly = true)
    public Catalog getCatalogById(long id) {
        Optional<Catalog> catalog = catalogRepository.findById(id);
        return catalog.orElse(null);  
    }
	
	@Override
	@Transactional(readOnly = false)
    public Catalog createCatalog(String name) {
        Catalog catalog = new Catalog();
        catalog.setName(name);
        catalog.setProducts(new ArrayList<>()); 
        
        return catalogRepository.save(catalog); 
        
    }

    @Override
    @Transactional(readOnly = false)
    public Catalog addProductToCatalog(Long catalogId, String productCode) {
        
        Catalog catalog = catalogRepository.findById(catalogId)
                .orElseThrow(() -> new RuntimeException("Catalog with ID " + catalogId + " not found"));

        
        Product product = productRepository.findByCode(productCode);		
        if (product == null) {	
        	throw new RuntimeException("Product with code " + productCode + " not found");
        }

        
        catalog.getProducts().add(product);

        return catalogRepository.save(catalog);
    }
	
    @Override
    @Transactional(readOnly = true)
    public List<Catalog> getAllCatalogs() {
        return catalogRepository.findAll();
    }
    
	@Override
	@Transactional(readOnly = false)
    public void deleteCatalog(Long id) {
        if (catalogRepository.existsById(id)) {
            catalogRepository.deleteById(id);
        } else {
            throw new RuntimeException("Catalog with ID " + id + " not found");
        }
    }
}
