import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { ViewalluserComponent } from './viewalluser.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ViewalluserComponent', () => {
  let component: ViewalluserComponent;
  let fixture: ComponentFixture<ViewalluserComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [IonicModule.forRoot(), HttpClientTestingModule],
    }).compileComponents();

    fixture = TestBed.createComponent(ViewalluserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
