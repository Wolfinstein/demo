import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ClassService} from "../../../../services/class.service";
import {GradeModel} from "../../../../models/grade.model";
import {UserInfoService} from "../../../../services/user-info.service";

@Component({
  selector: 'new-grade-component',
  templateUrl: './new.grade.popup.component.html',
  styleUrls: ['./new.grade.popup.component.scss']
})


export class NewGradePopupComponent {

  constructor(private classService: ClassService,
              private userInfo: UserInfoService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<NewGradePopupComponent>) {
  }

  onAdd(): void {
    let grade: GradeModel = new GradeModel(this.data.studentId, this.userInfo.getUserInfo().userId, this.data.gradeValue, this.data.subjectId);
    this.classService.addGrade(grade).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }
}
