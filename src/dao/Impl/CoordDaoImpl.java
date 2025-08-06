package dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        String sql = "SELECT coordId, lat, log, regionId, gu_name FROM MAP.region_coord) ";
        List<Coord> coordList = new ArrayList<>();

        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
        ) {
            while (rs.next()) {
            	Coord coord = new Coord();
            	coord.setCoordId(rs.getInt("coordId"));
            	coord.setLat(rs.getDouble("lat"));
            	coord.setLog(rs.getDouble("log"));
            	coord.setRegionId(rs.getInt("regionId"));
            	coord.setGu_name(rs.getString("gu_name"));
                coordList.add(coord);
                pstmt.close();
                rs.close();
            }
        }

        return coordList;
    }
	
	@Override
	public List<Coord> guCoordsList(String gu_name) throws SQLException {
		String sql = " SELECT COORDID, LAT, LOG, REGIONID, GU_NAME FROM MAP.REGION_COORD WHERE GU_NAME=? ";
		List<Coord> coordWhereGuName = new ArrayList<Coord>();
		
		try(PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			pstmt.setString(1, gu_name);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Coord coord = new Coord();
				coord.setCoordId(rs.getInt("coordId"));
				coord.setLat(rs.getDouble("lat"));
				coord.setLog(rs.getDouble("log"));
				coord.setRegionId(rs.getInt("regionId"));
				coord.setGu_name(rs.getString("gu_name"));
				coordWhereGuName.add(coord);
			}
			return coordWhereGuName;
	}
	}
	
	

	@Override
    public Coord getCoord(String gu_name) throws SQLException {
        String sql = " SELECT coordId, lat, log, regionId, gu_name FROM coord WHERE gu_name=? ";

        try (
            PreparedStatement pstmt = conn.prepareStatement(sql);
        ) {
            pstmt.setString(1, gu_name);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                	Coord coord = new Coord();
                	coord.setCoordId(rs.getInt("coordId"));
                	coord.setLat(rs.getDouble("lat"));
                	coord.setLog(rs.getDouble("log"));
                	coord.setRegionId(rs.getInt("regionId"));
                    coord.setGu_name(rs.getString("gu_name"));
                    return coord;
                }
            }
        }

        return null;
    }

	@Override
	public int insertCoord(Coord coord) throws SQLException {
		
		System.out.println(coord);
		
		String sql = " insert into map.region_coord(coordId, lat, log, regionId, gu_name) "
				+ " values(map.seq_region_coord.nextval, ?, ?, ?, ?) ";
		pstmt = conn.prepareStatement(sql);
		pstmt.setDouble(1, coord.getLat());
		pstmt.setDouble(2, coord.getLog());
		pstmt.setInt(3, coord.getRegionId());
		pstmt.setString(4, coord.getGu_name());
		int result = pstmt.executeUpdate();
		pstmt.close();
//		conn.commit();
		return result;
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
