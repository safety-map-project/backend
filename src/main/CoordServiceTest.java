package main;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.JsonArray;

import model.Coord;
import service.CoordService;
import service.Impl.CoordServiceImpl;
import util.APIUtil.CoordAPI;

public class CoordServiceTest {

	public static void main(String[] args) {
		
		List<Coord> coordList = CoordAPI.makeCoordList();
		coordList.stream().forEach(System.out::println);
		//		CoordService coordService = new CoordServiceImpl();
//		
//		try {
//			
//			int result = 0;
//			List<Coord> coordList = CoordAPI.makeCoordList();
//			for(Coord coord : coordList) {
//				result = coordService.insertCoord(coord);
//				if(result>0) result++;
//			}
//			System.out.println(result + "건 등록 완료");
//			
//			
//			
//		} catch(SQLException sqle) {
//			sqle.printStackTrace();
//		}
		
	}
	
}
