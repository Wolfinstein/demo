import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {BehaviorSubject, Observable} from 'rxjs';
import {ApiRequestService} from './api-request.service';
import {LoginInfoInStorage, UserInfoService} from "./user-info.service";
import {LoginModel} from "../models/login.model";

export interface LoginRequestParam {
  username: string;
  password: string;
}

@Injectable()
export class LoginService {

  public landingPage: string = "/home";

  constructor(private router: Router,
              private userInfoService: UserInfoService,
              private apiRequest: ApiRequestService) {
  }

  getToken(loginModel: LoginModel): Observable<any> {

    let bodyData: LoginRequestParam = {
      "username": loginModel.username,
      "password": loginModel.password,
    };

    let loginDataSubject: BehaviorSubject<any> = new BehaviorSubject<any>([]);
    let loginInfoReturn: LoginInfoInStorage;

    this.apiRequest.post('session', bodyData)
      .subscribe(response => {
          console.log(response);
          if (response.status == 200) {
            loginInfoReturn = {
              "success": true,
              "message": 'success',
              "landingPage": this.landingPage,
              "user": {
                "userId": response.body.item.userId,
                "email": response.body.item.email,
                "displayName": response.body.item.firstName + " " + response.body.item.lastName,
                "token": response.body.item.token,
                "role": response.body.item.role,
                "isUserTeacher": response.body.item.userTeacher,
                "isUserStudent": response.body.item.userStudent,
                "isUserParent": response.body.item.userParent
              }
            };
            this.userInfoService.storeUserInfo(JSON.stringify(loginInfoReturn.user));
          } else {
            loginInfoReturn = {
              "success": false,
              "message": "server_failure",
              "landingPage": '/login'
            };
          }
          loginDataSubject.next(loginInfoReturn);
        },
        err => {
          loginInfoReturn = {
            "success": false,
            "message": 'failure',
            "landingPage": '/login'
          };
          loginDataSubject.next(loginInfoReturn);
        });
    return loginDataSubject;
  }

  logout(navigatetoLogout = true): void {
    this.userInfoService.removeUserInfo();
    if (navigatetoLogout) {
      this.router.navigate(["logout"]);
    }
  }


}
