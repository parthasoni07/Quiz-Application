import { Component, Input, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';

interface Data {
  score: number;
}

@Component({
  selector: 'app-learner',
  templateUrl: './learner.component.html',
  styleUrls: ['./learner.component.css']
})
export class LearnerComponent implements OnInit {
  isVisible: Boolean = true;
  quizData: any[] = [];
  selectedAnswers: any = {};
  successMessage: string = '';
  errorMessage: string = '';
  successMessageColor: string = 'green'; // default to green
  isLogin: boolean = false;
  @Input() isLearnerVisible = true;
  questionId:number = 0;
  showLogin:boolean = false;
  constructor(private httpClient: HttpClient, private router: Router, private route:ActivatedRoute) {}

  ngOnInit() {
    this.route.params.subscribe((params) =>{
      this.questionId = params['id'];
    });
    // Get the quiz question data from the backend API.
    this.httpClient.get<any[]>(`http://localhost:8090/quiz/get/${this.questionId}`).subscribe(data => {
      // Update the component property with the quiz question data.
      this.quizData = data;
    });
  }

  onRadioButtonClick(questionId: number, option: string) {
    // Set the selected answer for the given question.
    this.selectedAnswers[questionId] = option;
  }

  onSubmit() {
    // Convert the selected answers to the specified format.
    const formData = [];
    for (const key in this.selectedAnswers) {
      formData.push({
        id: parseInt(key),
        response: this.selectedAnswers[key]
      });
    }

    // Post the selected answers to the backend API.
    this.httpClient.post('http://localhost:8090/quiz/submit/3', formData)
    //this.httpClient.post('http://localhost:8765/quiz-service/quiz/submit/1', formData)
   
      .subscribe(data => {
        // Handle the response from the backend API.
        // Log the `data` object to the console.
        console.log(data);

        // Set the success message color based on the score.
        if (data == 3 || data == 4 || data == 5) {
          this.successMessage = "Congrates you have passed the assessment with score" + " " + '('+ data + ')';
          this.successMessageColor = 'green';
        } else {
          this.errorMessage = "Sorry you have not passed the assessment your score is"+ " " + '('+ data + ')'+ '& you need to score min 3';
          this.successMessageColor = 'red';
        }

        // Route to the login component page after 5 seconds.
        setTimeout(() => {
          this.isLearnerVisible = false;
          this.showLogin=true;
          this.router.navigate(['/learner-home']);
          
        }, 5000);
      }, error => {
        // Handle the error.
        this.errorMessage = "Hacker is attacking is attacking3";
        console.log(error);
      });
  }
}


