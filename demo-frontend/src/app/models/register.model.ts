export class RegisterModel {
  constructor(public login: string,
              public password: string,
              public passwordRepeated: string,
              public email: string,
              public name: string,
              public surname: string) {
  }
}
