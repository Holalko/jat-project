package ufc.sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ufc.models.Login;

@LocalBean
@Stateless
public class RegistrationSB {

		@PersistenceContext
		private EntityManager em;
		
		public Boolean register(Login login) {
			List<Login> users = em.createQuery("SELECT l FROM Login l "
					+ "WHERE l.username = :username ", Login.class)
			.setParameter("username", login.getUsername())
			.getResultList();
			if(!users.isEmpty()) {
				return false;
			}
			
			em.persist(login);
			return true;
		}
		
}