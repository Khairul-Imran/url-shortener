package com.myprojects.urlshortener.controller;

import com.myprojects.urlshortener.service.QRCodeGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/qr")
public class QRCodeController {

    private QRCodeGeneratorService qrCodeGeneratorService;

    @Autowired
    public QRCodeController(QRCodeGeneratorService qrCodeGeneratorService) {
        this.qrCodeGeneratorService = qrCodeGeneratorService;
    }

    @GetMapping(value = "/generate/{url}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] generateQRCode(@PathVariable String url) {
        String fullUrl = "http://localhost:8080/" + url;
        return qrCodeGeneratorService.generateQRCodeImage(fullUrl);
    }
}
