package ufc.sessionBean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ufc.models.Fighter;
import ufc.models.Team;

@LocalBean
@Stateless
public class TeamSB {

	@PersistenceContext
	private EntityManager em;

	public List<Team> getAll() {
		List<Team> teams = em.createQuery("SELECT t FROM Team t", Team.class).getResultList();
		for (Team t : teams) {
			if (t.getFighters() == null || t.getFighters().isEmpty()) {
				t.setFighters(em.createQuery("SELECT f FROM Fighter f WHERE f.team = :team", Fighter.class)
						.setParameter("team", t).getResultList());
			}
		}
		return teams;
	}

	public void save(Team t) {
		if (t.getId() != null) {
			em.merge(t);
		} else {
			em.persist(t);
		}
	}

	public Object find(long parseLong) {
		return em.createQuery("SELECT t FROM Team t WHERE t.id = :id").setParameter("id", parseLong).getSingleResult();
	}

}
