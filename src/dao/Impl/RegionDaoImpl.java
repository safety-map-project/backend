package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		String sql = " select regionid, gu, si from MAP.region ";
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		List<Region> regionList = new ArrayList<Region>();
		while(rs.next()) {
			Region region = new Region();
			region.setRegionId(rs.getString("regionid"));
			region.setGu(rs.getString("gu"));
			region.setSi(rs.getString("si"));
			regionList.add(region);
		}
		return regionList;
	}

	@Override
	public Region getRegion(String regionId) throws SQLException {
		return null;
	}

	@Override
	public int insertRegion(Region region) throws SQLException {
		String sql = " insert into MAP.region (regionid, gu) values(?, ?) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, region.getRegionId());
		pstmt.setString(2, region.getGu());
		return pstmt.executeUpdate();
	}

	@Override
	public int updateRegion(Region region) throws SQLException {
		
		String sql = " update region set si=? where regionid=? ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, region.getSi());
		pstmt.setString(2, region.getRegionId());
		
		return pstmt.executeUpdate();
	}

	@Override
	public int deleteRegion(String regionId) throws SQLException {
		return 0;
	}

}
