package com.backbase.kalah.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


/**
 * @author Yengibar Manasyan
 */
@Entity
@Table(name = "player")
public class Player extends BaseEntity {

	@Column(name = "user_uuid", unique = true, nullable = false)
	private UUID uuid;


	// TODO uncomment for user registration and security integration
//	@Column(name = "username", unique = true, nullable = false, length = 50)
//	private String username;
//
//	@Column(name = "email", nullable = false, length = 50)
//	private String email;
//
//	@Column(name = "first_name", nullable = false)
//	private String firstName;
//
//	@Column(name = "last_name", nullable = false)
//	private String lastName;
//
//	@Column(name = "password_salt", nullable = false, length = 16)
//	private String passwordSalt;
//
//	@Column(name = "temporary_password", length = 16)
//	private String temporaryPassword;
//
//	@Column(name = "password", nullable = false, length = 64)
//	private String password;
//
//	@Column(name = "birthday", nullable = false)
//	private LocalDate birthday;
//
//	@Column(name = "gender", nullable = false)
//	private Character gender;
//
//	@Column(name = "active", nullable = false)
//	private Boolean active;

	/**
	 * Default constructor.
	 */
	public Player() {
		this.uuid = UUID.randomUUID();
	}

	// TODO write javadoc for all methods
	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getFirstName() {
//		return firstName;
//	}
//
//	public void setFirstName(String firstName) {
//		this.firstName = firstName;
//	}
//
//	public String getLastName() {
//		return lastName;
//	}
//
//	public void setLastName(String lastName) {
//		this.lastName = lastName;
//	}
//
//	public String getPasswordSalt() {
//		return passwordSalt;
//	}
//
//	public void setPasswordSalt(String passwordSalt) {
//		this.passwordSalt = passwordSalt;
//	}
//
//	public String getTemporaryPassword() {
//		return temporaryPassword;
//	}
//
//	public void setTemporaryPassword(String temporaryPassword) {
//		this.temporaryPassword = temporaryPassword;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public LocalDate getBirthday() {
//		return birthday;
//	}
//
//	public void setBirthday(LocalDate birthday) {
//		this.birthday = birthday;
//	}
//
//	public Character getGender() {
//		return gender;
//	}
//
//	public void setGender(Character gender) {
//		this.gender = gender;
//	}
//
//	public Boolean getActive() {
//		return active;
//	}
//
//	public void setActive(Boolean active) {
//		this.active = active;
//	}

	@Override
	public String toString() {
		return new org.apache.commons.lang3.builder.ToStringBuilder(this)
				.appendSuper(super.toString())
				.append("uuid", uuid)
//				.append("username", username)
//				.append("email", email)
//				.append("firstName", firstName)
//				.append("lastName", lastName)
//				.append("birthday", birthday)
//				.append("gender", gender)
//				.append("active", active)
				.toString();
	}
}
