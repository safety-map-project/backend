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
		
//		CoordService coordService = new CoordServiceImpl();
//		
//		List<Coord> coordList = CoordAPI.makeCoordList();
//		for(Coord coord : coordList) {
//			try {
//				coordService.insertCoord(
//						new Coord(coord.getCoordId()
//								, coord.getLat()
//								, coord.getLog()
//								, coord.getRegionId()));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		List<Coord> coordList = CoordAPI.makeCoordList();
		for(Coord coord : coordList) {
			System.out.println(coord);
		}
		
	}
	
}
