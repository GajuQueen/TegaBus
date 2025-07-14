package org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tegabus.user.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentVerifyResponseDto {
    private String status;
    private String message;
    private String dataStatus;
    private double amount;
    private String currency;
    private UserDto user;
    private String qrCodeBase64;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserDto {
        private String fullName;
        private String email;
        private String phoneNumber;
    }

}
