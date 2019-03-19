import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {ClassService} from "../../../../services/class.service";

@Component({
  selector: 'grade-edit-component',
  templateUrl: './grade.popup.component.html',
  styleUrls: ['./grade.popup.component.scss']
})


export class GradePopupComponent {

  constructor(private classService: ClassService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<GradePopupComponent>) {
  }

  onEdit(): void {
    this.classService.editGrade(this.data.gradeId, this.data.gradeValue).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }

  onDelete(): void {
    this.classService.deleteGrade(this.data.gradeId).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }

}
