import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  constructor(private http: HttpClient) { }

  getAll() {
    //Get Data from server
    console.log("Getting data from server");
  }

  add() {
    console.log("Adding employee to database");
  }
}
