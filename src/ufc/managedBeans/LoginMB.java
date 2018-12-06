package ufc.managedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import ufc.models.Login;
import ufc.models.enums.Role;
import ufc.sessionBean.LoginSB;

@ManagedBean
@SessionScoped
public class LoginMB {

	@EJB
	private LoginSB loginSB;

	private Login login = new Login();

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public void authenticate() {
		boolean isAuthenticated = loginSB.isAuthenticated(login);
		if (isAuthenticated) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
			session.setAttribute("loggedUser", login.getUsername());
			session.setAttribute("role", login.getRole());
		}
	}

	public boolean isLogged() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		return session.getAttribute("loggedUser") != null;
	}
	
	public String logout() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("loggedUser", null);
		session.setAttribute("role", null);
		return "/public/index?faces-redirect=true";
	}
	
	public boolean isAdmin() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		return Role.ADMIN.equals(session.getAttribute("role"));
	}

}
