package util;
/**
 * String工具类
 * 
 * @Data 2015/5/25
 * @Atuh fang fei
 * @return
 */
public class StringUtil {
	
	/**
	 * 
	 * 不为空
	 */
	public static boolean notEmpty(String str){
		if(str==null) return false;
		else if("".equals(str.trim())) return false;
		return true;
	}
	/**
	 * 都不为空
	 * @param str
	 * @return
	 */
	public static boolean allNotEmpty(String... str){
		if(str.length<1)return false;
		boolean flag=false;
		for (int i = 0; i < str.length; i++) {
			flag=notEmpty(str[i]);
			if(!flag)
				break;
		}
		return flag;
	}
	/**
	 * 都不为空
	 * @param str
	 * @return
	 */
	public static boolean notAllEmpty(String... str){
		if(str.length<1)return false;
		boolean flag=false;
		for (int i = 0; i < str.length; i++) {
			flag=notEmpty(str[i]);
			if(flag)
				break;
		}
		return flag;
	}
	
	/**
	 * 为空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		
		if(str==null || "".equals(str.trim())) 
			return true;
		else 
			return false;
		
	}
	/**
	 * 为空
	 * @param str
	 * @return
	 */
	public static boolean allIsEmpty(String... str){
		
		if(str.length<1) return false;
		
		boolean flag=true;
		for (int i = 0; i < str.length; i++) {
			flag=isEmpty(str[i]);
			if(!flag)
				break;
		}
		return flag;
		
	}
	/**
	 * 
	 * 获取字符串中所有的数字字符串 如1a2b3v 返回的是123
	 * @param str
	 * @return
	 */
	public static String getIntStr(String str){
		
		StringBuffer newStr=new StringBuffer();
		char[] chars=str.toCharArray();
		
		for (int i = 0; i < chars.length; i++) {
			Character c=chars[i];
			if(c.toString().matches("\\d"))
				newStr.append(c);
		}
		return newStr.toString();
	}
	
	/**
	 * oldStr-xxxx{},xxxx{}
	 * rexStr-\\{\\}
	 * strs-{"aaa","bbb"}
	 * return -xxxxxaaa,xxxxbbb
	 * @param oldStr
	 * @param rexStr
	 * @param strs
	 * @return
	 */
	public static String replace(String oldStr,String rexStr,String[] strs){
		if(StringUtil.isEmpty(rexStr)) rexStr="\\{\\}";
		for (int i = 0; i < strs.length; i++) {
			if(strs[i]==null)
				oldStr=oldStr.replaceFirst(rexStr, "null");
			else
				oldStr=oldStr.replaceFirst(rexStr, strs[i]);
		}
		return oldStr;
	}
	/**
	 * oldStr-xxxx{},xxxx{}
	 * rexStr-\\{\\}
	 * strs-{"aaa","bbb"}
	 * return -xxxxxaaa,xxxxbbb
	 * @param oldStr
	 * @param rexStr
	 * @param strs
	 * @return
	 */
	public static String replace(String oldStr,String... strs){
		return replace(oldStr, null, strs);
	}
	
}
