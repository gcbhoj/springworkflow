/**
 * @param Bhoj Gc
 * The following interface will be used to send a post request to backend to retreive product details
 */

export interface BarCodeScannerResult {
  _isValid: boolean;
  upc: string;
  format: string;
  contentType: string;
}
