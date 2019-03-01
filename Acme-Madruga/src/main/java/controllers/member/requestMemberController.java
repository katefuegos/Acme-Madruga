package controllers.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.MemberService;
import services.RequestService;
import controllers.AbstractController;

@Controller
@RequestMapping("/procession/member")
public class requestMemberController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private RequestService requestService;

	@Autowired
	private MemberService memberService;

	// Constructor---------------------------------------------------------

	public requestMemberController() {
		super();
	}

	// List ---------------------------------------------------------------
}
