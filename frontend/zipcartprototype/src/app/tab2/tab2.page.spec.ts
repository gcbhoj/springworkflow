import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Tab2Page } from './tab2.page';
import { ModalController } from '@ionic/angular';

describe('Tab2Page', () => {
  let component: Tab2Page;
  let fixture: ComponentFixture<Tab2Page>;

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
      imports: [Tab2Page], // standalone component
    })
      .overrideProvider(ModalController, { useValue: modalMock }) // ✅ FORCE override
      .compileComponents();

    fixture = TestBed.createComponent(Tab2Page);
    component = fixture.componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
