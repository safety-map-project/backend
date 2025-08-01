package main;

import java.util.List;

import model.Coord;
import util.APIUtil.CoordAPI;

public class CoordServiceTest {

	public static void main(String[] args) {
		
		List<Coord> cList = CoordAPI.makeCoordList();
		cList.stream().forEach(System.out::println);
		
	}
	
}
