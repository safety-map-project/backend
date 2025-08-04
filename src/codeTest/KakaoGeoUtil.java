package codeTest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonArray;

public class KakaoGeoUtil {

    public static Double[] getCoordinates(String address) throws IOException {
        String apiKey = "QvgnWQKUCrQ%2BqCLLRnIed%2BwfPlYFx7tS1JNhyY%2F9KwVyrLLMWGzLj565XUBiT6NYFl2kmqf6T%2B5BqoS6RVryLg%3D%3D"; // 여기에 실제 키 입력
        String encodedAddress = URLEncoder.encode(address, StandardCharsets.UTF_8);
        String urlStr = "https://api.odcloud.kr/api/15077036/v1/uddi:6b371c66-09a5-4efd-8445-bfd53672542e"
                + "?page=1&perPage=2100&serviceKey=" + apiKey;


        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", apiKey);

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
        JsonArray documents = json.getAsJsonArray("documents");

        if (documents.size() > 0) {
            JsonObject first = documents.get(0).getAsJsonObject();
            double lat = first.get("y").getAsDouble(); // 위도
            double lng = first.get("x").getAsDouble(); // 경도
            return new Double[] { lat, lng };
        }

        return null;
    }

    public static void main(String[] args) throws IOException {
        String address = "서울특별시 강남구 역삼동";
        Double[] coords = getCoordinates(address);

        if (coords != null) {
            System.out.println("위도: " + coords[0]);
            System.out.println("경도: " + coords[1]);
        } else {
            System.out.println("좌표 변환 실패");
        }
    }
}