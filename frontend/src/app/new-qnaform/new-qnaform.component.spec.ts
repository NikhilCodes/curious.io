import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewQNAFormComponent } from './new-qnaform.component';

describe('NewQNAFormComponent', () => {
  let component: NewQNAFormComponent;
  let fixture: ComponentFixture<NewQNAFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewQNAFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewQNAFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
