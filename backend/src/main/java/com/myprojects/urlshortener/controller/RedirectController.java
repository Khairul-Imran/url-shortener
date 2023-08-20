package com.myprojects.urlshortener.controller;

import com.myprojects.urlshortener.entity.RedirectWithAlias;
import com.myprojects.urlshortener.entity.RedirectWithHash;
import com.myprojects.urlshortener.request.RedirectCreationRequestUsingAlias;
import com.myprojects.urlshortener.request.RedirectCreationRequestUsingHash;
import com.myprojects.urlshortener.service.RedirectService;
import com.myprojects.urlshortener.service.TimeStampService; // Might remove
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import static com.myprojects.urlshortener.utility.HashGenerator.generateRandomHash;
import static com.myprojects.urlshortener.utility.HashUtils.isHash;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@RestController
public class RedirectController {

    private RedirectService redirectService;
    // Might remove
    // private TimeStampService timeStampService;

    @Autowired
    public RedirectController(RedirectService redirectService, TimeStampService timeStampService) {
        this.redirectService = redirectService;
        // this.timeStampService = timeStampService; // this too
    }

    @GetMapping("/{hashOrAlias}")
    public ResponseEntity<?> handleRedirect(@PathVariable String hashOrAlias) throws URISyntaxException{
        if (isHash(hashOrAlias)) { // If a hash is provided.
            RedirectWithHash redirectWithHash = redirectService.getHashRedirect(hashOrAlias);
            System.out.println("We're redirecting here!" + redirectWithHash);

            URI uri = new URI(redirectWithHash.getUrl());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
        } else {
            RedirectWithAlias redirectWithAlias = redirectService.getAliasRedirect(hashOrAlias);
            System.out.println("We're redirecting here!" + redirectWithAlias);

            URI uri = new URI(redirectWithAlias.getUrl());
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setLocation(uri);
            return new ResponseEntity<>(httpHeaders, MOVED_PERMANENTLY);
        }
    }

    @PostMapping("/create-alias")
    public ResponseEntity<?> createRedirectForAlias(@Valid @RequestBody RedirectCreationRequestUsingAlias redirectCreationRequestUsingAlias) {
        // Testing to see if the correct data is received from the backend.
        System.out.println("Received request with body: " + redirectCreationRequestUsingAlias);
        // For timestamp
        LocalDateTime currentTimestamp = LocalDateTime.now();
        LocalDateTime expiryTimestamp = currentTimestamp.plusMinutes(10);
        redirectCreationRequestUsingAlias.setExpiryTimestamp(expiryTimestamp);

        return ResponseEntity.ok(redirectService.createRedirectForAlias(redirectCreationRequestUsingAlias));
    }

    @PostMapping("/create-hash")
    public ResponseEntity<?> createRedirectForHash(@RequestBody RedirectCreationRequestUsingHash redirectCreationRequestUsingHash) {
        System.out.println("Received request with body: " + redirectCreationRequestUsingHash);
        redirectCreationRequestUsingHash.setHash(generateRandomHash());
        // For timestamp
        LocalDateTime currentTimestamp = LocalDateTime.now();
        LocalDateTime expiryTimestamp = currentTimestamp.plusMinutes(10);
        redirectCreationRequestUsingHash.setExpiryTimestamp(expiryTimestamp);

        return ResponseEntity.ok(redirectService.createRedirectForHash(redirectCreationRequestUsingHash));
    }
}
