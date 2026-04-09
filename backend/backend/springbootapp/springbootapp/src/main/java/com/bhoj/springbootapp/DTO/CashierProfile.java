package com.bhoj.springbootapp.DTO;

import com.bhoj.springbootapp.enums.CashierStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class CashierProfile {

    private String cashierId;
    private String cashierName;
    private CashierStatus status;
}
