package be.iccbxl.pid.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)

	private Long id;

	@NotEmpty(message ="Veuillez introduire un login")
	@Size(min = 5, max = 30, message = "Doit contenir minimum 5 caractères")
	private String login;
	@NotEmpty
	private String password;
	@NotEmpty
	private String firstname;
	@NotEmpty
	private String lastname;
	@NotEmpty
	@Email
	private String email;
	@NotEmpty
	@NotNull
	private String langue;

	private LocalDateTime created_at;
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();

	@ManyToMany(mappedBy = "users")
	private List<Representation> representations = new ArrayList<>();

	public User(){
		this.created_at = LocalDateTime.now();
	}
	
	public User(String login, String firstname, String lastname) {
		this.login = login;
		this.firstname = firstname;
		this.lastname = lastname;
		this.created_at = LocalDateTime.now();
	}

	public User(String login, String password, String firstname, String lastname, String email, String langue) {

		this.login = login;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email= email;
		this.langue = langue;
		this.created_at = LocalDateTime.now();
    }

    public Long getId() {
		return id;
	}	
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	 
    public String getLangue() {
		return langue;
	}

	public void setLangue(String langue) {
		this.langue = langue;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}
	
	public User addRole(Role role) {
		if(!this.roles.contains(role)) {
			this.roles.add(role);
			role.addUser(this);
		}
		
		return this;
	}
	
	public User removeRole(Role role) {
		if(this.roles.contains(role)) {
			this.roles.remove(role);
			role.getUsers().remove(this);
		}
		
		return this;
	}

	public List<Representation> getRepresentations() {
		return representations;
	}

	public User addRepresentation(Representation representation) {
		if(!this.representations.contains(representation)) {
			this.representations.add(representation);
			representation.addUser(this);
		}
		
		return this;
	}
	
	public User removeRepresentation(Representation representation) {
		if(this.representations.contains(representation)) {
			this.representations.remove(representation);
			representation.getUsers().remove(this);
		}
		
		return this;
	}
	
	@Override
	public String toString() {
		return login + "(" + firstname + " " + lastname + ")";
    }
}