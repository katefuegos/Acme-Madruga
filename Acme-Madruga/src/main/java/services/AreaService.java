package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AreaRepository;
import domain.Area;

@Service
@Transactional
public class AreaService {

	// Repository-----------------------------------------------

	@Autowired
	private AreaRepository areaRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public AreaService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Area create() {
		final Area res = new Area();
		return res;
	}

	public List<Area> findAll() {
		return this.areaRepository.findAll();
	}

	public Area findOne(final Integer areaId) {
		return this.areaRepository.findOne(areaId);
	}

	public Area save(final Area area) {
		Assert.notNull(area);
		final Area saved = this.areaRepository.save(area);
		return saved;
	}

	public void delete(final Area area) {
		this.areaRepository.delete(area);
	}

	// Other Methods--------------------------------------------

}
