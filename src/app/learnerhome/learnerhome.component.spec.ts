import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LearnerHomeComponent } from './learnerhome.component';

describe('LearnerhomeComponent', () => {
  let component: LearnerHomeComponent;
  let fixture: ComponentFixture<LearnerHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LearnerHomeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LearnerHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
