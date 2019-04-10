package mahmoud.maari.booking_system.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.Client;
import mahmoud.maari.booking_system.models.HaircutStyle;
import mahmoud.maari.booking_system.repository.HaircutStyleRepo;

@Service
@Transactional
public class HaircutStyleServiceImpl implements HaircutStyleService {

	private HaircutStyleRepo haircutRepo;

	@Autowired
	public HaircutStyleServiceImpl(HaircutStyleRepo hiarcut) {
		super();
		this.haircutRepo = hiarcut;
	}

	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#findById(int)
	 */
	@Override
	public HaircutStyle findById(int id) {
		Optional<HaircutStyle> result = haircutRepo.findById(id);
		
		return result.orElseThrow(IllegalArgumentException::new);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#findAll()
	 */
	@Override
	public List<HaircutStyle> findAll() {
		return (List<HaircutStyle>) haircutRepo.findAll();
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#findByName(java.lang.String)
	 */
	@Override
	public List<HaircutStyle> findByName(String name) {
		return haircutRepo.findByHaircutTypeIgnoreCase(name);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#removeHaircut(int)
	 */
	@Override
	public boolean removeHaircut(int id) {
		haircutRepo.deleteById(id);
		return haircutRepo.existsById(id);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#save(mahmoud.maari.booking_system.models.HaircutStyle)
	 */
	@Override
	public HaircutStyle save(HaircutStyle haircutStyle) {
		return haircutRepo.save(haircutStyle);
	}
	@Override
	public boolean addHaircutTOclient(HaircutStyle h,Client c) {
		List<HaircutStyle> haircut = new ArrayList<>();
		if(findById(h.getId()).equals(c.getHaircutStyle())){
			throw new IllegalArgumentException();
		}
		c.setHaircutStyle(h);
		return haircut.add(h);
	}
}
