import {Injectable} from '@angular/core';
import {ApiRequestService} from "./api-request.service";

@Injectable() @Injectable()
export class UserService {

  constructor(private apiRequest: ApiRequestService) {
  }

}
