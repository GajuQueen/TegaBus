package org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class paymentDto {
    private double amount;
    private String email;
    private String phone;
    private String name;
//    private String redirectUrl;

}
