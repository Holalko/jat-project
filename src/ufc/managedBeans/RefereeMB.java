package ufc.managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ufc.models.Referee;
import ufc.sessionBean.RefereeSB;

@ManagedBean
@SessionScoped
public class RefereeMB {
	@EJB
	private RefereeSB refereeSB;
	
	private Referee editedReferee;
	
	
	
	public Referee getEditedReferee() {
		return editedReferee;
	}

	public void setEditedReferee(Referee editedReferee) {
		this.editedReferee = editedReferee;
	}

	public List<Referee> getAll(){
		return this.refereeSB.getAll();
	}
	
	public String create() {
		editedReferee = new Referee();
		return "/admin/addReferee?faces-redirect=true";
	}
	
	public String edit(Referee r) {
		editedReferee = new Referee(r);
		return "/admin/addReferee?faces-redirect=true";
	}
	
	public String save() {
		refereeSB.save(editedReferee);
		return "/public/referees?faces-redirect=true";
	}
	
	public String cancel() {
		return "/public/referees?faces-redirect=true";
	}
}
