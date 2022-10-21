import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FuncionarioScreenComponent } from './funcionario-screen.component';

describe('FuncionarioScreenComponent', () => {
  let component: FuncionarioScreenComponent;
  let fixture: ComponentFixture<FuncionarioScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FuncionarioScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FuncionarioScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
