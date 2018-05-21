package util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class IpAddress {

	public InetAddress getLocalHostLANAdress() throws SocketException{
		
		InetAddress candidateAddress = null;
		
		for(Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();){
			NetworkInterface iface = (NetworkInterface) ifaces.nextElement();
			for(Enumeration inetAddrs = iface.getInetAddresses();inetAddrs.hasMoreElements();){
				InetAddress inetAddr = (InetAddress) inetAddrs.nextElement();
				if(!inetAddr.isLoopbackAddress()){
					if(!inetAddr.isSiteLocalAddress()){
						return inetAddr;
					}else if(candidateAddress == null){
						candidateAddress = inetAddr;
					}
				}
			}
		}
		if(candidateAddress != null){
			return candidateAddress;
		}
		InetAddress jdkSupplieAddress;
		try {
			jdkSupplieAddress = InetAddress.getLocalHost();
			return jdkSupplieAddress;
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public String getHostAddress(){
		try {
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
