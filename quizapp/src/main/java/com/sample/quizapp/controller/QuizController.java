package com.sample.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sample.quizapp.Dto.QuestionWrapper;
import com.sample.quizapp.Dto.SubmitResponse;
import com.sample.quizapp.Entities.Questionnaire;
import com.sample.quizapp.service.QuizService;

@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	@PostMapping("create")
	public ResponseEntity<String> createQuiz(@RequestParam("category") String category , @RequestParam("numQ") int numQ ,@RequestParam("title") String title) {
		return quizService.createQuiz(category,numQ,title);
	}
	
	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable (name = "id") Integer id){
		return quizService.getQuizQuestions(id);	 
	}

	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable (name = "id") Integer id, @RequestBody List<SubmitResponse> response) {
		return quizService.calculateResult(id,response);
		
	}
}
