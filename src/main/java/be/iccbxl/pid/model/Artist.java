package be.iccbxl.pid.model;

import java.util.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="artists")
public class Artist {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message="Ne peut être vide")
	@Size(min=2, max=60, message="Doit contenir minimum 2 caracteres et maximum 60.")
	private String firstname;
	@NotEmpty(message="Ne peut être vide")
	@Size(min=2, max=60, message="Doit contenir minimum 2 caracteres et maximum 60.")
	private String lastname;
	
	@ManyToMany(mappedBy = "artists")
	private List<Type> types = new ArrayList<>();

	protected Artist() {}

	public Artist(String firstname, String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Long getId() {
		return id;
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

	public List<Type> getTypes() {
		return types;
	}

	public Artist addType(Type type) {
		if(!this.types.contains(type)) {
			this.types.add(type);
			type.addArtist(this);
		}
		
		return this;
	}
	
	public Artist removeType(Type type) {
		if(this.types.contains(type)) {
			this.types.remove(type);
			type.getArtists().remove(this);
		}
		
		return this;
	}

	@Override
	public String toString() {
		return firstname + " " + lastname;
	}
	
}
