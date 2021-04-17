export class User {
  id: number;
  userName: string;
  password: string;
  firstName: string;
  lastName: string;
  role: string;
  deleted: boolean;

  constructor( id?: number, userName?: string, password?: string, firstName?: string, lastName?: string, role?: string, deleted?: boolean){
    this.id = id;
    this.userName = userName;
    this.password = password;
    this.firstName = firstName;
    this.lastName = lastName;
    this.role = role;
    this.deleted = deleted;
  }
}
