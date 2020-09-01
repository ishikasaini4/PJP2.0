package main.com.sapient.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DateController {

	@Autowired
	DateTimeBO bo;
	private final String result = "Result";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getMenu() {
		return "Menu";
	}

	@RequestMapping(value = "/Add", method = RequestMethod.GET)
	public String add(@RequestParam String date1, @RequestParam String date2, ModelMap map) {
		map.addAttribute("result", bo.add(date1, date2));
		return result;
	}

	@RequestMapping(value = "/Subtract", method = RequestMethod.GET)
	public String subtract(@RequestParam String date1, @RequestParam String date2, ModelMap map) {
		map.addAttribute("result", bo.subtract(date1, date2));
		return result;
	}

	@RequestMapping(value = "/MinusDays", method = RequestMethod.GET)
	public String MinusDays(@RequestParam String date1, @RequestParam String days, ModelMap map) {
		map.addAttribute("result", bo.minusNDays(date1, Integer.parseInt(days)));
		return result;
	}

	@RequestMapping(value = "/MinusWeeks", method = RequestMethod.GET)
	public String MinusWeeks(@RequestParam String date1, @RequestParam String weeks, ModelMap map) {
		map.addAttribute("result", bo.minusNWeeks(date1, Integer.parseInt(weeks)));
		return result;
	}

	@RequestMapping(value = "/MinusMonths", method = RequestMethod.GET)
	public String MinusMonths(@RequestParam String date1, @RequestParam String months, ModelMap map) {
		map.addAttribute("result", bo.minusNMonths(date1, Integer.parseInt(months)));
		return result;
	}

	@RequestMapping(value = "/DayOfWeek", method = RequestMethod.GET)
	public String DayOfWeek(@RequestParam String date1, ModelMap map) {
		map.addAttribute("result", bo.getDayOfTheWeek(date1));
		return result;
	}

	@RequestMapping(value = "/WeekOfYear", method = RequestMethod.GET)
	public String WeekOfYear(@RequestParam String date1, ModelMap map) {
		map.addAttribute("result", bo.getWeekNumber(date1));
		return result;
	}

	@RequestMapping(value = "/NLPToDate", method = RequestMethod.GET)
	public String NLPToDate(@RequestParam String phrase, ModelMap map) {
		map.addAttribute("result", bo.NLPToDate(phrase));
		return result;
	}

}
