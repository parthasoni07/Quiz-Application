import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username: string = '';
  password: string = '';
  usernameEntered: boolean = false;
  passwordEntered: boolean = false;
  showPassword: boolean = false;
  @Input()
  isVisible!: boolean;
  isLogin:Boolean = true;
  
  constructor(private router: Router) {}
  ngOnInit(): void {
    this.learnerVisible = false;
  }
  bagroundImage:string = 'assets/image/qb.webp'
  learnerVisible = true;

  

  login() {
    if (this.username === 'admin') {
      console.log("Parth admin");
      this.isVisible = false;
      this.router.navigate(['/admin']);
      this.isVisible = false;
    } else {
      this.isVisible = false;
      this.learnerVisible = false;
      this.router.navigate(['/learner-home']);
      this.isLogin = false;
      console.log(this.isLogin);
      this.isVisible = false;
    }
  }

}
