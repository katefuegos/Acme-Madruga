package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BoxRepository;
import domain.Box;

@Service
@Transactional
public class BoxService {

	// Repository-----------------------------------------------

	@Autowired
	private BoxRepository boxRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public BoxService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Box create() {
		final Box res = new Box();
		return res;
	}

	public List<Box> findAll() {
		return this.boxRepository.findAll();
	}

	public Box findOne(final Integer boxId) {
		return this.boxRepository.findOne(boxId);
	}

	public Box save(final Box box) {
		Assert.notNull(box);
		final Box saved = this.boxRepository.save(box);
		return saved;
	}

	public void delete(final Box box) {
		this.boxRepository.delete(box);
	}

	// Other Methods--------------------------------------------

	public Box findBoxByActorIdAndName(final int actorId, final String nameBox) {
		return this.boxRepository.findBoxByActorAndName(actorId, nameBox);
	}

	public Collection<Box> findSystemBoxesByActorId(final int actorId) {
		return this.boxRepository.findSystemBoxByActorId(actorId);
	}

	public Collection<Box> findBoxesByActorId(final int actorId) {
		return this.boxRepository.findBoxByActor(actorId);
	}

}