package server.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import server.entities.Catalog;

public interface IPdfExportService {
	ByteArrayInputStream exportCatalogsToPdf(List<Catalog> catalogs);
}
