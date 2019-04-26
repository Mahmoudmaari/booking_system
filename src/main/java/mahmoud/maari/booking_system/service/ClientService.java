package mahmoud.maari.booking_system.service;

import java.util.List;

import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.ClientC;

public interface ClientService {

	ClientC findById(int id);

	List<ClientC> findAll();

	List<ClientC> findByName(String name);

	boolean removeClinet(int id);

	ClientC save(ClientC client);



	boolean addBookingToClient(Booking b, ClientC c);

	boolean removeClientFromBooking(Booking b, ClientC c);

}