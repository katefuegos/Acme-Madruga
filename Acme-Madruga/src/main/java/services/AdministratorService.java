
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import domain.Actor;
import domain.Administrator;
import domain.Brotherhood;
import domain.Member;

@Service
@Transactional
public class AdministratorService {

	//Repository-----------------------------------------------

	@Autowired
	private AdministratorRepository	administratorRepository;

	//Services-------------------------------------------------
	@Autowired
	private BrotherhoodService		brotherhoodService;

	@Autowired
	private MemberService			memberService;


	//Constructor----------------------------------------------

	public AdministratorService() {

		super();
	}

	//Simple CRUD----------------------------------------------

	public Administrator create() {
		final Administrator administrator = new Administrator();
		final UserAccount userAccount = new UserAccount();
		final Collection<Authority> authorities = new ArrayList<Authority>();

		final Authority a = new Authority();
		a.setAuthority("ADMIN");
		authorities.add(a);
		userAccount.setAuthorities(authorities);
		userAccount.setEnabled(true);
		administrator.setUserAccount(userAccount);

		administrator.setIsBanned(false);
		administrator.setIsSpammer(false);
		return administrator;
	}
	public List<Administrator> findAll() {
		return this.administratorRepository.findAll();
	}

	public Administrator findOne(final Integer administratorId) {
		return this.administratorRepository.findOne(administratorId);
	}

	public Administrator save(final Administrator administrator) {
		Assert.notNull(administrator);
		final Administrator saved = this.administratorRepository.save(administrator);
		return saved;
	}
	public void delete(final Administrator entity) {
		this.administratorRepository.delete(entity);
	}

	//Other Methods--------------------------------------------

	public Actor isSpamer(final Actor actor) {
		Actor result = null;
		final UserAccount userAccount = actor.getUserAccount();
		final Authority authority = userAccount.getAuthorities().iterator().next();

		switch (authority.getAuthority()) {
		case "ADMIN":
			final Administrator administrator = this.findByUseraccount(userAccount);
			administrator.setIsSpammer(true);
			result = this.save(administrator);
			break;
		case "MEMBER":
			final Member member = this.memberService.findByUserAccountId(userAccount.getId());
			member.setIsSpammer(true);
			result = this.memberService.save(member);
			break;
		case "BROTHERHOOD":
			final Brotherhood brotherhood = this.brotherhoodService.findByUserAccountId(userAccount.getId());
			brotherhood.setIsSpammer(true);
			result = this.brotherhoodService.save(brotherhood);
			break;

		}

		return result;
	}

	public Administrator findByUseraccount(final UserAccount userAccount) {
		return this.administratorRepository.findAdministratorByUserAccount(userAccount.getId());

	}

	public Administrator findAdminByUsername(final String username) {
		return this.administratorRepository.findAdminByUsername(username);
	}

	public Collection<Object[]> queryC2() {
		Collection<Object[]> result = null;

		result = this.administratorRepository.queryC2();

		return result;
	}

}
