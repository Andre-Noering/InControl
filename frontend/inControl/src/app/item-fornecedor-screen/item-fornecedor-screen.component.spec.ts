import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemFornecedorScreenComponent } from './item-fornecedor-screen.component';

describe('ItemFornecedorScreenComponent', () => {
  let component: ItemFornecedorScreenComponent;
  let fixture: ComponentFixture<ItemFornecedorScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemFornecedorScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemFornecedorScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
