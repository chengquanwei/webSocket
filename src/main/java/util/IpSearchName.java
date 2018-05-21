package util;

public class IpSearchName {
	
	private static final String ip_search_name="ip_search_name.properties";
	
	
	public static String getName(String ip){
		String name = ConfigUtil.getConfigVal(ip_search_name,ip);
		return name;
	}
	public static void setName(String ip,String name){
		ConfigUtil.addOrModifyProp(ip_search_name,ip,name);
	}
}
