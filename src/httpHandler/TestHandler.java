package httpHandler;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import util.HandlerUtil;

public class TestHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		// options 확인하는 메서드
		HandlerUtil.optionsEquals(exchange);

		// JSON 배열 형식 CCTV 데이터
		String json = """
				[
				  {"lat": 37.5665, "lng": 126.9780},
				  {"lat": 37.5675, "lng": 126.9770},
				  {"lat": 37.5655, "lng": 126.9790}
				]
				""";

		// json 보내는 메서드
		HandlerUtil.sendResponse(exchange, json);
		
	} // handle

}
