package util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.util.ClassUtils;



/**
 * 配置文件解析类
 */
public class ConfigUtil {
	
	
	public static String getConfigVal(String propertiesPath,String key){
		InputStreamReader reader=null;
		String path=ClassUtils.getDefaultClassLoader().getResource("/").getPath()+propertiesPath;
		try {
			reader=new InputStreamReader(new FileInputStream(path),"utf-8");
			Properties properties=new Properties();
			
			properties.load(reader);
			
			return  StringUtil.isEmpty(properties.getProperty(key))?null:
						properties.getProperty(key).trim();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(reader!=null)
					reader.close();
			} catch (Exception e2) {
			}
		}
	}
	
	public static Map<String , String> getConfigVal(String propertiesPath,String... keys){
		
		InputStreamReader reader=null;
		String path=ClassUtils.getDefaultClassLoader().getResource("/").getPath()+propertiesPath;
		Map<String, String> returnMap=new HashMap<String, String>();
		try {
			reader=new InputStreamReader(new FileInputStream(path),"utf-8");
			Properties properties=new Properties();
			
			properties.load(reader);
			for (int i = 0;keys!=null&& i < keys.length; i++) {
				returnMap.put(keys[i],StringUtil.isEmpty(properties.getProperty(keys[i]))?null:
						properties.getProperty(keys[i]).trim() );
			}
			return  returnMap;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(reader!=null)
					reader.close();
			} catch (Exception e2) {
			}
		}
	}
	public static Map<String , String> getConfigVal(String propertiesPath){
		
		InputStreamReader reader=null;
		String path=ClassUtils.getDefaultClassLoader().getResource("/").getPath()+propertiesPath;
		Map<String, String> returnMap=new HashMap<String, String>();
		try {
			reader=new InputStreamReader(new FileInputStream(path),"utf-8");
			Properties properties=new Properties();
			
			properties.load(reader);
			
			Enumeration<?> nameEnums=properties.propertyNames();
			Object newElement=null;
			while (nameEnums.hasMoreElements()){
				newElement=nameEnums.nextElement();
				try {
					returnMap.put(newElement.toString(), properties.get(newElement).toString().trim());
				} catch (Exception e) {
				}
			}
			return  returnMap;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try {
				if(reader!=null)
					reader.close();
			} catch (Exception e2) {
			}
		}
	}
	
	
	/**
	 * 添加配置文件数据
	 * @param propertiesPath
	 * @param key
	 * @param val
	 * @return
	 */
	public static void addOrModifyProp(String propertiesPath,String key,String val){
		
		if(!StringUtil.allNotEmpty(key,val))
			return;
		String path=ClassUtils.getDefaultClassLoader().getResource("/").getPath()+propertiesPath;
		OutputStreamWriter writer=null;
		InputStreamReader reader=null;
		try {
			
			Properties properties=new Properties();
			reader=new InputStreamReader(new FileInputStream(path),"utf-8");
			properties.load(reader);
			
			writer=new OutputStreamWriter(new FileOutputStream(path),"utf-8");
			try {
				
				properties.setProperty(key, val);
			} catch (Exception e) {
			}
			
			
			properties.store(writer, null);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(writer!=null)
					writer.close();
			} catch (Exception e) {
			}
			try {
				if(reader!=null)
					reader.close();
			} catch (Exception e) {
			}
			
		}
		
		
	}
	
	/**
	 * 添加或修改基础配置数据
	 * @param key
	 * @return
	 */
	public static void addOrModify(String propertiesPath,String[] keys,String[] vals){
		
		String path=ClassUtils.getDefaultClassLoader().getResource("/").getPath()+propertiesPath;
		OutputStreamWriter writer=null;
		InputStreamReader reader=null;
		try {
			
			Properties properties=new Properties();
			reader=new InputStreamReader(new FileInputStream(path),"utf-8");
			properties.load(reader);
			
			
			writer=new OutputStreamWriter(new FileOutputStream(path),"utf-8");
			
			for (int i = 0; i < keys.length; i++) {
				if(!StringUtil.allNotEmpty(keys[i], vals[i]))
					continue;
				try {
					properties.setProperty(keys[i], vals[i]);
					
				} catch (Exception e) {
				}
			}
			properties.store(writer, null);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(writer!=null)
					writer.close();
			} catch (Exception e) {
			}
			try {
				if(reader!=null)
					reader.close();
			} catch (Exception e) {
			}
			
		}
		
	}
	
}
