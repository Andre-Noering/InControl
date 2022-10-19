import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LojasScreenComponent } from './lojas-screen.component';

describe('LojasScreenComponent', () => {
  let component: LojasScreenComponent;
  let fixture: ComponentFixture<LojasScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LojasScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LojasScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
