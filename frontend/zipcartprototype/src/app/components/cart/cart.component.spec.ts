import { Cart } from './../../classes/Models/Cart';
import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { CartComponent } from './cart.component';
import { PackagedProduct } from 'src/app/classes/Models/PackagedProduct';
import { UnPackagedProduct } from 'src/app/classes/Models/UnPackagedProduct';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;
  let cart: Cart;
  let pkgProduct: PackagedProduct;
  let unPkgProduct: UnPackagedProduct;

  beforeEach(waitForAsync(() => {
    pkgProduct = {
      productId: '1a1',
      productName: 'test packaged product',
      itemNumber: '3ax',
      imageUrl: '123456',
      quantity: 1,
      unitPrice: 10,
    } as PackagedProduct;

    unPkgProduct = {
      productId: '1b1',
      productName: 'test unpackaged product',
      itemNumber: '5by',
      weight: 10,
      unitPrice: 5,
    } as UnPackagedProduct;
    cart = {
      cartId: '123',
      userId: 'abc',
      packagedProductList: [pkgProduct],
      unpackagedProductList: [unPkgProduct],
      hst: 0,
      totalAmount: 0,
    } as Cart;
    TestBed.configureTestingModule({
      declarations: [],
      imports: [CartComponent, IonicModule.forRoot()],
    }).compileComponents();

    fixture = TestBed.createComponent(CartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  afterEach(() => {
    pkgProduct = {} as PackagedProduct;
    unPkgProduct = {} as UnPackagedProduct;
    cart = {} as Cart;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
