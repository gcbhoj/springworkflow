import { ProductService } from './../../services/springServices/productServices/product-service';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { BarcodescannerComponent } from 'src/app/components/barcodescanner/barcodescanner.component';
import { Datasharing } from 'src/app/services/datasharing/datasharing';
import { BarCodeScannerResult } from 'src/app/classes/DTOs/BarCodeScannerResultDTO';
import { ScannedProductDisplayComponent } from 'src/app/components/scanned-product-display/scanned-product-display.component';
import { ProductInformation } from 'src/app/classes/Models/PackagedProductInformation';
import { Router } from '@angular/router';
import { StartShoppingResponse } from 'src/app/classes/DTOs/StartShoppingResponse';
import { ToastServices } from 'src/app/services/toastService/toast-services';
import { PackagedProductRequests } from 'src/app/classes/DTOs/PackagedProductRequests';
import { IonicModule } from '@ionic/angular';
import { Subject, takeUntil } from 'rxjs';
import { CalculatorService } from 'src/app/services/calculatorService/calculator-service';
import { CartService } from 'src/app/services/springServices/cartServices/cart-service';

@Component({
  selector: 'app-scanitems',
  templateUrl: './scanitems.page.html',
  styleUrls: ['./scanitems.page.scss'],
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    BarcodescannerComponent,
    ScannedProductDisplayComponent,
    IonicModule,
  ],
})
export class ScanitemsPage implements OnInit, OnDestroy {
  private destroy$ = new Subject<void>();
  addItemsToCartButtonEnabled: boolean = true;

  productDisplayed = false;
  packagedProduct: ProductInformation = {
    itemNumber: '',
    productName: '',
    imageURL: '',
    price: 0,
    weight: '',
    ingredients: [],
    manufacturedDate: new Date(),
    expiryDate: new Date(),
    manufacturer: '',
    manufacturedIn: '',
    aboutProduct: '',
    quantity: 0,
  };

  cartInitResponse: StartShoppingResponse = {
    cartId: '',
    retailerName: '',
    budget: 0,
    message: '',
  };

  scannedPackagedProductRequest: PackagedProductRequests = {
    cartId: '',
    itemNumber: '',
  };

  //barcodes of mock data stored in mock server NOTE:FOR TESTING PURPOSES ONLY

  barcodes: string[] = ['5000112546415', '049000050158', '049000028911'];

  barCodeResults: BarCodeScannerResult = {
    _isValid: true,
    upc: '',
    format: '',
    contentType: '',
  };

  constructor(
    private dataSharing: Datasharing,
    private productService: ProductService,
    private router: Router,
    private toast: ToastServices,
    private calculator: CalculatorService,
    private cartServices: CartService,
  ) {}

  ngOnDestroy(): void {
    // ensure any subscribers using takeUntil(this.destroy$) are cleaned up
    this.destroy$.next();
    this.destroy$.complete();
  }

  ngOnInit() {
    this.receiveBarcodeDetails();
    this.receiveCartInitResponse();
  }
  /**
   * DATA SHARING
   */

  receiveBarcodeDetails() {
    this.dataSharing.barcodeDetails
      .pipe(takeUntil(this.destroy$))
      .subscribe((data) => {
        if (data) {
          this.barCodeResults = data;
        }
      });
  }

  //sharing the received packaged product information to display in its component
  sharePackagedProductInformation() {
    if (this.packagedProduct) {
      this.dataSharing.exchangePackagedProductInformation(this.packagedProduct);
      this.scannedPackagedProductRequest.itemNumber =
        this.packagedProduct.itemNumber;
    }
  }

  // receiving cart initialization response
  receiveCartInitResponse() {
    this.dataSharing.startShoppingResponseDetails
      .pipe(takeUntil(this.destroy$))
      .subscribe((data) => {
        if (data) {
          this.cartInitResponse = data;
          this.scannedPackagedProductRequest.cartId =
            this.cartInitResponse.cartId;
        }
      });
  }

  /**
   *  API REQUESTS
   */

  // posting the received bar code to receive the product details
  sendBarCode(results: BarCodeScannerResult) {
    this.productService
      .getProductByBarCode(results)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (data) => {
          this.packagedProduct = data;
          // initializing the share method
          this.sharePackagedProductInformation();
          //disables the scanner
          this.onProductLoaded();
        },
        error: (err) => {
          const message =
            err?.error?.message ||
            'FAILED TO GET PRODUCT BY SCANNING. PLEASE TRY AGAIN';
          this.toast.showError(message);
        },
      });
  }

  //Adding the scanned items to cart
  addScannedItemToCart() {
    this.cartServices
      .addPackagedProductToCart(this.scannedPackagedProductRequest)
      .pipe(takeUntil(this.destroy$))
      .subscribe({
        next: (response) => {
          this.toast.showSuccess(response.result);

          this.cartServices.getCartByCartId(this.cartInitResponse.cartId);
          this.removeScannedItem();
        },
        error: (err) => {
          const message = err?.error?.message || 'FAILED TO ADD ITEM TO CART';
          this.toast.showError(message);
        },
      });
  }

  /**
   * FUNCTIONALITIES
   */

  // calling product services with barcode

  getProductDetails() {
    this.sendBarCode(this.barCodeResults);
  }

  //removing the scanned item when cancel is pressed
  removeScannedItem() {
    //creating a empty object
    const emptyProduct: ProductInformation = {
      itemNumber: '',
      productName: '',
      imageURL: '',
      price: 0,
      weight: '',
      ingredients: [],
      manufacturedDate: new Date(),
      expiryDate: new Date(),
      manufacturer: '',
      manufacturedIn: '',
      aboutProduct: '',
      quantity: 0,
    };

    this.packagedProduct = emptyProduct;

    // update shared observable
    this.dataSharing.exchangePackagedProductInformation(emptyProduct);

    this.scannedPackagedProductRequest = {
      cartId: this.cartInitResponse.cartId,
      itemNumber: '',
    };

    // enabling the scanner
    this.onProductCleared();
    this.addItemsToCartButtonEnabled = false;
  }

  // navigate to home page
  goToHomePage() {
    this.router.navigate(['/tabs/tab1']);
  }

  // enabling and disabling the scanner to avoid multiple scanning

  onProductLoaded() {
    this.productDisplayed = true;
  }

  onProductCleared() {
    this.productDisplayed = false;
  }

  /**
   * MOCK FUNCTIONALITIES FOR TESTING PURPOSES
   */

  generateRandomBarcode() {
    for (let index = 0; index < this.barcodes.length; index++) {
      this.barCodeResults = {
        _isValid: true,
        upc: this.barcodes[index],
        format: 'text',
        contentType: 'text',
      };
    }
  }

  makeAPIRequest() {
    const randomIndex = Math.floor(Math.random() * this.barcodes.length);

    const result: BarCodeScannerResult = {
      _isValid: true,
      upc: this.barcodes[randomIndex],
      format: 'text',
      contentType: 'text',
    };

    this.sendBarCode(result);
  }
}
