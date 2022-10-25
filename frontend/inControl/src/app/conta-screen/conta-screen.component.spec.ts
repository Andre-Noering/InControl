import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContaScreenComponent } from './conta-screen.component';

describe('ContaScreenComponent', () => {
  let component: ContaScreenComponent;
  let fixture: ComponentFixture<ContaScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ContaScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContaScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
