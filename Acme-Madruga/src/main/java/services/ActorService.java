
package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;

@Service
@Transactional
public class ActorService {

	// Repository-----------------------------------------------

	@Autowired
	private ActorRepository	actorRepository;


	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public ActorService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Actor create() {
		final Actor res = new Actor();
		return res;
	}

	public List<Actor> findAll() {
		return this.actorRepository.findAll();
	}

	public Actor findOne(final Integer actorId) {
		return this.actorRepository.findOne(actorId);
	}

	public Actor save(final Actor actor) {
		Assert.notNull(actor);
		final Actor saved = this.actorRepository.save(actor);
		return saved;
	}

	public void delete(final Actor actor) {
		this.actorRepository.delete(actor);
	}

	// Other Methods--------------------------------------------

	public Actor findActorByUsername(final String username) {
		final Actor actor = this.actorRepository.findActorByUsername(username);
		return actor;
	}

	public Actor findByUserAccount(final UserAccount userAccount) {
		return this.actorRepository.findByUserAccount(userAccount.getId());
	}
	public Actor findPrincipal() {
		final UserAccount userAccount = LoginService.getPrincipal();
		return this.actorRepository.findByUserAccount(userAccount.getId());
	}
	public Collection<Actor> findSpammersActors() {
		return this.actorRepository.findSpammersActors();
	}
}
