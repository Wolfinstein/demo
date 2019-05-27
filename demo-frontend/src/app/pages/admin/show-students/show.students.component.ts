import {Component, Inject, OnInit} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ClassService} from "../../../services/class.service";
import {ClassEditModel} from "../../../models/class.edit.model";

@Component({
  selector: 'show-students-component',
  templateUrl: './show.students.component.html',
  styleUrls: ['./show.students.component.scss']
})


export class ShowStudentsComponent implements OnInit {

  studentsList: any[];

  constructor(private classService: ClassService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<ShowStudentsComponent>) {
    this.getStudents();
  }

  ngOnInit(): void {
    this.getStudents();
  }

  getStudents() {
    this.classService.getStudentss(this.data.id).subscribe(resp => {
      this.studentsList = resp;
    })
  }

}
