package email.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import email.CompanyEmailAccount;
import email.CompanyEmailAccountsStorage;
import email.CreateNewEmailAccount;
import email.Departments;

class CreateEmailAccountTest {

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
	void correctAccount() {
	  assertTrue(create.getAccount().equals(account));
	}

	@Test
	void differentMailboxCapacity() {
	  assertFalse(account.getMailboxCapacity() == account2.getMailboxCapacity());
	}

	@Test
	void uniquePasswords() {
	  assertFalse(account.getPassword().equals(account2.getPassword()));
	}

	@Test
	void expectedEmail() {
	  assertEquals("john.smith@sales.company.com", account.getEmail());
	} 
	
	@Test
	void differentIDs() {
	  assertFalse(account.getId() == account2.getId());
	}

	@Test
	void enterdFirstNameCorrect() {
	  assertEquals("john", account.getFirstName());
	}
	
	@Test
	void enterdLastNameCorrect() {
	  assertEquals("smith", account.getLastname());
	}
}
