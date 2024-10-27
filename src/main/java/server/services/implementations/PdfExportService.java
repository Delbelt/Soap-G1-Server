package server.services.implementations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import java.net.URL;
import java.util.Base64;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;

import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;

import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;

import lombok.extern.slf4j.Slf4j;
import server.entities.Catalog;
import server.entities.Product;
import server.services.IPdfExportService;

@Slf4j
@Service
public class PdfExportService implements IPdfExportService{
	
	@Override
	public ByteArrayInputStream exportCatalogsToPdf(List<Catalog> catalogs) {
	    Document document = new Document(PageSize.A4);
	    ByteArrayOutputStream out = new ByteArrayOutputStream();

	    try {
	        PdfWriter.getInstance(document, out);
	        document.open();

	        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
	        Font productFont = new Font(Font.HELVETICA, 12, Font.NORMAL);
	        Font boldFont = new Font(Font.HELVETICA, 12, Font.BOLD);

	        for (Catalog catalog : catalogs) {
	            // Añadir el nombre del catalogo
	            Paragraph catalogTitle = new Paragraph(catalog.getName(), titleFont);
	            catalogTitle.setAlignment(Element.ALIGN_CENTER);
	            catalogTitle.setSpacingAfter(20f);  // Espaciado despues del título
	            document.add(catalogTitle);

	            for (Product product : catalog.getProducts()) {
	                
	                Paragraph productName = new Paragraph(product.getName(), boldFont);
	                productName.setAlignment(Element.ALIGN_LEFT);
	                document.add(productName);

	                Paragraph productDetails = new Paragraph("Código: " + product.getCode() 
	                    + " | Tamaño: " + product.getSize() 
	                    + " | Color: " + product.getColor() 
	                    + " | Activo: " + (product.isActive() ? "Sí" : "No"), productFont);
	                productDetails.setAlignment(Element.ALIGN_LEFT);
	                document.add(productDetails);

	                if (product.getPhoto() != null && !product.getPhoto().isEmpty()) {
	                    try {
	                        if (product.getPhoto().startsWith("http")) {
	                            // Caso en que la imagen es una URL
	                            Image image = Image.getInstance(new URL(product.getPhoto()));
	                            image.scaleToFit(150, 150);  // Ajustar tamaño de la imagen
	                            image.setAlignment(Element.ALIGN_CENTER);
	                            document.add(image);
	                        } else {
	                            // Caso en que la imagen es base64
	                            byte[] imageBytes = Base64.getDecoder().decode(product.getPhoto());
	                            Image image = Image.getInstance(imageBytes);  // Crear la imagen a partir de los bytes
	                            image.scaleToFit(100, 100);  
	                            document.add(image);
	                        }
	                    } catch (Exception e) {
	                        Paragraph errorText = new Paragraph("Imagen inválida", productFont);
	                        errorText.setAlignment(Element.ALIGN_CENTER);
	                        document.add(errorText);  // Mostrar mensaje de error si no se puede cargar la imagen
	                    }
	                } else {
	                    Paragraph noImageText = new Paragraph("Sin imagen disponible", productFont);
	                    noImageText.setAlignment(Element.ALIGN_CENTER);
	                    document.add(noImageText);  // Si no hay imagen
	                }

	                // Agregar espaciado antes de la línea separadora
	                document.add(new Paragraph(" "));  // Linea en blanco para espaciado

	                // Línea separadora entre productos
	                LineSeparator separator = new LineSeparator();
	                separator.setLineWidth(1f);  // Espesor de la linea
	                separator.setOffset(-2);     // Ajustar el offset para mover la linea
	                document.add(new Chunk(separator));

	                // Agregar espaciado después de la línea separadora
	                document.add(new Paragraph(" "));
	            }

	            document.newPage();  // Nueva pagina para el siguiente catálogo
	        }

	        document.close();

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return new ByteArrayInputStream(out.toByteArray());
	}
}
