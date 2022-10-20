import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FuncionarioListItemComponent } from './funcionario-list-item.component';

describe('FuncionarioListItemComponent', () => {
  let component: FuncionarioListItemComponent;
  let fixture: ComponentFixture<FuncionarioListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FuncionarioListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FuncionarioListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
