package mahmoud.maari.booking_system.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Barber {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String phoneNaumber;

	@OneToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.EAGER)
	private List<Client> client = new ArrayList<>();



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

	public boolean addClient(Client c) {
		if (c.getBarber() != null) {
			throw new IllegalArgumentException();
		}
		c.setBarber(this);
		return client.add(c);

	}

	public List<Client> getClient() {
		return client;
	}

	public void setClient(List<Client> client) {
		this.client = client;
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
		return "Barber [id=" + id + ", name=" + name + ", phoneNaumber=" + phoneNaumber + ", client=" + client + "]";
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
