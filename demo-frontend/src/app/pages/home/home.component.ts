import {Component} from "@angular/core";
import {MatDialog} from "@angular/material";
import {LoginModel} from "../../models/login.model";
import {ActivatedRoute, NavigationEnd, Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {UserInfoService} from "../../services/user-info.service";
import {filter, map, mergeMap} from "rxjs/operators";
import {LoginComponent} from "../login/login.component";

@Component({
  selector: 'home-component',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})

export class HomeComponent {

  public selectedHeaderItemIndex: number = 0;
  public selectedSubNavItemIndex: number = 1;
  public userName: string = "";

  constructor(public dialog: MatDialog,
              private router: Router,
              private activeRoute: ActivatedRoute,
              private loginService: LoginService,
              private userInfoService: UserInfoService) {

    this.router.events.pipe(
      filter((event) => event instanceof NavigationEnd),
      map(_ => this.router.routerState.root),
      map(route => {
        while (route.firstChild) route = route.firstChild;
        return route;
      }),
      mergeMap(route => route.data)
    ).subscribe(data => {
      this.selectedHeaderItemIndex = data[0] ? data[0].selectedHeaderItemIndex : -1;
      this.selectedSubNavItemIndex = data[0] ? data[0].selectedSubNavItemIndex : -1;
    });

    this.userName = this.userInfoService.getUserName();
  }

  ngOnInit() {

    if (this.userInfoService.isLoggedIn()) {
      this.router.navigate(['/dashboard']);
    }
  }

  loginPopup() {
    let dialogRef = this.dialog.open(LoginComponent, {
      panelClass: 'login-popup-dialog',
      data: {loginModel: LoginModel}
    });

    dialogRef.afterClosed().subscribe(result => {
    });
  }


}
