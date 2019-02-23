package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.BrotherhoodService;
import services.FloaatService;
import domain.Floaat;

@Controller
@RequestMapping("/float")
public class FloaatController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private FloaatService floaatService;

	@Autowired
	private BrotherhoodService brotherhoodService;

	// Constructor---------------------------------------------------------

	public FloaatController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int brotherhoodId,
			final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			Assert.notNull(brotherhoodService.findOne(brotherhoodId));
			final Collection<Floaat> floaats= floaatService.findByBrotherhoodId(brotherhoodId);
			result = new ModelAndView("floaat/list");
			result.addObject("floaats", floaats);
			result.addObject("requestURI", "float/list.do?brotherhoodId="
					+ brotherhoodId);
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/brotherhood/list.do");
			if (brotherhoodService.findOne(brotherhoodId) == null)
				redirectAttrs.addFlashAttribute("message1",
						"floaat.error.unexist");
		}
		return result;
	}
}
