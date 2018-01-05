package email;

import java.io.Serializable;

/**
 * Company Email Account user.
 * @author Justin Alderson
 *
 */
public class CompanyEmailAccount implements Serializable{

	private static final long serialVersionUID = -6499293769173411714L;
	private String firstName;
	private String lastname;
	private String department;
	private String password;
	private String email;
	private int mailboxCapacity;
	private  int id;

	
public CompanyEmailAccount(String first,String last) {
		this.firstName = first;
		this.lastname = last;
	
	}
	
	public String getFirstName() {
		return firstName;
	}


	public String getLastname() {
		return lastname;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public int getMailboxCapacity() {
		return mailboxCapacity;
	}


	public void setMailboxCapacity(int mailboxCapacity) {
		this.mailboxCapacity = mailboxCapacity;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
