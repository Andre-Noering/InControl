import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PessoasListItemComponent } from './pessoas-list-item.component';

describe('PessoasListItemComponent', () => {
  let component: PessoasListItemComponent;
  let fixture: ComponentFixture<PessoasListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PessoasListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PessoasListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
