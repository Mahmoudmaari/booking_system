package mahmoud.maari.booking_system.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.BarberRate;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.ClientC;
import mahmoud.maari.booking_system.repository.ClientRepo;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

	private ClientRepo clientRepo;

	@Autowired
	public ClientServiceImpl(ClientRepo clientRepo) {
		super();
		this.clientRepo = clientRepo;
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.ClientService#findById(int)
	 */
	@Override
	public ClientC findById(int id) {
		Optional<ClientC> result= clientRepo.findById(id);
		
		return result.orElseThrow(IllegalArgumentException::new);
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.ClientService#findAll()
	 */
	@Override
	public List<ClientC> findAll() {
		return (List<ClientC>) clientRepo.findAll();
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.ClientService#findByName(java.lang.String)
	 */
	@Override
	public List<ClientC> findByName(String name){
		return clientRepo.findByclientNameIgnoreCase(name);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.ClientService#removeClinet(int)
	 */
	@Override
	public boolean removeClinet(int id ) {
		clientRepo.deleteById(id);
		return clientRepo.existsById(id);
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.ClientService#save(mahmoud.maari.booking_system.models.Client)
	 */
	@Override
	public ClientC save(ClientC client) {
		return clientRepo.save(client);
	}
	
	@Override
	public boolean takeRateFromClient(ClientC client,BarberRate r) {
		List<BarberRate> rate = new ArrayList<>();
		List<ClientC> clients = new ArrayList<>();
		clients.addAll(r.getClient());
		clients.add(findById(client.getId()));
		
		if(clients.equals(r.getClient())){
			throw new IllegalArgumentException();
		}
		
		
		return rate.add(r);
	}
	
	@Override
	public boolean addBookingToClient(Booking b,ClientC c) {
		List<Booking> booking = new ArrayList<>();

		if(findById(c.getId()).equals(b.getClient())) {
			throw new IllegalArgumentException();
		}
		b.setBooked(true);
		b.setClient(c);
		return booking.add(b);
	}

	@Override
	public boolean removeClientFromBooking(Booking b, ClientC c) {
		List<ClientC> client = new ArrayList<>();
		b.setClient(c=null);
		return client.add(c);
	}
}
