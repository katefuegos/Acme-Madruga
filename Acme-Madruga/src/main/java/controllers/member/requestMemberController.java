package controllers.member;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.MemberService;
import services.ProcessionService;
import services.RequestService;
import controllers.AbstractController;
import domain.Member;
import domain.Procession;
import domain.Request;

@Controller
@RequestMapping("/request/member")
public class requestMemberController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private RequestService requestService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private ProcessionService processionService;

	// Constructor---------------------------------------------------------

	public requestMemberController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/listMember", method = RequestMethod.GET)
	public ModelAndView list(final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			int memberId = memberService.findByUserAccountId(
					LoginService.getPrincipal().getId()).getId();
			Assert.notNull(memberService.findOne(memberId));

			Collection<Request> requests = requestService
					.findRequestByMemberId(memberId);

			Collection<Request> requestsPending = new ArrayList<Request>();
			Collection<Request> requestsRejected = new ArrayList<Request>();
			Collection<Request> requestsApproved = new ArrayList<Request>();

			if (!requests.isEmpty()) {
				for (Request r : requests) {
					if (r.getStatus().equals("PENDING")) {
						requestsPending.add(r);
					} else if (r.getStatus().equals("APPROVED")) {
						requestsApproved.add(r);
					} else {
						requestsRejected.add(r);
					}
				}
			}

			Collection<Procession> processions = processionService.findAll();
			if (!requestsPending.isEmpty()) {
				for (Request r : requestsPending) {
					processions.remove(r.getProcession());
				}
			}
			if (!requestsRejected.isEmpty()) {
				for (Request r : requestsRejected) {
					processions.remove(r.getProcession());
				}
			}
			if (!requestsApproved.isEmpty()) {
				for (Request r : requestsApproved) {
					processions.remove(r.getProcession());
				}
			}

			result = new ModelAndView("request/listMember");
			result.addObject("requestsApproved", requestsApproved);
			result.addObject("requestsRejected", requestsRejected);
			result.addObject("requestsPending", requestsPending);
			result.addObject("processions", processions);
			result.addObject("requestURI", "request/member/listMember.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/request/member/listMember.do");
		}
		return result;
	}

	// REQUEST
	@RequestMapping(value = "/request", method = RequestMethod.GET)
	public ModelAndView request(final int processionId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Procession procession = processionService.findOne(processionId);
		Member member = null;
		Request request = requestService.create();
		try {
			member = this.memberService.findByUserAccountId(LoginService
					.getPrincipal().getId());
			Assert.notNull(member);
			Collection<Request> requests = requestService
					.findRequestByMemberId(member.getId());
			Collection<Procession> processions = new ArrayList<Procession>();
			if (!requests.isEmpty()) {
				for (Request r : requests) {
					processions.add(r.getProcession());
				}
			}
			Assert.isTrue(!processions.contains(procession));
			request.setProcession(procession);
			requestService.save(request);

			result = new ModelAndView("redirect:/request/member/listMember.do");

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/request/member/listMember.do");
			if (procession == null)
				redirectAttrs.addFlashAttribute("message1",
						"request.error.processionUnexists");
			else
				result = new ModelAndView("commit.error");
		}

		return result;
	}

	// REQUEST
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(final int requestId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Request request = requestService.findOne(requestId);
		Member member = null;
		try {
			Assert.notNull(request);
			member = this.memberService.findByUserAccountId(LoginService
					.getPrincipal().getId());
			Assert.notNull(member);
			Assert.isTrue(request.getMember().getId() == member.getId());
			Assert.isTrue(request.getStatus().equals("PENDING"));
			requestService.delete(request);

			result = new ModelAndView("redirect:/request/member/listMember.do");

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/request/member/listMember.do");
			if (request == null)
				redirectAttrs.addFlashAttribute("message1",
						"request.error.unexist");
			if (!request.getMember().equals(member)) {
				redirectAttrs.addFlashAttribute("message1",
						"request.error.nobrotherhood");
			} else
				result = new ModelAndView("commit.error");
		}

		return result;
	}
}