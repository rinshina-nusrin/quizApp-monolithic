package com.sample.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sample.quizapp.Dto.QuestionWrapper;
import com.sample.quizapp.Dto.SubmitResponse;
import com.sample.quizapp.Entities.Questionnaire;
import com.sample.quizapp.Entities.Quiz;
import com.sample.quizapp.dao.QuestionDao;
import com.sample.quizapp.dao.QuizDao;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuestionDao questionDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		
		List<Questionnaire> questions = questionDao.findRandomQuestionsByCategory(category,numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		quizDao.save(quiz);
		return new ResponseEntity<>("success" , HttpStatus.CREATED);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Questionnaire> questionFromDb = quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser = new ArrayList<>();
		for(Questionnaire q :questionFromDb) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestion(),q.getOption_a(),q.getOption_b(),q.getOption_c(),q.getOption_d());
			questionForUser.add(qw);
		}
		
		return new ResponseEntity<>(questionForUser,HttpStatus.OK);
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<SubmitResponse> response) {
		Quiz quiz = quizDao.findById(id).get();
		List<Questionnaire> questions = quiz.getQuestions();
		int right = 0;
		int i = 0;
		for(SubmitResponse res : response) {
			if (res.getResponse().equals(questions.get(i).getCorrectAnswer())) {
				right++;
			}
			i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
