package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RequestRepository;
import domain.Request;

@Service
@Transactional
public class RequestService {

	// Repository-----------------------------------------------

	@Autowired
	private RequestRepository requestRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public RequestService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Request create() {
		final Request res = new Request();
		return res;
	}

	public List<Request> findAll() {
		return this.requestRepository.findAll();
	}

	public Request findOne(final Integer requestId) {
		return this.requestRepository.findOne(requestId);
	}

	public Request save(final Request request) {
		Assert.notNull(request);
		final Request saved = this.requestRepository.save(request);
		return saved;
	}

	public void delete(final Request request) {
		this.requestRepository.delete(request);
	}

	// Other Methods--------------------------------------------

}
