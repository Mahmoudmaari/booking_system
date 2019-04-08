package mahmoud.maari.booking_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.Client;
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
	public Client findById(int id) {
		Optional<Client> result= clientRepo.findById(id);
		
		return result.orElseThrow(IllegalArgumentException::new);
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.ClientService#findAll()
	 */
	@Override
	public List<Client> findAll() {
		return (List<Client>) clientRepo.findAll();
	}
	
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.ClientService#findByName(java.lang.String)
	 */
	@Override
	public List<Client> findByName(String name){
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
	public Client save(Client client) {
		return clientRepo.save(client);
	}
	
}
