import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { HttpRequest, HttpEventType, HttpResponse, HttpClient, HttpHeaders } from '@angular/common/http';
import { DomSanitizer } from '@angular/platform-browser';
import 'rxjs/add/observable/forkJoin';
import { BsModalService } from 'ngx-bootstrap/modal';
import { BsModalRef } from 'ngx-bootstrap/modal/bs-modal-ref.service';
import { Config } from '../../config/config';

@Component({
  selector: 'app-checking-list',
  templateUrl: './checking-list.component.html',
  styleUrls: ['./checking-list.component.scss']
})

export class CheckingListComponent implements OnInit {
  checkingList: any[];
  @ViewChild('errorModal') errorModal;
  @ViewChild('loadingModal') loadingModal;
  @ViewChild('previewModal') previewModal;
  bsModalRef: BsModalRef;
  modalHeader: string;
  modalContent: string;
  unsubscribeId: any;
  imageUrl: string;
  currentPage = 1;
  stateString = {
    1: '待审核'
  };

  constructor(private http: HttpClient,
              private sanitizer: DomSanitizer,
              private config: Config,
              private modalService: BsModalService) {
  }

  ngOnInit() {
    this.getCheckingList();
  }

  getCheckingList() {
    const communityId = 1;
    const size = 10;
    this.openModal(this.loadingModal, { ignoreBackdropClick: true });
    this.http.get(this.config.getEndpoint('checkingList') + `/${communityId}?page=${this.currentPage}&size=${size}`).subscribe((data: any) => {
      this.checkingList = data || [];
      setTimeout(() => {
        this.bsModalRef.hide();
      }, 500);
    }, () => {
      this.bsModalRef.hide();
      setTimeout(() => {
        this.modalHeader = '错误';
        this.modalContent = '审核列表获取失败';
        this.openModal(this.errorModal);
      }, 500);
    });
  }

  openModal(template: TemplateRef<any>, config?: any) {
    if (this.bsModalRef) {
      this.bsModalRef.hide();
    }
    this.bsModalRef = this.modalService.show(template, config);
    this.unsubscribeId = this.modalService.onHidden.subscribe((reason: string) => {
      this.unsubscribeId.unsubscribe();
    });
  }

  showImagePreview(image: string) {
    this.modalHeader = image;
    this.imageUrl = image;
    this.openModal(this.previewModal);
  }

  pageChanged(event: any): void {
    console.log('Page changed to: ' + event.page);
    console.log('Number items per page: ' + event.itemsPerPage);
  }
}
