package com.parth.quizservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.parth.quizservice.dao.QuizDao;
import com.parth.quizservice.feign.QuizInterface;
import com.parth.quizservice.model.QuestionWrapper;
import com.parth.quizservice.model.Quiz;
import com.parth.quizservice.model.QuizModel;
import com.parth.quizservice.model.Response;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;
	
	@Autowired
	QuizInterface quizInterface;


	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		List<Integer> questions = quizInterface.getQuestionsFromQuiz(category, numQ).getBody();
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuizQuestion(questions);
		quizDao.save(quiz);
		
		return new ResponseEntity<>("Success", HttpStatus.OK);
	}

	public List<QuestionWrapper> getQuizQuestion(Integer id) {
		
		Optional<Quiz> quiz = quizDao.findById(id);
		List<Integer> questionsFromDb = quiz.get().getQuizQuestion();
		List<QuestionWrapper> questions = quizInterface.getQuestionsFromId(questionsFromDb).getBody();
		
		return questions;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		//Quiz quiz = quizDao.findById(id).get();
		Integer result = quizInterface.getScore(responses).getBody();
		
		return new ResponseEntity<>(result,HttpStatus.OK);
	}
	
	public ResponseEntity<List<Quiz>> getAllQuizs() {
		
		 List<Quiz> quizDtls =  quizDao.findAll();
		 
		return new ResponseEntity<>(quizDtls, HttpStatus.OK);
	}
	
	

}
