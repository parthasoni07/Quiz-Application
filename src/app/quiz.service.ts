import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class QuizService {

  constructor(private httpClient: HttpClient) { }

  getQuizQuestions() {
    return this.httpClient.get<any[]>('http://localhost:8765/quiz-service/quiz/get/all');
  }
}
