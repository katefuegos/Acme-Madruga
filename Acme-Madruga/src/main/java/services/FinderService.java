package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FinderRepository;
import domain.Finder;

@Service
@Transactional
public class FinderService {

	// Repository-----------------------------------------------

	@Autowired
	private FinderRepository finderRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public FinderService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Finder create() {
		final Finder res = new Finder();
		return res;
	}

	public List<Finder> findAll() {
		return this.finderRepository.findAll();
	}

	public Finder findOne(final Integer finderId) {
		return this.finderRepository.findOne(finderId);
	}

	public Finder save(final Finder finder) {
		Assert.notNull(finder);
		final Finder saved = this.finderRepository.save(finder);
		return saved;
	}

	public void delete(final Finder finder) {
		this.finderRepository.delete(finder);
	}

	// Other Methods--------------------------------------------

}
