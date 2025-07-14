package org.example.tegabus.flutterWavePayment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.EmailService;
import org.example.tegabus.QRCodeGenerator;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.PaymentInitResponseDto;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.PaymentVerifyResponseDto;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.paymentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;
    private final EmailService emailService;

    @SecurityRequirement(name = "auth")
    @PostMapping("/initiate")
    @Operation(summary = "Initiate a new payment")
    public ResponseEntity<PaymentInitResponseDto> initiatePayment(@RequestBody @Valid paymentDto dto) {
        PaymentInitResponseDto responseDto = paymentService.initiatePayment(dto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @SecurityRequirement(name = "auth")
    @GetMapping("/verify/{transactionId}")
    @Operation(summary = "Verify a payment using Flutterwave transaction ID")
    public ResponseEntity<PaymentVerifyResponseDto> verifyPayment(@PathVariable String transactionId) {
        PaymentVerifyResponseDto responseDto = paymentService.verifyPayment(transactionId);

        try {

            if ("success".equalsIgnoreCase(responseDto.getStatus())) {

                PaymentVerifyResponseDto.UserDto user = responseDto.getUser();

                String ticketDetails = "Name: " + user.getFullName() +
                        "\nAmount: " + responseDto.getAmount() +
                        "\nPhone: " + user.getPhoneNumber() +
                        "\nBus: Ritco" +
                        "\nTrip Date: 2025-07-20";


                byte[] qrCodeImageBytes = QRCodeGenerator.generateQRCodeImage(ticketDetails, 250, 250);

                String emailBody = "<h3>Your Tegabus Ticket QR Code</h3>" +
                        "<p>Thank you for your payment. Here is your ticket QR code:</p>" +
                        "<img src='cid:qrCodeImage' alt='QR Code' />";

                emailService.sendEmailWithQRCode(
                        user.getEmail(),
                        "Your Tegabus Ticket",
                        emailBody,
                        qrCodeImageBytes
                );

                String qrCodeBase64 = Base64.getEncoder().encodeToString(qrCodeImageBytes);
                responseDto.setQrCodeBase64(qrCodeBase64);
                responseDto.setDataStatus("QR_SENT");

            } else {

                responseDto.setDataStatus("PAYMENT_FAILED");
            }

        } catch (Exception e) {
            e.printStackTrace();
            responseDto.setDataStatus("EMAIL_ERROR");
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
