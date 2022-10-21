import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddItemVendaComponent } from './add-item-venda.component';

describe('AddItemVendaComponent', () => {
  let component: AddItemVendaComponent;
  let fixture: ComponentFixture<AddItemVendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddItemVendaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddItemVendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
