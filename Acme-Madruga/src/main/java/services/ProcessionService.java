package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ProcessionRepository;
import domain.Procession;

@Service
@Transactional
public class ProcessionService {

	// Repository-----------------------------------------------

	@Autowired
	private ProcessionRepository processionRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ProcessionService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Procession create() {
		final Procession res = new Procession();
		return res;
	}

	public List<Procession> findAll() {
		return this.processionRepository.findAll();
	}

	public Procession findOne(final Integer processionId) {
		return this.processionRepository.findOne(processionId);
	}

	public Procession save(final Procession procession) {
		Assert.notNull(procession);
		final Procession saved = this.processionRepository.save(procession);
		return saved;
	}

	public void delete(final Procession procession) {
		this.processionRepository.delete(procession);
	}

	// Other Methods--------------------------------------------

}
