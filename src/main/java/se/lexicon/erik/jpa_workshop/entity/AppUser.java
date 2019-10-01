package se.lexicon.erik.jpa_workshop.entity;

import java.util.Objects;

public class AppUser {
	
	private int userId;
	private String firstName;
	private String lastName;
	private String email;
	
	public AppUser(int userId, String firstName, String lastName, String email) {
		this.userId = userId;
		setFirstName(firstName);
		setLastName(lastName);
		setEmail(email);
	}

	public AppUser(String firstName, String lastName, String email) {
		this(0, firstName, lastName, email);
	}	

	public AppUser() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserId() {
		return userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AppUser other = (AppUser) obj;
		return Objects.equals(email, other.email) && userId == other.userId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppUser [userId=");
		builder.append(userId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", email=");
		builder.append(email);
		builder.append("]");
		return builder.toString();
	}
}
