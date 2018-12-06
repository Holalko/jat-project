package ufc.managedBeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import ufc.models.Login;
import ufc.models.enums.Role;
import ufc.sessionBean.RegistrationSB;

@ManagedBean
@RequestScoped
public class RegistrationMB {

	@EJB
	private RegistrationSB registrationSB;
	
	private Login registerForm = new Login();

	public Login getRegisterForm() {
		return registerForm;
	}

	public void setRegisterForm(Login registerForm) {
		this.registerForm = registerForm;
	}
	
	public String register() {
		// TODO change to user
		registerForm.setRole(Role.ADMIN);
		if(registrationSB.register(registerForm)) {
			return "/public/login?faces-redirect=true";
		}
		return "/public/registration?faces-redirect=true";
	}
	
	
}
