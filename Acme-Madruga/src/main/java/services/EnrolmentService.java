package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.EnrolmentRepository;
import security.LoginService;
import domain.Enrolment;

@Service
@Transactional
public class EnrolmentService {

	// Repository-----------------------------------------------

	@Autowired
	private EnrolmentRepository enrolmentRepository;

	// Services-------------------------------------------------

	private BrotherhoodService brotherhoodService;

	// Constructor----------------------------------------------

	public EnrolmentService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Enrolment create() {
		final Enrolment res = new Enrolment();
		final Date momentEnrol = new Date();
		res.setMomentEnrol(momentEnrol);
		res.setPositionEN("");
		res.setPositionES("");
		res.setBrotherhood(this.brotherhoodService
				.findByUserAccountId(LoginService.getPrincipal().getId()));

		return res;
	}

	public List<Enrolment> findAll() {
		return this.enrolmentRepository.findAll();
	}

	public Enrolment findOne(final Integer enrolmentId) {
		Assert.notNull(enrolmentId);
		return this.enrolmentRepository.findOne(enrolmentId);
	}

	public Enrolment save(final Enrolment enrolment) {
		Assert.notNull(enrolment);
		Assert.isTrue(LoginService.getPrincipal().getAuthorities().toString()
				.contains("BROTHERHOOD"),
				"SOLO UN BROTHERHOOD PUEDE CREAR/EDITAR PROCESSION");
		if (enrolment.getId() == 0)
			enrolment
					.setMomentEnrol(new Date(System.currentTimeMillis() - 1000));
		final Enrolment saved = this.enrolmentRepository.save(enrolment);
		return saved;
	}

	public void delete(final Enrolment enrolment) {
		this.enrolmentRepository.delete(enrolment);
	}

	// Other Methods--------------------------------------------

	public Collection<Enrolment> findByBrotherhoodAndAccepted(
			final int brotherhoodId) {
		Assert.notNull(brotherhoodId);
		return this.enrolmentRepository
				.findByBrotherhoodAndAccepted(brotherhoodId);
	}

	public Collection<Enrolment> findByBrotherhood(int brotherhoodId) {
		Assert.notNull(brotherhoodId);
		return enrolmentRepository.findByBrotherhood(brotherhoodId);
	}

}
