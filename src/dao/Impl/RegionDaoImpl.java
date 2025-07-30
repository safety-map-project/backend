package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import dao.RegionDao;
import model.Region;
import util.ConnectionUtil;

public class RegionDaoImpl implements RegionDao {

	private Connection conn;
	private PreparedStatement pstmt;
	ResultSet rs;

	public RegionDaoImpl() {
		conn = ConnectionUtil.getConnectionUtil().getConnection();
	}

	@Override
	public List<Region> listRegion() throws SQLException {
		return null;
	}

	@Override
	public Region getRegion(int regionId) throws SQLException {
		return null;
	}

	@Override
	public int insertRegion(Region region) throws SQLException {
		return 0;
	}

	@Override
	public int updateRegion(Region region) throws SQLException {
		return 0;
	}

	@Override
	public int deleteRegion(int regionId) throws SQLException {
		return 0;
	}

}
