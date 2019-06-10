package mahmoud.maari.booking_system.form;


public class HaircutForm {

	private String haircutType;
	private String description;
	private double price;
	private int cutingHour;
	private int cutingMinutes;
	
	
	public int getCutingHour() {
		return cutingHour;
	}
	public void setCutingHour(int cutingHour) {
		this.cutingHour = cutingHour;
	}
	public int getCutingMinutes() {
		return cutingMinutes;
	}
	public void setCutingMinutes(int cutingMinutes) {
		this.cutingMinutes = cutingMinutes;
	}
	public String getHaircutType() {
		return haircutType;
	}
	public void setHaircutType(String haircutType) {
		this.haircutType = haircutType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
