import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddItemFornecedorComponent } from './add-item-fornecedor.component';

describe('AddItemFornecedorComponent', () => {
  let component: AddItemFornecedorComponent;
  let fixture: ComponentFixture<AddItemFornecedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddItemFornecedorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddItemFornecedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
