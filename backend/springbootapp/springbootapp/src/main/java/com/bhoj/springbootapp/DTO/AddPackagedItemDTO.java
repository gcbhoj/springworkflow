package com.bhoj.springbootapp.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class AddPackagedItemDTO {
    @NotBlank(message = "CART ID CANNOT BE BLANK OR NULL")
    public String cartId;
    @NotBlank(message = "ITEM ID CANNOT BE BLANK OR NULL")
    public String itemNumber;


}
