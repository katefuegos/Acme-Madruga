
package controllers.member;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import security.LoginService;
import services.AreaService;
import services.FinderService;
import services.MemberService;
import services.ProcessionService;
import controllers.AbstractController;
import domain.Area;
import domain.Finder;
import domain.Member;
import domain.Procession;

@Controller
@RequestMapping("/finder/member")
public class FinderMemberController extends AbstractController {

	// Services-----------------------------------------------------------
	@Autowired
	private FinderService		finderService;

	@Autowired
	private MemberService		memberService;

	@Autowired
	private ProcessionService	processionService;

	@Autowired
	private AreaService			areaService;


	// Constructor---------------------------------------------------------

	public FinderMemberController() {
		super();
	}

	// List ---------------------------------------------------------------
	@RequestMapping(value = "/listProcessions", method = RequestMethod.GET)
	public ModelAndView list(final RedirectAttributes redirectAttrs) {
		ModelAndView result;

		Finder finder = this.finderService.findFinder();
		//Comprobar fecha ultima actualización
		finder = this.finderService.updateFinder(finder);
		//Obtener resultados fixuptasks de finder
		final Collection<Procession> processions = finder.getProcessions();
		final String lang = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
		final Member member = this.memberService.findByUserAccountId(LoginService.getPrincipal().getId());
		result = new ModelAndView("finder/member/listProcessions");
		result.addObject("processions", processions);
		result.addObject("lang", lang);
		result.addObject("memberId", member.getId());
		result.addObject("areaService", this.areaService);
		result.addObject("requestURI", "finder/member/listProcessions.do");
		return result;
	}

	// Update finder ---------------------------------------------------------------		

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateFinder() {
		ModelAndView result;

		final Finder finder = this.finderService.findFinder();
		Collection<Area> areas;

		areas = this.areaService.findAll();
		final String lang = LocaleContextHolder.getLocale().getLanguage().toUpperCase();
		final Collection<String> nameAreas = new ArrayList<>();
		for (final Area area : areas)
			nameAreas.add(area.getName().toUpperCase());

		result = new ModelAndView("finder/member/update");
		result.addObject("finder", finder);
		result.addObject("areas", nameAreas);
		result.addObject("lang", lang);

		return result;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ModelAndView updateFinder(@Valid final Finder finder, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			final Collection<Area> areas = this.areaService.findAll();

			result = new ModelAndView("finder/member/update");
			result.addObject("finder", finder);
			result.addObject("areas", areas);

		} else
			try {
				this.finderService.save(finder);
				result = new ModelAndView("redirect:listProcessions.do");
			} catch (final Exception e) {
				final Collection<Area> areas = this.areaService.findAll();
				System.out.println("====" + e.getMessage());
				result = new ModelAndView("finder/member/update");
				result.addObject("finder", finder);
				result.addObject("areas", areas);
				result.addObject("message1", "message.commit.error");
			}
		return result;
	}
}
