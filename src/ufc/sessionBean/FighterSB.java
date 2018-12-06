package ufc.sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ufc.models.Fighter;

@LocalBean
@Stateless
public class FighterSB {

	@PersistenceContext
	private EntityManager em;
	
	public void save(Fighter f) {
		if(f.getId() != null) {
			em.merge(f);
		} else {
			em.persist(f);
		}
	}
	
	public List<Fighter> getAll(){
		return em.createQuery("SELECT f FROM Fighter f", Fighter.class).getResultList();
	}
	
	public void remove(Fighter f) {
		em.remove(f);
	}
	
	public void deleteAll() {
		em.createQuery("DELETE FROM Fighter").executeUpdate();
	}
	
	public Object find(long parseLong) {
		return em.createQuery("SELECT f FROM Fighter f WHERE f.id = :id")
				.setParameter("id", parseLong)
				.getSingleResult();
	}
	
}
