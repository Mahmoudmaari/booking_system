package mahmoud.maari.booking_system.models;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;



@Entity
public class BarberRate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private BigDecimal starRate;
	
	
	
	public BarberRate(BigDecimal starRate) {
		super();
		if(starRate.equals(new BigDecimal(0)) ) {
			throw new IllegalArgumentException();
		}
		this.starRate = starRate;
		
	}

	public BarberRate() {
	}

	public BigDecimal getStarRate() {
		return starRate;
	}

	public void setStarRate(BigDecimal starRate) {
		this.starRate = starRate;
	}

	
	public int getId() {
		return id;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BarberRate other = (BarberRate) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BarberRate [id=" + id + ", starRate=" + starRate + "]";
	}

	
}
