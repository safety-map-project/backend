package httpHandler;

import java.io.IOException;
import java.sql.SQLException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import dao.CctvDao;
import dao.Impl.CctvDaoImpl;
import util.HandlerUtil;

public class CCTVHandler implements HttpHandler {
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		if(exchange.getRequestMethod().equals("GET")) {
			CctvDao CCTVDao = new CctvDaoImpl();
			String cctvStr = "";
			
			try {
				cctvStr = CCTVDao.listCctv().toString();
			}catch (SQLException sqle) {
				sqle.printStackTrace();
			}
			
			HandlerUtil.sendResponse(exchange, cctvStr);
		}
		
	};
}
