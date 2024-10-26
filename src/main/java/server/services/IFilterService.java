package server.services;

import java.util.List;

import server.entities.Filter;

public interface IFilterService {
	public List<Filter> getFiltersByUserId(int userId);
	public Filter saveFilter(Filter filter, int userId);
}
