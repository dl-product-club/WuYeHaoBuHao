import {Injectable} from '@angular/core';
import {environment} from '../environments/environment';

@Injectable()
export class Config {
  DEFAULT_APIS = {
    checkingList: 'audits/all'
  };

  MOCK_APIS = {
    login: 'assets/mock/login.json',
    logout: 'assets/mock/logout.json',
    checkingList: 'assets/mock/checking-list.json',
  };

  constructor() {
  }

  public getEndpoint(name: string) {

    const apis = environment.baseUrl === '' ? this.MOCK_APIS : this.DEFAULT_APIS;

    const path = apis[name];
    if (!path) {
      return this.MOCK_APIS[name];
    }
    return environment.baseUrl + path;
  }
}
