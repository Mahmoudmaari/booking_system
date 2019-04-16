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
import javax.persistence.Transient;



@Entity
public class BarberRate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Transient
	private double oldRate;
	@ElementCollection(targetClass=BigDecimal.class)
	private List<BigDecimal> starRate;
	private BigDecimal RateResult;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	private Barber barber;
	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private ClientC client;

	public BarberRate(List<BigDecimal> starRate, BigDecimal rateResult) {
		super();
		this.starRate = starRate;
		this.RateResult = rateResult;
	}

	public BarberRate() {
	}

	public List<BigDecimal> getStarRate() {
		return starRate;
	}

	public void setStarRate(List<BigDecimal> starRate) {
		this.starRate = starRate;
	}

	public double getOldRate() {
		return oldRate;
	}

	public void setOldRate(double oldRate) {
		this.oldRate = oldRate;
	}

	public BigDecimal getRateResult() {
		return RateResult;
	}

	public void setRateResult(BigDecimal rateResult) {
		RateResult = rateResult;
	}

	public Barber getBarber() {
		return barber;
	}

	public void setBarber(Barber barber) {
		this.barber = barber;
	}

	public ClientC getClient() {
		return client;
	}

	public void setClient(ClientC client) {
		this.client = client;
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
		return "BarberRate [id=" + id + ", oldRate=" + oldRate + ", starRate=" + starRate + ", RateResult=" + RateResult
				+ ", barber=" + barber + ", client=" + client + "]";
	}

	public BigDecimal RateCal(List<BigDecimal> starRate) {

		starRate.forEach(s -> oldRate += s.doubleValue());
		oldRate /= starRate.size();
		RateResult = new BigDecimal(oldRate).setScale(1, RoundingMode.DOWN);
		return RateResult;
	}
	
}
