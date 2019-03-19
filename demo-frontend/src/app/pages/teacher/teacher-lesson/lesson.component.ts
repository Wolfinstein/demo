import {Component, ViewChild} from "@angular/core";
import {DatatableComponent} from "@swimlane/ngx-datatable";
import {ClassService} from "../../../services/class.service";
import {UserInfoService} from "../../../services/user-info.service";
import {ActivatedRoute} from "@angular/router";
import {MatDialog} from "@angular/material";
import {GradePopupComponent} from "./grade-popup/grade.popup.component";
import {NewGradePopupComponent} from "./new-grade-popup/new.grade.popup.component";
import {ChangeTopicPopupComponent} from "./change-topic/change.topic.popup.component";
import {SendNotificationPopupComponent} from "./send-notification/send.notification.popup.component";

@Component({
  selector: 'lesson-component',
  templateUrl: './lesson.component.html',
  styleUrls: ['./lesson.component.scss']
})


export class LessonComponent {

  @ViewChild(DatatableComponent) private lessonsTable: DatatableComponent;
  @ViewChild(DatatableComponent) private studentsTable: DatatableComponent;

  lessonsList: any[];
  studentsList: any[];

  constructor(private classService: ClassService,
              private userInfo: UserInfoService,
              private route: ActivatedRoute,
              private dialog: MatDialog) {


    this.getStudents();
    this.getLessons();
  }

  getLessons() {
    this.classService.getLessons(Number(this.route.snapshot.params['id'])).subscribe(resp => {
      this.lessonsList = [...resp];
    })
  };

  getStudents() {
    this.classService.getStudents(Number(this.route.snapshot.params['id'])).subscribe(resp => {
      this.studentsList = [...resp];
    })
  };

  editGradePopup(gradeId: String, gradeValue: String) {
    let dialogRef = this.dialog.open(GradePopupComponent, {
      panelClass: 'grade-edit-component',
      data: {gradeId: gradeId, gradeValue: gradeValue}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getStudents();
    });
  }

  addGrade(studentId: String) {
    let dialogRef = this.dialog.open(NewGradePopupComponent, {
      panelClass: 'new-grade-component',
      data: {studentId: studentId, gradeValue: '', subjectId: this.route.snapshot.params['id']}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getStudents();
    });
  }

  addLesson() {
    this.classService.addLesson(this.route.snapshot.params['id']).subscribe(resp => {
        this.getLessons();
      }
    )
  }

  deleteLesson(id: number) {
    this.classService.deleteLesson(id).subscribe(resp => {
      this.getLessons();
    })
  }

  editTopicPopup(lessonId: String, topic: String) {
    let dialogRef = this.dialog.open(ChangeTopicPopupComponent, {
      panelClass: 'topic-edit-component',
      data: {lessonId: lessonId, topic: topic}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getLessons();
    });
  }

  sendNotification(studentId: String) {
    let dialogRef = this.dialog.open(SendNotificationPopupComponent, {
      panelClass: 'send-notification-component',
      data: {studentId: studentId, message: ''}
    });
    dialogRef.afterClosed().subscribe(result => {
    });
  }

}
