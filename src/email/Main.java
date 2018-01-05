package email;

import java.util.Scanner;

public class Main {
	
	private static String name;
	private static String nameLast;

	/**
	 * Helper method to tidy up main.
	 * @param department
	 * @param create
	 * @param account
	 */
	public static void assignDepMailCap(int department,CreateNewEmailAccount create,CompanyEmailAccount account) {
		switch(department) {
		case 1:
			account.setDepartment(create.assignDepartment(Departments.SALES));
			account.setMailboxCapacity(create.setMailboxLimit(Departments.SALES));
			break;
		case 2:
			account.setDepartment(create.assignDepartment(Departments.ACCOUNTING));
			account.setMailboxCapacity(create.setMailboxLimit(Departments.ACCOUNTING));
			break;	
		case 3:
			account.setDepartment(create.assignDepartment(Departments.MARKETING));
			account.setMailboxCapacity(create.setMailboxLimit(Departments.MARKETING));
			break;
		default:
			account.setDepartment(create.assignDepartment(Departments.OFFICE));
			account.setMailboxCapacity(create.setMailboxLimit(Departments.OFFICE));
			break;
		}
	}
	
	@SuppressWarnings({ "resource" })
	public static void main(String[] args) {
		System.out.println("Welcome to the email admin system.");
		
		CompanyEmailAccountsStorage database = new CompanyEmailAccountsStorage();
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Please Select Your Options Below");
		System.out.println("0 To Create New Email Account\n1 To See An Individual Account\n2 See All Users Accounts");
		
		int selection = sc.nextInt();
		switch(selection) {
		case 0:
			System.out.println("Please Enter First Name and Last Name");
			name = sc.next();
			nameLast = sc.next();
			CreateNewEmailAccount create = new CreateNewEmailAccount( new CompanyEmailAccount(name, nameLast));
			CompanyEmailAccount account = create.getAccount();
			account.setId(CompanyEmailAccountsStorage.setEmailAccountUserId());
			
			System.out.println("Please Select Department\n1 For Sales\n2 For Accounting\n3 For Marketing");
			
			assignDepMailCap(sc.nextInt(), create, account);
			
			System.out.println("Please Select Password Length");
			int passwordNum = sc.nextInt();
			account.setPassword(create.createRandomPassword(passwordNum));
			account.setEmail(create.generateEmailAddress(account.getFirstName(), account.getLastname(), account.getDepartment()));
			
			System.out.println("New Email Account Created");
			System.out.println("Your User ID: " + account.getId());
			System.out.println("Name: " +account.getFirstName() + " " +account.getLastname());
			System.out.println("Department: " + account.getDepartment());
			System.out.println("Email: " + account.getEmail());
			System.out.println("Email Capacity: " + account.getMailboxCapacity());
			System.out.println("Password: " + account.getPassword());
			
			System.out.println("\nNew Account Added To Database");;
			database.addUserToDatabase(account);
			database.saveData();
			
			break;
		case 1:
			System.out.println("Please Enter User ID");
			int userId = sc.nextInt();
			System.out.println("Fetching Request");
			CompanyEmailAccount user = database.getAccount(userId);
			if(user != null) {
				System.out.println("Your User ID: " + user.getId());
				System.out.println("Name: " +user.getFirstName() + " " +user.getLastname());
				System.out.println("Department: " + user.getDepartment());
				System.out.println("Email: " + user.getEmail());
				System.out.println("Email Capacity: " + user.getMailboxCapacity());
				System.out.println("Password: " + user.getPassword());
			}else {
				System.out.println("Sorry, but that user does not exist.");
			}
			break;
			
		case 2:
			System.out.println("Users And Their Passwords\n");
			System.out.println(database.showAccountUsers());
			break;
		default:
			System.out.println("Sorry, there are currently only three options.");
			break;	
		}
		}
		
}


