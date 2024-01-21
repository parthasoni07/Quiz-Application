package com.parth.quizservice.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.parth.quizservice.model.QuestionWrapper;
import com.parth.quizservice.model.Response;


@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
	
		
	//generate
		@GetMapping("question/generate")
		public ResponseEntity<List<Integer>> getQuestionsFromQuiz(@RequestParam String category, @RequestParam int numQ);
		//getQuestions(questionId)
		@PostMapping("question/getQuestion")
		public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> question);
		
		//getScore
		@PostMapping("question/getScore")
		public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
			

}
