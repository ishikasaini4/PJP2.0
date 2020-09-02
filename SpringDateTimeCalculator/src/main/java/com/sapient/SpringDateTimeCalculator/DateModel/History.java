package com.sapient.SpringDateTimeCalculator.DateModel;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
@Entity
@Table(name = "history")
public class History {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private Long id;
	
	private String menuOption;
	
	private LocalDate givenDate;
	
	private String Remark;
	
	private LocalDate finalDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMenuOption() {
		return menuOption;
	}

	public void setMenuOption(String menuOption) {
		this.menuOption = menuOption;
	}

	public LocalDate getGivenDate() {
		return givenDate;
	}

	public void setGivenDate(LocalDate givenDate) {
		this.givenDate = givenDate;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public LocalDate getFinalDate() {
		return finalDate;
	}

	public void setFinalDate(LocalDate finalDate) {
		this.finalDate = finalDate;
	}
}
