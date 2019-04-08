package mahmoud.maari.booking_system.service;

import java.util.List;

import mahmoud.maari.booking_system.models.Client;

public interface ClientService {

	Client findById(int id);

	List<Client> findAll();

	List<Client> findByName(String name);

	boolean removeClinet(int id);

	Client save(Client client);

}