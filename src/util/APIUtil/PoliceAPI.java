package util.APIUtil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;

import model.Police;

public class PoliceAPI {

	List<Police> policeList = null;

	// 파출소 API 주소
	private static String PoliceAPI_URL = "https://api.odcloud.kr/api/15077036/v1/uddi:6b371c66-09a5-4efd-8445-bfd53672542e?page=1&perPage=2100&serviceKey=QvgnWQKUCrQ%2BqCLLRnIed%2BwfPlYFx7tS1JNhyY%2F9KwVyrLLMWGzLj565XUBiT6NYFl2kmqf6T%2B5BqoS6RVryLg%3D%3D";

	// 클라이언트 객체 생성
	private static HttpClient client = HttpClient.newHttpClient();

	// API 가져오는 메서드
	public List<Police> getPoliceAPI() throws IOException, InterruptedException {
		// 요청할 데이터 객체
		HttpRequest request = null;

		// 요청할 데이터
		// httpequest 반환
		request = HttpRequest.newBuilder().uri(URI.create(PoliceAPI_URL)).GET().build();

		HttpResponse response = client.send(request, BodyHandlers.ofString());
		System.out.println(response.body());
		
		
		

		return policeList;

	}

}
