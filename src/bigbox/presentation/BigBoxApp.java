package bigbox.presentation;

import java.util.ArrayList;
import java.util.Scanner;
import bigbox.business.Store;
import bigbox.db.StoreConstants;
import bigbox.db.StoreDAO;
import bigbox.db.StoreDAOFactory;
import bigbox.util.Validator;

/**
 * @author Rajiv Giri
 * @version 3.0
 *  
 * <p> This project is the 3.0 version or iteration of BigBox Application version 2.0. This is a console application and consists of Division and Store classes stored
 * into their respective packages. It implements the DAO Factory Pattern and stores and gets the data from MYSQL Database. It also includes MySQL connector and other Jar files and 
 * SQL Script in bigbox.SQL package.</p>
 * <p>This project consists of following features:-</p>
								      <ul>
								      <b> COMMAND MENU</b>
										 <li>list    - List all Stores</li>								
										 <li>div     - List all stores for a division</li>										 
										 <li>add     - Add a Store</li>
										 <li>del     - Delete a Store</li>
										 <li>help    - Show this menu</li>
										 <li>exit    - Exit this application</li>
										 <p>Enter a command: </p>
 *
 *<p>User will be prompted to Enter a command. User will have command menu at the top of the console.
 *   User can choose the command on the left side, right side is the short description of the command.</p>
 *   
 */
public class BigBoxApp implements StoreConstants {
	
	private static StoreDAO StoreDAO = null;
	private static Scanner sc = null;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// Print Welcome Message
		System.out.println("Welcome to the Big Box Application !!");
		System.out.println();
		// set the class variables
		StoreDAO = StoreDAOFactory.getStoreDAO();
		sc = new Scanner(System.in);
		/* displayMenu(); */

		// perform 1 or more actions
		String action = "";
		while (!action.equalsIgnoreCase("exit")) {
			// get the input from the user
			displayMenu();
			action = Validator.getString(sc, "Enter a command: ");
			System.out.println();

			if (action.equalsIgnoreCase("list"))
				displayAllStores();
			else if (action.equalsIgnoreCase("div") || action.equalsIgnoreCase("divison")) {
				String inDiv = Validator.getString(sc, "Enter a Divison Number: ");
				displayStoresWithInDivison(inDiv);
			} else if (action.equalsIgnoreCase("add"))
				addStore();
			else if (action.equalsIgnoreCase("del") || action.equalsIgnoreCase("delete"))
				deleteStore();
			else if (action.equalsIgnoreCase("help") || action.equalsIgnoreCase("menu")) {
				// displayMenu();
			} else if (action.equalsIgnoreCase("exit") || action.equalsIgnoreCase("quit"))
				System.out.println("Bye.\n");
			else
				System.out.println("Error! Not a valid command.\n");
		}
	}

	/**
	 * Display menu.
	 */
	public static void displayMenu() {
		System.out.println("COMMAND MENU");
		System.out.println("list    - List all Stores");
		System.out.println("div     - List all stores for a division");
		System.out.println("add     - Add a Store");
		System.out.println("del     - Delete a Store");
		System.out.println("help    - Show this menu");
		System.out.println("exit    - Exit this application\n");
	}

	/**
	 * Display all stores.
	 */
	private static void displayAllStores() {

		ArrayList<Store> stores = StoreDAO.getStores();
		if (stores == null) {
			System.out.println("Error unable to get stores.\n");
		} else {
			Store s = null;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stores.size(); i++) {
				s = stores.get(i);
				System.out.println("[Store:  store#=" + s.getStoreNumber() + ", Division Number="
						+ s.getDivisionNumber() + ", Sales=" + s.getFormattedSales() + "]\n" + "[Facilty: " + "id="
						+ s.getId() + ", name=" + s.getName() + ", address=" + s.getAddress() + ", city=" + s.getCity()
						+ ", state=" + s.getState() + ", zip=" + s.getZipCode() + "]");
			}
			System.out.println(sb.toString());
		}

	}

	private static void displayStoresWithInDivison(String inDiv) {

		ArrayList<Store> stores = StoreDAO.getStores();
		Store s = null;
		@SuppressWarnings("unused")
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < stores.size(); i++) {
			s = stores.get(i);
			if (s.getDivisionNumber().equals(inDiv)) {
				System.out.println("[Store:  store#=" + s.getStoreNumber() + ", Division Number="
						+ s.getDivisionNumber() + ", Sales=" + s.getFormattedSales() + "]\n" + "[Facilty: " + "id="
						+ s.getId() + ", name=" + s.getName() + ", address=" + s.getAddress() + ", city=" + s.getCity()
						+ ", state=" + s.getState() + ", zip=" + s.getZipCode() + "]");
			}
			System.out.println();
		}

	}

	/**
	 * Adds the store.
	 */
	public static void addStore() {
		// Get user Entries
		int infID = 0;
		String inDiv = Validator.getStringNumeric(sc, "Enter a division #:  ", 3);
		String inStore = Validator.getStringNumeric(sc, "Enter a Store #:  ", 5);
		double inSales = Validator.getDouble(sc, "Enter Sales Amount:  ");
		String inName = Validator.getString(sc, "Enter the Name of the store:  ");
		String inAddress = Validator.getString(sc, "Enter the Address of the store:  ");
		String inCity = Validator.getString(sc, "Enter the City of the store:  ");
		String inState = Validator.getString(sc, "Enter the State of the store:  ");
		String inZipCode = Validator.getString(sc, "Enter the Zip of the store:  ");
		Store store = new Store();
		store.setId(infID);
		store.setDivisionNumber(inDiv);
		store.setStoreNumber(inStore);
		store.setSales(inSales);
		store.setName(inName);
		store.setAddress(inAddress);
		store.setCity(inCity);
		store.setState(inState);
		store.setZipCode(inZipCode);
		StoreDAO.addStore(store);

		// StoreArray.addStore(store);
		System.out.println();
		System.out.println("The " + inDiv + " Division " + inStore + " Store with sales amount of " + inSales
				+ " has been added to the system.\n");

	}

	/**
	 * Delete store.
	 */
	public static void deleteStore() {
		String inDiv = Validator.getString(sc, "Enter Division Number to delete: ");
		String inStore = Validator.getString(sc, "Enter Store Number to delete: ");

		Store s = StoreDAO.getStore(inDiv, inStore);

		System.out.println();
		if (s != null) {
			StoreDAO.deleteStore(s);
			System.out.println(s.getDivisionNumber() + " and " + s.getStoreNumber() + " has been deleted.\n");
		} else {
			System.out.println("No matches for that div and store number.\n");
		}
	}

	/**
	 * Pad with spaces.
	 *
	 * @param string the string
	 * @param length the length
	 * @return the string
	 */
	public static String padWithSpaces(String string, int length) {
		if (string.length() < length) {
			StringBuilder sb = new StringBuilder(string);
			while (sb.length() < length) {
				sb.append(" ");
			}
			return sb.toString();
		} else {
			return string.substring(0, length);
		}
	}

	/**
	 * Pad with spaces 1.
	 *
	 * @param s the s
	 * @return the string
	 */
	public static String padWithSpaces1(String s) {
		if (s.length() < NAME_SIZE) {
			StringBuilder sb = new StringBuilder(s);
			while (sb.length() < NAME_SIZE) {
				sb.append(" ");
			}
			return sb.toString();
		} else {
			return s.substring(0, NAME_SIZE);
		}
	}

}
