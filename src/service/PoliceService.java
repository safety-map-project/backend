package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import model.Police;

public interface PoliceService {

	public abstract List<Police> listPolice() throws SQLException;

	public abstract Police getPolice(int policeId) throws SQLException;

	public abstract int insertPolice(Police police) throws SQLException, IOException, InterruptedException;

	public abstract int updatePolice(Police police) throws SQLException;

	public abstract int deletePolice(int policeId) throws SQLException;

}
