2018年4月9日13:39:15
	滚动条置底，并清除多余代码

	
	
	
	
	
	
	
	
	
	
	
	
	
错误记录：
	String path=ClassUtils.getDefaultClassLoader().getResource("/").getPath()+propertiesPath;
		/*	若此处报java.lang.NoClassDefFoundError: org/springframework/util/ClassUtils错误
		 *     错误原因：项目的jar包未发布到tomcat中
		 *     解决方法：选中项目右击 - properties - Deployment Assembly - 点击右边的Add - Java Build Path Entries - maven Dependencies - ok 重启 
		 *     
		 */
		 


计划：
	①、实现局域网单聊和群聊的基本功能
			单聊的实现思路： 首先读取好友列表显示在页面上，当选择一个好友进行聊天时，就已知该好友的ip地址了，
						然后根据发送消息，判断最后的字段是ip地址还是群聊标志在后台进行单聊和群聊的判断进行特定消息的发送。
	②、实现文件相互传输的功能

		