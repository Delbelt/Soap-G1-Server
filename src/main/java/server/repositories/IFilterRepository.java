package server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import server.entities.Filter;
import server.entities.User;

import java.util.List;

public interface IFilterRepository extends JpaRepository<Filter, Long> {
    List<Filter> findByUser(User user); // Encuentra filtros por usuario
}
