package de.wk.userservice;

import java.util.List;

public class User {

	private String login;
	private String firstName;
	private String lastName;
	
	private List<String> numbers;
	
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
	public List<String> getNumbers() {
		return numbers;
	}
	public void setNumbers(List<String> numbers) {
		this.numbers = numbers;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	@Override
	public String toString() {
		return new StringBuilder ()
			.append("Login:")
			.append(login)
			.append("Vorname:")
			.append(firstName)
			.append(",")
			.append("Lastname:")
			.append(lastName)
			.toString();
	}
	
}
