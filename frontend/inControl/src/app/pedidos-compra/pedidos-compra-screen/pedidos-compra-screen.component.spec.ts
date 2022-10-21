import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PedidosCompraScreenComponent } from './pedidos-compra-screen.component';

describe('PedidosCompraScreenComponent', () => {
  let component: PedidosCompraScreenComponent;
  let fixture: ComponentFixture<PedidosCompraScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PedidosCompraScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PedidosCompraScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
