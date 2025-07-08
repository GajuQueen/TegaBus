package org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentInitResponseDto {
    private String status;
    private String message;
    private String paymentLink;

}
