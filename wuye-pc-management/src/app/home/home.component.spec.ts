import {async, ComponentFixture, TestBed} from '@angular/core/testing';
import {HomeComponent} from './home.component';
import {UploadContactComponent} from '../upload-contact/upload-contact.component';
import {UploadMenuComponent} from '../upload-menu/upload-menu.component';
import {UploadMenuImageComponent} from '../upload-menu-image/upload-menu-image.component';
import {NotifyComponent} from '../notify/notify.component';
import {RouterOutletStubComponent} from '../../testing';
import {FormsModule} from '@angular/forms';
export class AccordionConfig {
  closeOthers = true;
}

describe('首页页面测试', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [
        FormsModule
      ],
      declarations: [HomeComponent,
        RouterOutletStubComponent,
        UploadContactComponent,
        UploadMenuComponent,
        UploadMenuImageComponent,
        NotifyComponent],
      providers: []
    });
  }));
  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('首页构建成功', () => {
    expect(component).toBeDefined();
  });

  it('首页初始化', () => {
    component.ngOnInit();
    expect(component.ngOnInit).toBeDefined();
  });

  it('点击菜谱，展开菜谱功能列表', () => {
    component.accordionChanged(0);
    expect(component.accordionChanged).toBeDefined();
  });

  it('点击通讯录，展开通讯录功能列表', () => {
    component.accordionChanged(1);
    expect(component.accordionChanged).toBeDefined();
  });

  it('点击公告，展开公告功能列表', () => {
    component.accordionChanged(2);
    expect(component.accordionChanged).toBeDefined();
  });
});
