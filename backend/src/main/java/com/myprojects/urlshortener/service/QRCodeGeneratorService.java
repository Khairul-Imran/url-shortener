package com.myprojects.urlshortener.service;

import net.glxn.qrgen.javase.QRCode;
import org.springframework.stereotype.Service;

@Service
public class QRCodeGeneratorService {
    public byte[] generateQRCodeImage(String text) {
        return QRCode.from(text).withSize(250, 250).stream().toByteArray();
    }
}
