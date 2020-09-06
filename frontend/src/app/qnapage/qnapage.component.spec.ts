import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QNAPageComponent } from './qnapage.component';

describe('QNAPageComponent', () => {
  let component: QNAPageComponent;
  let fixture: ComponentFixture<QNAPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QNAPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QNAPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
