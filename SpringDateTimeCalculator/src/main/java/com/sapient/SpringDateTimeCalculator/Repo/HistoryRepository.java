package com.sapient.SpringDateTimeCalculator.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.SpringDateTimeCalculator.DateModel.History;
@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {

}
