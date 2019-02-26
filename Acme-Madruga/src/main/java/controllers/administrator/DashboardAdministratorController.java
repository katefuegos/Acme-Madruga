
package controllers.administrator;

import java.text.DecimalFormat;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;
import domain.Procession;

@Controller
@RequestMapping("/dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	//Services-----------------------------------------------------------
	@Autowired
	private AdministratorService	administratorService;


	//Constructor-------------------------------------------------------

	public DashboardAdministratorController() {
		super();
	}

	//Dashboard---------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {
		final ModelAndView modelAndView = new ModelAndView("administrator/dashboard");
		final DecimalFormat df = new DecimalFormat("0.00");

		final String nulo = "n/a";

		//QueryC1
		final Object[] result = this.administratorService.queryC1();

		final Double avgC1 = (Double) result[0];
		final Double minC1 = (Double) result[1];
		final Double maxC1 = (Double) result[2];
		final Double stddevC1 = (Double) result[3];

		if (avgC1 != null)
			modelAndView.addObject("avgC1", df.format(avgC1));
		else
			modelAndView.addObject("avgC1", nulo);

		if (avgC1 != null)
			modelAndView.addObject("maxC1", df.format(maxC1));
		else
			modelAndView.addObject("maxC1", nulo);

		if (minC1 != null)
			modelAndView.addObject("minC1", df.format(minC1));
		else
			modelAndView.addObject("minC1", nulo);

		if (stddevC1 != null)
			modelAndView.addObject("stddevC1", df.format(stddevC1));
		else
			modelAndView.addObject("stddevC1", nulo);

		//QueryC2 - The largest brotherhood, minimum 1
		final Collection<Object[]> resultC2 = this.administratorService.queryC2();

		final Object[] largest = resultC2.iterator().next();

		final Integer idLargest = (Integer) largest[0];
		final String nameLargest = String.valueOf(largest[1]);
		final Long countLargest = (Long) largest[2];

		if (idLargest != null && nameLargest != null && countLargest != null) {
			modelAndView.addObject("idLargest", idLargest);
			modelAndView.addObject("nameLargest", nameLargest);
			modelAndView.addObject("countLargest", countLargest);
		}

		//QueryC3 - The smallest brotherhood, minimum 1
		final Collection<Object[]> resultC3 = this.administratorService.queryC3();

		final Object[] smallest = resultC3.iterator().next();

		final Integer idSmallest = (Integer) smallest[0];
		final String nameSmallest = String.valueOf(smallest[1]);
		final Long countSmallest = (Long) smallest[2];

		if (idSmallest != null && nameSmallest != null && countSmallest != null) {
			modelAndView.addObject("idSmallest", idSmallest);
			modelAndView.addObject("nameSmallest", nameSmallest);
			modelAndView.addObject("countSmallest", countSmallest);
		}

		//QueryC4 - The ratio of requests to march in a procession, grouped by their status.

		try {

			final Map<String, String> statusCount = new TreeMap<>();
			for (final Object[] resultC : this.administratorService.queryC4())
				statusCount.put(((String) resultC[0]).toUpperCase(), df.format(resultC[1]));

			modelAndView.addObject("statusCount", statusCount);

		} catch (final Exception e) {
			modelAndView.addObject("sizeC4", 0);
		}

		//QueryC5 - The processions that are going to be organised in 30 days or less.
		final Collection<Procession> processions = this.administratorService.queryC5();

		modelAndView.addObject("processionsC5", processions);

		//		//QueryC6
		//		final Double queryC6 = this.applicationService.queryC6();
		//		if (queryC6 != null)
		//			modelAndView.addObject("queryC6", df.format(queryC6));
		//		else
		//			modelAndView.addObject("queryC6", nulo);
		//		//QueryC7
		//		final Double queryC7 = this.applicationService.queryC7();
		//		if (queryC7 != null)
		//			modelAndView.addObject("queryC7", df.format(queryC7));
		//		else
		//			modelAndView.addObject("queryC7", nulo);
		//
		//		//QueryC8
		//		final Double queryC8 = this.applicationService.queryC8();
		//		if (queryC8 != null)
		//			modelAndView.addObject("queryC8", df.format(queryC8));
		//		else
		//			modelAndView.addObject("queryC8", nulo);

		return modelAndView;
	}
}
