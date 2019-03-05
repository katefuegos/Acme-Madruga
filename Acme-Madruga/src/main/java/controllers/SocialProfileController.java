package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.ActorService;
import services.SocialProfileService;
import domain.Actor;
import domain.SocialProfile;

@Controller
@RequestMapping("/socialProfile")
public class SocialProfileController extends AbstractController {

	@Autowired
	private SocialProfileService socialProfileService;

	@Autowired
	private ActorService actorService;

	// List------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView;

		final Actor a = this.actorService.findByUserAccount(LoginService
				.getPrincipal());
		final Collection<SocialProfile> socialProfiles = this.socialProfileService
				.findByActor(a.getId());

		modelAndView = new ModelAndView("socialProfile/list");
		modelAndView.addObject("socialProfiles", socialProfiles);
		modelAndView.addObject("requestURI", "/socialProfile/list.do");
		modelAndView.addObject("username", a.getUserAccount().getUsername());

		return modelAndView;

	}

	// Create
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		final SocialProfile socialProfile = this.socialProfileService.create();

		result = this.createModelAndView(socialProfile);

		return result;
	}

	// Show------------------------------------------------------------

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int socialProfileId) {
		final ModelAndView modelAndView = new ModelAndView("socialProfile/show");

		final SocialProfile socialProfile = this.socialProfileService
				.findOne(socialProfileId);

		modelAndView.addObject("socialProfile", socialProfile);
		modelAndView.addObject("isRead", true);
		modelAndView.addObject("requestURI",
				"/socialProfile/show.do?socialProfileId=" + socialProfileId);

		return modelAndView;

	}

	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int socialProfileId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;
		SocialProfile socialProfile;
		try {
			final Actor a = this.actorService.findByUserAccount(LoginService
					.getPrincipal());
			socialProfile = this.socialProfileService.findOne(socialProfileId);
			Assert.notNull(socialProfile);
			result = this.editModelAndView(socialProfile);
			result.addObject("actorId", a.getId());
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/socialProfile/list.do");
			if (this.socialProfileService.findOne(socialProfileId) == null)
				redirectAttrs.addFlashAttribute("message",
						"socialProfile.error.unexist");
			else if (this.socialProfileService.findOne(socialProfileId)
					.getActor() == null)
				redirectAttrs.addFlashAttribute("message",
						"socialProfile.error.notFromActor");

		}
		return result;
	}

	// Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final SocialProfile socialProfile,
			final BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors())
			result = this.editModelAndView(socialProfile);
		else
			try {
				// Llamar al reconstruct y rellenarlos con los atributos que
				// no se han modificado en la vista
				this.socialProfileService.reconstruct(socialProfile, binding);
				this.socialProfileService.save(socialProfile);
				result = new ModelAndView("redirect:/socialProfile/list.do");
			} catch (final Throwable oops) {
				result = this.editModelAndView(socialProfile,
						"socialProfile.commit.error");
			}
		return result;
	}

	// delete

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final SocialProfile socialProfile,
			final BindingResult binding) {
		ModelAndView result;
		try {
			this.socialProfileService.delete(socialProfile);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.editModelAndView(socialProfile,
					"socialProfile.commit.error");
		}
		return result;
	}

	// CreateModelAndView

	protected ModelAndView createModelAndView(final SocialProfile socialProfile) {
		ModelAndView result;

		result = this.createModelAndView(socialProfile, null);

		return result;

	}

	protected ModelAndView createModelAndView(final SocialProfile socialProfile,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("socialProfile/create");
		result.addObject("socialProfile", socialProfile);
		result.addObject("message", message);
		result.addObject("isRead", false);

		result.addObject("requestURI", "socialProfile/create.do");

		return result;
	}

	protected ModelAndView editModelAndView(final SocialProfile socialProfile) {
		ModelAndView result;

		result = this.editModelAndView(socialProfile, null);

		return result;

	}

	protected ModelAndView editModelAndView(final SocialProfile socialProfile,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("socialProfile/edit");
		result.addObject("socialProfile", socialProfile);
		result.addObject("message", message);
		result.addObject("isRead", false);

		result.addObject("requestURI", "socialProfile/edit.do?socialProfileId="
				+ socialProfile.getId());

		return result;
	}

	protected ModelAndView showModelAndView(final SocialProfile socialProfile) {
		ModelAndView result;

		result = this.showModelAndView(socialProfile, null);

		return result;

	}

	protected ModelAndView showModelAndView(final SocialProfile socialProfile,
			final String message) {
		final ModelAndView result;

		result = new ModelAndView("socialProfile/display");
		result.addObject("socialProfile", socialProfile);
		result.addObject("message", message);
		result.addObject("isRead", true);

		result.addObject("requestURI", "socialProfile/show.do?socialProfileId="
				+ socialProfile.getId());

		return result;
	}

}
