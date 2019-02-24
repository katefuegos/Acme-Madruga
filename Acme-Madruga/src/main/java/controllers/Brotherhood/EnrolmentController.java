package controllers.Brotherhood;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.BrotherhoodService;
import services.ConfigurationService;
import services.EnrolmentService;
import services.MemberService;
import controllers.AbstractController;
import domain.Enrolment;
import forms.EnrolmentForm;

@Controller
@RequestMapping("/enrolment/brotherhood")
public class EnrolmentController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private EnrolmentService enrolmentService;

	@Autowired
	private BrotherhoodService brotherhoodService;

	@Autowired
	private MemberService memberService;

	@Autowired
	private ConfigurationService configurationService;

	// Constructor---------------------------------------------------------

	public EnrolmentController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			Integer brotherhoodId = brotherhoodService.findByUserAccountId(
					LoginService.getPrincipal().getId()).getId();
			Assert.notNull(brotherhoodService.findOne(brotherhoodId));

			final Collection<Enrolment> enrolments = enrolmentService
					.findByBrotherhood(brotherhoodId);

			Collection<EnrolmentForm> enrolmentForms = new ArrayList<EnrolmentForm>();
			for (Enrolment e : enrolments) {
				enrolmentForms.add(new EnrolmentForm(e, memberService
						.findByEnrolment(e)));
			}

			String lang = LocaleContextHolder.getLocale().getLanguage()
					.toUpperCase();
			result = new ModelAndView("enrolment/list");
			result.addObject("lang", lang);
			result.addObject("enrolmentForms", enrolmentForms);
			result.addObject("requestURI",
					"enrolment/brotherhood/list.do?brotherhoodId="
							+ brotherhoodId);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	// ENROL
	@RequestMapping(value = "/enrol", method = RequestMethod.GET)
	public ModelAndView enrol(final int enrolmentId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Integer brotherhoodId = brotherhoodService.findByUserAccountId(
				LoginService.getPrincipal().getId()).getId();
		Enrolment enrolment = enrolmentService.findOne(enrolmentId);
		try {
			Assert.notNull(brotherhoodService.findOne(brotherhoodId));
			Assert.notNull(enrolment);
			Assert.isTrue(enrolment.getBrotherhood().equals(
					brotherhoodService.findOne(brotherhoodId)));
			Assert.isTrue(enrolment.isAccepted() == false);

			result = this.enrolModelAndView(enrolment);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/enrolment/brotherhood/list.do");
			if (enrolment == null)
				redirectAttrs.addFlashAttribute("message1",
						"enrolment.error.unexist");
			else if (!enrolment.getBrotherhood().equals(
					brotherhoodService.findOne(brotherhoodId)))
				redirectAttrs.addFlashAttribute("message1",
						"enrolment.error.noBrotherhood");
			else if (enrolment.isAccepted() == true)
				redirectAttrs.addFlashAttribute("message1",
						"enrolment.error.isAccepted");
			else
				result = this.enrolModelAndView(enrolment, "commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/enrol", method = RequestMethod.POST, params = "save")
	public ModelAndView enrolSave(@Valid final Enrolment enrolment,
			final BindingResult binding, final RedirectAttributes redirectAttrs) {
		ModelAndView result = null;
		Integer brotherhoodId = brotherhoodService.findByUserAccountId(
				LoginService.getPrincipal().getId()).getId();
		if (binding.hasErrors())
			result = this.enrolModelAndView(enrolment, "commit.error");
		else
			try {
				Assert.notNull(brotherhoodService.findOne(brotherhoodId));
				Assert.notNull(enrolment);
				Assert.isTrue(enrolment.getBrotherhood().equals(
						brotherhoodService.findOne(brotherhoodId)));

				enrolment.setAccepted(true);
				enrolment.setMomentEnrol(new Date(
						System.currentTimeMillis() - 1000));
				this.enrolmentService.save(enrolment);

				result = new ModelAndView(
						"redirect:/enrolment/brotherhood/list.do");

			} catch (final Throwable oops) {

				result = new ModelAndView(
						"redirect:/enrolment/brotherhood/list.do");
				if (enrolment == null)
					redirectAttrs.addFlashAttribute("message1",
							"enrolment.error.unexist");
				else if (!enrolment.getBrotherhood().equals(
						brotherhoodService.findOne(brotherhoodId)))
					redirectAttrs.addFlashAttribute("message1",
							"enrolment.error.noBrotherhood");
				else
					result = this.enrolModelAndView(enrolment, "commit.error");
			}
		return result;
	}

	// DROPOUT
	@RequestMapping(value = "/dropout", method = RequestMethod.GET)
	public ModelAndView dropout(final int enrolmentId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Integer brotherhoodId = brotherhoodService.findByUserAccountId(
				LoginService.getPrincipal().getId()).getId();
		Enrolment enrolment = enrolmentService.findOne(enrolmentId);
		try {
			Assert.notNull(brotherhoodService.findOne(brotherhoodId));
			Assert.notNull(enrolment);
			Assert.isTrue(enrolment.getBrotherhood().equals(
					brotherhoodService.findOne(brotherhoodId)));
			Assert.isTrue(enrolment.isAccepted() == true);

			result = this.dropOutModelAndView(enrolment);

		} catch (final Throwable e) {

			result = new ModelAndView("redirect:/enrolment/brotherhood/list.do");
			if (enrolment == null)
				redirectAttrs.addFlashAttribute("message1",
						"enrolment.error.unexist");
			else if (!enrolment.getBrotherhood().equals(
					brotherhoodService.findOne(brotherhoodId)))
				redirectAttrs.addFlashAttribute("message1",
						"enrolment.error.noBrotherhood");
			else if (enrolment.isAccepted() == false)
				redirectAttrs.addFlashAttribute("message1",
						"enrolment.error.isNotAccepted");
			else
				result = this.dropOutModelAndView(enrolment, "commit.error");
		}

		return result;
	}

	@RequestMapping(value = "/dropout", method = RequestMethod.POST, params = "save")
	public ModelAndView dropOutSave(@Valid final Enrolment enrolment,
			final BindingResult binding, final RedirectAttributes redirectAttrs) {
		ModelAndView result = null;
		Integer brotherhoodId = brotherhoodService.findByUserAccountId(
				LoginService.getPrincipal().getId()).getId();
		if (binding.hasErrors())
			result = this.dropOutModelAndView(enrolment, "commit.error");
		else
			try {
				Assert.notNull(brotherhoodService.findOne(brotherhoodId));
				Assert.notNull(enrolment);
				Assert.isTrue(enrolment.getBrotherhood().equals(
						brotherhoodService.findOne(brotherhoodId)));

				enrolment.setAccepted(false);
				enrolment.setMomentDropOut(new Date(
						System.currentTimeMillis() - 1000));
				this.enrolmentService.save(enrolment);

				result = new ModelAndView(
						"redirect:/enrolment/brotherhood/list.do");

			} catch (final Throwable oops) {

				result = new ModelAndView(
						"redirect:/enrolment/brotherhood/list.do");
				if (enrolment == null)
					redirectAttrs.addFlashAttribute("message1",
							"enrolment.error.unexist");
				else if (!enrolment.getBrotherhood().equals(
						brotherhoodService.findOne(brotherhoodId)))
					redirectAttrs.addFlashAttribute("message1",
							"enrolment.error.noBrotherhood");
				else
					result = this
							.dropOutModelAndView(enrolment, "commit.error");
			}
		return result;
	}

	protected ModelAndView enrolModelAndView(final Enrolment enrolment) {
		ModelAndView result;
		result = this.enrolModelAndView(enrolment, null);
		return result;
	}

	protected ModelAndView enrolModelAndView(final Enrolment enrolment,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("enrolment/enrol");
		result.addObject("message1", message);
		result.addObject(
				"requestURI",
				"enrolment/brotherhood/enrol.do?enrolmentId="
						+ enrolment.getId());
		result.addObject("enrolment", enrolment);
		return result;
	}

	protected ModelAndView dropOutModelAndView(final Enrolment enrolment) {
		ModelAndView result;
		result = this.dropOutModelAndView(enrolment, null);
		return result;
	}

	protected ModelAndView dropOutModelAndView(final Enrolment enrolment,
			final String message) {
		final ModelAndView result;

		Collection<String> positionsES = new ArrayList<String>();
		positionsES.add("HOLA");
		positionsES.add("ADIOS");
		positionsES.add("PORFAVOR");
		Collection<String> positionsEN = new ArrayList<String>();
		positionsEN.add("HELLO");
		positionsEN.add("GOODBYE");
		positionsEN.add("PLEASE");

		result = new ModelAndView("enrolment/dropout");
		result.addObject("message1", message);
		result.addObject(
				"requestURI",
				"enrolment/brotherhood/dropout.do?enrolmentId="
						+ enrolment.getId());
		result.addObject("enrolment", enrolment);
		result.addObject("positionsEN", positionsEN);
		result.addObject("positionsES", positionsES);
		return result;
	}
}