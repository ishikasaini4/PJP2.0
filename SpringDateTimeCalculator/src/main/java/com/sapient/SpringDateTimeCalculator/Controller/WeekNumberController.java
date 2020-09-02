package com.sapient.SpringDateTimeCalculator.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.SpringDateTimeCalculator.DateModel.History;
import com.sapient.SpringDateTimeCalculator.DateModel.WeekNumber;
import com.sapient.SpringDateTimeCalculator.OperationsBO.WeekNumberOperation;
import com.sapient.SpringDateTimeCalculator.Repo.HistoryRepository;
import com.sapient.SpringDateTimeCalculator.Repo.WeekNumberRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/")
public class WeekNumberController {
	@Autowired
	WeekNumberRepository weekNumberRepository;
	
	@Autowired
	HistoryRepository historyRepository;
	
	@Autowired
	History history;
	
	@Autowired
	WeekNumber weekNumber;
	
	@GetMapping("/weeknumber/all")
	public List<WeekNumber> getAllDayOfWeek() {
		return weekNumberRepository.findAll();
	}
	
	@GetMapping("/weeknumber")
	public String getDayOfWeek() {
		return "weekNumber";
	}
	
	@GetMapping("/weeknumber/{id}")
	public WeekNumber getWeekNumberById(@PathVariable(value="id") Long operationId) 
			throws NotFoundException {
		
		return weekNumberRepository.findById(operationId)
				.orElseThrow(() -> new NotFoundException("Not found" + operationId));
	}
	
	@PostMapping("/weeknumber")
	public String creatWeekNumber(Model model, @RequestParam String givenDate) {
		weekNumber.setGivenDate(LocalDate.parse(givenDate));
		weekNumber.setWeekNumber(WeekNumberOperation.weekNumber(weekNumber.getGivenDate()));
		
		model.addAttribute("givenDate", weekNumber.getGivenDate());
		model.addAttribute("weekNumber", weekNumber.getWeekNumber());
		
		weekNumberRepository.save(weekNumber);
		
		//History entry
		history.setGivenDate(weekNumber.getGivenDate());
		history.setFinalDate(weekNumber.getGivenDate());
		history.setMenuOption("week_number");
		history.setRemark( Integer.toString(weekNumber.getWeekNumber()));
						
		historyRepository.save(history);
		
		return "weekNumber";
	}
	
	@DeleteMapping("/weeknumber/{id}")
    public ResponseEntity<?> deleteWeekNumber(@PathVariable(value = "id") Long operationId) 
    		throws NotFoundException {
        weekNumberRepository.deleteById(operationId);

        return ResponseEntity.ok().build();
	}
}
