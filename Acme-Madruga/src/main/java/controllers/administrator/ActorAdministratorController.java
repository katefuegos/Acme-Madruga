
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import services.ActorService;
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("/actor/administrator")
public class ActorAdministratorController extends AbstractController {

	// Service---------------------------------------------------------

	@Autowired
	private ActorService	actorService;


	// Constructor-----------------------------------------------------

	public ActorAdministratorController() {
		super();
	}

	// List------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView modelAndView;

		final Collection<Actor> actors = this.actorService.findSpammersActors();

		modelAndView = new ModelAndView("actor/list");
		modelAndView.addObject("actors", actors);
		modelAndView.addObject("requestURI", "/actor/administrator/list.do");

		return modelAndView;

	}

	// Ban-------------------------------------------------------------

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam final int actorId, final RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = new ModelAndView();

		final Actor actor = this.actorService.findOne(actorId);
		try {
			Assert.notNull(actor);
			Assert.isTrue((actor.getIsBanned() == false) && (actor.getIsSpammer() == true));
			this.actorService.ban(actor);
			modelAndView = new ModelAndView("redirect:list.do");
		} catch (final Exception e) {
			modelAndView = new ModelAndView("redirect:list.do");

			if (actor == null)
				redirectAttrs.addFlashAttribute("message", "actor.error.unexist");
			else if (!((actor.getIsBanned() == false) && (actor.getIsSpammer() == true)))
				redirectAttrs.addFlashAttribute("message", "actor.error.toBan");
		}

		return modelAndView;

	}

	// UnBan-------------------------------------------------------------

	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam final int actorId, final RedirectAttributes redirectAttrs) {
		ModelAndView modelAndView = null;

		final Actor actor = this.actorService.findOne(actorId);
		try {
			Assert.notNull(actor);
			Assert.isTrue((actor.getIsBanned() == true) && (actor.getIsSpammer() == true));
			this.actorService.unban(actor);
			modelAndView = new ModelAndView("redirect:list.do");

		} catch (final Exception e) {
			modelAndView = new ModelAndView("redirect:list.do");

			if (actor == null)
				redirectAttrs.addFlashAttribute("message", "actor.error.unexist");
			else if (!((actor.getIsBanned() == true) && (actor.getIsSpammer() == true)))
				redirectAttrs.addFlashAttribute("message", "actor.error.toUnban");
		}

		return modelAndView;

	}
}
