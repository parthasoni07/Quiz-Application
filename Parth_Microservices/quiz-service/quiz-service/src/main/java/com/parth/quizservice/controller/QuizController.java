package com.parth.quizservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parth.quizservice.model.QuestionWrapper;
import com.parth.quizservice.model.Quiz;
import com.parth.quizservice.model.QuizDto;
import com.parth.quizservice.model.QuizModel;
import com.parth.quizservice.model.Response;
import com.parth.quizservice.service.QuizService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("quiz")
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	//Admin panel to create the quiz
	@PostMapping("create")
	public ResponseEntity<String>createQuiz(@RequestBody QuizDto quizDto) {
		return quizService.createQuiz(quizDto.getCategoryName(),quizDto.getNumQuestion(),quizDto.getTitle());

	}
	
	@GetMapping("get/{id}")
	public List<QuestionWrapper> getQuizQuestion(@PathVariable Integer id){
		return quizService.getQuizQuestion(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer>submitQuiz(@PathVariable Integer id ,@RequestBody List<Response> responses) {
		System.out.println(responses.get(1).getResponse());
		System.out.println(responses.get(1).getId());
		return quizService.calculateResult(id,responses);
	}
	
	//get all quizs
	@CrossOrigin(origins = "*")
	@GetMapping("getAllQuiz")
	public ResponseEntity<List<Quiz>> getAllQuizs(){
		return quizService.getAllQuizs();
	}
	

}
