import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddVendaComponent } from './add-venda.component';

describe('AddVendaComponent', () => {
  let component: AddVendaComponent;
  let fixture: ComponentFixture<AddVendaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddVendaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddVendaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
