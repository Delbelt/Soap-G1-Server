package server.services.implementations;

import java.util.List;
import java.util.NoSuchElementException;

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
    private IFilterRepository filterRepository;
	@Autowired
    private UserService userService;

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
	
	@Transactional
	public Filter saveFilter(Filter filter, int userId) {
		User user = userService.findById(userId);
		
		if (user == null) {
			throw new NoSuchElementException("User with id " + userId + " not found");
		}
		
		filter.setUser(user);
	    log.info("[FilterService][saveFilter]: Saving filter - " + filter);
	    return filterRepository.save(filter);
	}
	
	@Transactional
	public Filter editFilter(Filter filter) {
	    // Busca el filtro existente
	    Filter existingFilter = filterRepository.findById(filter.getId())
	            .orElseThrow(() -> new NoSuchElementException("Filter with id " + filter.getId() + " not found"));

	    // Actualiza los campos necesarios
	    existingFilter.setName(filter.getName());
	    existingFilter.setProductCode(filter.getProductCode());
	    existingFilter.setStartRequestDate(filter.getStartRequestDate());
	    existingFilter.setEndRequestDate(filter.getEndRequestDate());
	    existingFilter.setStartReceiptDate(filter.getStartReceiptDate());
	    existingFilter.setEndReceiptDate(filter.getEndReceiptDate());
	    existingFilter.setStatus(filter.getStatus());
	    existingFilter.setStore(filter.getStore());

	    log.info("[FilterService][editFilter]: Editing filter - " + existingFilter);
	    return filterRepository.save(existingFilter);
	}

	@Transactional
	public boolean deleteFilter(int filterId) {
	    if (!filterRepository.existsById(filterId)) {
	        throw new NoSuchElementException("Filter with id " + filterId + " not found");
	    }

	    filterRepository.deleteById(filterId);
	    log.info("[FilterService][deleteFilter]: Deleted filter with id - " + filterId);
	    return true; // Retorna true si la eliminación fue exitosa
	}


}
