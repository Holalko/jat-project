package ufc.sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ufc.models.Referee;

@LocalBean
@Stateless
public class RefereeSB {
	
	@PersistenceContext
	private EntityManager em;
	
	public void save(Referee r) {
		if(r.getId() != null) {
			em.merge(r);
		} else {
			em.persist(r);
		}
	}
	
	public List<Referee> getAll(){
		return em.createQuery("SELECT r FROM Referee r", Referee.class).getResultList();
	}
	
	public void remove(Referee r) {
		em.remove(r);
	}
	
	public Object find(long parseLong) {
		return em.createQuery("SELECT r FROM Referee r WHERE r.id = :id")
				.setParameter("id", parseLong)
				.getSingleResult();
	}
	
}
