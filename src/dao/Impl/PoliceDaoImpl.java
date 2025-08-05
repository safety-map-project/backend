package dao.Impl;

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
	public int insertPolice(List<Police> policeList) throws SQLException {
		String sql = "INSERT INTO MAP.POLICE (POLICEID, LOCATION, LAT, LOG, REGIONID, NAME) VALUES (MAP.SEQ_POLICE.NEXTVAL, ?, ?, ?, ?, ?)";
		int successCount = 0;

		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			for (Police p : policeList) {
				try {
					pstmt.setString(1, p.getLocation());
					pstmt.setDouble(2, p.getLat());
					pstmt.setDouble(3, p.getLog());
					pstmt.setInt(4, p.getRegionId());
					pstmt.setString(5, p.getPolice_address());

					int result = pstmt.executeUpdate();
					if (result == 1) {
						successCount++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return successCount;
	}

	@Override
	public List<Police> listPolice() throws SQLException {
		String sql = " select policeid, location, lat, log, regionId, name from map.police ";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<Police> policeList = new ArrayList<Police>();
		while(rs.next()) {
			Police police = new Police();
			police.setPoliceId(rs.getInt("policeid"));
			police.setLocation(rs.getString("location"));
			police.setLat(rs.getDouble("lat"));
			police.setLog(rs.getDouble("log"));
			police.setRegionId(rs.getInt("regionId"));
			police.setName(rs.getString("name"));
			policeList.add(police);
		}
		return policeList;
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
}