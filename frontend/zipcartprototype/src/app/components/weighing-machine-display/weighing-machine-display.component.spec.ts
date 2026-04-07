import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { WeighingMachineDisplayComponent } from './weighing-machine-display.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('WeighingMachineDisplayComponent', () => {
  let component: WeighingMachineDisplayComponent;
  let fixture: ComponentFixture<WeighingMachineDisplayComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [IonicModule.forRoot(), HttpClientTestingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(WeighingMachineDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
