package project.HaoyangTian;

import java.io.Serializable;
/**
 * @author Haoyang Tian G47995931
 * 2015-9-19 15:56:42
 */
@SuppressWarnings("serial")
public class Data implements Serializable {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String companyName;
	
	public Data() {
		super();
	}
	public Data(String firstName, String lastName, String phoneNumber,
			String companyName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.companyName = companyName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "         firstName:" + firstName + "\n                lastName:" + lastName
				+ "\n                phoneNumber:" + phoneNumber + "\n                companyName:"
				+ companyName;
	}
	
}
