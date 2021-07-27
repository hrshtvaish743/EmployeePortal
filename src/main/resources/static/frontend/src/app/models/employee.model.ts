export class Employee {
  private firstName : String;
  private lastName : String;
  private dateOfBirth: String;
  private department: String;


  constructor(firstName: String, lastName: String, dateOfBirth: String, department: String) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.department = department;
  }
}
