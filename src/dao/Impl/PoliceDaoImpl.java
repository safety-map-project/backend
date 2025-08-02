package dao.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.PoliceDao;
import model.Police;
import util.ConnectionUtil;
import util.APIUtil.PoliceAPI;

public class PoliceDaoImpl implements PoliceDao {

	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;

	public PoliceDaoImpl() {
		conn = ConnectionUtil.getConnectionUtil().getConnection();
	}

	@Override
	public List<Police> listPolice() throws SQLException {
		return null;
	}

	@Override
	public Police getPolice(int policeId) throws SQLException {
		return null;
	}

	@Override
		public int insertPolice(Police police) throws IOException, InterruptedException, SQLException {
			PoliceAPI api = new PoliceAPI();
	
			// JSON 문자열 받아오기
			String jsonStr = api.getPoliceAPI();
	
			// JSON → Police 객체 리스트
			List<Police> policeList = api.insertPoliceList(jsonStr);
	
			// 지역명 기반으로 REGIONID 설정 (if문 방식)
			for (Police p : policeList) {
				String location = p.getLocation();
	
				if (location.contains("강남구")) {
					p.setRegionId(1);
				} else if (location.contains("강서구")) {
					p.setRegionId(2);
				} else if (location.contains("중구")) {
					p.setRegionId(3);
				} else if (location.contains("송파구")) {
					p.setRegionId(4);
				} else if (location.contains("노원구")) {
					p.setRegionId(5);
				} else if (location.contains("성동구")) {
					p.setRegionId(6);
				} else {
					System.out.println("지역명 매칭 실패: " + location);
			}
		}

		// DB 연결
		if (conn != null) {
			String insertSQL = "INSERT INTO POLICE (POLICEID, NAME, LOCATION, LAT, LOG, REGIONID) VALUES (SEQ_POLICE.NEXTVAL, ?, ?, ?, ?, ?)";
			String coordSQL = "SELECT LAT, LOG FROM REGION_COORD WHERE REGIONID = ?";

			PreparedStatement insertStmt = null;
			PreparedStatement coordStmt = null;

			try {
				insertStmt = conn.prepareStatement(insertSQL);
				coordStmt = conn.prepareStatement(coordSQL);

				for (Police p : policeList) {
					if (p.getRegionId() == 0) {
						continue;
					}

					coordStmt.setInt(1, p.getRegionId());
					ResultSet rs = coordStmt.executeQuery();

					double lat = 0;
					double log = 0;

					if (rs.next()) {
						lat = rs.getDouble("LAT");
						log = rs.getDouble("LOG");
					} else {
						System.out.println("REGION_COORD에 REGIONID " + p.getRegionId() + "가 없음. 건너뜀.");
						continue;
					}

					insertStmt.setString(1, p.getPolice_address());
					insertStmt.setString(2, p.getLocation());
					insertStmt.setDouble(3, lat);
					insertStmt.setDouble(4, log);
					insertStmt.setInt(5, p.getRegionId());

					insertStmt.executeUpdate();
				}

			} finally {
				if (insertStmt != null)
					insertStmt.close();
				if (coordStmt != null)
					coordStmt.close();
				conn.close();
			}

		} else {
			System.out.println("DB 연결 실패");
		}

		return 0;
	}

	@Override
	public int updatePolice(Police police) throws SQLException {
		return 0;
	}

	@Override
	public int deletePolice(int policeId) throws SQLException {
		return 0;
	}

}
