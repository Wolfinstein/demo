import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";
import {Router} from "@angular/router";
import {TeacherUpdateModel} from "../../../models/teacher.update";
import {UserService} from "../../../services/user.service";
import {UserInfoService} from "../../../services/user-info.service";

@Component({
  selector: 'update-teacher',
  templateUrl: './update.teacher.component.html',
  styleUrls: ['./update.teacher.component.scss'],
})
export class UpdateTeacherComponent {

  currentUser: any;

  constructor(public dialogRef: MatDialogRef<UpdateTeacherComponent>,
              @Inject(MAT_DIALOG_DATA) public data: TeacherUpdateModel,
              private router: Router,
              private userService: UserService,
              private userInfo: UserInfoService,
              public dialog: MatDialog) {
    this.getUser();
  }

  getUser() {
    this.userService.getUser(Number(this.userInfo.getUserInfo().userId)).subscribe(resp => {
      this.currentUser = resp;
    })
  }

  onUpdateClick(teacher: TeacherUpdateModel): void {
    this.userService.updateTeacher(Number(this.userInfo.getUserInfo().userId), teacher).subscribe(resp => {
        this.getUser();
        this.dialogRef.close();

      }
    );
  }


}

