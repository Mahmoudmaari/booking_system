package mahmoud.maari.booking_system.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Barber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String phoneNaumber;




	public Barber(String name, String phoneNaumber) {
		super();
		this.name = name;
		this.phoneNaumber = phoneNaumber;
	}

	public Barber() {
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
	public String toString() {
		return "Barber [id=" + id + ", name=" + name + ", phoneNaumber=" + phoneNaumber + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Barber other = (Barber) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
