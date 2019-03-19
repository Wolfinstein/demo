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

  updateTeacher(id: number, user : TeacherUpdateModel): Observable<any> {
    return this.apiRequest.put('teacher/' + id, user)
  }

}
