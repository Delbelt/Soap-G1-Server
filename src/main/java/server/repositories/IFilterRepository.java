package server.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import server.entities.Filter;

@Repository
public interface IFilterRepository extends JpaRepository<Filter, Integer> {
    List<Filter> findByUserId(int userId);

}
