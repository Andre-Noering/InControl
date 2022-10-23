import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedidoCompraItemFornecedorListItemComponent } from './pedido-compra-item-fornecedor-list-item.component';

describe('PedidoCompraItemFornecedorListItemComponent', () => {
  let component: PedidoCompraItemFornecedorListItemComponent;
  let fixture: ComponentFixture<PedidoCompraItemFornecedorListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PedidoCompraItemFornecedorListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PedidoCompraItemFornecedorListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
