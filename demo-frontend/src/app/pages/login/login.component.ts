import {Component, Inject, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {LoginModel} from "../../models/login.model";
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from "@angular/material";

@Component({
  selector: 'login-component',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})


export class LoginComponent implements OnInit {
  errMsg: string = '';

  constructor(public dialogRef: MatDialogRef<LoginComponent>,
              @Inject(MAT_DIALOG_DATA) public data: LoginModel,
              private router: Router,
              private loginService: LoginService,
              public dialog: MatDialog) {
  }


  ngOnInit(): void {
    this.loginService.logout(false);
  }

  onLoginInClick(loginModel: LoginModel): void {
    this.loginService.getToken(loginModel).subscribe(resp => {
        if (resp.message == 'failure') {
          this.errMsg = 'Check your credentials or activate account.';
        } else if (resp.message == 'server_failure') {
          this.errMsg = 'Server error occurred.';
        } else if (resp.message == 'success') {
          this.dialogRef.close();
          this.router.navigate(['/dashboard']);
          location.reload();
        }
      }
    );
  }


}
