import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FornecedorListItemComponent } from './fornecedor-list-item.component';

describe('FornecedorListItemComponent', () => {
  let component: FornecedorListItemComponent;
  let fixture: ComponentFixture<FornecedorListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FornecedorListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FornecedorListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
