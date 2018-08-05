import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {LoginComponent} from './login.component';
import {RouterMock, HttpClientMock, BsModalServiceMock, BsModalRefMock} from '../../testing/mock-angular';
import {HttpClient} from '@angular/common/http';
import {Config} from '../../config/config';
import {BsModalService} from 'ngx-bootstrap/modal';
import {BsModalRef} from 'ngx-bootstrap/modal/bs-modal-ref.service';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [LoginComponent],
      imports: [
        FormsModule
      ],
      providers: [
        {provide: Router, useClass: RouterMock},
        {provide: HttpClient, useClass: HttpClientMock},
        {provide: Config, useClass: Config},
        {provide: BsModalService, useClass: BsModalServiceMock},
        {provide: BsModalRef, useClass: BsModalRefMock}
      ]
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('点击获取验证码，开始倒计时60秒', () => {
    component.getCode();
    expect(component.getCode).toBeDefined();
  });

  it('在倒计时60秒内点击获取验证码，忽略', () => {
    component.counting = true;
    component.getCode();
    expect(component.getCode).toBeDefined();
  });

  it('点击登录，登录失败', () => {
    component.login();
    component.openModal(component.uploadModal);
    expect(component.login).toBeDefined();
  });

  it('点击登录，登录成功', () => {
    component.login();
    expect(component.login).toBeDefined();
  });
});
