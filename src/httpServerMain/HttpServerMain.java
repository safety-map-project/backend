package httpServerMain;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

import httpHandler.CCTVHandler;
import httpHandler.CrimeHandler;
import httpHandler.TestHandler;
import httpHandler.TestHandler2;
import httpHandler.PoliceHandler;
import httpHandler.RegionHandler;

public class HttpServerMain {
	
	public static void main(String[] args) {
		
		try {
			
			// 포트번호 8000으로 http 객체 생성
			HttpServer httpServer = HttpServer.create(new InetSocketAddress(8000), 0);
			
			// 컨텍스트(요청 경로) 설정
			// 핸들러 설정
			// 요청 경로에 따라 핸들러가 수행됨
			httpServer.createContext("/api/cctv", new CCTVHandler());
			httpServer.createContext("/api/police", new PoliceHandler());
			httpServer.createContext("/api/crime", new CrimeHandler());
			httpServer.createContext("/api/region", new RegionHandler());
			httpServer.createContext("/test", new TestHandler());
			httpServer.createContext("/test2", new TestHandler2());
			
			httpServer.setExecutor(null); // executor null: 단일 스레드 사용
			httpServer.start(); // 서버 시작
			System.out.println("http://localhost:8000 서버 시작됨");
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
	} // main

}// class
