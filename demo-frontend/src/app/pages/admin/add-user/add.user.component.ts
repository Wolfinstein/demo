import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UserService} from "../../../services/user.service";
import {ClassService} from "../../../services/class.service";
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {el} from "@angular/platform-browser/testing/src/browser_util";

@Component({
  selector: 'add-user-component',
  templateUrl: './add.user.component.html',
  styleUrls: ['./add.user.component.scss']
})


export class AddUserComponent {

  userForm: FormGroup;
  submitted: boolean = false;
  classes: any[];
  errMsg: string = "";

  constructor(private userService: UserService,
              private classService: ClassService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<AddUserComponent>, public formBuilder: FormBuilder) {

    this.userForm = this.formBuilder.group({
      'name': [this.data.name, Validators.required],
      'surname': [this.data.surname],
      'email': [this.data.email, [Validators.required, Validators.email]],
      'phone': [this.data.phone, Validators.required],
      'birthDate': [this.data.birthDate, Validators.required],
      'login': [this.data.login, Validators.required],
      'password': [this.data.login, Validators.required],
      'isUserTeacher': [this.data.isUserTeacher = false, Validators.required],
      'isUserParent': [this.data.isUserParent = false, Validators.required],
      'isUserStudent': [this.data.isUserStudent = false, Validators.required],
      'classId': [this.data.classId]
    });


    this.classService.getClasses().subscribe(resp => {
      this.classes = resp;
    })
  }

  get f() {
    return this.userForm.controls;
  }


  onAdd(): void {
    this.submitted = true;

    if (this.userForm.invalid) {
      return;
    } else {
      this.userService.addUser(this.userForm.getRawValue()).subscribe(resp => {
          this.dialogRef.close();
        }, error1 => {
          if (error1.status == 406) {
            this.errMsg = 'Student needs a class';
          } else if (error1.status == 409) {
            this.errMsg = 'User needs at least one role';
          }
        }
      );
    }
  }

}
