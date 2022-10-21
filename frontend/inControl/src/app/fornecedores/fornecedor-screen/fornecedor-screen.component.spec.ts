import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FornecedorScreenComponent } from './fornecedor-screen.component';

describe('FornecedorScreenComponent', () => {
  let component: FornecedorScreenComponent;
  let fixture: ComponentFixture<FornecedorScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FornecedorScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FornecedorScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
