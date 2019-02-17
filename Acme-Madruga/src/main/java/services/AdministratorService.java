package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	// Repository-----------------------------------------------

	@Autowired
	private AdministratorRepository administratorRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Administrator create() {
		final Administrator res = new Administrator();
		return res;
	}

	public List<Administrator> findAll() {
		return this.administratorRepository.findAll();
	}

	public Administrator findOne(final Integer administratorId) {
		return this.administratorRepository.findOne(administratorId);
	}

	public Administrator save(final Administrator administrator) {
		Assert.notNull(administrator);
		final Administrator saved = this.administratorRepository
				.save(administrator);
		return saved;
	}

	public void delete(final Administrator administrator) {
		this.administratorRepository.delete(administrator);
	}

	// Other Methods--------------------------------------------

	public Administrator findByUseraccount(final UserAccount userAccount) {
		return this.administratorRepository
				.findAdministratorByUserAccount(userAccount.getId());

	}

	public Administrator findAdminByUsername(final String username) {
		return this.administratorRepository.findAdminByUsername(username);
	}
}
