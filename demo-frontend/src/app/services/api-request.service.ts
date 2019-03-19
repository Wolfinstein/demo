import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {Router} from '@angular/router';
import {Observable} from 'rxjs';
import {UserInfoService} from "./user-info.service";
import {AppConfig} from "../config/app-config";


@Injectable()
export class ApiRequestService {


  constructor(private appConfig: AppConfig,
              private http: HttpClient,
              private router: Router,
              private userInfoService: UserInfoService) {
  }


  getHeaders(): HttpHeaders {
    let headers = new HttpHeaders();
    let token = this.userInfoService.getStoredToken();
    headers = headers.append('Content-Type', 'application/json');
    if (token !== null) {
      headers = headers.append("Authorization", token);
    }
    return headers;
  }

  getHeadersForUpload(): HttpHeaders {
    let headers = new HttpHeaders();
    let token = this.userInfoService.getStoredToken();
    if (token !== null) {
      headers = headers.append("Authorization", token);
    }
    return headers;
  }


  get(url: string, params?: HttpParams): Observable<any> {
    return this.http.get(this.appConfig.baseApiPath + url, {headers: this.getHeaders(), params: params})
  }


  download(url: string, params?: HttpParams): Observable<any> {
    return this.http.get(this.appConfig.baseApiPath + url, {
      responseType: 'blob',
      headers: this.getHeadersForUpload(),
      params: params
    })
  }

  post(url: string, body: Object, params?: HttpParams): Observable<any> {
    return this.http.post(this.appConfig.baseApiPath + url, JSON.stringify(body), {
      headers: this.getHeaders(),
      params: params
    })
  }


  put(url: string, body: Object): Observable<any> {
    let me = this;
    return this.http.put(this.appConfig.baseApiPath + url, JSON.stringify(body), {headers: this.getHeaders()})

  }

  upload(url: string, body: Object, params?: HttpParams): Observable<any> {
    let me = this;
    return this.http.post(this.appConfig.baseApiPath + url, body, {headers: this.getHeadersForUpload(), params: params})

  }


  delete(url: string): Observable<any> {
    let me = this;
    return this.http.delete(this.appConfig.baseApiPath + url, {headers: this.getHeaders(), observe: 'response'})

  }

}
