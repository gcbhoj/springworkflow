import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BarCodeScannerResult } from 'src/app/classes/DTOs/BarCodeScannerResultDTO';
import { ProductInformation } from 'src/app/classes/Models/PackagedProductInformation';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  private backendURL: string = 'http://localhost:5000/api/v1/product';
  private backendURLEmulator: string = 'http://10.0.2.2:5000/api/v1/product';
  private backendURLDevice: string = 'httP://10.0.0.87:5000/api/v1/product';

  constructor(private http: HttpClient) {}

  getProductByBarCode(
    request: BarCodeScannerResult,
  ): Observable<ProductInformation> {
    return this.http.post<ProductInformation>(
      `${this.backendURL}/pkgd-item-bar-code`,
      request,
    );
  }
}
