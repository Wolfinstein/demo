import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {OverlayContainer} from "@angular/cdk/overlay";
import {UserInfoService} from "./services/user-info.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  constructor(private router: Router,
              private overlayContainer: OverlayContainer,
              private userInfo: UserInfoService,) {
  }

  user: any = this.userInfo.getUserInfo();
  username: string = "";

  isLoggedIn: boolean = false;

  ngOnInit(): void {
    this.isLoggedIn = this.userInfo.isLoggedIn();
  }

  goToAccount() {
    this.username = this.userInfo.getUserInfo().displayName.replace(' ', '_');
    this.router.navigate(['account/' + this.username]);
  }


  isAdmin(): boolean {
    return this.user.role == 'ADMIN';
  }

  isStudent(): boolean {
    return this.user.role == 'STUDENT';
  }

  isTeacher(): boolean {
    return this.user.role == 'TEACHER';
  }

  isParent(): boolean {
    return this.user.role == 'PARENT';

  }

}
