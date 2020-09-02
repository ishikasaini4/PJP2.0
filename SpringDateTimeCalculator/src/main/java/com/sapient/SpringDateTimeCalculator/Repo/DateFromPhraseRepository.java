package com.sapient.SpringDateTimeCalculator.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.SpringDateTimeCalculator.DateModel.DateFromPhrase;

@Repository
public interface DateFromPhraseRepository extends JpaRepository<DateFromPhrase, Long> {

}
