import { StartShopping } from './../../../classes/DTOs/StartShoppingDTO';
import { TestBed } from '@angular/core/testing';

import { CartService } from './cart-service';
import { StartShoppingResponse } from 'src/app/classes/DTOs/StartShoppingResponse';
import {
  HttpClientTestingModule,
  HttpTestingController,
} from '@angular/common/http/testing';

describe('CartService', () => {
  let service: CartService;
  let initCart: StartShopping;
  let response: StartShoppingResponse;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    initCart = {
      userId: '123',
      retailerId: 'xyz',
      budget: 0,
    };

    response = {
      cartId: 'cart-123',
      retailerName: 'test retailer',
      budget: 0,
      message: 'HAPPY SHOPPING',
    };

    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [CartService],
    });
    service = TestBed.inject(CartService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    initCart = {
      userId: '',
      retailerId: '',
      budget: 0,
    };

    response = {
      cartId: '',
      retailerName: '',
      budget: 0,
      message: '',
    };
    httpMock.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('Cart Initialization Tests', () => {
    it('should fail when userId is missing', () => {
      initCart.userId = '';

      service.initializeCart(initCart).subscribe({
        next: () => fail('should have failed'),
        error: (err) => {
          expect(err).toBeTruthy(); // ✅ ensure error exists
          expect(err.status).toBe(400); // ✅ now safe
          expect(err.error.message).toBe('USER ID IS REQUIRED');
        },
      });
      const req = httpMock.expectOne('http://localhost:5000/api/v1/cart/');

      expect(req.request.method).toBe('POST');

      req.flush(
        { message: 'USER ID IS REQUIRED' },
        { status: 400, statusText: 'Bad Request' },
      );
    });

    it('should fail when retailer Id is missing', () => {
      initCart.retailerId = '';

      service.initializeCart(initCart).subscribe({
        next: () => fail('should have failed'),
        error: (err) => {
          expect(err).toBeTruthy(); // ✅ ensure error exists
          expect(err.status).toBe(400); // ✅ now safe
          expect(err.error.message).toBe('RETAILER ID IS REQUIRED');
        },
      });
      const req = httpMock.expectOne('http://localhost:5000/api/v1/cart/');
      expect(req.request.method).toBe('POST');

      req.flush(
        { message: 'RETAILER ID IS REQUIRED' },
        { status: 400, statusText: 'Bad Request' },
      );
    });

    it('should fail when Budget is Negative', () => {
      initCart.budget = -100;

      service.initializeCart(initCart).subscribe({
        next: () => fail('should have failed'),
        error: (err) => {
          expect(err).toBeTruthy(); // ✅ ensure error exists
          expect(err.status).toBe(400); // ✅ now safe
          expect(err.error.message).toBe('BUDGET CANNOT BE NEGATIVE');
        },
      });
      const req = httpMock.expectOne('http://localhost:5000/api/v1/cart/');
      expect(req.request.method).toBe('POST');

      req.flush(
        { message: 'BUDGET CANNOT BE NEGATIVE' },
        { status: 400, statusText: 'Bad Request' },
      );
    });

    it('should initialize cart successfully', () => {
      service.initializeCart(initCart).subscribe((res) => {
        expect(res).toEqual(response);
      });

      const req = httpMock.expectOne('http://localhost:5000/api/v1/cart/');
      expect(req.request.method).toBe('POST');

      req.flush(response);
    });
  });
});
