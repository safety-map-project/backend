package dao;

import java.sql.SQLException;
import java.util.List;

import model.Cctv;

public interface CctvDao {

	public abstract List<Cctv> listCctv() throws SQLException;
	
	public abstract Cctv getCctv(int cctvId) throws SQLException;
	
	public abstract int insertCctv(Cctv cctv) throws SQLException;
	 
	public abstract int updateCctv(Cctv cctv) throws SQLException;
	
	public abstract int deleteCctv(int cctvId) throws SQLException;
	
}
