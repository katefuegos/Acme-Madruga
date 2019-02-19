package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BrotherhoodRepository;
import domain.Brotherhood;

@Service
@Transactional
public class BrotherhoodService {

	// Repository-----------------------------------------------

	@Autowired
	private BrotherhoodRepository brotherhoodRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public BrotherhoodService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Brotherhood create() {
		final Brotherhood res = new Brotherhood();
		return res;
	}

	public List<Brotherhood> findAll() {
		return this.brotherhoodRepository.findAll();
	}

	public Brotherhood findOne(final Integer brotherhoodId) {
		return this.brotherhoodRepository.findOne(brotherhoodId);
	}

	public Brotherhood save(final Brotherhood brotherhood) {
		Assert.notNull(brotherhood);
		final Brotherhood saved = this.brotherhoodRepository.save(brotherhood);
		return saved;
	}

	public void delete(final Brotherhood brotherhood) {
		this.brotherhoodRepository.delete(brotherhood);
	}

	// Other Methods--------------------------------------------
	public Brotherhood findByUserAccountId(int userAccountId) {
		return brotherhoodRepository.findByUserAccountId(userAccountId);
	}
}
