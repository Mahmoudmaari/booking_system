package mahmoud.maari.booking_system.form;

import java.math.BigDecimal;
import java.util.List;

public class BarberRateForm {
	private List<BigDecimal> starRate;
	private BigDecimal RateResult;
	private double oldRate;
	
	public List<BigDecimal> getStarRate() {
		return starRate;
	}
	public void setStarRate(List<BigDecimal> starRate) {
		this.starRate = starRate;
	}
	public BigDecimal getRateResult() {
		return RateResult;
	}
	public void setRateResult(BigDecimal rateResult) {
		RateResult = rateResult;
	}
	public double getOldRate() {
		return oldRate;
	}
	public void setOldRate(double oldRate) {
		this.oldRate = oldRate;
	}

	

}
