import {Component, OnInit, OnDestroy, ViewChild, TemplateRef} from '@angular/core';
import {Router} from '@angular/router';
import {HttpClient} from '@angular/common/http';
import {BsModalService} from 'ngx-bootstrap/modal';
import {BsModalRef} from 'ngx-bootstrap/modal/bs-modal-ref.service';
import {Config} from '../../config/config';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit, OnDestroy {
  bsModalRef: BsModalRef;
  @ViewChild('uploadModal') uploadModal;
  modalHeader: string;
  modalContent: string;
  access = {
    name: '',
    password: ''
  };

  constructor(private router: Router,
              private http: HttpClient,
              private config: Config,
              private modalService: BsModalService) {
  }

  ngOnInit() {
  }

  ngOnDestroy() {
  }

  openModal(template: TemplateRef<any>) {
    this.bsModalRef = this.modalService.show(template);
  }

  login() {
    console.log('login');
    this.http.get(this.config.getEndpoint('login')).subscribe(() => {
      this.router.navigateByUrl('/home');
    }, () => {
      this.modalHeader = '错误';
      this.modalContent = '登录失败';
      this.openModal(this.uploadModal);
    });
  }
}
