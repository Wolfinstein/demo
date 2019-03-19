import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {OverlayContainer} from "@angular/cdk/overlay";
import {UserInfoService} from "./services/user-info.service";
import {UserService} from "./services/user.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  constructor(private router: Router,
              private userService: UserService,
              private overlayContainer: OverlayContainer,
              private userInfo: UserInfoService,) {
  }

  user: any = this.userInfo.getUserInfo();
  username: string = "";

  empty: boolean;


  isLoggedIn: boolean = false;

  ngOnInit(): void {
    this.isLoggedIn = this.userInfo.isLoggedIn();
    this.userService.isNew(Number(this.userInfo.getUserInfo().userId)).subscribe(rsep => {
      this.empty = rsep;
    })
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
