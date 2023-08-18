package com.myprojects.urlshortener.request;

import jakarta.validation.constraints.NotNull;

public class RedirectCreationRequestUsingAlias {
    @NotNull
    private final String alias;

    @NotNull
    private final String url;

    public RedirectCreationRequestUsingAlias(final @NotNull String alias, final @NotNull String url) {
        this.alias = alias;
        this.url = url;
    }

    public @NotNull String getAlias() {
        return alias;
    }

    public @NotNull String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "RedirectCreationRequest{" +
                "alias='" + alias + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
