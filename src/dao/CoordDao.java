package dao;

import java.sql.SQLException;
import java.util.List;

import model.Coord;


public interface CoordDao {
	
	public abstract List<Coord> listCoord() throws SQLException;
	
	public abstract Coord getCoord(String gu_name) throws SQLException;

	public abstract int insertCoord(Coord coord) throws SQLException;

	public abstract int updateCoord(Coord coord) throws SQLException;

	public abstract int deleteCoord(String coordId) throws SQLException;

	List<Coord> guCoordsList(String gu_name) throws SQLException;
}
