package services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MemberRepository;
import domain.Member;

@Service
@Transactional
public class MemberService {

	// Repository-----------------------------------------------

	@Autowired
	private MemberRepository memberRepository;

	// Services-------------------------------------------------

	// Constructor----------------------------------------------

	public MemberService() {
		super();
	}

	// Simple CRUD----------------------------------------------

	public Member create() {
		final Member res = new Member();
		return res;
	}

	public List<Member> findAll() {
		return this.memberRepository.findAll();
	}

	public Member findOne(final Integer memberId) {
		return this.memberRepository.findOne(memberId);
	}

	public Member save(final Member member) {
		Assert.notNull(member);
		final Member saved = this.memberRepository.save(member);
		return saved;
	}

	public void delete(final Member member) {
		this.memberRepository.delete(member);
	}

	// Other Methods--------------------------------------------

}
