import { Component, OnInit } from '@angular/core';
import {Employee} from "../models/employee.model";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  private currentUser : Employee;
  private employees: Employee[] = []

  constructor() {
    this.currentUser = new Employee(
      "Harshit",
      "Vaish",
      "22/10/1995",
      "Some Department"
    );
  }

  ngOnInit(): void {

  }

}
