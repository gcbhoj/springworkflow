import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FruitsAndVegPage } from './fruits-and-veg.page';
import { ModalController } from '@ionic/angular';

describe('FruitsAndVegPage', () => {
  let component: FruitsAndVegPage;
  let fixture: ComponentFixture<FruitsAndVegPage>;

  const modalMock = {
    create: jasmine.createSpy('create').and.returnValue(
      Promise.resolve({
        present: jasmine.createSpy('present'),
        dismiss: jasmine.createSpy('dismiss'),
      }),
    ),
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FruitsAndVegPage],
    })
      .overrideProvider(ModalController, { useValue: modalMock })
      .compileComponents();
    fixture = TestBed.createComponent(FruitsAndVegPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
