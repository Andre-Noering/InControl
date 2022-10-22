import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemFornecedorListITemComponent } from './item-fornecedor-list-item.component';

describe('ItemFornecedorListITemComponent', () => {
  let component: ItemFornecedorListITemComponent;
  let fixture: ComponentFixture<ItemFornecedorListITemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemFornecedorListITemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemFornecedorListITemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
