package httpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RegionHandler implements HttpHandler {

    private static final Gson gson = new Gson();
    // 운영에서는 "*" 대신 허용할 도메인을 지정하거나 환경설정으로 뺄 것
    private static final String ALLOWED_ORIGIN = "*";
    private static final int MAX_NAME_LENGTH = 100;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            String method = exchange.getRequestMethod();
            log("요청 들어옴: " + method + " " + exchange.getRequestURI());

            // CORS Preflight 처리
            if ("OPTIONS".equalsIgnoreCase(method)) {
                sendPreflight(exchange);
                return;
            }

            // 공통 응답 헤더 (실제 응답)
            Headers respHeaders = exchange.getResponseHeaders();
            respHeaders.set("Content-Type", "application/json; charset=utf-8");
            respHeaders.set("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
            respHeaders.set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            respHeaders.set("Access-Control-Allow-Headers", "Content-Type");

            // 쿼리 파라미터 파싱
            Map<String, String> params = parseQueryParams(exchange.getRequestURI());
            String name = params.get("name");

            // 유효성 검사
            if (name == null || name.isBlank()) {
                JsonObject err = new JsonObject();
                err.addProperty("status", "error");
                err.addProperty("message", "'name' 쿼리 파라미터가 필요합니다.");
                sendJson(exchange, 400, err);
                return;
            }
            if (name.length() > MAX_NAME_LENGTH) {
                JsonObject err = new JsonObject();
                err.addProperty("status", "error");
                err.addProperty("message", "'name'이 너무 깁니다. 최대 " + MAX_NAME_LENGTH + "자까지 허용됩니다.");
                sendJson(exchange, 400, err);
                return;
            }

            // 실제 비즈니스 로직 자리 (여기선 예시로 그대로 되돌려줌)
            JsonObject data = new JsonObject();
            data.addProperty("status", "ok");
            data.addProperty("name", name);
            // 예: region 정보도 포함하려면 아래처럼 추가 가능
            JsonObject region = new JsonObject();
            region.addProperty("safety", "safe"); // 실제 판단 로직을 대체
            region.addProperty("resolvedName", name);
            data.add("region", region);

            sendJson(exchange, 200, data);
        } catch (Exception e) {
            log("예외 발생: " + e.getMessage());
            JsonObject err = new JsonObject();
            err.addProperty("status", "error");
            err.addProperty("message", "서버 내부 오류");
            sendJson(exchange, 500, err);
        } finally {
            exchange.close(); // 안전하게 닫기
        }
    }

    private void sendPreflight(HttpExchange exchange) throws IOException {
        Headers h = exchange.getResponseHeaders();
        h.set("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
        h.set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        h.set("Access-Control-Allow-Headers", "Content-Type");
        exchange.sendResponseHeaders(204, -1); // 본문 없음
        exchange.close();
    }

    private void sendJson(HttpExchange exchange, int statusCode, JsonObject body) throws IOException {
        byte[] bytes = gson.toJson(body).getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private Map<String, String> parseQueryParams(URI uri) {
        Map<String, String> result = new HashMap<>();
        String raw = uri.getRawQuery();
        if (raw == null || raw.isBlank()) return result;
        String[] pairs = raw.split("&");
        for (String pair : pairs) {
            int idx = pair.indexOf('=');
            try {
                if (idx > 0) {
                    String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
                    String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
                    result.put(key, value);
                } else {
                    String key = URLDecoder.decode(pair, StandardCharsets.UTF_8);
                    result.put(key, "");
                }
            } catch (IllegalArgumentException e) {
                // 디코딩 실패한 잘못된 파라미터 무시
                log("쿼리 파싱 중 무시된 항목: " + pair);
            }
        }
        return result;
    }

    private void log(String s) {
        System.out.println("[RegionHandler] " + s);
    }
}
