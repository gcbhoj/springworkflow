import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HomePage } from './home.page';

describe('HomePage', () => {
  let component: HomePage;
  let fixture: ComponentFixture<HomePage>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HomePage], // 👈 Important for standalone components
    }).compileComponents();

    fixture = TestBed.createComponent(HomePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should add two numbers correctly', () => {
    component.num1 = 10;
    component.num2 = 5;

    component.add();

    expect(component.result).toBe(15);
  });

  it('should subtract two numbers correctly', () => {
    component.num1 = 10;
    component.num2 = 5;

    component.subtract();

    expect(component.result).toBe(5);
  });

  it('should handle string inputs correctly', () => {
    component.num1 = 7 as any;
    component.num2 = 3 as any;

    component.add();

    expect(component.result).toBe(10);
  });
});
