package bigbox.db;

import java.util.ArrayList;

import bigbox.business.Store;

/**
 * The Interface StoreReader.
 */
public interface StoreReader {

	/**
	 * Gets the store.
	 *
	 * @param inDiv the in div
	 * @param inStore the in store
	 * @return the store
	 */
	public Store getStore(String inDiv, String inStore);

	/**
	 * Gets the stores.
	 *
	 * @return the stores
	 */
	public ArrayList<Store> getStores();

}
