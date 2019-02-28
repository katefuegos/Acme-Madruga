
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Configuration;
import domain.Message;

@Service
@Transactional
public class ActorService {

	// Repository-----------------------------------------------

	@Autowired
	private ActorRepository			actorRepository;

	// Services-------------------------------------------------
	@Autowired
	private MessageService			messageService;

	@Autowired
	private ConfigurationService	configurationService;


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

	public void ban(final Actor actor) {
		actor.setIsBanned(true);
		actor.getUserAccount().setEnabled(false);
		this.save(actor);
	}

	public void unban(final Actor actor) {
		actor.setIsBanned(false);
		actor.setIsSpammer(false);
		actor.getUserAccount().setEnabled(true);
		this.save(actor);

	}

	public Collection<Actor> findActorsNegativePolarity() {

		return this.actorRepository.findActorsNegativePolarity();

	}

	public Collection<Actor> asignSpammers() {

		Collection<Actor> result = this.actorRepository.asignSpammersActors();

		final Collection<Actor> actors = new ArrayList<>();
		for (final Actor actor : result) {
			actor.setIsSpammer(true);
			actors.add(actor);
		}
		if (!actors.isEmpty())
			result = this.actorRepository.save(actors);
		else
			result = actors;

		return result;
	}
	public void updatePolarity() {
		final Collection<Actor> allActors = this.actorRepository.findAll();

		//final Collection<Actor> actors = new ArrayList<>();
		for (final Actor actor : allActors)
			actor.setPolarityScore(this.updateIndividualPoparity(actor));

		this.actorRepository.save(allActors);

	}

	protected Double updateIndividualPoparity(final Actor actor) {
		Double result = 0.0;

		final Collection<Message> sentMessages = this.messageService.findSentMessage(actor);
		if (sentMessages != null && !sentMessages.isEmpty()) {
			// Se obtiene todas las palabras de todos los mensajes de un actor
			final StringBuilder strBuilder = new StringBuilder();

			for (final Message message : sentMessages) {
				strBuilder.append(" ");
				strBuilder.append(message.getSubject());
				strBuilder.append(" ");
				strBuilder.append(message.getBody());
				strBuilder.append(" ");
				strBuilder.append(message.getTags());
				strBuilder.append(" ");
			}

			String comments = strBuilder.toString();

			final Configuration configuration = this.configurationService.findOne();

			final Collection<String> positiveWords = new LinkedList<>();
			for (final String string : configuration.getPositiveWords().keySet())
				positiveWords.addAll(configuration.getPositiveWords().get(string));

			final Collection<String> negativeWords = new LinkedList<>();
			for (final String string : configuration.getNegativeWords().keySet())
				negativeWords.addAll(configuration.getNegativeWords().get(string));

			comments = comments.toUpperCase();

			double p = 0.0;
			for (final String positive : positiveWords)
				if (comments.contains(" " + positive.toUpperCase() + " "))
					p++;

			double n = 0.0;
			for (final String negative : negativeWords)
				if (comments.contains(" " + negative.toUpperCase() + " "))
					n++;
			final double total = p + n;
			if (total != 0.0)
				result = (p - n) / total;
		}
		return result;
	}

}
