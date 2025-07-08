package org.example.tegabus.flutterWavePayment;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.PaymentInitResponseDto;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.PaymentVerifyResponseDto;
import org.example.tegabus.flutterWavePayment.flutterWavePaymentDtos.paymentDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

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
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
