package ufc.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Fight {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String place;
	private LocalDate date;
	
	@ManyToOne
	private Fighter redCorner;
	
	@ManyToOne
	private Fighter blueCorner;
	
	@ManyToOne
	private Referee referee;
	
	private String result;

	public Fight() {
		super();
	}
	public Fight(Fight f) {
		super();
		this.id = f.id;
		this.place = f.place;
		this.date = f.date;
		this.redCorner = f.redCorner;
		this.blueCorner = f.blueCorner;
		this.referee = f.referee;
		this.result = f.result;
	}
	
	public Fight(Long id, String place,String result, LocalDate date, Fighter redCorner, Fighter blueCorner, Referee referee) {
		super();
		this.id = id;
		this.result = result;
		this.place = place;
		this.date = date;
		this.redCorner = redCorner;
		this.blueCorner = blueCorner;
		this.referee = referee;
	}

	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Fighter getRedCorner() {
		return redCorner;
	}

	public void setRedCorner(Fighter redCorner) {
		this.redCorner = redCorner;
	}

	public Fighter getBlueCorner() {
		return blueCorner;
	}

	public void setBlueCorner(Fighter blueCorner) {
		this.blueCorner = blueCorner;
	}

	public Referee getReferee() {
		return referee;
	}

	public void setReferee(Referee referee) {
		this.referee = referee;
	}
	
	
}
