import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SobreNosScreenComponent } from './sobre-nos-screen.component';

describe('SobreNosScreenComponent', () => {
  let component: SobreNosScreenComponent;
  let fixture: ComponentFixture<SobreNosScreenComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SobreNosScreenComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SobreNosScreenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
