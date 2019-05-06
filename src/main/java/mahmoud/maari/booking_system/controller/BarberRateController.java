package mahmoud.maari.booking_system.controller;

import java.math.BigDecimal;
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

import mahmoud.maari.booking_system.form.BarberRateForm;
import mahmoud.maari.booking_system.models.Barber;
import mahmoud.maari.booking_system.models.BarberRate;
import mahmoud.maari.booking_system.models.Booking;
import mahmoud.maari.booking_system.service.BarberRateService;
import mahmoud.maari.booking_system.service.BarberService;
import mahmoud.maari.booking_system.service.BookingService;

@RestController
public class BarberRateController {

	private BarberRateService rateSV;
	private BookingService bookingSV;
	private BarberService barberSV;

	
	@Autowired
	public BarberRateController(BarberRateService rateSV, BookingService bookingSV, BarberService barberSV) {
		super();
		this.rateSV = rateSV;
		this.bookingSV = bookingSV;
		this.barberSV = barberSV;
	}

	
	@GetMapping("/BarberRate")
	public ResponseEntity<List<BarberRate>> getAllRate(){
		List<BarberRate> rate = rateSV.findAll();
		if(rate.isEmpty()) {
		return	ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(rate);
				
	}
	

	@PostMapping("/BarberRate")
	public ResponseEntity<BarberRate> create (@Valid @RequestBody BarberRateForm rateForm){
		BarberRate rate = rateSV.save(new BarberRate(rateForm.getStarRate()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(rate);
	}
	
	@PutMapping("/BarberRate/{id}")
	public ResponseEntity<BigDecimal> cal(@PathVariable int id){
		
	   Barber barber = barberSV.findById(id);
	   
	
		return ResponseEntity.ok().body(bookingSV.RateCal(barber));
	}
	
	@GetMapping("/BarberRate/{id}")
	public ResponseEntity<BarberRate> findById(@PathVariable int id){
		return ResponseEntity.ok().body(rateSV.findById(id));		
	}
	
	@PutMapping("/BarberRate/rate/{RID}/{BID}")
	public ResponseEntity<BarberRate> addRateToBooking(@PathVariable int RID,@PathVariable int BID){
		BarberRate rate = rateSV.findById(RID);
		Booking booking = bookingSV.findById(BID);
		if(rate==null) {
			ResponseEntity.notFound().build();
		}
		rateSV.addRateToBooking(rate, booking);
		return ResponseEntity.accepted().body(rateSV.save(rate));
	}
}
