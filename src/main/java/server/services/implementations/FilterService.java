package server.services.implementations;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import server.entities.Filter;
import server.repositories.IFilterRepository;
import server.services.IFilterService;

@Slf4j
@Service
public class FilterService implements IFilterService {
	@Autowired
    private IFilterRepository filterRepository;

	@Override
	@Transactional(readOnly = true)
    public List<Filter> getFiltersByUserId(int userId) {
		
		var filters = filterRepository.findByUserId(userId);
		
		if (filters.isEmpty()) {
			throw new NoSuchElementException("Filters with UserId " + userId + " not found");
		}
		
		log.info("[FilterService][getFiltersByUserId]: " + filters);
		
        return filters;
    }
}
