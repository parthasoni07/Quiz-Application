import { HttpClient } from '@angular/common/http';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-learnerhome',
  templateUrl: './learnerhome.component.html',
  styleUrls: ['./learnerhome.component.css'],
})
export class LearnerHomeComponent implements OnInit {
  totalQuizzes: number = 0;
  quizData: any[] = [];
  learnerVisible = false;
  isSidebarOpen: boolean = false; // Variable to control sidebar visibility
  @Input() isLoggedin: boolean = false;
  showLogin:boolean = false;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    // Initialize isLoggedin to false
    this.isLoggedin = false;
    // Fetch quiz data from an API endpoint
    this.http.get<any>('http://localhost:8090/quiz/getAllQuiz').subscribe((data) => {
      this.quizData = data;
      console.log(data);
    });
  }

  loadLearner(quizId: number) {
    this.learnerVisible = true;
    this.router.navigate(['/learner', quizId]);
  }

  // Method to navigate to the home page
  navigateToHome() {
    this.router.navigate(['/home']);
  }

  // Method to handle login again
  loginAgain() {
    this.router.navigate(['/login']);
    // Implement your login logic here
  }

  // Method to toggle the sidebar
  toggleSidebar() {
    this.isSidebarOpen = !this.isSidebarOpen;
  }

  // Method to get card styles based on quiz ID
  getCardStyle(quizId: number) {
    if (quizId === 1) {
      return { 'background-color': 'red' };
    } else if (quizId === 2) {
      return { 'background-color': 'green' };
    } else {
      return { 'background-color': 'green' };
    }
  }
}
