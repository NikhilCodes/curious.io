import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AnswerCellComponent } from './answer-cell.component';

describe('AnswerCellComponent', () => {
  let component: AnswerCellComponent;
  let fixture: ComponentFixture<AnswerCellComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AnswerCellComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AnswerCellComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
