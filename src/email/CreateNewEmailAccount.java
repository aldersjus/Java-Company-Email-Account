package email;

import java.util.Random;

/**
 * Class to create a email user account for a new employee.
 * @author Justin Alderson
 *
 */
public class CreateNewEmailAccount {
	
	private String[] departments = {"sales","accounting","marketing"};
	private CompanyEmailAccount account;

	public CreateNewEmailAccount(CompanyEmailAccount account) {
		this.account = account;
	}
	
	

	/**
	 * @param d enum Departments
	 * @return the department as a String
	 */
	public String assignDepartment(Departments d) {
		String department = null;
		switch(d) {
			case SALES:
				department = departments[0];
				break;
			case ACCOUNTING: 
				department = departments[1];
				break;
			case MARKETING:
				department = departments[2];
				break;
			default:
				department = "office";
				break;
		}
		return department;
		
	}

	/**
	 * Requires != null
	 * @param firstName
	 * @param lastName
	 * @param department
	 * @return email address string
	 */
	public String generateEmailAddress(String firstName, String lastName, String department) {
		StringBuilder sb = new StringBuilder();
		sb.append(firstName).append(".").append(lastName).append("@").append(department)
		.append(".").append("company").append(".").append("com");
		return sb.toString().toLowerCase();
		
	}
	
	/**
	 * Requires not null,
	 * @param d Departments
	 * @return mailbox size
	 */
	public int setMailboxLimit(Departments d) {
		int limit;
		switch(d) {
		case SALES:
			limit = 300;
			break;
		case ACCOUNTING:
			limit = 150;
			break;
		case MARKETING:
			limit = 250;
			break;
		default:
			limit = 100;
			break;
		}
		return limit;
		
	}
	
	/**
	 * Generate a random password for the new user.
	 * @param length of password requested
	 * @return password String
	 */
	public String createRandomPassword(int length) {
		Random rand = new Random();
		String passwordSet = "abcdefghijklmnopqrstu#%*!1234567890";
		char[] password = new char[length];
		for(int i = 0; i<length; i++) {
			int z = (int)(rand.nextDouble() * 30);
			password[i] = passwordSet.charAt(z);
		}
		return new String(password);
	}

	
	public CompanyEmailAccount getAccount() {
		return account;
	}

	public void setAccount(CompanyEmailAccount account) {
		this.account = account;
	}

}
