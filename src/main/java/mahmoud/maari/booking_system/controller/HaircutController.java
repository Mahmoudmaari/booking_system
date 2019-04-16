package mahmoud.maari.booking_system.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import mahmoud.maari.booking_system.form.HaircutForm;
import mahmoud.maari.booking_system.models.HaircutStyle;
import mahmoud.maari.booking_system.service.HaircutStyleService;

@RestController
public class HaircutController {
	
	private HaircutStyleService hairStyleSV;

	@Autowired
	public HaircutController(HaircutStyleService hairStyleSV) {
		super();
		this.hairStyleSV = hairStyleSV;
	}
	
	@GetMapping("/HaircutStyle")
	public ResponseEntity<List<HaircutStyle>> getAllHaircut(){
		List<HaircutStyle> haircut = hairStyleSV.findAll();
		if(haircut.isEmpty()) {
			ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(haircut);
	}
	@PostMapping("/HaircutStyle")
	public ResponseEntity<HaircutStyle> create (@RequestBody HaircutForm newHaircut){
		if(newHaircut == null) {
			ResponseEntity.badRequest().build();
		}
		HaircutStyle haircut = hairStyleSV.save(new HaircutStyle(newHaircut.getHaircutType(), newHaircut.getDescription(), newHaircut.getPrice()));
		return ResponseEntity.accepted().body(haircut);
		
	}
	@GetMapping("/HaircutStyle/{id}")
	public ResponseEntity<HaircutStyle> findByid(@PathVariable int id){
		if(hairStyleSV.findById(id)==null) {
			ResponseEntity.noContent().build();
		}
		HaircutStyle haircut = hairStyleSV.findById(id);
		return ResponseEntity.ok(haircut);
		
	}
	@PutMapping("/HaircutStyle/{id}")
	public ResponseEntity<HaircutStyle> update(@PathVariable int id,@Valid @RequestBody HaircutForm newhaircut){
		if(hairStyleSV.findById(id)==null) {
			ResponseEntity.notFound().build();
		}
		HaircutStyle haircut = hairStyleSV.findById(id);
		haircut.setHaircutType(newhaircut.getHaircutType());
		haircut.setDescription(newhaircut.getDescription());
		haircut.setPrice(newhaircut.getPrice());
		return ResponseEntity.accepted().body(haircut);
	}
}
