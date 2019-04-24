package mahmoud.maari.booking_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mahmoud.maari.booking_system.form.ClientForm;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.ClientC;
import mahmoud.maari.booking_system.service.BookingService;
import mahmoud.maari.booking_system.service.ClientService;

@RestController
public class ClientController {

	private ClientService clientSV;
	private BookingService bookingSV;
	@Autowired
	public ClientController(ClientService clientSV,BookingService bookingSV) {
		super();
		this.clientSV = clientSV;
		this.bookingSV = bookingSV;
	}
	
	@GetMapping("/ClientC")
	public ResponseEntity<List<ClientC>> getAll(){
		List<ClientC> client = clientSV.findAll();
		if(client.isEmpty()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(client);
	}
	@PostMapping("/ClientC")
	public ResponseEntity<ClientC> create(@RequestBody ClientForm newclient){
		if(newclient == null) {
			ResponseEntity.badRequest().build();
		}
		ClientC client = clientSV.save(new ClientC(newclient.getClientName(), newclient.getBirthDate(),
				newclient.getGender(), newclient.getPhoneNumber(), newclient.getEmail(), newclient.getPassword()));
		return ResponseEntity.status(HttpStatus.CREATED).body(client);
	}
	@GetMapping("/ClientC/{id}")
	public ResponseEntity<ClientC> findById(@PathVariable int id){
		if(clientSV.findById(id)==null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(clientSV.findById(id));
	}
	@PutMapping("/ClientC/{id}")
	public ResponseEntity<ClientC> update(@PathVariable int id, @Valid @RequestBody ClientForm newclient){
		if(clientSV.findById(id)==null) {
			ResponseEntity.notFound().build();
		}
		ClientC client = clientSV.findById(id);
		client.setClientName(newclient.getClientName());
		client.setBirthDate(newclient.getBirthDate());
		client.setGender(newclient.getGender());
		client.setPhoneNumber(newclient.getPhoneNumber());
		client.setEmail(newclient.getEmail());
		
		if(client.getPassword().equals(newclient.getPassword())) {
			return ResponseEntity.accepted().body(client);
		}else {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
		
	}
	@PutMapping("/ClientC/{id}/password")
	public ResponseEntity<ClientC> changePassword(@PathVariable int id,@Valid @RequestBody ClientForm newclient){
		ClientC client = clientSV.findById(id);
		if(!client.getPassword().equals(newclient.getOldPassword())) {
			ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
		client.setPassword(newclient.getPassword());
		return ResponseEntity.accepted().body(client);
	}
	@PostMapping("/ClientC/{BID}/{CID}")
	public ResponseEntity<Boolean> add(@PathVariable int BID,@PathVariable int CID){
		if(bookingSV.findById(BID)==null) {
			ResponseEntity.notFound().build();
		}
		ClientC client = clientSV.findById(CID);
		Booking booking = bookingSV.findById(BID);
		return ResponseEntity.ok(clientSV.addBookingToClient(booking, client));
	}
	@PostMapping("/ClientC/Remove/{BID}/{CID}")
	public ResponseEntity<Boolean> remove (@PathVariable int BID,@PathVariable int CID){
		if(bookingSV.findById(BID)==null) {
			ResponseEntity.notFound().build();
		}
		ClientC client = clientSV.findById(CID);
		Booking booking = bookingSV.findById(BID);
		return ResponseEntity.ok(clientSV.addBookingToClient(booking, client));
	}
}
