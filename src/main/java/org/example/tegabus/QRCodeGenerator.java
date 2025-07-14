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

    public static byte[] generateQRCodeImage(String text, int width, int height) throws Exception {
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.MARGIN, 1);


        BitMatrix bitMatrix = new MultiFormatWriter().encode(
                text,
                BarcodeFormat.QR_CODE,
                width,
                height,
                hints
        );


        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);


        return pngOutputStream.toByteArray();
    }


    public static String generateQRCodeBase64(String text, int width, int height) throws Exception {
        byte[] qrImageBytes = generateQRCodeImage(text, width, height);
        return Base64.getEncoder().encodeToString(qrImageBytes);
    }
}
