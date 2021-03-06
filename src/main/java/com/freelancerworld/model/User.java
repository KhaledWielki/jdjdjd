package com.freelancerworld.model;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private int id;

	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;

	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String password;

	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;

	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;

	@Column(name = "phone_number")
	@NotEmpty(message = "Please provide your phone number for contact")
	private String phoneNumber;

	@Column(name = "description")
	private String description;

	@Column(name = "average_mark")
	private double averageMark;

	@Column(name = "active")
	private int active;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_profession", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "profession_id"))
	private Set<Profession> professions;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_request", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "request_id"))
	private Set<Request> requestsContractors;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private List<Request> requests;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAverageMark() {
		return averageMark;
	}

	public void setAverageMark(double averageMark) {
		this.averageMark = averageMark;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public Set<Profession> getProfessions() {
		return professions;
	}

	public void setProfessions(Set<Profession> professions) {
		this.professions = professions;
	}

	@JsonBackReference(value="user-requests")
	public Set<Request> getRequestsContractors() {
		return requestsContractors;
	}

	public void setRequestsContractors(Set<Request> requestsContractors) {
		this.requestsContractors = requestsContractors;
	}

	@JsonBackReference
	public List<Request> getRequests() {
		return requests;
	}

	public void setRequests(List<Request> requests) {
		this.requests = requests;
	}
}