package com.sample.quizapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.quizapp.Entities.Questionnaire;
import com.sample.quizapp.dao.QuestionDao;

@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<List<Questionnaire>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST) ;
	}

	public List<Questionnaire> getAllQuestionsByCategory(String category) {
		return questionDao.findByCategory(category);
	}

	public String addQuestion(Questionnaire questionnaire) {
		questionDao.save(questionnaire);
		return "success";
	}
    
//	delete update
	
}
