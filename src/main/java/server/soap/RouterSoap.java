package server.soap;

public class RouterSoap {
	
	// COUNTRY
	public final static String NAMESPACE_COUNTRY = "http://soap/countries";
	public final static String RESOURCE_COUNTRY  = "xsd/countries.xsd";
	
	// GENERAL
	public final static String URLMAPPINGS  = "/ws/*";
	
	// PURCHASE ORDER
	public final static String NAMESPACE_PURCHASE_ORDER = "http://soap/purchase_orders";
	public final static String RESOURCE_PURCHASE_ORDER = "xsd/purchase_orders.xsd";
	
	// FILTER
	public final static String NAMESPACE_FILTER = "http://soap/filters";
	public final static String RESOURCE_FILTER = "xsd/filters.xsd";

}
