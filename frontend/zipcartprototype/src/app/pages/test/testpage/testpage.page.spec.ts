import { ComponentFixture, TestBed } from '@angular/core/testing';
import { TestpagePage } from './testpage.page';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { IonicModule } from '@ionic/angular';

describe('TestpagePage', () => {
  let component: TestpagePage;
  let fixture: ComponentFixture<TestpagePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        TestpagePage, // standalone page
        IonicModule.forRoot(), // Ionic
        HttpClientTestingModule, // ✅ Provides HttpClient
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(TestpagePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
