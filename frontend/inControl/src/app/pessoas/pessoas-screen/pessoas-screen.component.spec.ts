import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoasScreenComponent } from './pessoas-screen.component';

describe('PessoasScreenComponent', () => {
  let component: PessoasScreenComponent;
  let fixture: ComponentFixture<PessoasScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PessoasScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PessoasScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
