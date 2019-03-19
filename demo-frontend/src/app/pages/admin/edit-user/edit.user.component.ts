import {Component, Inject} from "@angular/core";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {UserService} from "../../../services/user.service";

@Component({
  selector: 'user-edit-component',
  templateUrl: './edit.user.component.html',
  styleUrls: ['./edit.user.component.scss']
})


export class EditUserComponent {

  constructor(private userService: UserService,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public dialogRef: MatDialogRef<EditUserComponent>) {

  }

  editUser(): void {
    console.log(this.data.data);
    this.userService.editUser(this.data.data.id, this.data.data).subscribe(resp => {
        this.dialogRef.close();
      }
    );
  }

}
