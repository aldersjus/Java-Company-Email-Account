package email;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Stores company email accounts.
 * Saves data to a file.
 * @author Justin Alderson
 *
 */
public class CompanyEmailAccountsStorage {
	
	static int emailAccountUserId = 2000;
	
	//public for testing
	//ID unique identifier, CompanyEmailAccount object.
	public HashMap<Integer, CompanyEmailAccount> hm = new HashMap<>();
	
	//public for testing
	//ID unique identifier, name of user and password. In case user forgets password.
	public HashMap<Integer, String> passwordNames = new HashMap<>();
	
	
	public CompanyEmailAccountsStorage() {
		loadData();
	}
	
	/**
	 * Assigns an id to user.
	 * @return a new emailAccountUserId
	 */
	public static int setEmailAccountUserId() {
		return ++emailAccountUserId;
		
	}
	
	/**
	 * If someone forgets there password
	 * @param firstName
	 * @param lastName
	 * @return
	 */
	public String showAccountUsers() {
		StringBuilder sb = new StringBuilder();
		for(Integer i : passwordNames.keySet()) {
			sb.append("ID: ").append(i).append(" Name: ").append(passwordNames.get(i)).append("\n\n");
		}
		return sb.toString();
	}
	
	/**
	 * Uses password to store unique user accounts.
	 * @param account
	 */
	public void addUserToDatabase(CompanyEmailAccount account) {
		hm.put(account.getId(), account);
		passwordNames.put(account.getId(), account.getFirstName() + " " + account.getLastname() + " Password: " + account.getPassword());
	}
	
	/**
	 * returns account by using ID to identify.
	 * @param password
	 * @return
	 */
	public CompanyEmailAccount getAccount(int id ) {
		CompanyEmailAccount user = hm.get(id);
		return user;
	}
	
	/**
	 * Load data to file
	 */
	
	@SuppressWarnings("unchecked")
	public void loadData() {
		Logger log = Logger.getLogger(this.getClass().getName());
		try(FileInputStream fis = new FileInputStream("company_email_accounts.ser");
				ObjectInputStream ois = new ObjectInputStream(fis) ){
					emailAccountUserId = (int) ois.readObject();
					passwordNames = (HashMap<Integer, String>) ois.readObject();
					hm = (HashMap<Integer, CompanyEmailAccount>) ois.readObject();
		}catch (IOException | ClassNotFoundException e){
				log.log(Level.SEVERE, e.getMessage() + "io exception");
		}
		finally {
			log.log(Level.FINE,"Data was loaded");
		}
	}

	
	/**
	 * Save data to file
	 */
	public void saveData() {
		Logger log = Logger.getLogger(this.getClass().getName());
		try(FileOutputStream fos = new FileOutputStream("company_email_accounts.ser");
				ObjectOutputStream oos = new ObjectOutputStream(fos) ){
					oos.writeObject(emailAccountUserId);
					oos.writeObject(passwordNames);
					oos.writeObject(hm);
		}catch (IOException e){
				log.log(Level.SEVERE, e.getMessage());
		}finally {
			log.log(Level.FINE,"Data was saved");
		}
	}
}
