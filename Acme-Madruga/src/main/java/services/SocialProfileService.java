
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActorRepository;
import repositories.SocialProfileRepository;
import security.LoginService;
import security.UserAccount;
import domain.SocialProfile;

@Service
@Transactional
public class SocialProfileService {

	// Repository

	@Autowired
	private SocialProfileRepository	repository;
	@Autowired
	private ActorRepository			actorRepository;

	// Services

	@Autowired
	private ActorService			actorService;

	@Autowired
	private ServiceUtils			serviceUtils;


	//Constructor----------------------------------------------------------------------------

	public SocialProfileService() {
		super();
	}

	// Simple CRUD methods -------------------------------------------------------------------
	public SocialProfile create(final int actorId) {
		final SocialProfile profile = new SocialProfile();
		profile.setActor(this.actorRepository.findOne(actorId));
		return profile;
	}

	public Collection<SocialProfile> findAll() {
		Collection<SocialProfile> profiles;

		profiles = this.repository.findAll();
		Assert.notNull(profiles);

		return profiles;
	}
	public SocialProfile findOne(final int profileId) {
		SocialProfile profile;
		profile = this.repository.findOne(profileId);
		Assert.notNull(profileId);

		return profile;
	}

	public SocialProfile save(final SocialProfile profile) {
		Assert.notNull(profile);
		this.checkPrincipal(profile);
		SocialProfile result;

		result = this.repository.save(profile);

		return result;
	}
	public void delete(final SocialProfile profile) {

		Assert.notNull(profile);
		this.checkPrincipal(profile);
		this.repository.delete(profile);
	}
	//Other Methods-----------------------------------------------------------------
	public Boolean checkPrincipal(final SocialProfile profile) {
		final UserAccount u = profile.getActor().getUserAccount();
		Assert.isTrue(u.equals(LoginService.getPrincipal()), "este perfil no corresponde con este actor");
		return true;
	}
}
