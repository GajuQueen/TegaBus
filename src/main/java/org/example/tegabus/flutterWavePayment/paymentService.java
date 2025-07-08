package org.example.tegabus.flutterWavePayment;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
//import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.FlutterwaveInitiateRequest;
//import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.PaymentInitResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class paymentService {

    @Value("{flutterwave.secret-Key}")
    private String secretKey;

    @Value("${flutterwave.base-url}")
    private String baseUrl;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

//   public PaymentInitResponseDto

}
