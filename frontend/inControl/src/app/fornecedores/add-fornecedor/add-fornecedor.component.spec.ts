import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFornecedorComponent } from './add-fornecedor.component';

describe('AddFornecedorComponent', () => {
  let component: AddFornecedorComponent;
  let fixture: ComponentFixture<AddFornecedorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddFornecedorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddFornecedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
