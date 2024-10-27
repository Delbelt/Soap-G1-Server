package server.services;

import java.util.List;

import server.entities.Catalog;

public interface ICatalogService {
	Catalog getCatalogById(long id);
	
	Catalog createCatalog(String name);
	
	Catalog addProductToCatalog(Long catalogId, String productCode);
	
	List<Catalog> getAllCatalogs();
	
	void deleteCatalog(Long id);
}
