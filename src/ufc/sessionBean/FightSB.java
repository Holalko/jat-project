package ufc.sessionBean;


import java.time.LocalDate;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ufc.models.Fight;
import ufc.models.Fighter;

@LocalBean
@Stateless
public class FightSB {

	@PersistenceContext
	private EntityManager em;
	
	public List<Fight> getAll(){
		return em.createQuery("SELECT f FROM Fight f", Fight.class).getResultList();
	}
	
	public List<Fight> getUpcoming(){
		return em.createQuery("SELECT f FROM Fight f WHERE f.date > :today", Fight.class)
				.setParameter("today", LocalDate.now())
				.getResultList();
	}
	
	public List<Fight> getOldFights(){
		return em.createQuery("SELECT f FROM Fight f WHERE f.date < :today", Fight.class)
				.setParameter("today", LocalDate.now())
				.getResultList();
	}
	
	public List<Fight> getFightersFights(Fighter f){
		return em.createQuery("SELECT f FROM Fight f WHERE f.redCorner = :fighter OR f.blueCorner = :fighter", Fight.class)
				.setParameter("fighter", f)
				.getResultList();
	}
	

	public List<Fight> getUpcomigFightsForFighter(Fighter f){
		return em.createQuery("SELECT f FROM Fight f WHERE (f.redCorner = :fighter OR f.blueCorner = :fighter) AND f.date > :today", Fight.class)
				.setParameter("fighter", f)
				.setParameter("today", LocalDate.now())
				.getResultList();
	}
	
	public void save(Fight f) {
		if(f.getId() != null) {
			em.merge(f);
		} else {
			em.persist(f);
		}
	}
	
	
	
}
