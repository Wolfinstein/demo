import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UserService} from "../../../services/user.service";
import {ClassService} from "../../../services/class.service";

@Component({
  selector: 'add-user-component',
  templateUrl: './add.user.component.html',
  styleUrls: ['./add.user.component.scss']
})


export class AddUserComponent {

  classes: any[];

  constructor(private userService: UserService,
              private classService: ClassService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<AddUserComponent>) {

    this.classService.getClasses().subscribe(resp => {
      this.classes = resp;
    })
  }


  onAdd(): void {
    console.log(this.data);
    this.userService.addUser(this.data).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }

}
