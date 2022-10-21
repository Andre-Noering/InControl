import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemVendaScreenComponent } from './item-venda-screen.component';

describe('ItemVendaScreenComponent', () => {
  let component: ItemVendaScreenComponent;
  let fixture: ComponentFixture<ItemVendaScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemVendaScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemVendaScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
