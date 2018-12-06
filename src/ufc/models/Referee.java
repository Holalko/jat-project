package ufc.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Referee extends Person {

	@OneToMany
	private List<Fight> fights;

	public Referee() {
		super();
	}

	public Referee(Referee r) {
		super();
		this.fights = r.fights;
		this.setId(r.getId());
		this.setFirstName(r.getFirstName());
		this.setLastName(r.getLastName());
	}
	
	public Referee(List<Fight> fights) {
		super();
		this.fights = fights;
	}

	public List<Fight> getFights() {
		return fights;
	}

	public void setFights(List<Fight> fights) {
		this.fights = fights;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (getId() ^ (getId() >>> 32));
		return result;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Referee other = (Referee) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}
	
	
}
