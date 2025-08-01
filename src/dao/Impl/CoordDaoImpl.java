package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.CoordDao;
import model.Coord;
import util.ConnectionUtil;

public class CoordDaoImpl implements CoordDao {
	
	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;
	
	public CoordDaoImpl() {
		conn = ConnectionUtil.getConnectionUtil().getConnection();
	}

	@Override
	public List<Coord> listCoord() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coord getCoord(String coordId) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertCoord(Coord coord) throws SQLException {
		String sql = " insert into Map.region_coord (seq_region_coord.nextVal, lat, log, region_id) "
				+ " values(seq_region_coord.nextVal, ?, ?, ?) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setDouble(1, coord.getLat());
		pstmt.setDouble(2, coord.getLog());
		pstmt.setString(3, coord.getRegionId());
		return pstmt.executeUpdate();
	}

	@Override
	public int updateCoord(Coord coord) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCoord(String coordId) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
