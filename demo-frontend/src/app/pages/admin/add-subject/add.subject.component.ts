import {Component, Inject} from "@angular/core";
import {UserService} from "../../../services/user.service";
import {ClassService} from "../../../services/class.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";

@Component({
  selector: 'add-subject-component',
  templateUrl: './add.subject.component.html',
  styleUrls: ['./add.subject.component.scss']
})


export class AddSubjectComponent {

  classes: any[];
  teachers: any[];

  constructor(private userService: UserService,
              private classService: ClassService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<AddSubjectComponent>) {

    this.classService.getClasses().subscribe(resp => {
      this.classes = resp;
    });

    this.classService.getTeachers().subscribe(resp => {
      this.teachers = resp;
    })
  }

  onAdd(): void {
    console.log(this.data);
    this.classService.addSubject(this.data.classId, this.data).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }

}
