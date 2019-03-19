import {Injectable} from '@angular/core';
import {ApiRequestService} from "./api-request.service";
import {Observable} from "rxjs";
import {TeacherUpdateModel} from "../models/teacher.update";

@Injectable()
export class UserService {

  constructor(private apiRequest: ApiRequestService) {
  }

  getUser(id: number): Observable<any> {
    return this.apiRequest.get('user/show/' + id)
  }

  updateTeacher(id: number, user: TeacherUpdateModel): Observable<any> {
    return this.apiRequest.put('teacher/' + id, user)
  }

  getUsers(): Observable<any> {
    return this.apiRequest.get('users')
  }

  deleteUser(id: number): Observable<any> {
    return this.apiRequest.delete('users/' + id)
  }

  editKids(id: number, kidIds: any): Observable<any> {
    return this.apiRequest.put('users/kids/' + id, kidIds);
  }

  getPotentialKids(): Observable<any> {
    return this.apiRequest.get('users/potentialKids');
  }

  editUser(id: number, user: any): Observable<any> {
    return this.apiRequest.put('users/edit/' + id, user)
  }

  addUser(user: any): Observable<any> {
    return this.apiRequest.post('users/add', user)
  }

  getLogs(): Observable<any> {
    return this.apiRequest.get('logs');
  }

  isNew(id: number): Observable<any> {
    return this.apiRequest.get('notifications/color/' + id);
  }
}
