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

import mahmoud.maari.booking_system.form.BookingForm;
import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.models.HaircutStyle;
import mahmoud.maari.booking_system.service.BarberService;
import mahmoud.maari.booking_system.service.BookingService;
import mahmoud.maari.booking_system.service.HaircutStyleService;

@RestController
public class BookingController {

	private BookingService bookingSV;
	private BarberService barberSV;
	private HaircutStyleService haircutSV;

	@Autowired
	public BookingController(BookingService bookingSV,BarberService barberSV,HaircutStyleService haircutSV) {
		super();
		this.bookingSV = bookingSV;
		this.barberSV = barberSV;
		this.haircutSV = haircutSV;
	}
	@CrossOrigin(origins="*")
	@GetMapping("/Booking")
	public ResponseEntity<List<Booking>> getAllBooking(){
		List<Booking> booking = bookingSV.findAll();
		if(booking.isEmpty()) {
			return ResponseEntity.noContent().build();
		}else {
			return ResponseEntity.ok(booking);
		}
	}
	@CrossOrigin(origins="*")
	@PostMapping("/Booking")
	public ResponseEntity<Booking> create(@RequestBody BookingForm newBooking){
		if(newBooking.getBookingDate()==null ) {
		return	ResponseEntity.badRequest().build();
		}else {

			Booking booking = new Booking(newBooking.getBookingDate(),
					newBooking.getBookingTime(), newBooking.isBooked());
			
				bookingSV.save(booking);
			
				return ResponseEntity.status(HttpStatus.CREATED).body(booking);
		}
		
		
	}
	@CrossOrigin(origins="*")
	@GetMapping("/Booking/{id}")
	public ResponseEntity<Booking> findById(@PathVariable int id, @Valid @RequestBody Booking booking){
		if(bookingSV.findById(id)==null) {
			ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(bookingSV.findById(id));
	}
	@CrossOrigin(origins="*")
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
	@CrossOrigin(origins="*")
	@PostMapping("/Booking/barber/{OID}/{BID}")
	public ResponseEntity<Booking> addBarber (@PathVariable int OID,@PathVariable int BID ){
		if(barberSV.findById(BID)==null || bookingSV.findById(OID)==null) {
			ResponseEntity.badRequest().build();
		}
		Booking booking = bookingSV.findById(OID);
		Barber barber = barberSV.findById(BID);
		
		return ResponseEntity.ok().body(bookingSV.addBarberToBooking(booking, barber));
	}
	@CrossOrigin(origins="*")
	@PostMapping("/Booking/haircut/{BID}/{HID}")
	public ResponseEntity<Booking> addHaircut(@PathVariable int BID,@PathVariable int HID){
		if(haircutSV.findById(HID)==null ||bookingSV.findById(BID)==null ) {
			ResponseEntity.badRequest().build();
		}
		HaircutStyle haircut = haircutSV.findById(HID);
		Booking booking = bookingSV.findById(BID);
		
		return ResponseEntity.ok().body(bookingSV.addHaircutToBooking(booking, haircut));
	}
} 
