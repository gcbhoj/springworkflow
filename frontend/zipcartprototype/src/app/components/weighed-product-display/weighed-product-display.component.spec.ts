import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { WeighedProductDisplayComponent } from './weighed-product-display.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('WeighedProductDisplayComponent', () => {
  let component: WeighedProductDisplayComponent;
  let fixture: ComponentFixture<WeighedProductDisplayComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [IonicModule.forRoot(), HttpClientTestingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(WeighedProductDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
