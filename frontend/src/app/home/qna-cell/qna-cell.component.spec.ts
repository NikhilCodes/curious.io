import { ComponentFixture, TestBed } from '@angular/core/testing';

import { QnaCellComponent } from './qna-cell.component';

describe('QnaCellComponent', () => {
  let component: QnaCellComponent;
  let fixture: ComponentFixture<QnaCellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ QnaCellComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(QnaCellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
