import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPedidoCompraItemFornecedorComponent } from './add-pedido-compra-item-fornecedor.component';

describe('AddPedidoCompraItemFornecedorComponent', () => {
  let component: AddPedidoCompraItemFornecedorComponent;
  let fixture: ComponentFixture<AddPedidoCompraItemFornecedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPedidoCompraItemFornecedorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPedidoCompraItemFornecedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
