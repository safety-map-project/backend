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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;

import model.Police;
import util.ConnectionUtil;

public class PoliceAPI {

	List<Police> policeList = null;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 파출소 API 주소
	private static String PoliceAPI_URL = "https://api.odcloud.kr/api/15077036/v1/uddi:6b371c66-09a5-4efd-8445-bfd53672542e?page=1&perPage=2100&serviceKey=QvgnWQKUCrQ%2BqCLLRnIed%2BwfPlYFx7tS1JNhyY%2F9KwVyrLLMWGzLj565XUBiT6NYFl2kmqf6T%2B5BqoS6RVryLg%3D%3D";

	// 클라이언트 객체 생성
	private static HttpClient client = HttpClient.newHttpClient();

	// API에서 JSON 데이터 가져오는 메서드
	public Object getPoliceAPI() throws IOException, InterruptedException, SQLException {
		// 요청할 데이터 객체
		HttpRequest request = null;

		// 요청할 데이터
		// httpequest 반환
		request = HttpRequest.newBuilder().uri(URI.create(PoliceAPI_URL)).GET().build();

		// 받아온 데이터
		HttpResponse response = client.send(request, BodyHandlers.ofString());
		String jsonStr = (String)response.body();

		return jsonStr;

	}

	// JSON 데이터 파싱 후에 리스트에 넣는 메서드
	public List<Police> insertList(String jsonStr) {

		return policeList;
	}

	

	// 1. JSON 파싱 > 관서명하고 구분 붙여서 1개, 주소 1개 ( 을지 지구대, 서울특별시 중구)
	// 2. police 객체 리스트에 추가

}
