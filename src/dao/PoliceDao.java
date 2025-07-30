package dao;

import java.sql.SQLException;
import java.util.List;

import model.Police;

public interface PoliceDao {

	public abstract List<Police> listPolice() throws SQLException;
	
	public abstract Police getPolice(int policeId) throws SQLException;
	
	public abstract int insertPolice(Police police) throws SQLException;
	
	public abstract int updatePolice(Police police) throws SQLException;
	
	public abstract int deletePolice(int policeId) throws SQLException;
	
}
