import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VendaScreenComponent } from './venda-screen.component';

describe('VendaScreenComponent', () => {
  let component: VendaScreenComponent;
  let fixture: ComponentFixture<VendaScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VendaScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(VendaScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
