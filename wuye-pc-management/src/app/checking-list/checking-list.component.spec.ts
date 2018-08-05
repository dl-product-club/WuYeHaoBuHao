import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckingListComponent } from './checking-list.component';

describe('CheckingListComponent', () => {
  let component: CheckingListComponent;
  let fixture: ComponentFixture<CheckingListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CheckingListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CheckingListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
