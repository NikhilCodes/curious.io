import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAnswerComponent } from './create-answer.component';

describe('CreateAnswerComponent', () => {
  let component: CreateAnswerComponent;
  let fixture: ComponentFixture<CreateAnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateAnswerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
