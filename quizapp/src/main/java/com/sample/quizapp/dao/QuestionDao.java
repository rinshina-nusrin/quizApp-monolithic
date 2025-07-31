package com.sample.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.quizapp.Entities.Questionnaire;

@Repository
public interface QuestionDao extends JpaRepository<Questionnaire, Integer>{

	List<Questionnaire> findByCategory(String category);

	@Query(value = "SELECT * FROM questionnaire q WHERE q.category=:category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
	List<Questionnaire> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);

}
