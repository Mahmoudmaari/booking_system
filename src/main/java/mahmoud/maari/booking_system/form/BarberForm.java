package mahmoud.maari.booking_system.form;

public class BarberForm {

	private String name;
	private String phoneNaumber;
	private boolean available;
	
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNaumber() {
		return phoneNaumber;
	}
	public void setPhoneNaumber(String phoneNaumber) {
		this.phoneNaumber = phoneNaumber;
	}
}
