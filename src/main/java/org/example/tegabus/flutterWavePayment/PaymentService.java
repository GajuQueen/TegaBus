package org.example.tegabus.flutterWavePayment;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import okhttp3.*;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.PaymentInitResponseDto;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.PaymentVerifyResponseDto;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.paymentDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.UUID;


@Service
public class PaymentService {

    @Value("${flutterwave.secret-key}")
    private String secretKey;

    @Value("${flutterwave.base-url}")
    private String baseUrl;

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    public PaymentInitResponseDto initiatePayment(paymentDto dto) {
        String txRef = UUID.randomUUID().toString();

        JsonObject body = new JsonObject();
        body.addProperty("tx_ref", txRef);
        body.addProperty("amount", dto.getAmount());
        body.addProperty("currency", "RWF");
        body.addProperty("redirect_url", dto.getRedirectUrl());

        JsonObject customer = new JsonObject();
        customer.addProperty("name", dto.getName());
        customer.addProperty("email", dto.getEmail());
        customer.addProperty("phoneNumber", dto.getPhone());
        body.add("customer", customer);

        Request request = new Request.Builder()
                .url(baseUrl + "/payments")
                .post(RequestBody.create(body.toString(), MediaType.parse("application/json")))
                .addHeader("Authorization", "Bearer " + secretKey)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()){
            String responseBody = response.body().string();

            if (!response.isSuccessful()) {
                throw new RuntimeException("Payment initiation failed: " + responseBody);
            }


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
    public PaymentVerifyResponseDto verifyPayment(String transactionId) {
        try {
            Request request = new Request.Builder()
                    .url(baseUrl + "/transactions/" + transactionId + "/verify")
                    .get()
                    .addHeader("Authorization", "Bearer " + secretKey)
                    .build();

            Response response = client.newCall(request).execute();

            if (!response.isSuccessful()) {
                throw new RuntimeException("Payment verification failed: " + response.message());
            }

            String responseBody = response.body().string();
            JsonObject root = gson.fromJson(responseBody, JsonObject.class);
            JsonObject data = root.getAsJsonObject("data");
            JsonObject customer = data.getAsJsonObject("customer");

            PaymentVerifyResponseDto dto = new PaymentVerifyResponseDto();
            dto.setStatus(root.get("status").getAsString());
            dto.setMessage(root.get("message").getAsString());
            dto.setDataStatus(data.get("status").getAsString());
            dto.setAmount(data.get("amount").getAsDouble());
            dto.setCurrency(data.get("currency").getAsString());
            PaymentVerifyResponseDto.UserDto userDto = new PaymentVerifyResponseDto.UserDto();
            userDto.setFullName(customer.get("name").getAsString());
            userDto.setEmail(customer.get("email").getAsString());
            userDto.setPhoneNumber(customer.get("phoneNumber").getAsString());
            dto.setUser(userDto);

            return dto;
        } catch (IOException e) {
            throw new RuntimeException("Network error during payment verification", e);
        }
    }
}
