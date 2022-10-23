import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPedidoCompraComponent } from './add-pedido-compra.component';

describe('AddPedidoCompraComponent', () => {
  let component: AddPedidoCompraComponent;
  let fixture: ComponentFixture<AddPedidoCompraComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddPedidoCompraComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddPedidoCompraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
