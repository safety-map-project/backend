package util;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class HandlerUtil {

	public static void optionsEquals(HttpExchange exchange) throws IOException {
		// 요청한 메서드의 이름을 가져옴
		String method = exchange.getRequestMethod();

		// 프론트에서 헤더를 보내면 cors에서 options로 요청을 보냄
		// OPTIONS 요청 처리
		if ("OPTIONS".equalsIgnoreCase(method)) {
			exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
			exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
			exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");
			exchange.sendResponseHeaders(204, -1); // 204: 응답은 성공했으나 본문이 없음, -1: 응답의 본문의 길이가 없음
			return;
		}
	} // optionsEquals
	
	public static void sendResponse(HttpExchange exchange, String json) throws IOException {
		
		exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");
		exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*"); // CORS 허용
		byte[] bytes = json.getBytes("UTF-8"); // json byte로 가져오기
		exchange.sendResponseHeaders(200, bytes.length); // 200 응답코드, 응답 길이 보냄
		
		// 바디에 json담음
		OutputStream os = exchange.getResponseBody(); 
		os.write(bytes);
		os.close();
		
	} // sendResponse

}
