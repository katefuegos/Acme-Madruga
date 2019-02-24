package controllers.Brotherhood;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.BrotherhoodService;
import services.ProcessionService;
import services.RequestService;
import controllers.AbstractController;
import domain.Procession;
import domain.Request;

@Controller
@RequestMapping("/request/brotherhood")
public class RequestBrotherhoodController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private ProcessionService processionService;

	@Autowired
	private BrotherhoodService brotherhoodService;

	@Autowired
	private RequestService requestService;

	// Constructor---------------------------------------------------------

	public RequestBrotherhoodController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			int brotherhoodId = brotherhoodService.findByUserAccountId(
					LoginService.getPrincipal().getId()).getId();
			Assert.notNull(brotherhoodService.findOne(brotherhoodId));
			Collection<Procession> processions = processionService
					.findByBrotherhoodId(brotherhoodId);
			Collection<Request> requests = new ArrayList<Request>();
			if (!processions.isEmpty()) {
				for (Procession p : processions) {
					requests.addAll(requestService.findRequestByProcessionId(p
							.getId()));
				}
			}
			Collection<Request> requestsPending = new ArrayList<Request>();
			Collection<Request> requestsRejected = new ArrayList<Request>();
			Collection<Request> requestsApproved = new ArrayList<Request>();

			for (Request r : requests) {
				if (r.getStatus().equals("PENDING")) {
					requestsPending.add(r);
				} else if (r.getStatus().equals("APPROVED")) {
					requestsApproved.add(r);
				} else {
					requestsRejected.add(r);
				}
			}

			result = new ModelAndView("request/list");
			result.addObject("requestsApproved", requestsApproved);
			result.addObject("requestsRejected", requestsRejected);
			result.addObject("requestsPending", requestsPending);
			result.addObject("requestURI", "request/brotherhood/list.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	// ACCEPT
	@RequestMapping(value = "/accept", method = RequestMethod.GET)
	public ModelAndView accept(final int requestId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Request request = requestService.findOne(requestId);
		final int brotherhoodId = this.brotherhoodService.findByUserAccountId(
				LoginService.getPrincipal().getId()).getId();
		try {
			Assert.notNull(brotherhoodId);
			Assert.isTrue(request != null);
			Assert.isTrue(request.getProcession().getBrotherhood().getId() == brotherhoodId);
			Assert.isTrue(request.getStatus().equals("PENDING"));
			int roow = 1;
			int coluumn = 1;
			while (requestService.findRequestByPosition(roow, coluumn, request
					.getProcession().getId()) != null) {
				if (roow >= coluumn) {
					roow = roow + 1;
				} else {
					coluumn = coluumn + 1;
				}
			}
			request.setRoow(roow);
			request.setColuumn(coluumn);
			result = this.acceptModelAndView(request);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/request/brotherhood/list.do");
			if (request == null)
				redirectAttrs.addFlashAttribute("message1",
						"request.error.unexist");
			else if (!(request.getProcession().getBrotherhood().getId() == brotherhoodId))
				redirectAttrs.addFlashAttribute("message1",
						"request.error.nobrotherhood");
			else if (request.getStatus() != "PENDING")
				redirectAttrs.addFlashAttribute("message1",
						"request.error.statusNoPendingAccept");
			else
				result = this.acceptModelAndView(request, "commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/accept", method = RequestMethod.POST, params = "save")
	public ModelAndView acceptSave(@Valid final Request request,
			final BindingResult binding, final RedirectAttributes redirectAttrs) {
		ModelAndView result = null;
		final int brotherhoodId = this.brotherhoodService.findByUserAccountId(
				LoginService.getPrincipal().getId()).getId();
		if (binding.hasErrors())
			result = this.acceptModelAndView(request, "commit.error");
		else
			try {
				Assert.notNull(brotherhoodId);
				Assert.isTrue(request != null);
				Assert.isTrue(request.getProcession().getBrotherhood().getId() == brotherhoodId);

				request.setStatus("APPROVED");
				this.requestService.save(request);

				result = new ModelAndView(
						"redirect:/request/brotherhood/list.do");

			} catch (final Throwable oops) {

				result = new ModelAndView(
						"redirect:/request/brotherhood/list.do");
				if (request == null)
					redirectAttrs.addFlashAttribute("message1",
							"request.error.unexist");
				else if (!(request.getProcession().getBrotherhood().getId() == brotherhoodId))
					redirectAttrs.addFlashAttribute("message1",
							"request.error.nobrotherhood");
				else
					result = this.acceptModelAndView(request, "commit.error");
			}
		return result;
	}

	// DECLINE
	@RequestMapping(value = "/decline", method = RequestMethod.GET)
	public ModelAndView decline(final int requestId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Request request = requestService.findOne(requestId);
		final int brotherhoodId = this.brotherhoodService.findByUserAccountId(
				LoginService.getPrincipal().getId()).getId();
		try {
			Assert.notNull(brotherhoodId);
			Assert.isTrue(request != null);
			Assert.isTrue(request.getProcession().getBrotherhood().getId() == brotherhoodId);
			Assert.isTrue(request.getStatus().equals("PENDING"));

			result = this.declineModelAndView(request);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/request/brotherhood/list.do");
			if (request == null)
				redirectAttrs.addFlashAttribute("message1",
						"request.error.unexist");
			else if (!(request.getProcession().getBrotherhood().getId() == brotherhoodId))
				redirectAttrs.addFlashAttribute("message1",
						"request.error.nobrotherhood");
			else if (request.getStatus() != "PENDING")
				result = this.declineModelAndView(request,
						"request.error.reasonRejectNeed");
			else
				result = this.declineModelAndView(request, "commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/decline", method = RequestMethod.POST, params = "save")
	public ModelAndView declineSave(@Valid final Request request,
			final BindingResult binding, final RedirectAttributes redirectAttrs) {
		ModelAndView result = null;
		final int brotherhoodId = this.brotherhoodService.findByUserAccountId(
				LoginService.getPrincipal().getId()).getId();
		if (binding.hasErrors())
			result = this.acceptModelAndView(request, "commit.error");
		else
			try {
				Assert.notNull(brotherhoodId);
				Assert.isTrue(request != null);
				Assert.isTrue(request.getProcession().getBrotherhood().getId() == brotherhoodId);
				Assert.isTrue(!request.getReasonReject().equals(""));
				request.setStatus("REJECTED");
				this.requestService.save(request);

				result = new ModelAndView(
						"redirect:/request/brotherhood/list.do");

			} catch (final Throwable oops) {

				result = new ModelAndView(
						"redirect:/request/brotherhood/list.do");
				if (request == null)
					redirectAttrs.addFlashAttribute("message1",
							"request.error.unexist");
				else if (!(request.getProcession().getBrotherhood().getId() == brotherhoodId))
					redirectAttrs.addFlashAttribute("message1",
							"request.error.nobrotherhood");
				else if (request.getReasonReject().equals(""))
					result = this.declineModelAndView(request,
							"request.error.reasonRejectNeed");
				else
					result = this.declineModelAndView(request, "commit.error");
			}
		return result;
	}

	// METHODS
	protected ModelAndView acceptModelAndView(final Request request) {
		ModelAndView result;
		result = this.acceptModelAndView(request, null);
		return result;
	}

	protected ModelAndView acceptModelAndView(final Request request,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("request/accept");
		result.addObject("message1", message);
		result.addObject("requestURI",
				"request/brotherhood/accept.do?requestId=" + request.getId());
		result.addObject("request", request);
		return result;
	}

	protected ModelAndView declineModelAndView(final Request request) {
		ModelAndView result;
		result = this.declineModelAndView(request, null);
		return result;
	}

	protected ModelAndView declineModelAndView(final Request request,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("request/decline");
		result.addObject("message1", message);
		result.addObject("requestURI",
				"request/brotherhood/decline.do?requestId=" + request.getId());
		result.addObject("request", request);
		return result;
	}

}
