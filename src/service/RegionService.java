package service;

import java.sql.SQLException;
import java.util.List;

import model.Region;

public interface RegionService {

	public abstract List<Region> listRegion() throws SQLException;

	public abstract Region getRegion(int regionId) throws SQLException;

	public abstract int insertRegion(Region region) throws SQLException;

	public abstract int updateRegion(Region region) throws SQLException;

	public abstract int deleteRegion(int regionId) throws SQLException;

}
