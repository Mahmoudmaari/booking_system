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

import mahmoud.maari.booking_system.form.BookingForm;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.service.BookingService;

@RestController
public class BookingController {

	private BookingService bookingSV;

	@Autowired
	public BookingController(BookingService bookingSV) {
		super();
		this.bookingSV = bookingSV;
	}
	
	@GetMapping("/Booking")
	public ResponseEntity<List<Booking>> getAllBooking(){
		List<Booking> booking = bookingSV.findAll();
		if(booking.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(booking);
		}
	}
	@PostMapping("/Booking")
	public ResponseEntity<Booking> create(@RequestBody BookingForm newBooking){
		if(newBooking == null) {
			ResponseEntity.badRequest().build();
		}
		Booking booking = bookingSV.save(new Booking(newBooking.getBookingDate(), newBooking.getBookingTime(), newBooking.isBooked()));
		return ResponseEntity.status(HttpStatus.CREATED).body(booking);
	}
	@GetMapping("/Booking/{id}")
	public ResponseEntity<Booking> findById(@PathVariable int id, @Valid @RequestBody Booking booking){
		if(bookingSV.findById(id)==null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(bookingSV.findById(id));
	}
	@PutMapping("/Booking/{id}")
	public ResponseEntity<Booking> update(@PathVariable int id,@Valid @RequestBody BookingForm newBooking){
		if(bookingSV.findById(id)== null) {
			ResponseEntity.notFound().build();
		}
		Booking booking = bookingSV.findById(id);
		booking.setBookingDate(newBooking.getBookingDate());
		booking.setBookingTime(newBooking.getBookingTime());
		booking.setBooked(newBooking.isBooked());
		return ResponseEntity.accepted().body(booking);
	}
} 
