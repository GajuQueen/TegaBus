package org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class paymentDto {
    @NotNull
    private double amount;
    @NotBlank
    private String email;
    @NotBlank
    private String phone;
    @NotBlank
    private String name;
    private String redirectUrl = "https://example.com/payment-success";

}
