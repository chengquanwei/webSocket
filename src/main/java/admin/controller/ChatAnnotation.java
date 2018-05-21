package admin.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import util.IpAddress;
import util.IpSearchName;

/**
 * 
 * @author Administrator
 *
 */
@ServerEndpoint("/websocket/{ip}")
public class ChatAnnotation {
	
	private static final String LAN_GROUP = "lan_group";
	private static int onlineCount = 0;
	private static CopyOnWriteArraySet<ChatAnnotation> webSocketSet = new CopyOnWriteArraySet<ChatAnnotation>();
	private static Map<String,CopyOnWriteArraySet<ChatAnnotation>> socketMap = Collections.synchronizedMap(new HashMap<String,CopyOnWriteArraySet<ChatAnnotation>>());
	private Session session;
	
	@OnOpen
	public void onOpen(@PathParam(value="ip") String ip,Session session){
		this.session = session;
		webSocketSet.add(this);
		addOnlineCount();
		System.out.println("连接-ip地址："+ip);
		System.out.println("新加入，在线人数"+getOnlineCount());
		//设计单聊
		if(socketMap == null || socketMap.get(ip) == null ||  socketMap.get(ip).size() == 0){
			CopyOnWriteArraySet<ChatAnnotation> sockeIP = new CopyOnWriteArraySet<ChatAnnotation>();
			sockeIP.add(this);
			socketMap.put(ip, sockeIP);
		}else if(socketMap != null && socketMap.get(ip) != null ){
			CopyOnWriteArraySet<ChatAnnotation> sockeIP = socketMap.get(ip);
			sockeIP.add(this);
		}		
	}
	
	@OnClose
	public void onClose(@PathParam(value="ip") String ip){
		webSocketSet.remove(this);
		socketMap.get(ip).remove(this);
		subOnlineCount();
		IpAddress ia = new IpAddress();
		System.out.println("断开-ip地址："+ia.getHostAddress());
	}
	
	
	@OnMessage
	public void onMessage(String message,Session session){
		String msg[] = message.split("~@");
		String nickname = IpSearchName.getName(msg[0]);
		
		if(nickname == null){
			//若配置文件中不存在该用户的ip，则将此用户的ip添加到配置文件中
			IpSearchName.setName(msg[0], msg[0]);
//			nickname = msg[0];
			nickname = IpSearchName.getName(msg[0]);
		}
		
		if(LAN_GROUP.equals(msg[2])){
			//群发
			for(ChatAnnotation item : webSocketSet){
				try {
					item.sendMessage( nickname + "@~" + msg[1] );
				} catch (IOException e) {
					e.printStackTrace();
					continue;
				}
			}
		}else{//单聊
			CopyOnWriteArraySet<ChatAnnotation> toSockeIP = socketMap.get(msg[2]);
			//
			if(toSockeIP == null){
				CopyOnWriteArraySet<ChatAnnotation> fromSockeIP = socketMap.get(msg[0]);
				String toIpName = IpSearchName.getName(msg[2]);
				if(toIpName != null){
					System.out.println("你的好友 " + toIpName + " 已离线");
					for(ChatAnnotation item : fromSockeIP){
						try {
							item.sendMessage("你的好友 " + toIpName + " 已离线");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else{
					for(ChatAnnotation item : fromSockeIP){
						try {
							item.sendMessage("用户不存在！");
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}else{
				for(ChatAnnotation item : toSockeIP){
					try {
						item.sendMessage(nickname + "@~" + msg[1]);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	@OnError
	public void onError(Session session,Throwable error){
		System.out.println("发生错误");
		error.printStackTrace();
	}

	private void sendMessage(String message) throws IOException {
		// TODO Auto-generated method stub
		this.session.getBasicRemote().sendText(message);
	}

	private static synchronized void subOnlineCount() {
		// TODO Auto-generated method stub
		ChatAnnotation.onlineCount--;
	}

	private static synchronized int getOnlineCount() {
		// TODO Auto-generated method stub
		return onlineCount;
	}

	private static synchronized void addOnlineCount() {
		// TODO Auto-generated method stub
		ChatAnnotation.onlineCount++;
	}
	
}
