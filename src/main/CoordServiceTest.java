package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;

import model.Coord;
import service.CoordService;
import service.Impl.CoordServiceImpl;
import util.APIUtil.CoordAPI;

public class CoordServiceTest {

	public static void main(String[] args) {
		
		CoordService coordService = new CoordServiceImpl();
		
		try {
			List<Coord> coordList = CoordAPI.makeCoordList();
			
			for(Coord coord : coordList) {
				coordService.insertCoord(coord);
			}
			
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}
	
}
