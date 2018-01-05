package email.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import email.CompanyEmailAccount;
import email.CompanyEmailAccountsStorage;
import email.CreateNewEmailAccount;
import email.Departments;

class CompanyEmailAccountsStorageTest {
	
	static CreateNewEmailAccount create,create2;
	static CompanyEmailAccount account,account2;
	static CompanyEmailAccountsStorage database;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		database = new CompanyEmailAccountsStorage();
		create = new CreateNewEmailAccount( new CompanyEmailAccount("john", "smith"));
		account = create.getAccount();
		account.setId(CompanyEmailAccountsStorage.setEmailAccountUserId());
		account.setDepartment(create.assignDepartment(Departments.SALES));
		account.setMailboxCapacity(create.setMailboxLimit(Departments.SALES));
		account.setPassword(create.createRandomPassword(10));
		account.setEmail(create.generateEmailAddress(account.getFirstName(), account.getLastname(), account.getDepartment()));
		//database.addUserToDatabase(account);
		
		create2 = new CreateNewEmailAccount( new CompanyEmailAccount("tom", "walker"));
		account2 = create2.getAccount();
		account2.setId(CompanyEmailAccountsStorage.setEmailAccountUserId());
		account2.setDepartment(create2.assignDepartment(Departments.MARKETING));
		account2.setMailboxCapacity(create2.setMailboxLimit(Departments.MARKETING));
		account2.setPassword(create2.createRandomPassword(10));
		account2.setEmail(create2.generateEmailAddress(account2.getFirstName(), account2.getLastname(), account2.getDepartment()));
		//database.addUserToDatabase(account2);
	}
	
	@Test
	void databaseAddUserToDatabase_hashMapAndPasswordNames_Increased() {
		int now = database.hm.size();
		database.addUserToDatabase(account);
		database.addUserToDatabase(account2);
		assertTrue((database.hm.size() & database.passwordNames.size()) > now);
	}
	
	
	@Test
	void databaseGetAccount_notNull() {
		assertFalse(database.getAccount(2001).equals(null));
	}
	
	@Test
	void databaseShowAccountUsers_notNull() {
		assertFalse(database.showAccountUsers().equals(null));
	}
	
	@Test//Checking that emailAccountUserId has been increased.
	void databaseSetEmail() {
		assertTrue(CompanyEmailAccountsStorage.setEmailAccountUserId() > 2000);
	}
	
}
