package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EnrolmentRepository;
import domain.Enrolment;

@Service
@Transactional
public class EnrolmentService {

	// Repository-----------------------------------------------

	@Autowired
	private EnrolmentRepository enrolmentRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public EnrolmentService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Enrolment create() {
		final Enrolment res = new Enrolment();
		return res;
	}

	public List<Enrolment> findAll() {
		return this.enrolmentRepository.findAll();
	}

	public Enrolment findOne(final Integer enrolmentId) {
		return this.enrolmentRepository.findOne(enrolmentId);
	}

	public Enrolment save(final Enrolment enrolment) {
		Assert.notNull(enrolment);
		final Enrolment saved = this.enrolmentRepository.save(enrolment);
		return saved;
	}

	public void delete(final Enrolment enrolment) {
		this.enrolmentRepository.delete(enrolment);
	}

	// Other Methods--------------------------------------------

}
