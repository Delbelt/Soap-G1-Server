package server.entities;

import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="filter")
public class Filter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="userId", nullable=false)
	private User user;
	
	@Column(name="name", nullable=false)
	private String name;
	
	// Filtros los cuales pueden ser NULL
	
	private String productCode;
	
	@Column(name = "startRequestDate",columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime startRequestDate; // Fecha de inicio para solicitudes
	
	@Column(name = "endRequestDate",columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime endRequestDate;   // Fecha de fin para solicitudes
	
	@Column(name = "startReceiptDate",columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startReceiptDate;  // Fecha de inicio para recepción
	
	@Column(name = "endReceiptDate",columnDefinition = "DATETIME")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endReceiptDate;    // Fecha de fin para recepción
    
	@Column(name ="status")
	private String status;
	
	@Column(name="codeStore") // No aplico relacion ya que solo es para filtrar
	private String store;
	

}
