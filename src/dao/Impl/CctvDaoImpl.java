package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CctvDao;
import model.Cctv;
import util.ConnectionUtil;

public class CctvDaoImpl implements CctvDao {

	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;

	public CctvDaoImpl() {
		conn = ConnectionUtil.getConnectionUtil().getConnection();
	}

	@Override
	public List<Cctv> listCctv() throws SQLException {
		String sql = " select cctvId, location, regionId, lat, log " +
					" from MAP.CCTV ";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		List<Cctv> cctvList = new ArrayList<Cctv>();
		if (rs != null) {
			while (rs.next()) {
				Cctv cctv = new Cctv();
				cctv.setCctvId(rs.getInt("cctvid"));
				cctv.setLocation(rs.getString("location"));
				cctv.setLat(rs.getDouble("lat"));
				cctv.setLog(rs.getDouble("log"));
				cctv.setRegionId(rs.getInt("regionid"));
				cctvList.add(cctv);
			}
		}
		
		return cctvList;
	}

	@Override
	public Cctv getCctv(int cctvId) throws SQLException {
		String sql = " select cctvid, location, lat, log, regionid " +
					" from MAP.CCTV " +
					" where cctvid = ? ";
		
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		Cctv cctv = new Cctv();
		if (rs != null) {
			while (rs.next()) {
				cctv.setCctvId(rs.getInt("cctvid"));
				cctv.setLocation(rs.getString("location"));
				cctv.setLat(rs.getDouble("lat"));
				cctv.setLog(rs.getDouble("log"));
				cctv.setRegionId(rs.getInt("regionid"));
			}
		}
		return cctv;
	}

	@Override
	public int insertCctv(Cctv cctv) throws SQLException {
		int result = 0;
		String sql = " insert into MAP.CCTV " +
					" values(MAP.SEQ_CCTV.nextval, ?, ?, ?, ?) ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cctv.getLocation());
		pstmt.setDouble(2, cctv.getLat());
		pstmt.setDouble(3, cctv.getLog());
		pstmt.setInt(4, cctv.getRegionId());
		result = pstmt.executeUpdate();
		
		pstmt.close();
		
		return result;
	}

	@Override
	public int updateCctv(Cctv cctv) throws SQLException {
		String sql = " update MAP.CCTV " +
					" set location = ?, lat = ?, log = ?, regionid = ? " +
					" where cctvid = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, cctv.getLocation());
		pstmt.setDouble(2, cctv.getLat());
		pstmt.setDouble(3, cctv.getLog());
		pstmt.setInt(4, cctv.getRegionId());
		
		return pstmt.executeUpdate();
	}

	@Override
	public int deleteCctv(int cctvId) throws SQLException {
		String sql = " delete MAP.CCTV " +
					" where cctvid = ? ";
		
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, cctvId);
		
		return pstmt.executeUpdate();
	}

}
