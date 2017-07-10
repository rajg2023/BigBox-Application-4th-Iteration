package bigbox.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bigbox.business.Store;
import bigbox.util.DBUtil;

/**
 * The Class StoreDB.
 */
public class StoreDB implements StoreDAO {

	/* (non-Javadoc)
	 * @see bigbox.db.StoreReader#getStores()
	 */
	public ArrayList<Store> getStores() {
		String sql = "SELECT * FROM Store ";
		ArrayList<Store> stores = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Store s = getProductFromRow(rs);
				stores.add(s);

			}
			return stores;
		} catch (SQLException e) {
			System.err.println("Something is not right" + e);
		}
		return stores;
	}

	/**
	 * Gets the all stores with in divison.
	 *
	 * @return the all stores with in divison
	 */
	public ArrayList<Store> getAllStoresWithInDivison() {
		String sql = " SELECT * FROM Store " + " WHERE Divison = ? ";
		ArrayList<Store> stores = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				Store s = getProductFromRow(rs);
				stores.add(s);

			}
			return stores;
		} catch (SQLException e) {
			System.err.println("Something is not right" + e);
		}
		return stores;
	}

	/**
	 * Gets the product from row.
	 *
	 * @param rs the rs
	 * @return the product from row
	 * @throws SQLException the SQL exception
	 */
	public static Store getProductFromRow(ResultSet rs) throws SQLException {
		Store s = null;
		try {
			int id = rs.getInt(1);
			String divison = rs.getString(2);
			String store = rs.getString(3);
			double sales = rs.getDouble(4);
			String name = rs.getString(5);
			String address = rs.getString(6);
			String city = rs.getString(7);
			String state = rs.getString(8);
			String zipcode = rs.getString(9);

			s = new Store();
			s.setId(id);
			s.setDivisionNumber(divison);
			s.setStoreNumber(store);
			s.setSales(sales);
			s.setName(name);
			s.setAddress(address);
			s.setCity(city);
			s.setState(state);
			s.setZipCode(zipcode);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return s;
	}

	/* (non-Javadoc)
	 * @see bigbox.db.StoreReader#getStore(java.lang.String, java.lang.String)
	 */
	public Store getStore(String inDiv, String inStore) {
		String sql = "SELECT * FROM Store";
		@SuppressWarnings("unused")
		ArrayList<Store> stores = new ArrayList<>();
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				getProductFromRow(rs);
			}
		} catch (SQLException e) {
			System.err.println(e);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see bigbox.db.StoreWriter#addStore(bigbox.business.Store)
	 */
	public boolean addStore(Store s) {
		String sql = "INSERT INTO Store (Divison,Store,Sales,Name,Address,City,State,ZipCode) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setString(1, s.getDivisionNumber());
			ps.setString(2, s.getStoreNumber());
			ps.setDouble(3, s.getSales());
			ps.setString(4, s.getName());
			ps.setString(5, s.getAddress());
			ps.setString(6, s.getCity());
			ps.setString(7, s.getState());
			ps.setString(8, s.getZipCode());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see bigbox.db.StoreWriter#deleteStore(bigbox.business.Store)
	 */
	public boolean deleteStore(Store s) {
		String sql = "DELETE FROM Store " + "WHERE FacilityID = ?";
		Connection connection = DBUtil.getConnection();
		try (PreparedStatement ps = connection.prepareStatement(sql)) {
			ps.setInt(1, s.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			System.err.println(e);
		}
		return false;
	}

}
