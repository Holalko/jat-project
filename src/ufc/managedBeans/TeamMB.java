package ufc.managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ufc.models.Team;
import ufc.sessionBean.TeamSB;

@ManagedBean
@SessionScoped
public class TeamMB {

	@EJB
	private TeamSB teamSB;
	
	private Team editedTeam;

	public List<Team> getAllTeams(){
		return teamSB.getAll();
	}
	
	public Team getEditedTeam() {
		return editedTeam;
	}

	public void setEditedTeam(Team team) {
		this.editedTeam = team;
	}
	
	public String create() {
		editedTeam = new Team();
		return "/admin/addTeam?faces-redirect=true";
	}
	
	public String save() {
		teamSB.save(editedTeam);
		return "/public/teams?faces-redirect=true";
	}
	
	public String cancel() {
		return "/public/teams?faces-redirect=true";
	}
	
}
