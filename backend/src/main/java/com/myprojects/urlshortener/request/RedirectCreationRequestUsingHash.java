package com.myprojects.urlshortener.request;

import jakarta.validation.constraints.NotNull;

public class RedirectCreationRequestUsingHash {

    private String hash; // Changed from final variable.

    @NotNull
    private final String url;

    public RedirectCreationRequestUsingHash(final String hash, final @NotNull String url) {
        this.hash = hash;
        this.url = url;
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

    @Override
    public String toString() {
        return "RedirectCreationRequest{" +
                "hash='" + hash + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
