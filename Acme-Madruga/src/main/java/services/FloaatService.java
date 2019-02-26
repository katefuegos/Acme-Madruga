
package services;

import java.util.Collection;

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
	private FloaatRepository	floaatRepository;


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

	public Collection<Floaat> findAll() {
		Collection<Floaat> floaats;

		floaats = this.floaatRepository.findAll();
		Assert.notNull(floaats);

		return floaats;
	}

	public Floaat findOne(final Integer floaatId) {
		Floaat floaat;
		floaat = this.floaatRepository.findOne(floaatId);
		Assert.notNull(floaat);

		return floaat;
	}

	public Floaat save(final Floaat floaat) {
		Assert.notNull(floaat);
		final Floaat saved = this.floaatRepository.save(floaat);
		return saved;
	}

	public void delete(final Floaat floaat) {
		Assert.notNull(floaat);
		this.floaatRepository.delete(floaat);
	}

	// Other Methods--------------------------------------------

	public Collection<Floaat> findByBrotherhoodId(final int brotherhoodId) {
		Assert.notNull(brotherhoodId);
		return this.floaatRepository.findByBrotherhoodId(brotherhoodId);
	}

}
