import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { BarcodescannerComponent } from './barcodescanner.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('BarcodescannerComponent', () => {
  let component: BarcodescannerComponent;
  let fixture: ComponentFixture<BarcodescannerComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [IonicModule.forRoot(), HttpClientTestingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(BarcodescannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
