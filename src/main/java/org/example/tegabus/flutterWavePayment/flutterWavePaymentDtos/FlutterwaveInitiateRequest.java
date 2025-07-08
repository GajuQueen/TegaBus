package org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.tegabus.user.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlutterwaveInitiateRequest {
    private String tx_ref;
    private double amount;
    private String currency = "RWF";
    private String redirect_url;
    private String payment_options = "card,mobilemoneyrwanda";
    private User user;
}
