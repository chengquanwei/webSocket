package util;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Deprecated
@Component
public class RequestListener implements ServletRequestListener {

	public void requestDestroyed(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void requestInitialized(ServletRequestEvent src) {
		// TODO Auto-generated method stub
		((HttpServletRequest)src.getServletRequest()).getSession();
	}
	

}
