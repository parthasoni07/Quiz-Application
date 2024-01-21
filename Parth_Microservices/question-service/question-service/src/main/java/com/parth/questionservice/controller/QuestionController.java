package com.parth.questionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parth.questionservice.model.Question;
import com.parth.questionservice.model.QuestionWrapper;
import com.parth.questionservice.model.Response;
import com.parth.questionservice.service.QuestionService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	 QuestionService questionService;
	
	@Autowired
	Environment environment;

	@GetMapping("allQuestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		ResponseEntity<List<Question>> questions = questionService.getAllQuestions();
		return questions;
	}
	//Get all question by category
	@GetMapping("category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
		
	}
	
	//To add questions
	@PostMapping("add")
	public ResponseEntity<String> addQuestions(@RequestBody Question question) {
		return questionService.addQuestions(question);
		
	}
	//generate
	@GetMapping("generate")
	public ResponseEntity<List<Integer>> getQuestionsFromQuiz(@RequestParam String category, @RequestParam int numQ){
		return questionService.getQuestionsFromQuiz(category,numQ);
		
	}
	
	//getScore
	@PostMapping("getQuestion")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionId) {
		System.out.println(environment.getProperty("local.server.port"));
		return questionService.getQuestionsFromId(questionId);
		
	}
	
	@PostMapping("getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
		return questionService.getScore(responses);
		
	}
	
	
	
	//generate
	//getQuestions(questionId)
	//getScore

}
