import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemVendaListItemComponent } from './item-venda-list-item.component';

describe('ItemVendaListItemComponent', () => {
  let component: ItemVendaListItemComponent;
  let fixture: ComponentFixture<ItemVendaListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemVendaListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemVendaListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
