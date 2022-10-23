import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendasFuncionarioScreenComponent } from './vendas-funcionario-screen.component';

describe('VendasFuncionarioScreenComponent', () => {
  let component: VendasFuncionarioScreenComponent;
  let fixture: ComponentFixture<VendasFuncionarioScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VendasFuncionarioScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendasFuncionarioScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
