package ufc.managedBeans;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import ufc.models.Fight;
import ufc.sessionBean.FightSB;

@ManagedBean
@SessionScoped
public class FightMB {

	@EJB
	private FightSB fightSB;
	
	private Fight editedFight;
	
	public List<Fight> getAll(){
		return this.fightSB.getAll();
	}
	
	public List<Fight> getUpcoming(){
		return this.fightSB.getUpcoming();
	}
	
	public List<Fight> getOldFights(){
		return this.fightSB.getOldFights();
	}
	
	public String create() {
		editedFight = new Fight();
		return "/admin/addFight?faces-redirect=true";
	}
	
	public String edit(Fight f) {
		editedFight = new Fight(f);
		return "/admin/addFight?faces-redirect=true";
	}
	
	public String setResult(Fight f) {
		editedFight = new Fight(f);
		return "/admin/fightResult?faces-redirect=true";
	}
	
	 private UIComponent component;

    public UIComponent getComponent() {
        return component;
    }

    public void setComponent(UIComponent component) {
        this.component = component;
    }
	
	public String save() {
		if(editedFight.getRedCorner().equals(editedFight.getBlueCorner())) {
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(component.getClientId(), new FacesMessage("Can't select one fighter 2 times!"));
	        return "";
		}
		
		fightSB.save(editedFight);
		return "/public/fights?faces-redirect=true";
	}
	
	public String cancel() {
		return "/public/fights?faces-redirect=true";
	}

	public Fight getEditedFight() {
		return editedFight;
	}

	public void setEditedFight(Fight editedFight) {
		this.editedFight = editedFight;
	}
	
	
	
}
