import { TestBed, async } from '@angular/core/testing';

import { AppComponent } from './app.component';
import { RouterOutletStubComponent } from '../testing';

describe('网页测试', () => {
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [
        AppComponent, RouterOutletStubComponent
      ],
    }).compileComponents();
  }));

  it('单页应用构建成功', async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app).toBeTruthy();
  }));
});
