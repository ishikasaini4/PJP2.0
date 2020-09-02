package com.sapient.SpringDateTimeCalculator.Controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.function.Supplier;

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

import com.sapient.SpringDateTimeCalculator.DateModel.DateFromPhrase;
import com.sapient.SpringDateTimeCalculator.DateModel.History;
import com.sapient.SpringDateTimeCalculator.OperationsBO.DateFromPhraseOperation;
import com.sapient.SpringDateTimeCalculator.Repo.DateFromPhraseRepository;
import com.sapient.SpringDateTimeCalculator.Repo.HistoryRepository;

import javassist.NotFoundException;

@Controller
@RequestMapping("/")
public class DateFromPhraseController {
	
	@Autowired
	DateFromPhraseRepository dateFromPhraseRepository;
	
	@Autowired
	HistoryRepository historyRepository;
	
	@Autowired
	History history;
	
	@Autowired
	DateFromPhrase dateFromPhrase;
	
	@GetMapping("/datefromphrase/all")
	public List<DateFromPhrase> getAll() {
		return dateFromPhraseRepository.findAll();
	}
	
	@GetMapping("/datefromphrase")
	public String getDatesFromPhrase() {
		return "dateFromPhrase";
	}
	
	@GetMapping("/datefromphrase/{id}")
	public DateFromPhrase getDateFromPhraseById(@PathVariable(value="id") final Long operationId) 
			throws Throwable {
		
		return dateFromPhraseRepository.findById(operationId)
				.orElseThrow(new Supplier<Throwable>() {
					public Throwable get() {
						return new NotFoundException("Not found" + operationId);
					}
				});
	}
	
	@PostMapping("/datefromphrase")
	public String createDateFromPhrase(Model model, @RequestParam String phrase) throws IOException {
		dateFromPhrase.setGivenDate(LocalDate.now());
		dateFromPhrase.setPhrase(phrase);
		dateFromPhrase.setFinalDate(DateFromPhraseOperation.handleOperation(dateFromPhrase.getGivenDate(), phrase));
		
		dateFromPhraseRepository.save(dateFromPhrase);
		
		model.addAttribute("givenDate", dateFromPhrase.getGivenDate());
		model.addAttribute("finalDate", dateFromPhrase.getFinalDate());
		model.addAttribute("phrase", dateFromPhrase.getPhrase());
		
		
		// History entry
		history.setGivenDate(dateFromPhrase.getGivenDate());
		history.setFinalDate(dateFromPhrase.getFinalDate());
		history.setMenuOption("date_from_phrase");
		history.setRemark("Date From Phrase");
		
		historyRepository.save(history);
		
		return "dateFromPhrase";
	}
	
	@DeleteMapping("/datefromphrase/{id}")
    public ResponseEntity<?> deleteDateFromPhrase(@PathVariable(value = "id") Long operationId) 
    		throws NotFoundException {
        dateFromPhraseRepository.deleteById(operationId);
        return ResponseEntity.ok().build();
	}

}
