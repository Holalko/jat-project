package ufc.managedBeans;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import ufc.models.Fight;
import ufc.models.Fighter;
import ufc.sessionBean.FightSB;
import ufc.sessionBean.FighterSB;

@ManagedBean
@SessionScoped
public class FighterMB {

	@EJB
	private FighterSB fighterSB;

	@EJB
	private FightSB fightSB;

	private Fighter editedFighter;
	private Part picture;

	public List<Fighter> getAllFighters() {
		List<Fighter> fighters = fighterSB.getAll();
		return fighters;
	}

	public String upload() {
		byte[] buffer = new byte[(int) picture.getSize()];
		try (InputStream inputStream = picture.getInputStream()) {
			int readed = inputStream.read(buffer);
			if (readed != picture.getSize()) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error upload file.", "Error upload file."));
			}
			editedFighter.setImage(buffer);
			fighterSB.save(editedFighter);
		} catch (IOException e) {
			// Error handling
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error upload file.", "Error upload file."));
		}
		return "";
	}

	public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
		List<FacesMessage> msgs = new ArrayList<FacesMessage>();
		Part file = (Part) value;
		if (file.getSize() > 500 * 1024) {
			msgs.add(new FacesMessage("file too big"));
		}
		if (!"image/png".equals(file.getContentType())) {
			msgs.add(new FacesMessage("not a text file"));
		}
		if (!msgs.isEmpty()) {
			throw new ValidatorException(msgs);
		}
	}

	public Part getPicture() {
		return picture;
	}

	public void setPicture(Part picture) {
		this.picture = picture;
	}

	public void deleteAll() {
		fighterSB.deleteAll();
	}

	public Fighter getEditedFighter() {
		return editedFighter;
	}

	public void setEditedFighter(Fighter fighter) {
		this.editedFighter = fighter;
	}

	public String create() {
		editedFighter = new Fighter();
		return "/admin/addFighter?faces-redirect=true";
	}

	public String edit(Fighter f) {
		editedFighter = new Fighter(f);
		return "/admin/addFighter?faces-redirect=true";
	}

	public String showInfo(Fighter f) {
		editedFighter = new Fighter(f);
		if (editedFighter.getFight() == null || editedFighter.getFight().isEmpty()) {
			editedFighter.setFight(fightSB.getFightersFights(f));
		}
		return "/private/fighterInfo?faces-redirect=true";
	}

	public List<Fight> getUpcomingFights() {
		return fightSB.getUpcomigFightsForFighter(editedFighter);
	}

	public String save() {
		fighterSB.save(editedFighter);
		return "/public/fighters?faces-redirect=true";
	}

	public String cancel() {
		return "/public/fighters?faces-redirect=true";
	}

}
