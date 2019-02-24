package controllers.Brotherhood;

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
import controllers.AbstractController;
import domain.Brotherhood;
import domain.Procession;

@Controller
@RequestMapping("/procession/brotherhood")
public class ProcessionBrotherhoodController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private ProcessionService processionService;

	@Autowired
	private BrotherhoodService brotherhoodService;

	// Constructor---------------------------------------------------------

	public ProcessionBrotherhoodController() {
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
			final Collection<Procession> processions = processionService
					.findByBrotherhoodId(brotherhoodId);
			result = new ModelAndView("procession/list2");
			result.addObject("processions", processions);
			result.addObject("requestURI",
					"procession/brotherhood/list.do?brotherhoodId="
							+ brotherhoodId);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/");
		}
		return result;
	}

	// CREATE
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Procession procession = this.processionService.create();

		result = this.createModelAndView(procession);

		return result;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Procession procession,
			final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(procession, "commit.error");
		else
			try {
				this.processionService.save(procession);

				result = new ModelAndView(
						"redirect:/procession/brotherhood/list.do");
			} catch (final Throwable oops) {
				result = this.createModelAndView(procession, "commit.error");
			}
		return result;
	}

	// EDIT
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final int processionId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Procession procession = null;
		final Brotherhood b = this.brotherhoodService
				.findByUserAccountId(LoginService.getPrincipal().getId());
		try {
			procession = this.processionService.findOne(processionId);
			Assert.notNull(procession);
			Assert.isTrue(processionService.findOne(processionId)
					.getBrotherhood().equals(b));
			Assert.isTrue(processionService.findOne(processionId).isDraftMode());
			result = this.editModelAndView(procession);

		} catch (final Throwable e) {

			result = new ModelAndView(
					"redirect:/procession/brotherhood/list.do");
			if (processionService.findOne(processionId) == null)
				redirectAttrs.addFlashAttribute("message1",
						"procession.error.unexist");
			else if (processionService.findOne(processionId).isDraftMode() == false)
				redirectAttrs.addFlashAttribute("message1",
						"procession.error.notDraftMode");
			else if (!processionService.findOne(processionId).getBrotherhood()
					.equals(b))
				redirectAttrs.addFlashAttribute("message1",
						"procession.error.notFromActor");
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid final Procession procession,
			final BindingResult binding) {
		ModelAndView result;
		final Brotherhood b = this.brotherhoodService
				.findByUserAccountId(LoginService.getPrincipal().getId());
		if (binding.hasErrors())
			result = this.editModelAndView(procession, "commit.error");
		else
			try {
				Assert.notNull(procession);
				Assert.isTrue(procession.getBrotherhood().equals(b));
				Assert.isTrue(procession.isDraftMode());
				this.processionService.save(procession);

				result = new ModelAndView(
						"redirect:/procession/brotherhood/list.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(procession, "commit.error");
			}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid final Procession procession,
			final BindingResult binding) {
		ModelAndView result;
		final Brotherhood b = this.brotherhoodService
				.findByUserAccountId(LoginService.getPrincipal().getId());
		if (binding.hasErrors())
			result = this.editModelAndView(procession, "commit.error");
		else
			try {
				Assert.notNull(procession);
				Assert.isTrue(procession.getBrotherhood().equals(b));
				Assert.isTrue(procession.isDraftMode());
				this.processionService.delete(procession);

				result = new ModelAndView(
						"redirect:/procession/brotherhood/list.do");
			} catch (final Throwable oops) {

				result = this.editModelAndView(procession, "commit.error");
			}
		return result;
	}

	// SHOW
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(final int processionId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		Procession procession = null;
		final Brotherhood b = this.brotherhoodService
				.findByUserAccountId(LoginService.getPrincipal().getId());
		try {
			procession = this.processionService.findOne(processionId);
			Assert.notNull(procession);
			Assert.isTrue(procession.getBrotherhood().equals(b));
			result = this.ShowModelAndView(procession);

		} catch (final Throwable e) {

			result = new ModelAndView(
					"redirect:/procession/brotherhood/list.do");
			if (processionService.findOne(processionId) == null)
				redirectAttrs.addFlashAttribute("message1",
						"procession.error.unexist");
			else if (processionService.findOne(processionId).isDraftMode() == false)
				redirectAttrs.addFlashAttribute("message1",
						"procession.error.notDraftMode");
			else if (!processionService.findOne(processionId).getBrotherhood()
					.equals(b))
				redirectAttrs.addFlashAttribute("message1",
						"procession.error.notFromActor");
		}
		return result;
	}

	// MODEL
	protected ModelAndView createModelAndView(final Procession procession) {
		ModelAndView result;
		result = this.createModelAndView(procession, null);
		return result;
	}

	protected ModelAndView createModelAndView(final Procession procession,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("procession/create");
		result.addObject("message1", message);
		result.addObject("requestURI", "procession/brotherhood/create.do");
		result.addObject("procession", procession);
		result.addObject("isRead", false);

		return result;
	}

	protected ModelAndView editModelAndView(final Procession procession) {
		ModelAndView result;
		result = this.editModelAndView(procession, null);
		return result;
	}

	protected ModelAndView editModelAndView(final Procession procession,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("procession/edit");
		result.addObject("message1", message);
		result.addObject(
				"requestURI",
				"procession/brotherhood/edit.do?processionId="
						+ procession.getId());
		result.addObject("procession", procession);
		result.addObject("isRead", false);

		return result;
	}

	protected ModelAndView ShowModelAndView(final Procession procession) {
		ModelAndView result;
		result = this.ShowModelAndView(procession, null);
		return result;
	}

	protected ModelAndView ShowModelAndView(Procession procession,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("procession/show");
		result.addObject("message1", message);
		result.addObject(
				"requestURI",
				"procession/brotherhood/show.do?processionId="
						+ procession.getId());
		result.addObject("procession", procession);
		result.addObject("isRead", true);

		return result;
	}
}
