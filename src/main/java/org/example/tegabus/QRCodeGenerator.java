package org.example.tegabus;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {
    // This method creates a PNG image of the QR code and returns it as bytes
    public static byte[] generateQRCodeImage(String text, int width, int height) throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);  // Controls white border around QR code

        // Convert the text to a QR code matrix of pixels
        BitMatrix bitMatrix = new MultiFormatWriter().encode(
                text,
                BarcodeFormat.QR_CODE,
                width,
                height,
                hints
        );

        // Convert the BitMatrix (QR code) into an image stream (PNG bytes)
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);

        // Return the bytes of the PNG image
        return pngOutputStream.toByteArray();
    }

    // This method creates a Base64 encoded string of the QR code image
    public static String generateQRCodeBase64(String text, int width, int height) throws Exception {
        byte[] qrImageBytes = generateQRCodeImage(text, width, height);
        return Base64.getEncoder().encodeToString(qrImageBytes);
    }
}
