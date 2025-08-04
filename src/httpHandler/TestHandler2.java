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
				"  },\n" +
				"  {\n" +
				"    \"name\": \"역삼지구대\",\n" +
				"    \"address\": \"서울특별시 강남구 역삼동 언주로108길\",\n" +
				"    \"lat\": 37.5083487223016,\n" +
				"    \"lng\": 127.040377150597\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"강남경찰서 삼성2파출소\",\n" +
				"    \"address\": \"서울특별시 강남구 선릉로 626\",\n" +
				"    \"lat\": 37.5124098757427,\n" +
				"    \"lng\": 127.043517868339\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"강남경찰서 논현1파출소\",\n" +
				"    \"address\": \"서울특별시 강남구 학동로 169\",\n" +
				"    \"lat\": 37.5138651397213,\n" +
				"    \"lng\": 127.02932175785\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"강남경찰서 신사파출소\",\n" +
				"    \"address\": \"서울특별시 강남구 도산대로 143\",\n" +
				"    \"lat\": 37.5183718725269,\n" +
				"    \"lng\": 127.024133471487\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"강남경찰서 삼성1파출소\",\n" +
				"    \"address\": \"서울특별시 강남구 영동대로112길 4\",\n" +
				"    \"lat\": 37.5149074360264,\n" +
				"    \"lng\": 127.060545856713\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"강남경찰서 청담파출소\",\n" +
				"    \"address\": \"서울특별시 강남구 학동로95길 19\",\n" +
				"    \"lat\": 37.5207979095084,\n" +
				"    \"lng\": 127.054275568617\n" +
				"  },\n" +
				"  {\n" +
				"    \"name\": \"강남경찰서 논현2파출소\",\n" +
				"    \"address\": \"서울특별시 강남구 학동로 227\",\n" +
				"    \"lat\": 37.5152038126855,\n" +
				"    \"lng\": 127.033846444455\n" +
				"  }\n" +
				"]";



		// json 보내는 메서드
		HandlerUtil.sendResponse(exchange, json);
		
	} // handle

}
