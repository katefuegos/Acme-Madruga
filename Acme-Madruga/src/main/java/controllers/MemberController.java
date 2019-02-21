package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.BrotherhoodService;
import services.EnrolmentService;
import services.MemberService;
import domain.Enrolment;
import domain.Member;

@Controller
@RequestMapping("/member")
public class MemberController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private MemberService memberService;

	@Autowired
	private BrotherhoodService brotherhoodService;

	@Autowired
	private EnrolmentService enrolmentService;

	// Constructor---------------------------------------------------------

	public MemberController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int brotherhoodId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			final Collection<Enrolment> enrolments = enrolmentService
					.findByBrotherhoodAndAccepted(brotherhoodId);
			final Collection<Member> members = new ArrayList<Member>();
			if (!enrolments.isEmpty()) {
				for (Enrolment e : enrolments) {
					members.add(memberService.findByEnrolment(e));
				}
			}

			result = new ModelAndView("member/list");
			result.addObject("members", members);
			result.addObject("requestURI", "members/list.do?brotherhoodId="
					+ brotherhoodId);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/brotherhood/list.do");
			if (brotherhoodService.findOne(brotherhoodId) == null)
				redirectAttrs.addFlashAttribute("message1",
						"application.error.unexist");
		}
		return result;
	}
}
