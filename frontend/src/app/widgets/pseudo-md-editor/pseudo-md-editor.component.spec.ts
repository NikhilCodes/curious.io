import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PseudoMdEditorComponent } from './pseudo-md-editor.component';

describe('PseudoMdEditorComponent', () => {
  let component: PseudoMdEditorComponent;
  let fixture: ComponentFixture<PseudoMdEditorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PseudoMdEditorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PseudoMdEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
