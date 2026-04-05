import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { BarcodescannerComponent } from './barcodescanner.component';

describe('BarcodescannerComponent', () => {
  let component: BarcodescannerComponent;
  let fixture: ComponentFixture<BarcodescannerComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [BarcodescannerComponent, IonicModule.forRoot()],
    }).compileComponents();

    fixture = TestBed.createComponent(BarcodescannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
