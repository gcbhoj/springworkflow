import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';
import { Camera } from '@capacitor/camera';
import { CameraComponent } from './camera.component';

const cameraMock = {
  getPhoto: jasmine.createSpy('getPhoto').and.returnValue(
    Promise.resolve({
      base64String: 'mock-image',
      path: 'mock-path',
      format: 'jpeg',
    }),
  ),
};

describe('CameraComponent', () => {
  let component: CameraComponent;
  let fixture: ComponentFixture<CameraComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [],
      imports: [CameraComponent, IonicModule.forRoot()],
      providers: [{ provide: Camera, useValue: cameraMock }],
    }).compileComponents();

    fixture = TestBed.createComponent(CameraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', async () => {
    await fixture.whenStable(); // wait for async tasks to finish
    expect(component).toBeTruthy();
  });
});
