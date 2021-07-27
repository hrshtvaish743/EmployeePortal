import {RouterModule, Routes} from "@angular/router";
import {NgModule} from "@angular/core";
import {HomeComponent} from "./home/home.component";
import {AddEmployeeComponent} from "./add-employee/add-employee.component";
import {ListEmployeeComponent} from "./list-employee/list-employee.component";
import {LoginComponent} from "./login/login.component";

const routes : Routes = [
  { path: '', component: HomeComponent},
  { path: 'login', component: LoginComponent},
  { path: 'add', component: AddEmployeeComponent},
  { path: 'getall', component: ListEmployeeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
