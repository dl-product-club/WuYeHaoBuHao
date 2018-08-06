// import {Config} from "./config";
// import {BrowserDynamicTestingModule, platformBrowserDynamicTesting} from '@angular/platform-browser-dynamic/testing';
// import {async, TestBed} from "@angular/core/testing";
//
// window['ENV'] = {
//   environment: 'mock',
//   baseUrl: ''
// };
//
// describe('Mock环境服务测试', () => {
//   let service: Config;
//   beforeEach(async(() => {
//     TestBed.configureTestingModule({
//       providers: [
//         Config
//       ]
//     })
//       .compileComponents()
//       .then(() => {  });
//   }));
//
//   beforeEach(() => {
//     service = TestBed.get(Config);
//   });
//
//   it('环境服务 定义成功', () => {
//     expect(service).toBeDefined();
//   });
//
//   it('根据环境得到请求路径, mock环境下的login路径', () => {
//     service.Env='mockEnv';
//     let loginUrl = service.getEndpoint('login');
//     expect(loginUrl).toBe("assets/mock/login.json");
//   });
//
//   it('根据环境得到请求路径，dev环境下的login路径', () => {
//     service.Env = 'devEnv';
//     window['ENV'].baseUrl = 'http://120.78.81.170/recruit';
//     let loginUrl = service.getEndpoint('login');
//     expect(loginUrl).toBe("http://120.78.81.170/recruit/uaa/oauth/token");
//   });
// });
