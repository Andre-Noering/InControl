import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedidosCompraFuncionarioScreenComponent } from './pedidos-compra-funcionario-screen.component';

describe('PedidosCompraFuncionarioScreenComponent', () => {
  let component: PedidosCompraFuncionarioScreenComponent;
  let fixture: ComponentFixture<PedidosCompraFuncionarioScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PedidosCompraFuncionarioScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PedidosCompraFuncionarioScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
