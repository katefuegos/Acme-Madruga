
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import security.UserAccount;
import services.BrotherhoodService;
import domain.Brotherhood;

@Controller
@RequestMapping("/brotherhood")
public class BrotherhoodController extends AbstractController {

	// Services-----------------------------------------------------------

	@Autowired
	private BrotherhoodService	brotherhoodService;


	// Constructor---------------------------------------------------------

	public BrotherhoodController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		final Collection<Brotherhood> brotherhoods = this.brotherhoodService.findAll();

		result = new ModelAndView("brotherhood/list");
		result.addObject("brotherhoods", brotherhoods);
		result.addObject("requestURI", "brotherhood/list.do");
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(final RedirectAttributes redirectAttrs) {
		final UserAccount uA = LoginService.getPrincipal();
		ModelAndView modelAndView;
		final Brotherhood brotherhood = this.brotherhoodService.findByUserAccountId(uA.getId());
		try {

			modelAndView = this.createEditModelAndView(brotherhood);
		} catch (final Exception e) {

			modelAndView = new ModelAndView("redirect:/index.do");
		}

		return modelAndView;
	}

	// Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Brotherhood brotherhood, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(brotherhood);
		else
			try {
				this.brotherhoodService.save(brotherhood);
				result = new ModelAndView("redirect:/brotherhood/actor/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(brotherhood, "brotherhood.commit.error");
			}
		return result;
	}
	// CreateModelAndView

	protected ModelAndView createEditModelAndView(final Brotherhood brotherhood) {
		ModelAndView result;

		result = this.createEditModelAndView(brotherhood, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Brotherhood brotherhood, final String message) {
		ModelAndView result;

		result = new ModelAndView("brotherhood/actor/edit");
		result.addObject("brotherhood", brotherhood);
		result.addObject("message", message);
		return result;
	}
}
