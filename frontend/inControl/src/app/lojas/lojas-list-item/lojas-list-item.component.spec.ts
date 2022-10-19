import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LojasListItemComponent } from './lojas-list-item.component';

describe('LojasListItemComponent', () => {
  let component: LojasListItemComponent;
  let fixture: ComponentFixture<LojasListItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LojasListItemComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(LojasListItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
