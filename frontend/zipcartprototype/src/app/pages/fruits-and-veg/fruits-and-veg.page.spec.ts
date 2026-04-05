import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FruitsAndVegPage } from './fruits-and-veg.page';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { IonicModule } from '@ionic/angular';

describe('FruitsAndVegPage', () => {
  let component: FruitsAndVegPage;
  let fixture: ComponentFixture<FruitsAndVegPage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        FruitsAndVegPage, // standalone page
        IonicModule.forRoot(), // Ionic
        HttpClientTestingModule, // ✅ Provides HttpClient
      ],
    }).compileComponents();
    fixture = TestBed.createComponent(FruitsAndVegPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
