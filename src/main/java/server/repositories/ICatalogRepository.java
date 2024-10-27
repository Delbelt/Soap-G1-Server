package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import server.entities.Catalog;

public interface ICatalogRepository extends JpaRepository<Catalog, Long>{

}
