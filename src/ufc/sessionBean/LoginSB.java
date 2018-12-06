package ufc.sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ufc.models.Login;

@LocalBean
@Stateless
public class LoginSB {

	@PersistenceContext
	private EntityManager em;
	
	public Boolean isAuthenticated(Login login) {
		List<Login> users = em.createQuery("SELECT l FROM Login l "
				+ "WHERE l.username = :username "
				+ "AND l.password = :password", Login.class)
		.setParameter("username", login.getUsername())
		.setParameter("password", login.getPassword())
		.getResultList();
		if(!users.isEmpty()) {
			login.setRole(users.get(0).getRole());
		}
		return !users.isEmpty();
	}
	
}
