package com.bhoj.springbootapp.calculatorService;

import com.bhoj.springbootapp.DTO.CartProfile;
import com.bhoj.springbootapp.DTO.PackagedProductDTO;
import com.bhoj.springbootapp.DTO.UnpackagedProductDTO;
import com.bhoj.springbootapp.beans.PackagedProduct;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
public class CalculatorServices {

    private final BigDecimal TAX_PERCENT = new BigDecimal("0.13");



    public BigDecimal calculateTotalCostPackagedProduct(List<PackagedProductDTO> products) {

        BigDecimal total = BigDecimal.ZERO;

        for (PackagedProductDTO p : products) {
            BigDecimal itemTotal = calculateTotalPackagedProductCost(
                    p.getQuantity(),
                    p.getUnitPrice()
            );

            total = total.add(itemTotal);
        }

        return total;
    }
    public BigDecimal calculateTotalCostUnPackagedProduct(List<UnpackagedProductDTO> products) {

        BigDecimal total = BigDecimal.ZERO;

        for (UnpackagedProductDTO p : products) {
            BigDecimal itemTotal = calculateTotalUnPackagedProductCost(
                    p.getWeight(),
                    p.getUnitPrice()
            );

            total = total.add(itemTotal);
        }

        return total;
    }
    public BigDecimal calculateHSTAmount(BigDecimal totalCost) {


        return totalCost.multiply(this.TAX_PERCENT)
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTotalUnPackagedProductCost(double weight, BigDecimal unitPrice) {
        return BigDecimal.valueOf(weight).multiply(unitPrice);
    }

    private BigDecimal calculateTotalPackagedProductCost(Long qty, BigDecimal unitPrice) {
        return BigDecimal.valueOf(qty).multiply(unitPrice);
    }
}
