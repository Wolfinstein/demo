import {Component} from "@angular/core";
import {ClassService} from "../../services/class.service";
import {MatDialog} from "@angular/material";
import {EditClassComponent} from "./edit-class/edit.class.component";
import {UserService} from "../../services/user.service";
import {EditKidsComponent} from "./edit-kids/edit.kids.component";
import {EditUserComponent} from "./edit-user/edit.user.component";
import {AddUserComponent} from "./add-user/add.user.component";
import {AddSubjectComponent} from "./add-subject/add.subject.component";

@Component({
  selector: 'admin-component',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss'],
})

export class AdminComponent {

  classesList: any[];
  userList: any[];
  subjectList: any[];
  logs : any[];

  constructor(private dialog: MatDialog,
              private classService: ClassService,
              private userService: UserService) {
    this.getClasses();
    this.getUsers();
    this.getSubs();
    this.userService.getLogs().subscribe(resp =>{
      this.logs = resp;
    })
  }

  getSubs() {
    this.classService.getSubs().subscribe(resp => {
      this.subjectList = [...resp];
    })
  }


  getClasses() {
    this.classService.getClasses().subscribe(resp => {
      this.classesList = [...resp]
    })
  };

  deleteClass(id: number) {
    this.classService.deleteClass(id).subscribe(resp => {
      this.getClasses();
    })
  }

  editClassPopup(data: any) {

    let dialogRef = this.dialog.open(EditClassComponent, {
      panelClass: 'class-edit-component',
      data: {model: data}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getClasses();
    });
  }

  addClass() {
    this.classService.createClass().subscribe(resp => {
      this.getClasses();
    })
  }

  getUsers() {
    this.userService.getUsers().subscribe(resp => {
      this.userList = [...resp];
    })
  }

  deleteUser(id: number) {
    this.userService.deleteUser(id).subscribe(resp => {
      this.getUsers();
      this.getClasses();
    })
  }

  deleteSubject(id: number) {
    this.classService.deleteSubject(id).subscribe(resp => {
      this.getUsers();
      this.getClasses();
      this.getSubs();
    })
  }

  editKidsPopup(data: any) {
    let dialogRef = this.dialog.open(EditKidsComponent, {
      panelClass: 'kids-edit-component',
      data: {data}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getUsers();
    });
  }

  editUserPopup(data: any) {
    let dialogRef = this.dialog.open(EditUserComponent, {
      panelClass: 'user-edit-component',
      data: {data}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getUsers();
    });
  }

  addUserPopup() {
    let dialogRef = this.dialog.open(AddUserComponent, {
      panelClass: 'user-add-component',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getUsers();
    });
  }

  addSubjectPopup() {
    let dialogRef = this.dialog.open(AddSubjectComponent, {
      panelClass: 'subject-add-component',
      data: {}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.getSubs();
    });
  }

}
