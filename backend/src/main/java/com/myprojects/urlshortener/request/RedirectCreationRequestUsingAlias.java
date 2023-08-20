package com.myprojects.urlshortener.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class RedirectCreationRequestUsingAlias {
    @NotNull
    private final String alias;

    @NotNull
    private final String url;

    private LocalDateTime expiryTimestamp;

    public RedirectCreationRequestUsingAlias(final @NotNull String alias, final @NotNull String url, LocalDateTime expiryTimestamp) {
        this.alias = alias;
        this.url = url;
        this.expiryTimestamp = expiryTimestamp;
    }

    public @NotNull String getAlias() {
        return alias;
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
                "alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                ", timestamp='" + expiryTimestamp + '\'' +
                '}';
    }
}
