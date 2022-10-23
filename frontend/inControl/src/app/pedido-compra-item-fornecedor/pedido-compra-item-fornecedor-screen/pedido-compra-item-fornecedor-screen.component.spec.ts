import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedidoCompraItemFornecedorScreenComponent } from './pedido-compra-item-fornecedor-screen.component';

describe('PedidoCompraItemFornecedorScreenComponent', () => {
  let component: PedidoCompraItemFornecedorScreenComponent;
  let fixture: ComponentFixture<PedidoCompraItemFornecedorScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PedidoCompraItemFornecedorScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PedidoCompraItemFornecedorScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
