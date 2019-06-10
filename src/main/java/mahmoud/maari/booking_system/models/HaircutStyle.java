package mahmoud.maari.booking_system.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HaircutStyle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String haircutType;
	private String description;
	private double price;
	private int cutingHour;
	private int cutingMinutes;



	public HaircutStyle(String haircutType, String description, double price, int cutingHour, int cutingMinutes) {
		super();
		this.haircutType = haircutType;
		this.description = description;
		this.price = price;
		this.cutingHour = cutingHour;
		this.cutingMinutes = cutingMinutes;
	}




	public HaircutStyle() {
	}

	


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

	@Override
	public String toString() {
		return "HaircutStyle [id=" + id + ", haircutType=" + haircutType + ", description=" + description + ", price="
				+ price + "]";
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
		HaircutStyle other = (HaircutStyle) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
