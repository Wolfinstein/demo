import {Component} from "@angular/core";
import {MatDialog} from "@angular/material";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../../services/login.service";
import {UserInfoService} from "../../services/user-info.service";

@Component({
  selector: 'dashboard-component',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})

export class DashboardComponent {


  constructor() {
  }
}
