import {Injectable} from "@angular/core";
import {ApiRequestService} from "./api-request.service";
import {Observable} from "rxjs";
import {GradeModel} from "../models/grade.model";
import {ClassEditModel} from "../models/class.edit.model";

@Injectable()
export class ClassService {

  constructor(private apiRequest: ApiRequestService) {
  }

  getClasses(): Observable<any> {
    return this.apiRequest.get('classes')
  }

  getSubjects(id: number): Observable<any> {
    return this.apiRequest.get('subjects/' + id)
  }

  getLessons(id: number): Observable<any> {
    return this.apiRequest.get('lessons/' + id)
  }

  getStudents(id: number): Observable<any> {
    return this.apiRequest.get('students/' + id)
  }

  editGrade(id: String, grade: String): Observable<any> {
    return this.apiRequest.put('grade/' + id, grade)
  }

  deleteGrade(id: String): Observable<any> {
    return this.apiRequest.delete('grade/' + id)
  }

  addGrade(grade: GradeModel): Observable<any> {
    return this.apiRequest.post('grade/', grade);
  }

  addLesson(id: number): Observable<any> {
    return this.apiRequest.post('lesson/add/' + id, null)
  }

  deleteLesson(id: number): Observable<any> {
    return this.apiRequest.delete('lesson/' + id);
  }

  editTopic(id: number, topic: String): Observable<any> {
    return this.apiRequest.put('lesson/changeTopic/' + id, topic);
  }

  getPresences(id: number): Observable<any> {
    return this.apiRequest.get('presences/' + id);
  }

  editPresence(id: number, presenceType: String): Observable<any> {
    return this.apiRequest.put('presences/edit/' + id, presenceType);
  }

  sendNotification(id: number, message: String): Observable<any> {
    return this.apiRequest.post('notification/add/' + id, message)
  }

  getNotifications(id: number): Observable<any> {
    return this.apiRequest.get('notifications/' + id);
  }

  changeStatus(id: number): Observable<any> {
    return this.apiRequest.get('notifications/changeStatus/' + id);
  }

  getStudentData(id: number): Observable<any> {
    return this.apiRequest.get('students/data/' + id);
  }

  getStats(id: number): Observable<Blob> {
    return this.apiRequest.download('studentReport/' + id)
  }

  getStudentAbsences(id: number): Observable<any> {
    return this.apiRequest.get('students/absences/' + id);
  }

  getStudentDataForParent(id: number): Observable<any> {
    return this.apiRequest.get('students/parent/data/' + id);
  }

  getStudentAbsencesForParent(id: number): Observable<any> {
    return this.apiRequest.get('students/parent/absences/' + id)
  }

  editClass(id: number, model: ClassEditModel): Observable<any> {
    return this.apiRequest.put('classes/' + id, model)
  }

  getTeachers(): Observable<any> {
    return this.apiRequest.get('students/teachers')
  }

  deleteClass(id: number): Observable<any> {
    return this.apiRequest.delete('classes/' + id);
  }

  createClass(): Observable<any> {
    return this.apiRequest.post('classes/add', null);
  }

  getSchedule(id: number): Observable<any> {
    return this.apiRequest.get('schedule/' + id);
  }

  getSubs(): Observable<any> {
    return this.apiRequest.get('subjects/getAll');
  }

  deleteSubject(id: number): Observable<any> {
    return this.apiRequest.delete('subjects/' + id);
  }

  addSubject(id: number, subject: any): Observable<any> {
    return this.apiRequest.post('subjects/add/' + id, subject);
  }

}
