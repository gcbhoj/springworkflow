import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { UnpackageditemComponent } from './unpackageditem.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('UnpackageditemComponent', () => {
  let component: UnpackageditemComponent;
  let fixture: ComponentFixture<UnpackageditemComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [IonicModule.forRoot(), HttpClientTestingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(UnpackageditemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
