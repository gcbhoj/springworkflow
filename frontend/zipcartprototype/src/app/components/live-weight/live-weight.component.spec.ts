import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { LiveWeightComponent } from './live-weight.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('LiveWeightComponent', () => {
  let component: LiveWeightComponent;
  let fixture: ComponentFixture<LiveWeightComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [IonicModule.forRoot(), HttpClientTestingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(LiveWeightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
