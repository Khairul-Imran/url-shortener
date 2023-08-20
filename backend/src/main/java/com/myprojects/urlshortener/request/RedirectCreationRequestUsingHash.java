package com.myprojects.urlshortener.request;

import jakarta.validation.constraints.NotNull;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

public class RedirectCreationRequestUsingHash {

    private String hash; // Changed from final variable.

    @NotNull
    private final String url;

    private LocalDateTime expiryTimestamp;

    public RedirectCreationRequestUsingHash(final String hash, final @NotNull String url, LocalDateTime expiryTimestamp) {
        this.hash = hash;
        this.url = url;
        this.expiryTimestamp = expiryTimestamp;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public @NotNull String getHash() {
        return hash;
    }

    public @NotNull String getUrl() {
        return url;
    }

    public void setExpiryTimestamp(LocalDateTime expiryTimestamp) {
        this.expiryTimestamp = expiryTimestamp;
    }

    public LocalDateTime getExpiryTimestamp() {
        return expiryTimestamp;
    }

    @Override
    public String toString() {
        return "RedirectCreationRequest{" +
                "hash='" + hash + '\'' +
                ", url='" + url + '\'' +
                ", timestamp='" + expiryTimestamp + '\'' +
                '}';
    }

}
