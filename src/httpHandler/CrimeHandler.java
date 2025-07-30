package httpHandler;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import util.HandlerUtil;

public class CrimeHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		HandlerUtil.optionsEquals(exchange);
		
	}

}
