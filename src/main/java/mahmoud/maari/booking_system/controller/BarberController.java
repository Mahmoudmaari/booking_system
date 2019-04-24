package mahmoud.maari.booking_system.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mahmoud.maari.booking_system.form.BarberForm;
import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.service.BarberService;
import mahmoud.maari.booking_system.service.BookingService;

@RestController
public class BarberController {

	 private BarberService barberSV;
	 private BookingService bookingSV;
	 
	@Autowired
	public BarberController(BarberService barberSV,BookingService bookingSV) {
		super();
		this.barberSV = barberSV;
		this.bookingSV = bookingSV;
	}


	@CrossOrigin(origins="*")
	@GetMapping("/Barber")
	public ResponseEntity<List<Barber>> getAllBarber(){
		List<Barber> barber = barberSV.findAll();
		if (barber.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
		return ResponseEntity.ok(barber);
		}
		
	}
	@PostMapping("/Barber")
	public ResponseEntity<Barber> create(@RequestBody BarberForm newBarber){
		if(newBarber == null) {
			return ResponseEntity.badRequest().build();
		}else {
			
		Barber barber = barberSV.save(new Barber(newBarber.getName(), newBarber.getPhoneNaumber(),newBarber.isAvailable() ));
		return ResponseEntity.status(HttpStatus.CREATED).body(barber);
		}	
	}
	@GetMapping("/Barber/{id}")
	public ResponseEntity<Barber> findById(@PathVariable int id ){
		if(barberSV.findById(id)== null) {
			ResponseEntity.notFound().build();
		}
	return ResponseEntity.ok().body(barberSV.findById(id));	
	}
	@PutMapping("/Barber/{id}")
	public ResponseEntity<Barber> update (@PathVariable int id,@Valid @RequestBody BarberForm newbarber){
		if(barberSV.findById(id)==null) {
			return ResponseEntity.notFound().build();
		}else {
			Barber barber = barberSV.findById(id);
			barber.setName(newbarber.getName());
			barber.setPhoneNaumber(newbarber.getPhoneNaumber());
			barber.setAvailable(newbarber.isAvailable());
			return ResponseEntity.accepted().body(barberSV.save(barber));
		}
	}
	@PostMapping("/Barber/{OID}/{BID}")
	public ResponseEntity<Boolean> add (@PathVariable int OID,@PathVariable int BID ){
		if(barberSV.findById(BID) == null) {
			return ResponseEntity.notFound().build();
		}
		Barber barber = barberSV.findById(BID);
		Booking booking = bookingSV.findById(OID);
		
		return ResponseEntity.ok(barberSV.addBookingToBarber(booking, barber));
			
		
	}

	@PostMapping("/Barber/Remove/{OID}/{BID}")
	public ResponseEntity<Boolean> remove (@PathVariable int OID,@PathVariable int BID ){
		if(barberSV.findById(BID) == null) {
			return ResponseEntity.notFound().build();
		}
		Barber barber = barberSV.findById(BID);
		Booking booking = bookingSV.findById(OID);
		
		return ResponseEntity.ok(barberSV.removeBarberFromBooking(booking, barber));	
		
	}
	@PutMapping("/Barber/{id}/available")
	public ResponseEntity<Barber> available(@PathVariable int id ,@Valid @RequestBody BarberForm newBarber){
		if(barberSV.findById(id)== null) {
			return ResponseEntity.notFound().build();
		}
		Barber barber = barberSV.findById(id);
		barber.setAvailable(newBarber.isAvailable());
		return ResponseEntity.accepted().body(barber);
	}
}
