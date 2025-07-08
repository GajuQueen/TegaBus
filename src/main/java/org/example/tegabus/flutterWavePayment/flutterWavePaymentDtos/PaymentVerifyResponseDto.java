package org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos;

import org.example.tegabus.user.User;

public class PaymentVerifyResponseDto {
    private String status;
    private String message;

    private String dataStatus;
    private double amount;
    private String currency;

    private User user;
}
