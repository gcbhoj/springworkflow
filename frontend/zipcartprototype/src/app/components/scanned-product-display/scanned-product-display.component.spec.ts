import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ScannedProductDisplayComponent } from './scanned-product-display.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ScannedProductDisplayComponent', () => {
  let component: ScannedProductDisplayComponent;
  let fixture: ComponentFixture<ScannedProductDisplayComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [IonicModule.forRoot(), HttpClientTestingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(ScannedProductDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
