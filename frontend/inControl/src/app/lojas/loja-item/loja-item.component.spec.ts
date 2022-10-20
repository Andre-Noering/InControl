import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LojaItemComponent } from './loja-item.component';

describe('LojaItemComponent', () => {
  let component: LojaItemComponent;
  let fixture: ComponentFixture<LojaItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LojaItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LojaItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
