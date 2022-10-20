import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddLojaComponent } from './add-loja.component';

describe('AddLojaComponent', () => {
  let component: AddLojaComponent;
  let fixture: ComponentFixture<AddLojaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddLojaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddLojaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
