package mahmoud.maari.booking_system.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mahmoud.maari.booking_system.models.HaircutStyle;
import mahmoud.maari.booking_system.repository.HaircutStyleRepo;

@Service
@Transactional
public class HaircutStyleServiceImpl implements HaircutStyleService {

	private HaircutStyleRepo haircut;

	@Autowired
	public HaircutStyleServiceImpl(HaircutStyleRepo hiarcut) {
		super();
		this.haircut = hiarcut;
	}

	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#findById(int)
	 */
	@Override
	public HaircutStyle findById(int id) {
		Optional<HaircutStyle> result = haircut.findById(id);
		
		return result.orElseThrow(IllegalArgumentException::new);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#findAll()
	 */
	@Override
	public List<HaircutStyle> findAll() {
		return (List<HaircutStyle>) haircut.findAll();
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#findByName(java.lang.String)
	 */
	@Override
	public List<HaircutStyle> findByName(String name) {
		return haircut.findByhaircutTypeIgnoreCase(name);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#removeHaircut(int)
	 */
	@Override
	public boolean removeHaircut(int id) {
		haircut.deleteById(id);
		return haircut.existsById(id);
	}
	/* (non-Javadoc)
	 * @see mahmoud.maari.booking_system.service.HaircutStyleService#save(mahmoud.maari.booking_system.models.HaircutStyle)
	 */
	@Override
	public HaircutStyle save(HaircutStyle haircutStyle) {
		return haircut.save(haircutStyle);
	}
}
