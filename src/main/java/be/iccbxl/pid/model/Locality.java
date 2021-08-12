package be.iccbxl.pid.model;

import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import javax.persistence.OneToMany;


@Entity
@Table(name="localities")
public class Locality {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@NotEmpty(message="Ne peut être vide")
	@Size(max=6, message="Doit contenir au maximum 6 caractères.")
	private String postalCode;
	@NotEmpty(message="Ne peut être vide")
	@Size(max=60, message="Doit contenir au maximum 60 caractères.")
	private String locality;

	@OneToMany(targetEntity=Location.class, mappedBy="locality")
	private List<Location> locations = new ArrayList<>();
	
	protected Locality() {	}

	public Locality(String postalCode, String locality) {
		this.postalCode = postalCode;
		this.locality = locality;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getLocality() {
		return locality;
	}
	
	public void setLocality(String locality) {
		this.locality = locality;
	}

	public List<Location> getLocations() {
		return locations;
	}

	public Locality addLocation(Location location) {
		if(!this.locations.contains(location)) {
			this.locations.add(location);
			location.setLocality(this);
		}
		
		return this;
	}
	
	public Locality removeLocation(Location location) {
		if(this.locations.contains(location)) {
			this.locations.remove(location);
			if(location.getLocality().equals(this)) {
				location.setLocality(null);
			}
		}
		
		return this;
	}
	
	@Override
	public String toString() {
		return "Locality [id=" + id + ", postalCode=" + postalCode + ", locality=" + locality + "]";
	}
	
}