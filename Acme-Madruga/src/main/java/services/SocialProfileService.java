package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialProfileRepository;
import domain.SocialProfile;

@Service
@Transactional
public class SocialProfileService {

	// Repository-----------------------------------------------

	@Autowired
	private SocialProfileRepository socialProfileRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public SocialProfileService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public SocialProfile create() {
		final SocialProfile res = new SocialProfile();
		return res;
	}

	public List<SocialProfile> findAll() {
		return this.socialProfileRepository.findAll();
	}

	public SocialProfile findOne(final Integer socialProfileId) {
		return this.socialProfileRepository.findOne(socialProfileId);
	}

	public SocialProfile save(final SocialProfile socialProfile) {
		Assert.notNull(socialProfile);
		final SocialProfile saved = this.socialProfileRepository.save(socialProfile);
		return saved;
	}

	public void delete(final SocialProfile socialProfile) {
		this.socialProfileRepository.delete(socialProfile);
	}

	// Other Methods--------------------------------------------

}
