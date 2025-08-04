package main;

import java.sql.SQLException;
import java.util.List;

import model.Cctv;
import service.CctvService;
import service.Impl.CctvServiceImpl;
import service.Impl.RegionServiceImpl;
import util.APIUtil.CctvAPI;

public class CctvServiceTest {

	public static void main(String[] args) {
		CctvService cctvService = new CctvServiceImpl();

//		Cctv cctv = new Cctv(1, "서울특별시 역삼동", 11680, 12.3, 45.6);
//		try {
//			cctvService.insertCctv(cctv);
//		}catch (SQLException sqle){
//			sqle.printStackTrace();
//		}
		try {
			List<Cctv> cctvList = CctvAPI.save();
			for (Cctv cctv : cctvList) {
				cctvService.insertCctv(cctv);
			}
			
//			cctvService.listCctv().stream().forEach(System.out::println);
		}catch (SQLException sqle){
			sqle.printStackTrace();
		}
	}

}
