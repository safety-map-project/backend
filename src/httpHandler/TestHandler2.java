package httpHandler;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import util.HandlerUtil;

public class TestHandler2 implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {

		// options 확인하는 메서드
		HandlerUtil.optionsEquals(exchange);

		String json = "[\n" +
				"  {\n" +
				"    \"name\": \"상봉파출소\",\n" +
				"    \"address\": \"서울특별시 중랑구 중랑천로 69\",\n" +
				"    \"lat\": 37.595655,\n" +
				"    \"lng\": 127.085453\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"먹골파출소\",\n" +
				"    \"address\": \"서울특별시 중랑구 숙선옹주로7길 1\",\n" +
				"    \"lat\": 37.598000,\n" +
				"    \"lng\": 127.078920\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"면목본동파출소\",\n" +
				"    \"address\": \"서울특별시 중랑구 상봉로 27\",\n" +
				"    \"lat\": 37.588080,\n" +
				"    \"lng\": 127.089909\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"면목삼팔파출소\",\n" +
				"    \"address\": \"서울특별시 중랑구 사가정로41길 45\",\n" +
				"    \"lat\": 37.580080,\n" +
				"    \"lng\": 127.088746\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"망우지구대\",\n" +
				"    \"address\": \"서울특별시 중랑구 망우로 391\",\n" +
				"    \"lat\": 37.597454,\n" +
				"    \"lng\": 127.094000\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"용마지구대\",\n" +
				"    \"address\": \"서울특별시 중랑구 동일로 566\",\n" +
				"    \"lat\": 37.595375,\n" +
				"    \"lng\": 127.092157\n" +
				"  }\n" +
				"]";

		// json 보내는 메서드
		HandlerUtil.sendResponse(exchange, json);
		
	} // handle

}
