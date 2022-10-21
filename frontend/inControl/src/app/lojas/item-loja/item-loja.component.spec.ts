import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ItemLojaComponent } from './item-loja.component';

describe('ItemLojaComponent', () => {
  let component: ItemLojaComponent;
  let fixture: ComponentFixture<ItemLojaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ItemLojaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ItemLojaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
