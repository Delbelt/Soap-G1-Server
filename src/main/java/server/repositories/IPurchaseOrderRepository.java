package server.repositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import server.entities.PurchaseOrder;

public interface IPurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
	
	@Query("FROM PurchaseOrder po inner join fetch po.items where po.idPurchaseOrder=(:id)")
	public PurchaseOrder findByDispatchOrderRelationship(int id);
	
	@Query("SELECT po FROM PurchaseOrder po LEFT JOIN FETCH po.items")
    List<PurchaseOrder> getAllRelationship();
	
	@Query("SELECT po FROM PurchaseOrder po LEFT JOIN FETCH po.items WHERE FUNCTION('DATE', po.requestDate) = :requestDate")
    List<PurchaseOrder> getAllRelationshipFromDate(LocalDate requestDate);
	
	@Query("SELECT po FROM PurchaseOrder po LEFT JOIN FETCH po.items WHERE po.state = :state")
    List<PurchaseOrder> getAllFromState(String state);
	
		@Query("SELECT o FROM PurchaseOrder o " +
		"JOIN o.items i " +
		"JOIN o.store s " +
		"WHERE(:code IS NULL OR i.code = :code) " +
		"AND(:startRequestDate IS NULL OR o.requestDate >= :startRequestDate) " +
		"AND(:endRequestDate IS NULL OR o.requestDate <= :endRequestDate) " +
		"AND (:state IS NULL OR o.state = :state) " +
		"AND (:codeStore IS NULL OR s.code = :codeStore)")
		List<PurchaseOrder> findFilteredOrders(
				@Param("code") String code,
				@Param("startRequestDate") LocalDateTime startRequestDate,
		        @Param("endRequestDate") LocalDateTime endRequestDate,
				@Param("state") String state,
				@Param("codeStore") String codeStore);
		
		
	@Query("SELECT o FROM PurchaseOrder o WHERE o.idPurchaseOrder = :id")
	Optional<PurchaseOrder> findByidPurchaseOrder(@Param("id") int id);
	
}