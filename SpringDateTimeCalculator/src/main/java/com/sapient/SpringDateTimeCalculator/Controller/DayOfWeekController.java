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

import com.sapient.SpringDateTimeCalculator.DateModel.DayOfWeek;
import com.sapient.SpringDateTimeCalculator.DateModel.History;
import com.sapient.SpringDateTimeCalculator.OperationsBO.DayOfWeekOperation;
import com.sapient.SpringDateTimeCalculator.Repo.DayOfWeekRepository;
import com.sapient.SpringDateTimeCalculator.Repo.HistoryRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/")
public class DayOfWeekController {
	
	@Autowired
	DayOfWeekRepository dayOfWeekRepository;
	
	@Autowired
	HistoryRepository historyRepository;
	
	@Autowired
	History history;
	
	@Autowired
	DayOfWeek dayOfWeek;
	
	@GetMapping("/dayofweek/all")
	public List<DayOfWeek> getAllDayOfWeek() {
		return dayOfWeekRepository.findAll();
	}
	
	@GetMapping("/dayofweek")
	public String getDayOfWeek() {
		return "dayofweek";
	}
	
	@GetMapping("/dayofweek/{id}")
	public DayOfWeek getDayOfWeekById(@PathVariable(value="id") Long operationId) 
			throws NotFoundException {
		
		return dayOfWeekRepository.findById(operationId)
				.orElseThrow(() -> new NotFoundException("Not found" + operationId));
	}
	
	@PostMapping("/dayofweek")
	public String createDayOfWeek(Model model, @RequestParam String givenDate) {
		dayOfWeek.setGivenDate(LocalDate.parse(givenDate));
		dayOfWeek.setDayOfWeek(DayOfWeekOperation.dayOfWeek(dayOfWeek.getGivenDate()));
		
		model.addAttribute("givendate", dayOfWeek.getGivenDate());
		model.addAttribute("day", dayOfWeek.getDayOfWeek());
		
		dayOfWeekRepository.save(dayOfWeek);
		
		// History entry
		history.setGivenDate(dayOfWeek.getGivenDate());
		history.setFinalDate(dayOfWeek.getGivenDate());
		history.setMenuOption("day_of_week");
		history.setRemark(dayOfWeek.getDayOfWeek());
				
		historyRepository.save(history);
		
		return "dayofweek";
	}
	
	@DeleteMapping("/dayofweek/{id}")
    public ResponseEntity<?> deleteDayOfWeek(@PathVariable(value = "id") Long operationId) 
    		throws NotFoundException {

        dayOfWeekRepository.deleteById(operationId);

        return ResponseEntity.ok().build();
	}
}
