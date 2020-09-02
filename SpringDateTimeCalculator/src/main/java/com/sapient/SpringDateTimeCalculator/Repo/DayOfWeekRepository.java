package com.sapient.SpringDateTimeCalculator.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.SpringDateTimeCalculator.DateModel.DayOfWeek;;

@Repository
public interface DayOfWeekRepository extends JpaRepository<DayOfWeek, Long> {

}

