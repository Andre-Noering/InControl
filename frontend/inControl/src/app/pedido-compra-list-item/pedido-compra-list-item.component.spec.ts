import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedidoCompraListItemComponent } from './pedido-compra-list-item.component';

describe('PedidoCompraListItemComponent', () => {
  let component: PedidoCompraListItemComponent;
  let fixture: ComponentFixture<PedidoCompraListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PedidoCompraListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PedidoCompraListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
