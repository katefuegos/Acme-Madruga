
package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.BrotherhoodService;
import services.FloaatService;
import domain.Floaat;

@Controller
@RequestMapping("/area")
public class AreaController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private FloaatService		areaService;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	// Constructor---------------------------------------------------------

	public AreaController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(final int brotherhoodId, final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		try {
			Assert.notNull(this.brotherhoodService.findOne(brotherhoodId));
			final Collection<Floaat> areas = this.areaService.findByBrotherhoodId(brotherhoodId);
			result = new ModelAndView("area/list");
			result.addObject("areas", areas);
			result.addObject("requestURI", "area/list.do");
		} catch (final Throwable e) {
			result = new ModelAndView("redirect:/area/list.do");
		}
		return result;
	}
	//Edit

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int areaId) {
		ModelAndView result;
		Floaat area;

		area = this.areaService.findOne(areaId);
		Assert.notNull(area);
		result = this.createEditModelAndView(area);

		return result;
	}
	//Save

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Floaat area, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(area);
		else
			try {
				this.areaService.save(area);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(area, "area.commit.error");
			}
		return result;
	}

	//delete

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Floaat area, final BindingResult binding) {
		ModelAndView result;
		try {
			this.areaService.delete(area);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(area, "area.commit.error");
		}
		return result;
	}

	protected ModelAndView createEditModelAndView(final Floaat area) {
		ModelAndView result;

		result = this.createEditModelAndView(area, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Floaat area, final String messageCode) {
		final ModelAndView result;

		result = new ModelAndView("area/edit");
		result.addObject("area", area);

		result.addObject("message", messageCode);

		return result;
	}

}
