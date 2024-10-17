package server.services;

import java.util.List;

import server.entities.Filter;
import server.entities.User;

public interface IFilterService {

	public Filter saveFilter(Filter filter);
	
	public List<Filter> getFiltersByUser(User user);
	
	public void deleteFilter(Long filterId);
}
