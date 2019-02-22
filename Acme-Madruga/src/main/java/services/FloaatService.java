package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FloaatRepository;
import domain.Floaat;

@Service
@Transactional
public class FloaatService {

	// Repository-----------------------------------------------

	@Autowired
	private FloaatRepository floaatRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public FloaatService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Floaat create() {
		final Floaat res = new Floaat();
		return res;
	}

	public List<Floaat> findAll() {
		return this.floaatRepository.findAll();
	}

	public Floaat findOne(final Integer floaatId) {
		return this.floaatRepository.findOne(floaatId);
	}

	public Floaat save(final Floaat floaat) {
		Assert.notNull(floaat);
		final Floaat saved = this.floaatRepository.save(floaat);
		return saved;
	}

	public void delete(final Floaat floaat) {
		this.floaatRepository.delete(floaat);
	}

	// Other Methods--------------------------------------------

	public Collection<Floaat> findByBrotherhoodId(int brotherhoodId) {
		Assert.notNull(brotherhoodId);
		return floaatRepository.findByBrotherhoodId(brotherhoodId);
	}

}
