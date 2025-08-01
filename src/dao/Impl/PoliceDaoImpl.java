package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.PoliceDao;
import model.Police;
import util.ConnectionUtil;

public class PoliceDaoImpl implements PoliceDao {

	private Connection conn;
	private PreparedStatement st;
	ResultSet set;

	public PoliceDaoImpl() {
		conn = ConnectionUtil.getConnectionUtil().getConnection();
	}

	@Override
	public List<Police> listPolice() throws SQLException {
		return null;
	}

	@Override
	public Police getPolice(int policeId) throws SQLException {
		String sql = " SELECT POLICEID, LOCATION, REGIONID, LAT, LOG " +
					" FROM POLICE " +
					" WHERE POLICEID = ? ";
		
		st = conn.prepareStatement(sql);
		st.setInt(1, policeId);
		set = st.executeQuery();
		
		Police police = new Police();
		if (set != null) {
			while (set.next()) {
				police.setPoliceId(set.getInt("policeId"));
				police.setLocation(set.getString("location"));
				police.setRegionId(set.getString("regionId"));
				police.setLat(set.getInt("lat"));
				police.setLog(set.getInt("log"));
			}
		}
		
		return police;
	}

	@Override
	public int insertPolice(Police police) throws SQLException {
		String sql = " INSERT INTO POLICE " +
					" VALUES(SEQ_POLICE.NEXTVAL, ?, ?, ?, ?) ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(0, sql);
//		rs = pstmt.executeQuery();
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
