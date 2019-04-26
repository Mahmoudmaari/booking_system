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
import mahmoud.maari.booking_system.models.BarberRate;
import mahmoud.maari.booking_system.service.BarberRateService;

@RestController
public class BarberRateController {

	private BarberRateService rateSV;

	
	@Autowired
	public BarberRateController(BarberRateService rateSV) {
		super();
		this.rateSV = rateSV;
		
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
		rate.setRateResult(rate.RateCal(rate.getStarRate()));
		return ResponseEntity.status(HttpStatus.CREATED).body(rate);
	}
	
	@PutMapping("/BarberRate/{id}")
	public ResponseEntity<BarberRate> add(@PathVariable int id,@Valid @RequestBody BarberRateForm rateForm){
		
		List<BigDecimal> newRate = rateForm.getStarRate();
		BarberRate rate = rateSV.findById(id);
		rate.getStarRate().addAll(newRate);
		rate.setRateResult(rate.RateCal(rate.getStarRate()));
		return ResponseEntity.ok().body(rateSV.save(rate));
	}
	
	@GetMapping("/BarberRate/{id}")
	public ResponseEntity<BarberRate> findById(@PathVariable int id){
		return ResponseEntity.ok().body(rateSV.findById(id));		
	}
	
	
	
}
