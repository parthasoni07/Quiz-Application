// admin.component.ts
import { Component, OnInit } from '@angular/core';
import { AdminDataService } from './admin-data.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css'],
})
export class AdminComponent implements OnInit {
  

  constructor(private adminDataService: AdminDataService) {}

  ngOnInit(): void {
   
  }

 
  
}
