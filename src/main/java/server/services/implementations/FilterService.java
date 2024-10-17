package server.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import server.entities.Filter;
import server.entities.User;
import server.repositories.IFilterRepository;
import server.services.IFilterService;

@Slf4j
@Service
public class FilterService implements IFilterService {

	@Autowired
    private IFilterRepository repository;

	@Override
	@Transactional
    public Filter saveFilter(Filter filter) {
        return repository.save(filter); // Guarda un nuevo filtro
    }

    @Override
	@Transactional(readOnly = true)
    public List<Filter> getFiltersByUser(User user) {
        return repository.findByUser(user); // Obtiene filtros por usuario
    }

    @Override
	@Transactional
    public void deleteFilter(Long filterId) {
    	repository.deleteById(filterId); // Elimina un filtro por ID
    }
}
