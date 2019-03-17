import {Component, HostBinding} from '@angular/core';
import {Router} from "@angular/router";
import {OverlayContainer} from "@angular/cdk/overlay";
import {UserInfoService} from "./services/user-info.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  constructor(private router: Router,
              private overlayContainer: OverlayContainer,
              private userInfo: UserInfoService,) {
  }

  username: string = "";

  IsLoggedIn(): boolean {
    return this.userInfo.isLoggedIn();
  };

  goToAccount() {
    this.username = this.userInfo.getUserInfo().displayName.replace(' ', '_');
    this.router.navigate(['account/' + this.username]);
  }


}
