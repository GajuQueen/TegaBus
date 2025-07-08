package org.example.tegabus.flutterWavePayment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.FlutterwaveInitiateRequest;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.PaymentInitResponseDto;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.paymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;

@Service
public class paymentService {

    @Value("{flutterwave.secret-Key}")
    private String secretKey;

    @Value("${flutterwave.base-url}")
    private String baseUrl;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

   public PaymentInitResponseDto initiatePayment(paymentDto dto){
       String txRef = UUID.randomUUID().toString();

       JsonObject body = new JsonObject();
       body.addProperty("tx_ref", txRef);
       body.addProperty("amount", dto.getAmount());
       body.addProperty("currency", "RWF");
       body.addProperty("redirect_url", dto.getRedirectUrl());

       JsonObject customer = new JsonObject();
       customer.addProperty("name", dto.getName());
       customer.addProperty("email", dto.getEmail());
       customer.addProperty("phoneNumbe     r", dto.getPhone());
       body.add("customer", customer);

       Request request = new Request.Builder()
               .url(baseUrl + "/payments")
               .post(RequestBody.create(body.toString(), MediaType.parse("application/json")))
               .addHeader("Authorization", "Bearer " + secretKey)
               .addHeader("Content-Type", "application/json")
               .build();

           try {
               Response response = client.newCall(request).execute();

               if (!response.isSuccessful()) {
                   throw new RuntimeException("Payment initiation failed: " + response.message());
               }

               String responseBody = response.body().string();

       JsonObject json = gson.fromJson(responseBody, JsonObject.class);
       JsonObject data = json.getAsJsonObject("data");

       PaymentInitResponseDto responseDto = new PaymentInitResponseDto();
       responseDto.setStatus(json.get("status").getAsString());
       responseDto.setMessage(json.get("message").getAsString());
       responseDto.setPaymentLink(data.get("link").getAsString());

       return responseDto;
           } catch (IOException e) {
               throw new RuntimeException("Failed to connect to Flutterwave: " + e.getMessage());
           }

   }

//   ************************************************************************************


}
