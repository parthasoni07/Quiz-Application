// admin-data.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AdminDataService {
  private apiUrl = 'http://localhost:8765/quiz-service/quiz/get/1'; // Replace with your API URL

  constructor(private http: HttpClient) {}

  getQuizData(): Observable<any> {
    return this.http.get(this.apiUrl);
  }
}
