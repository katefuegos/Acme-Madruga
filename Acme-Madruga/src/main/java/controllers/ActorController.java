/*
 * ProfileController.java
 * 
 * Copyright (C) 2018 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers;

import java.util.Date;

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

import security.LoginService;
import services.ActorService;
import services.BrotherhoodService;
import services.MemberService;
import domain.Actor;
import domain.Brotherhood;

@Controller
@RequestMapping("/actor")
public class ActorController extends AbstractController {

	@Autowired
	private ActorService		actorService;

	@Autowired
	private MemberService		memberService;

	@Autowired
	private BrotherhoodService	brotherhoodService;


	// Edit ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;

		final Actor a = this.actorService.findByUserAccount(LoginService.getPrincipal());
		Assert.notNull(a);

		result = this.createEditModelAndView(a);

		return result;
	}

	// Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Actor actor, final BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(actor);
		else
			try {
				this.actorService.update(actor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(actor, "actor.commit.error");

			}
		return result;
	}

	// CreateModelAndView

	protected ModelAndView createEditModelAndView(final Actor actor) {
		ModelAndView result;

		result = this.createEditModelAndView(actor, null);

		return result;

	}

	protected ModelAndView createEditModelAndView(final Actor actor, final String message) {
		ModelAndView result;

		result = new ModelAndView("actor/edit");
		result.addObject("actor", actor);
		result.addObject("message1", message);
		result.addObject("isRead", false);
		result.addObject("requestURI", "actor/edit.do");

		return result;
	}

	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public ModelAndView show(@RequestParam final int actorId, final RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = new ModelAndView("actor/edit");

		final Actor actor = this.actorService.findOne(actorId);
		final Brotherhood brother = this.brotherhoodService.findOne(actorId);

		try {
			Assert.notNull(actor);
			String title = null;
			Date estDate = null;
			if (brother != null) {
				title = brother.getTitle();
				estDate = brother.getEstablishementDate();
			}
			modelAndView.addObject("actor", actor);
			modelAndView.addObject("isRead", true);
			modelAndView.addObject("establishementDate", estDate);
			modelAndView.addObject("title", title);
			modelAndView.addObject("requestURI", "/actor/administrator/show.do?actorId=" + actorId);
		} catch (final Throwable e) {
			modelAndView = new ModelAndView("redirect:list.do");

			if (actor == null)
				redirectAttrs.addFlashAttribute("message1", "actor.error.unexist");
		}
		return modelAndView;

	}
}
