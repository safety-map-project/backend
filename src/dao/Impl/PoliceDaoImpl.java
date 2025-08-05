package dao.Impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.PoliceDao;
import model.Police;
import util.ConnectionUtil;

public class PoliceDaoImpl implements PoliceDao {

	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;

	public PoliceDaoImpl() {
		conn = ConnectionUtil.getConnectionUtil().getConnection();
	}

	@Override
	public int insertPolice(List<Police> policeList) throws IOException, InterruptedException, SQLException {
		String insertSQL = "INSERT INTO MAP.POLICE(POLICEID, LOCATION, LAT, LOG, REGIONID, NAME) VALUES (MAP.SEQ_POLICE.NEXTVAL, ?, ? , ? , 29110 , ?)";
		int count = 0;

		try {
			pstmt = conn.prepareStatement(insertSQL);

			for (Police police : policeList) {
				pstmt.setString(1, police.getLocation()); // 주소
				pstmt.setDouble(2, police.getLat()); // 위도
				pstmt.setDouble(3, police.getLog()); // 경도
				pstmt.setString(4, police.getName()); // 파출소
				count += pstmt.executeUpdate();

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		}

		return count;
	}

	@Override
	public Police getPolice(int policeId) throws SQLException {
		return null;
	}

	@Override
	public int updatePolice(Police police) throws SQLException {
		return 0;
	}

	@Override
	public int deletePolice(int policeId) throws SQLException {
		return 0;
	}

	@Override
	public int insertPolice(Police police) throws SQLException {
		return 0;
	}

	@Override
	public List<Police> listPolice() throws SQLException {
		String sql = " select policeid, location, lat, log, regionId, name from map.police ";

		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<Police> policeList = new ArrayList<Police>();

		try {
			while (rs.next()) {
				Police police = new Police();
				police.setPoliceId(rs.getInt("policeid"));
				police.setLocation(rs.getString("location"));
				police.setLat(rs.getDouble("lat"));
				police.setLog(rs.getDouble("log"));
				police.setRegionId(rs.getInt("regionId"));
				police.setName(rs.getString("name"));
				policeList.add(police);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rs.close();
			pstmt.close();
		}
		return policeList;
	}
}