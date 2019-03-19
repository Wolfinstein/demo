import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {UserInfoService} from "../../services/user-info.service";
import {UserService} from "../../services/user.service";
import {MatDialog} from "@angular/material";
import {UpdateTeacherComponent} from "./update-teacher/update.teacher.component";
import {TeacherUpdateModel} from "../../models/teacher.update";

@Component({
  selector: 'user-account',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss'],
})
export class UserAccountComponent implements OnInit {

  sessionUser: any;
  user: any;

  constructor(private userInfoService: UserInfoService,
              private userService: UserService,
              private router: Router,
              private dialog: MatDialog) {
  }

  ngOnInit() {
    this.sessionUser = this.userInfoService.getUserInfo();
    this.userService.getUser(this.sessionUser['userId']).subscribe(response => {
      this.user = response;
    });
  }

  updateTeacherPopup() {
    let updateTeacherModel: TeacherUpdateModel = new TeacherUpdateModel(
      this.user.name, this.user.email, this.user.phone, this.user.surname);

    let dialogRef = this.dialog.open(UpdateTeacherComponent, {
      panelClass: 'update-teacher',
      data: {updateTeacherModel: updateTeacherModel}
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }


}
