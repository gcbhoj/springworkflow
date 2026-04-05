import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ScanitemsPage } from './scanitems.page';
import { IonicModule } from '@ionic/angular';
import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('ScanitemsPage', () => {
  let component: ScanitemsPage;
  let fixture: ComponentFixture<ScanitemsPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        ScanitemsPage, // standalone page
        IonicModule.forRoot(), // Ionic
        HttpClientTestingModule, // ✅ Provides HttpClient
      ],
    }).compileComponents();
    fixture = TestBed.createComponent(ScanitemsPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
