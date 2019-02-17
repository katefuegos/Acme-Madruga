package services;

import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Box;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Repository-----------------------------------------------

	@Autowired
	private MessageRepository messageRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public MessageService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Message create() {
		final Message res = new Message();
		return res;
	}

	public List<Message> findAll() {
		return this.messageRepository.findAll();
	}

	public Message findOne(final Integer messageId) {
		return this.messageRepository.findOne(messageId);
	}

	public Message save(final Message message) {
		Assert.notNull(message);
		final Message saved = this.messageRepository.save(message);
		return saved;
	}

	public void delete(final Message message) {
		this.messageRepository.delete(message);
	}

	// Other Methods--------------------------------------------

	public Collection<Message> findByBox(final Box box) {
		Assert.notNull(box, "findByBox - Box must not be null");

		final Collection<Message> result = this.messageRepository
				.findByBoxId(box.getId());

		return result;

	}

}
