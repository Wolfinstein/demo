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
          if (response !== undefined && response !== null && response.operationStatus === "SUCCESS") {
            loginInfoReturn = {
              "success": true,
              "message": 'success',
              "landingPage": this.landingPage,
              "user": {
                "userId": response.item.userId,
                "email": response.item.email,
                "displayName": response.item.firstName + " " + response.item.lastName,
                "token": response.item.token,
                "role": response.item.role,
                "isUserTeacher": response.item.userTeacher,
                "isUserStudent": response.item.userStudent,
                "isUserParent": response.item.userParent
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
