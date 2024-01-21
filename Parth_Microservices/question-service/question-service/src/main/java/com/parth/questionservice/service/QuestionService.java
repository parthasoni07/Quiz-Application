package com.parth.questionservice.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.parth.questionservice.dao.QuestionDao;
import com.parth.questionservice.model.Question;
import com.parth.questionservice.model.QuestionWrapper;
import com.parth.questionservice.model.Response;


@Service
public class QuestionService {
	
	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
		return new ResponseEntity<>(questionDao.findAll(),HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
		
	}

//	public Long getcountQuestions() {
//		return questionDao.count();
//	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionDao.findByCategory(category),HttpStatus.OK);
			} catch(Exception e) {
				e.printStackTrace();
			}
		return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<String> addQuestions(Question question) {
		questionDao.save(question);
		return new ResponseEntity<>("Success",HttpStatus.CREATED);
	}
	//getQuestionsFromQuiz
	public ResponseEntity<List<Integer>> getQuestionsFromQuiz(String category, int numQ) {
		List<Integer>quizQuestion = questionDao.findQuestionsByCategory(category,numQ);
		return new ResponseEntity<>(quizQuestion, HttpStatus.OK);
	}
	//getQuestionsFromId
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(List<Integer> questionIds) {
		List<QuestionWrapper> wrappers = new ArrayList<>();
		List<Question> questions = new ArrayList<>();
		
		for(Integer id: questionIds) {
			questions.add(questionDao.findById(id).get());
		}
		
		for(Question q : questions) {
			QuestionWrapper qw = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getCategory(),q.getOption1(),q.getOption2(),q.getOption3());
			wrappers.add(qw);
		}
		
		
		return new ResponseEntity<>(wrappers,HttpStatus.OK);
	}
	
	

	public ResponseEntity<Integer> getScore(List<Response> responses) {
		
		int result = 0;
		for(Response response: responses) {
			 Question question = questionDao.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer())) {
				result++;
			}
			
		}
		return new ResponseEntity<>(result,HttpStatus.OK);
		
	}

}
