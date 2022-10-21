import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendaListItemComponent } from './venda-list-item.component';

describe('VendaListItemComponent', () => {
  let component: VendaListItemComponent;
  let fixture: ComponentFixture<VendaListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VendaListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendaListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
