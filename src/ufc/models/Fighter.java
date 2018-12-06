package ufc.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class Fighter extends Person {

	private String nickName;
	
	@ManyToOne
	private Team team;
	
	@OneToMany
	private List<Fight> fight;
	
	private byte[] image;
	
	
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getColorForFightResult(Fight f) {
		String idInStr = getId().toString();
		if(idInStr.equals(f.getResult())) {
			return "bg-success";
		} else if ("draw".equals(f.getResult())) {
			return "bg-warning";
		} else if(f.getResult() == null) {
			return "";
		}
		return "bg-danger";
	}
	
	public Integer getWinsCount() {
		Integer w = 0;
		String idInStr = getId().toString();
		for(Fight f : fight) {
			if(idInStr.equals(f.getResult())) {
				w++;
			}
		}
		return w;
	}
	
	public Integer getLosesCount() {
		Integer l = 0;
		String idInStr = getId().toString();
		for(Fight f : fight) {
			if(f.getResult() == null) {
				continue;
			}
			if(!idInStr.equals(f.getResult()) && !"draw".equals(f.getResult())) {
				l++;
			}
		}
		return l;
	}
	
	public Integer getDrawsCount() {
		Integer d = 0;
		for(Fight f : fight) {
			if("draw".equals(f.getResult())) {
				d++;
			}
		}
		return d;
	}

	public Fighter(Fighter f) {
		this.nickName = f.nickName;
		this.team = f.getTeam();
		this.fight = f.getFight();
		this.setFirstName(f.getFirstName());
		this.setLastName(f.getLastName());
		this.setId(f.getId());
		this.image = f.image;
	}
	
	public String getFullName() {
		return getFirstName() + " '" + nickName + "' " + getLastName();
	}
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public List<Fight> getFight() {
		return fight;
	}

	public void setFight(List<Fight> fight) {
		this.fight = fight;
	}

	public Fighter(String nickName, Team team, List<Fight> fight) {
		super();
		this.nickName = nickName;
		this.team = team;
		this.fight = fight;
	}

	public Fighter() {
		super();
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
		Fighter other = (Fighter) obj;
		if (getId() != other.getId())
			return false;
		return true;
	}
	
}
